package job.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;

import job.bean.Order;
import job.bean.OrderItem;
import job.bean.Products;
import dbutil.Dbutil;

public class ProductsDao {
	public void addProduct(Products product)
	{
			String sql = "insert into hzg_products values(?,?,?,?,?,?,?) ";
			Connection conn = null;
			PreparedStatement stat = null;
			conn = Dbutil.getConnection();
			try {
				stat = conn.prepareStatement(sql); 
				stat.setString(1,product.getId());
				stat.setString(2, product.getName());
				stat.setDouble(3, product.getPrice());
				stat.setString(4,product.getCategory());
				stat.setInt(5, product.getPnum());
				stat.setString(6, product.getImgurl());
				stat.setString(7, product.getDescription());
				stat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				Dbutil.free(null, stat, conn);		
			}	
	}
	public List<Products> findAll()
	{		
		String sql = "select * from hzg_products";
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		List<Products> list = new ArrayList<Products>();
		conn = Dbutil.getConnection();
		try {
			stat = conn.prepareStatement(sql);
			res = stat.executeQuery();
			while(res.next())
			{
				Products pds =  new Products();
				pds.setId(res.getString("id"));
				pds.setName(res.getString("name"));
				pds.setPrice(res.getDouble("price"));
				pds.setPnum(res.getInt("pnum"));
				pds.setCategory(res.getString("category"));
				pds.setImgurl(res.getString("imgurl"));
				pds.setDescription(res.getString("description"));
				list.add(pds);				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			Dbutil.free(null, stat, conn);		
		}	
	}
	public Products findById(String id)
	{
		Products pds =  new Products();
		String sql = "select * from hzg_products where id = ?";
		Connection conn = Dbutil.getConnection();
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			res = stat.executeQuery();
			if(res.next())
			{				
				pds.setId(res.getString("id"));
				pds.setName(res.getString("name"));
				pds.setPrice(res.getDouble("price"));
				pds.setPnum(res.getInt("pnum"));
				pds.setCategory(res.getString("category"));
				pds.setImgurl(res.getString("imgurl"));
				pds.setDescription(res.getString("description"));					
			}
			return pds;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			Dbutil.free(null, stat, conn);		
		}	
		
	}
	public void delProducts(String id){
		String sql = "delete from hzg_products where id=? ";
		Connection conn = Dbutil.getConnection();
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Dbutil.free(null, stat, conn);
		}	
	}
	// 修改商品的数量
	public void updateProductCount(Order order) throws SQLException {

		// 要修改的数量在哪?
		List<OrderItem> items = order.getOrderItems();

		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < items.size(); i++) {

			OrderItem item = items.get(i);
			params[i][0] = item.getBuynum();
			params[i][1] = item.getProduct_id();

		}

		String sql = "update hzg_products set pnum=pnum-? where id=?";

		QueryRunner runner = new QueryRunner();

		runner.batch(Dbutil.getConnection(), sql, params);

		// for(OrderItem item:items){
		//
		// runner.update(sql,item.getBuynum(),item.getProduct_id());
		//
		// }
	}

	// 当删除订单时，修改商品数量
	public void updateProductCount(List<OrderItem> items) throws SQLException {

		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < items.size(); i++) {

			OrderItem item = items.get(i);
			params[i][0] = item.getBuynum();
			params[i][1] = item.getProduct_id();

		}

		String sql = "update hzg_products set pnum=pnum+? where id=?";

		QueryRunner runner = new QueryRunner();

		runner.batch(Dbutil.getConnection(), sql, params);
	}
}
