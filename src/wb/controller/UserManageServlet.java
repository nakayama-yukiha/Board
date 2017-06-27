package wb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wb.beans.Branches;
import wb.beans.Sections;
import wb.beans.User;
import wb.service.BranchService;
import wb.service.SectionService;
import wb.service.UserService;

@WebServlet(urlPatterns = { "/usermanage" })
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> user = new UserService().getUserAcount();
		request.setAttribute("userinformations", user);

		List<Branches> branch = new BranchService().getBranches();
		request.setAttribute("branchs", branch);


		List<Sections> section = new SectionService().getSections();
		request.setAttribute("sections", section);



		request.getRequestDispatcher("usermanage.jsp").forward(request, response);
	}
}
