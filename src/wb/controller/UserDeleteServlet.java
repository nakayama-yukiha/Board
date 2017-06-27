package wb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wb.service.UserService;

@WebServlet(urlPatterns = { "/userDelete" })
@MultipartConfig(maxFileSize = 100000)//あとで検索
public class UserDeleteServlet extends HttpServlet {
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
	//ここでユーザーサービスからDAO呼び出して削除
	new UserService().userDelete(i);

	response.sendRedirect("usermanage");

	}
}