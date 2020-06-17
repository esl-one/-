package job.service;

import java.util.List;

import job.bean.User;
import job.dao.UserDao;

public class UserService {
	private UserDao ud  ;
	public UserService() {
		super();
		this.ud = new UserDao();
	}
	public void addUserService(User user)
	{
		 try {
			ud.addUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}
	public List<User> queryAllDao(){
		
		return ud.queryAll();
	}
	public User findUserDao(String username,String password)
	{
		return ud.findUserByUserNameAndPassword(username, password);
		
	}
	public void updateUserDao(User user)
	{
		ud.update(user);
	}

}
