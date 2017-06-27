package wb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wb.service.DeleteService;

@WebServlet(urlPatterns = { "/commentDelete" })
@MultipartConfig(maxFileSize = 100000)//あとで検索
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse	response) throws ServletException, IOException {


			request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	int i = Integer.parseInt(request.getParameter("commentId"));
	//ここでユーザーサービスからDAO呼び出して削除
	new DeleteService().commentDelete(i);

	response.sendRedirect("./");

	}
}