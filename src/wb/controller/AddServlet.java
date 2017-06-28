package wb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import wb.beans.Branches;
import wb.beans.Sections;
import wb.service.BranchService;
import wb.service.SectionService;

@WebServlet(urlPatterns = { "/add" })
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branches> branch = new BranchService().getBranches();
		request.setAttribute("branches", branch);

		List<Sections> section = new SectionService().getSections();
		request.setAttribute("sections", section);


		request.getRequestDispatcher("add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
	ServletException {

		String branchName = (request.getParameter("branchName"));
		String sectionName = (request.getParameter("sectionName"));
		String branchId = (request.getParameter("branchId"));
		String sectionId = (request.getParameter("sectionId"));

		System.out.println("ブランチIDは" + branchId);

		if (StringUtils.isBlank(branchName) == false) {
			Branches branches = new Branches();
			branches.setName(request.getParameter("branchName"));

			new BranchService().register(branches);
		}


		if (StringUtils.isBlank(sectionName) == false) {
			Sections sections = new Sections();
			sections.setName(request.getParameter("sectionName"));

			new SectionService().register(sections);
		}

		if (StringUtils.isBlank(branchId) == false) {
			new BranchService().branchDelete(branchId);
		}

		if (StringUtils.isBlank(sectionId) == false) {
			new SectionService().sectionDelete(sectionId);
		}



		response.sendRedirect("add");


	}
}
