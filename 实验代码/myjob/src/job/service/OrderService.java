package job.service;


import java.sql.SQLException;
import java.util.List;
import job.bean.Order;
import job.bean.OrderItem;
import job.bean.User;
import job.dao.OrderDao;
import job.dao.OrderItemDao;
import job.dao.ProductsDao;

public class OrderService {

		public void addOrder(User user,Order order){
			OrderDao dao = new OrderDao();
			OrderItemDao idao = new OrderItemDao();
			//ProductsDao pdao = new ProductsDao();
			try {
				dao.createOrder(order);
				idao.addOrderItem(order);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		public List<Order> fingByUser(User user){
			OrderDao od = new OrderDao();
			List<Order> list = od.findOrder(user);
			return list;			
		}

		// 根据id删除订单
		public void delete(String id) {

			OrderDao dao = new OrderDao();
			OrderItemDao idao = new OrderItemDao();
			ProductsDao pdao = new ProductsDao();
			// 1.修改商品表中商品数量

			try {
			
				// 1.1 得到商品的数量
				List<OrderItem> items = idao.findOrderItemByOrderId(id);
				// 1.2修改商品的数量
				pdao.updateProductCount(items);
				// 2.删除订单项
				idao.delOrderItem(id);
				// 3.删除订单
				dao.delOrder(id);
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			} 
		
		// 根据用户查找订单
		public List<Order> find(User user) throws SQLException {

			List<Order> orders = new OrderDao().findOrder(user); // 查询订单信息,不包含订单中的商品信息

			OrderItemDao idao = new OrderItemDao();

			// 根据得到的订单，查询订单中商品信息.
			for (Order order : orders) {

				List<OrderItem> items = idao.findOrderItemByOrderId(order);

				order.setOrderItems(items);

			}
			System.out.println(orders.size());
			return orders;
		}
		// 根据订单号修改订单状态
		public void updateState(String id) throws SQLException {
			OrderDao dao = new OrderDao();

			dao.updateState(id);
		}


}
