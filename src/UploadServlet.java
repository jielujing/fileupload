

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	    Part part = request.getPart("myfile");
	    String disposition = part.getHeader("Content-Disposition");
		String suffix = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1);
		//随机的生成一个32的字符串
		String filename = UUID.randomUUID()+suffix;
		//获取上传的文件名
		InputStream is = part.getInputStream();
		//动态获取服务器的路径
		String serverpath = "f:/upload";
		FileOutputStream fos = new FileOutputStream(serverpath+"/"+filename);
		byte[] bty = new byte[1024];
		int length =0;
		while((length=is.read(bty))!=-1){
		      fos.write(bty,0,length);
		     }
		fos.close();
		is.close();
	    part.write(serverpath);
		response.sendRedirect("list.jsp");
		    
	}

}
