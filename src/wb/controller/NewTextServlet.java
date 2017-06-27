package wb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import wb.beans.Contributes;
import wb.beans.User;
import wb.beans.UserContribute;
import wb.service.NewTextService;

@WebServlet(urlPatterns = { "/NewText" })
public class NewTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HashSet<String> categorys = new HashSet<String>();
		List<UserContribute> contributecategory = new NewTextService().getCategory();

		for(UserContribute contribute : contributecategory) {
			categorys.add(contribute.getCategory());
		}
		//カテゴリ表示
		request.setAttribute("categorys", categorys);

		request.getRequestDispatcher("newtext.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
	ServletException {

		HashSet<String> categorys = new HashSet<String>();
		List<UserContribute> contributecategory = new NewTextService().getCategory();

		for(UserContribute contribute : contributecategory) {
			categorys.add(contribute.getCategory());
		}
		//カテゴリ表示
		request.setAttribute("categorys", categorys);

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			String cate = (request.getParameter("category"));
			if (cate == null || StringUtils.isBlank(cate)) {
				cate = (request.getParameter("category2"));
			}

			System.out.println(cate);

			User user = (User) session.getAttribute("loginUser");

			Contributes contributes = new Contributes();
			contributes.setTitle(request.getParameter("title"));
			contributes.setCategory(cate);
			contributes.setText(request.getParameter("text"));
			contributes.setUserId(user.getId());

			new NewTextService().register(contributes);

			response.sendRedirect("./");
		} else {

			String title = request.getParameter("title");
			String category = request.getParameter("category");
			String text = request.getParameter("text");
			String category2 = request.getParameter("category2");

			request.setAttribute("reTitle", title);
			request.setAttribute("reCategory", category);
			request.setAttribute("reCategory2", category2);
			request.setAttribute("reText", text);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newtext.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>
	messages) {

		System.out.println(request.getParameter("category"));
		System.out.println(request.getParameter("category2"));

		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String category2 = request.getParameter("category2");
		String text = request.getParameter("text");

		if (StringUtils.isEmpty(category) == true && StringUtils.isBlank(category2) == true) {
			messages.add("カテゴリーを記入してください");
		}

		if (StringUtils.isEmpty(category) == false && StringUtils.isBlank(category2) == false) {
			messages.add("カテゴリは選択するか追加するかどちらかになります");
		}

		if (StringUtils.isBlank(title) == true) {
			messages.add("タイトルを入力してください");
		}
		if (50 < title.length()) {
			messages.add("タイトルは50文字以下で入力してください");
		}
		//if (StringUtils.isEmpty(category) == true) {
			//messages.add("カテゴリーを入力してください");
		//}
		if (10 < category.length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}
		//if (StringUtils.isEmpty(text) == true) {
		//	messages.add("本文を入力してください");
	//	}
		if (StringUtils.isBlank(text) == true) {
			messages.add("本文を入力してください");
		}
		if (1000 < text.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}


