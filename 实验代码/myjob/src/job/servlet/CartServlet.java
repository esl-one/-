package job.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import job.bean.Products;
import job.bean.User;
import job.dao.ProductsDao;


/**
 * Servlet implementation class ＣartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated action stub
		
		String action = request.getParameter("action");
		if ("add".equals(action)) {// 添加商品到购物车
			add(request, response);
		} else if ("remove".equals(action)) { // 从购物车删除商品
			remove(request, response);
		} else if ("update".equals(action)) {// 修改购物车商品
			update(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated action stub
		doGet(request, response);
	}
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");		
		if (user == null) {
			response.getWriter().write(
					"请先<a href='" + request.getContextPath()
							+ "/login.jsp'>登录</a>");
			return;
		}
		String id = request.getParameter("id");
		Products p;
		try {
			p = new ProductsDao().findById(id);
			// 3.得到购物车
			
			Map<Products, Integer> cart = (Map<Products, Integer>) request.getSession().getAttribute("cart");

			if (cart == null) {// 如果cart不存在，说明是第一次购物.
				cart = new HashMap<Products, Integer>();
			}
			// 判断购物车中是否有要添加商品。

			Integer count = cart.put(p, 1);
			if (count != null) {
				// 说明有吗
				cart.put(p, count + 1);			
			}	
			request.getSession().setAttribute("cart", cart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/m3.jsp");
		
	}
	public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		Products p = new Products();
		p.setId(id);

		// 得到购物车
		Map<Products, Integer> cart = (Map<Products, Integer>) request
				.getSession().getAttribute("cart");
		cart.remove(p);

		if (cart.size() == 0) {
			request.getSession().removeAttribute("cart");
		}
		System.out.println("del");
		response.sendRedirect(request.getContextPath() + "/showCart.jsp");
		
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = Integer.parseInt(request.getParameter("count"));

		// 这是要修改的商品
		Products p = new Products();
		p.setId(id);

		// 得到购物车
		Map<Products, Integer> cart = (Map<Products, Integer>) request
				.getSession().getAttribute("cart");

		// 修改商品的数量
		if (count == 0) {
			cart.remove(p); // 将商品从购物车中移除
		} else {
			cart.put(p, count);
		}
		request.getSession().setAttribute("cart", cart);
		System.out.println("update");
		response.sendRedirect(request.getContextPath() + "/showCart.jsp");
		
	}
}
