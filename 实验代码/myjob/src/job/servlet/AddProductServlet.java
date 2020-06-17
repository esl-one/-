package job.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import job.bean.Products;
import job.bean.User;
import job.service.ProductsService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dbutil.PicUtils;
import dbutil.UploadUtils;


/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		
		Map<String, String[]> map = new HashMap<String, String[]>();

		// 2.创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 5); // 设置缓存区大小为5m
		factory.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));// 设置临时文件存储位置

		// 3.创建ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 判断是否是上传操作
		if (upload.isMultipartContent(request)) {
			upload.setHeaderEncoding("utf-8");// 解决上传文件中文乱问题

			// 4.得到所有的FileItem
			try {
				List<FileItem> items = upload.parseRequest(request);

				// 5.遍历items
				for (FileItem item : items) {
					// 判断是否是上传项
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String value = item.getString("utf-8");

						map.put(fieldName, new String[] { value }); // 封装非上传项组件信息到map
					} else {
						// 是上传组件
						String fileName = item.getName(); // 得到上传文件名称 注意：可以包含路径.
						// c:/a/a.txt a.txt
						// 得到真实名称
						fileName = UploadUtils.subFileName(fileName); // a.txt

						// 得到随机名称
						String uuidFileName = UploadUtils
								.generateRandonFileName(fileName);

						// 得到随机目录
						String randomDir = UploadUtils
								.generateRandomDir(uuidFileName);

						String path = this.getServletContext().getRealPath(
								"/upload" + randomDir);

						File pathFile = new File(path);

						if (!pathFile.exists()) { // 目录不存在，创建
							pathFile.mkdirs();
						}

						// 得到一个imgurl
						String imgurl = "/upload" + randomDir + "/"
								+ uuidFileName;

						map.put("imgurl", new String[] { imgurl }); // 封装上传图片的路径.

						try {
							item.write(new File(pathFile, uuidFileName)); // 文件上传操作
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			// 封装商品的id
			map.put("id", new String[] { UUID.randomUUID().toString() });

			Products product = new Products();

			try {
				BeanUtils.populate(product, map); // 封装数据到Product对象.
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			// 做一个功能：得到商品缩略图
			PicUtils putils = new PicUtils(this.getServletContext()
					.getRealPath(product.getImgurl()));
			putils.resize(200, 200);

			// 调用ProductService中的添加商品方法.

			ProductsService service = new ProductsService();

			service.addProduct(product);
			response.sendRedirect("m3.jsp"); 
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
