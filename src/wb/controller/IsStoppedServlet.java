package wb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wb.beans.User;
import wb.service.UserService;

@WebServlet(urlPatterns = { "/isStopped" })
@MultipartConfig(maxFileSize = 100000)//あとで検索
public class IsStoppedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse	response) throws ServletException, IOException {


			request.getRequestDispatcher("usermanage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	int i = Integer.parseInt(request.getParameter("id"));
	int j = Integer.parseInt(request.getParameter("isStopped"));
	//List<String> user = new ArrayList<String>();
	//user.add(request.getParameter("id"));
	//user.add(request.getParameter("isStopped"));

	//beanを発動しながら、idを引数にユーザーサービス実施
	User editUser = new UserService().getUser(i);
	editUser.setIsStopped(j);
	new UserService().isStoppedUpdate(i, j);

	response.sendRedirect("usermanage");

	}
}