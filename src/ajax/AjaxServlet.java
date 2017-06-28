package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class AjaxServlet extends HttpServlet {
     
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	response.setContentType("text/plain");
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        out.println("Hello, world!");
    }
 
    @Override
	public void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException {
        response.setContentType("text/plain");
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String param = URLDecoder.decode(request.getParameter("text1"),"utf8");
        int result = 0;
        try {
            int n = Integer.parseInt(param);
            for(int i = 1;i <= n;i++){
                result += i;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}