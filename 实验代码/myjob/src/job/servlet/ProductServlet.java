package job.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import job.bean.Products;
import job.service.ProductsService;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if("findById".equals(action)){
			findById(request, response);			
		}
		else if("findAll".equals(action)){			
			findAll(request,response);
		}
		else if("del".equals(action)){
			delProduct(request, response);
		}
		else if("godel".equals(action)){
			godel(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	public void findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ProductsService pds;
		try {
			pds = new ProductsService();
			List<Products> list = pds.findAll();
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.getRequestDispatcher("/fistpage.jsp").forward(request, response);
	}
	public void findById(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ProductsService pds;
		try {
			String id = request.getParameter("id");
			pds = new ProductsService();
			Products pd =  pds.findById(id);
			request.setAttribute("pd", pd);
			request.getRequestDispatcher("/productInfo.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void delProduct(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ProductsService pds;
		try {
		
			String id = request.getParameter("id");
			pds = new ProductsService();
			System.out.println(id);
			 pds.delProduct(id);
			 
			request.getRequestDispatcher("/m3.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	public void godel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ProductsService pds;
		try {
			pds = new ProductsService();
			List<Products> list = pds.findAll();
			request.setAttribute("list", list);
		//	System.out.println(list.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.getRequestDispatcher("/delproduct.jsp").forward(request, response);
	}
}
