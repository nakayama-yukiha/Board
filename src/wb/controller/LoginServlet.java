package wb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wb.beans.User;
import wb.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//ブラウザに対して表示すべき情報の命令をしている
		request.getRequestDispatcher("login.jsp").forward(request, response);
		//値をセットするはフォワードでやっている
		//レスポンスにはHTTPの情報が入っている
		//処理後に画面表示したいときに使う
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		System.out.println(loginId);
		System.out.println(password);


		LoginService loginService = new LoginService();
		User user = loginService.login(loginId, password);


		HttpSession session = request.getSession();
		if (user != null) {

			session.setAttribute("loginUser", user);
			response.sendRedirect("./");
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);

			request.setAttribute("reLoginId", loginId);
			request.setAttribute("rePassword", password);

			//response.sendRedirect("login");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}



	}



}