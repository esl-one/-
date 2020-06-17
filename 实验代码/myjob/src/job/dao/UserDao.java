package job.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.Dbutil;

import job.bean.User;
public class UserDao {
	public void addUser(User user){
//	 String sql = "insert into users values(null,?,?,?,?,'user')";
	 Connection conn = Dbutil.getConnection();
//	 PreparedStatement statment=null;
	try {
		  CallableStatement cs=conn.prepareCall("{call hzg_procedure(?,?,?,?)}");
		  cs.setString(1, user.getUsername());
          cs.setString(2,user.getPassword());
          cs.setString(3,user.getNickname());
          cs.setString(4,user.getEmail());
//	  statment.executeUpdate();	
          cs.executeUpdate();
          //6.关闭资源
          cs.close();
          conn.close();
		 
		 
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	
	}
	public User findUserByUserNameAndPassword(String username ,String password)
	{
		
		User user = null;
		String sql = "select * from hzg_users where username=? and password =?";		
		Connection conn = Dbutil.getConnection();
		PreparedStatement statment = null;
		ResultSet resultSet = null;
		try {
			statment = conn.prepareStatement(sql);
			statment.setString(1, username);
			statment.setString(2, password);
			resultSet = statment.executeQuery();
			if(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setNickname(resultSet.getString("nickname"));
				user.setRole(resultSet.getString("role"));
				user.setEmail(resultSet.getString("email"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{			
			Dbutil.free(resultSet, statment, conn);
		}	
		return user;
	}
	public List<User> queryAll(){
		User user = null;
		List<User> list = new ArrayList<User>();
		String sql = "select * from hzg_users";
		Connection conn = Dbutil.getConnection();
		PreparedStatement statment = null;
		ResultSet resultSet = null;
		try {
			statment = conn.prepareStatement(sql);
			resultSet = statment.executeQuery();
			while(resultSet.next()){
				user = new User();				
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setNickname(resultSet.getString("nickname"));
				user.setRole(resultSet.getString("role"));
				user.setEmail(resultSet.getString("email"));	
				list.add(user);				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}
	public void update(User user)
	{
		String sql = "update hzg_users set username = ?,password = ?,nickname = ?,email = ? where id =?";
		 Connection conn = Dbutil.getConnection();
		 PreparedStatement statment=null;
		try {
			statment = conn.prepareStatement(sql);
			 statment.setString(1, user.getUsername());
			 statment.setString(2, user.getPassword());
			 statment.setString(3, user.getNickname());
			 statment.setString(4,user.getEmail());
			 statment.setInt(5, user.getId());
			 statment.executeUpdate();
			 }catch (SQLException e) {
					e.printStackTrace();
				} finally{
					Dbutil.free(null, statment, conn);		
				}				
	}
	public void delete(int id){
		
		String sql = "delete from hzg_users where id=?";
		 Connection conn = Dbutil.getConnection();
		 PreparedStatement statment=null;
		 try {
			statment = conn.prepareStatement(sql);
			statment.setInt(1, id);
			statment.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbutil.free(null, statment, conn);		
		}	
	}
}
