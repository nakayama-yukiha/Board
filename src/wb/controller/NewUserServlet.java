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

import wb.beans.Branches;
import wb.beans.Sections;
import wb.beans.User;
import wb.service.BranchService;
import wb.service.SectionService;
import wb.service.UserService;

@WebServlet(urlPatterns = { "/newuser" })
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Branches> branch = new BranchService().getBranches();
		request.setAttribute("branch", branch);

		List<Sections> section = new SectionService().getSections();
		request.setAttribute("section", section);

		request.setAttribute("reBranchId", 0);
		request.setAttribute("reSectionId", 0);


		request.getRequestDispatcher("newuser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();



		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			int i = Integer.parseInt(request.getParameter("branchId"));
			int j = Integer.parseInt(request.getParameter("sectionId"));
			int k = Integer.parseInt(request.getParameter("isStopped"));

			User user = new User();
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(i);
			user.setSectionId(j);
			user.setIsStopped(k);

			new UserService().register(user);//ユーザーサービスのメソッド呼び出し
			response.sendRedirect("usermanage");
		} else {

			String loginId = request.getParameter("loginId");
			String name = request.getParameter("name");
			String branchId = request.getParameter("branchId");
			String sectionId = request.getParameter("sectionId");

			request.setAttribute("reLoginId", loginId);
			request.setAttribute("reName", name);
			request.setAttribute("reSectionId", sectionId);
			request.setAttribute("reBranchId", branchId);

			List<Branches> branch = new BranchService().getBranches();
			request.setAttribute("branch", branch);

			List<Sections> section = new SectionService().getSections();
			request.setAttribute("section", section);


			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newuser.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String name = request.getParameter("name");
		String branchId = request.getParameter("branchId");
		String sectionId = request.getParameter("sectionId");
		String s = "0";
		System.out.println(branchId);
		System.out.println(password);
		System.out.println(repassword);

		UserService userService = new UserService();
		User userCheck = userService.getUserCheck(loginId);

		if (userCheck != null) {
			messages.add("このログインIDはすでに使われています");
		}




		if (StringUtils.isBlank(loginId) == true) {
			messages.add("IDを入力してください");
		}
		if (!(loginId.matches("^[a-zA-Z0-9]{5,20}$"))) {
			messages.add("ログインIDは半角英数字5文字以上20文字以下です");
		}
		if (StringUtils.isBlank(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (!(password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,}$"))) {
			messages.add("パスワードは記号を含む半角英数字6文字以上です");
		}
		if (StringUtils.isBlank(name) == true) {
			messages.add("氏名を入力してください");
		}
		//if (name.matches("[ ¥t¥n¥x0B¥f¥r]")) {
		//	messages.add("正しく氏名を入力してください");
		//}
		if (!(name.matches(".{0,10}"))) {
			messages.add("氏名は10文字以下です");
		}
		if (StringUtils.isEmpty(branchId) == true) {
			messages.add("勤務地を選択してください");
		}
		if (branchId.equals(s)) {
			messages.add("勤務地を選択してください");
		}
		if (StringUtils.isEmpty(sectionId) == true) {
			messages.add("部署・役職を選択してください");
		}
		if (sectionId.equals(s)) {
			messages.add("部署・役職を選択してください");
		}
		if (!(password.equals(repassword))) {
			messages.add("パスワードが一致していません");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
