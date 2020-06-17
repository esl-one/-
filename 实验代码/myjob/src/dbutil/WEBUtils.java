package dbutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * 
 * 
 * @author Administrator
 * 
 */
public class WEBUtils {

	private WEBUtils() {
	}

	/**
	 *
	 * 
	 * @param request
	 * @param response
	 * @param message
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void forward(HttpServletRequest request,
			HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/pages/message.jsp").forward(
				request, response);
	}

	/**
	 * �
	 * 
	 * @param request
	 * @param beanClass
	 * @return
	 */
	public static <T> T request2Bean(HttpServletRequest request,
			Class<T> beanClass) {

		try {
			T bean = beanClass.newInstance();

			// 
			String id = request.getParameter("id");
			if ("".equals(id) || null == id) {
				// ֵ
				BeanUtils.setProperty(bean, "id", UUID.randomUUID().toString());
			}
			// 
			ConvertUtils.register(new DateLocaleConverter(), Date.class);

			// 
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String name = (String) parameterNames.nextElement();
				// 
				String value = request.getParameter(name);
				//ֵ
				BeanUtils.setProperty(bean, name, value);
			}

			return bean;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 
	 * 
	 * @param request
	 * @param beanClass
	 * @return
	 */
	public static <T> T doUpload(HttpServletRequest request, Class<T> beanClass) {
			
		try {
			T bean = beanClass.newInstance();
			
			String id = request.getParameter("id");
			String uuid = UUID.randomUUID().toString();
			if ("".equals(id) || null == id) {
				BeanUtils.setProperty(bean, "id", uuid);
			}
			// 
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			
			//
			ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
			//
			@SuppressWarnings("unchecked")
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			
			//
			for (FileItem fileItem : fileItems) {
				//
				if (fileItem.isFormField()) {
					//
					String name = fileItem.getFieldName();
					//String value = fileItem.getString();
					String value = fileItem.getString("UTF-8");
					BeanUtils.setProperty(bean, name, value);
				} else {
					//
					String fileName = fileItem.getName();
					//
					String extendName = fileName.substring(fileName.lastIndexOf("."));
					
					//
					fileName = uuid + extendName;
					//
					String webPath = "/images/" + fileName;
					//
					String path = request.getSession().getServletContext().getRealPath(webPath);
					
					//
					File file = new File(path);
					file.getParentFile().mkdirs();
					file.createNewFile();
					
					//ַ
					//
					InputStream inputStream = fileItem.getInputStream();
					OutputStream outputStream = new FileOutputStream(file);
					Streams.copy(inputStream, outputStream, true);
					
					//
					fileItem.delete();
					
					//
					String name = fileItem.getFieldName();
					String value = webPath;
					BeanUtils.setProperty(bean, name, value);
				}
			}
			
			
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException(e);
		} 
		
	}

}
