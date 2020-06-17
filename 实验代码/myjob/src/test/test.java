package test;

import static org.junit.Assert.*;

import java.util.List;

import job.bean.Order;
import job.bean.Products;
import job.bean.User;
import job.dao.OrderDao;
import job.dao.ProductsDao;
import job.dao.UserDao;
import job.service.ProductsService;
import job.service.UserService;

import org.junit.Test;

public class test {

	@Test
	public void test() {
//		UserDao us = new UserDao();
//		UserService usd = new UserService();
//		User s = new User();
//		s.setUsername("4441222");
//		s.setPassword("1sfsaf");
//		s.setNickname("alie22dfdf");
//		s.setEmail("4522223@qq.com");
//		usd.addUserService(s);
//		s.setId(1);
//		s.setRole("user");
//		Order or = new Order();
//		or.setId("1");
//		or.setMoney(65.11);
//		or.setOrdertime(null);
//		or.setPaystate(0);
//		or.setReceiverinfo("adfsadfsadf");
//		or.setUser_id(1);
		
//		System.out.println("0001");
	
//		System.out.println(list.size());	
	
//		List<User> user = us.queryAll();
//		System.out.println(us.findUserByUserNameAndPassword("alice", "12456").getUsername());
//		System.out.println(user.size());
		ProductsDao pd = new ProductsDao();
		ProductsService psd = new ProductsService();
		Products ps = new Products();
		psd.delProduct("d0dc6249-4175-4bb4-8459-f2b6ed0ff387");
//		System.out.println(ps.getName());
//		ps.setId("1000");
//		ps.setPrice(56.3);
//		ps.setDescription("this is very good");
//		ps.setCategory("VB");
//		ps.setPnum(600);
//		ps.setImgurl("asf4a54d4af4");
//		ps.setName("BH");
//		psd.addProduct(ps);
		//pd.addProduct(ps);
//		ps.setId("134sfa7af6fa");
//		System.out.println(pd.findById("134sfa7af6fa").getName());
		//System.out.println(pd.findAll().size());
//	OrderDao ord = new OrderDao();
//	ord.updateState("1");
//		ProductsDao psd = new ProductsDao();
//		psd.delProducts("134sfa7af6fa");
	}

}
