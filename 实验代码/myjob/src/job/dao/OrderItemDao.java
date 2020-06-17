package job.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dbutil.Dbutil;
import job.bean.Order;
import job.bean.OrderItem;



public class OrderItemDao {
	// 添加订单项
	public void addOrderItem(Order order) throws SQLException{
		
		List<OrderItem> items = order.getOrderItems();

		Object[][] params = new Object[items.size()][3];

		for (int i = 0; i < items.size(); i++) {

			OrderItem item = items.get(i);
			params[i][0] = item.getOrder_id();
			params[i][1] = item.getProduct_id();
			params[i][2] = item.getBuynum();
		}

		String sql = "insert into orderitem values(?,?,?)";
		QueryRunner runner = new QueryRunner();
		runner.batch(Dbutil.getConnection(), sql, params);

	}
	public List<OrderItem> findOrderItemByOrderId(Order order)
			throws SQLException {

		String sql = "select * from orderitem,hzg_products where orderitem.product_id=hzg_products.id and  order_id=?";
		
		QueryRunner runner = new QueryRunner(Dbutil.getDataSource());

		return runner.query(sql,
				new BeanListHandler<OrderItem>(OrderItem.class), order.getId());
	
	}

	// 根据订单id查询所有订单项
	public List<OrderItem> findOrderItemByOrderId(String id)
			throws SQLException {
		String sql = "select * from orderitem where order_id=?";
		QueryRunner runner = new QueryRunner(Dbutil.getDataSource());
		return runner.query(Dbutil.getConnection(),sql,
				new BeanListHandler<OrderItem>(OrderItem.class), id);
	}

	//删除订单项
	public void delOrderItem(String id) throws SQLException {
		
		String sql="delete from orderItem where order_id=?";
		
		QueryRunner runner = new QueryRunner();
		
		runner.update(Dbutil.getConnection(),sql,id);
	}
}
