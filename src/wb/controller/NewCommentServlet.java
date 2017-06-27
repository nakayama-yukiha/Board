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

import org.apache.commons.lang.StringUtils;

import wb.beans.Comments;
import wb.beans.User;
import wb.service.NewTextService;

@WebServlet(urlPatterns = { "/newComment" })
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
	ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			int i = Integer.parseInt(request.getParameter("contributeId"));

			Comments comments = new Comments();

			comments.setContributeId(i);
			comments.setUserId(user.getId());
			comments.setText(request.getParameter("comment"));


			new NewTextService().register(comments);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");

		}
	}

	private boolean isValid(HttpServletRequest request, List<String>
	messages) {

		String comment = request.getParameter("comment");


		if (StringUtils.isBlank(comment) == true) {
			messages.add("コメントを入力してください");
		}
		if (500 < comment.length()) {
			messages.add("コメントは500文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
