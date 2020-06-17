package job.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import job.bean.Order;
import job.bean.OrderItem;
import job.bean.Products;
import job.bean.User;
import job.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("add".equals(action)) {
			add(request, response);
		} else if ("del".equals(action)) {
			del(request, response);
		} else if ("search".equals(action)) {
			search(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Order order = new Order();
		// 1.1 表单数据
		try {
			BeanUtils.populate(order, request.getParameterMap()); // 只封装了表单数据到javaBean，简单说，只有receiverinfo
																	// money两项
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 1.2 订单编号 当前用户id
		order.setId(UUID.randomUUID().toString());

		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		order.setUser_id(user.getId());

		// 1.3 将订单项封装到订单中.
		Map<Products, Integer> cart = (Map<Products, Integer>) request
				.getSession().getAttribute("cart"); // 得到购物车
		List<OrderItem> items = new ArrayList<OrderItem>();
		for (Products p : cart.keySet()) {

			OrderItem item = new OrderItem();

			item.setOrder_id(order.getId());
			item.setProduct_id(p.getId());
			item.setBuynum(cart.get(p));

			items.add(item);
		}

		order.setOrderItems(items);

		// 2.调用OrderService中方法，创建订单
		OrderService service = new OrderService();

		try {
			service.addOrder(user, order);
			response.getWriter().write(
					"下单成功,<a href='" + request.getContextPath()
							+ "/m3.jsp'>继续购物</a>，<a href='"
							+ request.getContextPath()
							+ "/OrderServlet?action=search'>查看订单</a>");

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}
	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			String id = request.getParameter("id"); // 得到要删除的订单的id。

			// 调用OrderService中删除订单操作
			OrderService service = new OrderService();

			try {
				service.delete(id);

				// 在次查询订单
				response.sendRedirect(request.getContextPath()
						+ "/OrderServlet?action=search");
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			response.getWriter().write(
					"请先<a href='" + request.getContextPath()
							+ "/login.jsp'>登录</a>");
			return;
		}
	//	System.out.println(user.getId());
		// 2.调用OrderService中查询订单操作
		OrderService service = new OrderService();

		try {
			List<Order> orders = service.find(user);
			//System.out.println(orders.size());
			request.setAttribute("orders", orders);

			request.getRequestDispatcher("/showOrder.jsp").forward(request,
					response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
