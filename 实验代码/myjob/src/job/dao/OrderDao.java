package job.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbutil.Dbutil;
import job.bean.Order;
import job.bean.User;

public class OrderDao {
	public void createOrder(Order order)
	{
		String sql = "insert into orders values(?,?,?,0,null,?)";
		Connection conn = Dbutil.getConnection();
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, order.getId());
			stat.setDouble(2, order.getMoney());
			stat.setString(3, order.getReceiverinfo());
			stat.setInt(4, order.getUser_id());
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			Dbutil.free(null, stat, conn);		
		}		
	}
	// 根据用户查询订单
	public List<Order> findOrder(User user)
	{
		String sql = null;
		List<Order> orders = new ArrayList<Order>();
		Connection conn =  Dbutil.getConnection();
		
		
		if("admin".equals(user.getRole())){			
			sql = "select orders.*,username,nickname from orders,hzg_users where orders.user_id=hzg_users.id";
			PreparedStatement statment = null;
			ResultSet resultSet = null;
			try {
				statment = conn.prepareStatement(sql);
				resultSet = statment.executeQuery();
				while(resultSet.next())
				{
					Order or = new Order();
					or.setId(resultSet.getString("id"));
					or.setMoney(resultSet.getDouble("money"));
					or.setNickname(resultSet.getString("nickname"));
					or.setOrdertime(resultSet.getDate("ordertime"));
					or.setPaystate(resultSet.getInt("paystate"));
					or.setReceiverinfo(resultSet.getString("receiverinfo"));
					or.setUser_id(resultSet.getInt("user_id"));
					or.setUsername(resultSet.getString("username"));
					orders.add(or);					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				Dbutil.free(resultSet, statment, conn);		
			}	
			
		}
		else if("user".equals(user.getRole())) {
			sql = "select orders.*,username,nickname from orders,hzg_users where orders.user_id=hzg_users.id and user_id=?";
			PreparedStatement statment = null;
			ResultSet resultSet = null;
			try {
				statment = conn.prepareStatement(sql);
				statment.setInt(1, user.getId());
				resultSet = statment.executeQuery();
				while(resultSet.next())
				{
					Order or = new Order();
					or.setId(resultSet.getString("id"));
					or.setMoney(resultSet.getDouble("money"));
					or.setNickname(resultSet.getString("nickname"));
					or.setOrdertime(resultSet.getDate("ordertime"));
					or.setPaystate(resultSet.getInt("paystate"));
					or.setReceiverinfo(resultSet.getString("receiverinfo"));
					or.setUser_id(resultSet.getInt("user_id"));
					or.setUsername(resultSet.getString("username"));
					orders.add(or);				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Dbutil.free(resultSet, statment, conn);		
			}	
		
		}
	return orders;	
	}	
	//删除订单
	public void delOrder(String id){
		String sql = "delete from orders where id=?";
		Connection conn = Dbutil.getConnection();
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			Dbutil.free(null, stat, conn);		
		}		
	}
	//修改订单状态
		public void updateState(String id){
			String sql="update orders set paystate=1 where id=?";
			Connection conn = Dbutil.getConnection();
			PreparedStatement stat = null;
			try {
				stat = conn.prepareStatement(sql);
				stat.setString(1, id);
				stat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally{
				Dbutil.free(null, stat, conn);		
			}		
			
		}

}
