package wb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import wb.beans.Branches;
import wb.beans.Sections;
import wb.beans.User;
import wb.exception.NoRowsUpdatedRuntimeException;
import wb.service.BranchService;
import wb.service.SectionService;
import wb.service.UserService;

@WebServlet(urlPatterns = { "/setting" })
@MultipartConfig(maxFileSize = 100000)//あとで検索
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse	response) throws ServletException, IOException {

			System.out.println(request.getParameter("editid"));
			//ここでeditidを引数にして得たユーザー情報をリクエスト領域におく
			HttpSession session = request.getSession();
			List<String> messages = new ArrayList<String>();

			String check = request.getParameter("editid");
			if (request.getParameter("editid") == null
					|| StringUtils.isEmpty(check) ) {

				messages.add("ページが存在しません");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("usermanage");
				return;
			}

			if (!(check.matches("^[0-9]$"))) {
				messages.add("ページが存在しません");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("usermanage");
				return;
			}



			int i = Integer.parseInt(request.getParameter("editid"));
			User editUser = new UserService().getUser(i);

			if (session.getAttribute("editUser") != null){
				session.removeAttribute("editUser");
			}

			session.setAttribute("editUser", editUser);
			//request.setAttribute("editUser", editUser);


			List<Branches> branch = new BranchService().getBranches();
			request.setAttribute("branch", branch);
			//System.out.println(branch);

			List<Sections> section = new SectionService().getSections();
			request.setAttribute("section", section);


			request.getRequestDispatcher("setting.jsp").forward(request, response);
		}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		User editUser = getEditUser(request);
		//session.setAttribute("editUser", editUser);
		//request.setAttribute("editUser", editUser);

		//List<Branches> branch = new BranchService().getBranches();
		//request.setAttribute("branch", branch);
		//System.out.println(branch);

		//List<Sections> section = new SectionService().getSections();
	//	request.setAttribute("section", section);



		HttpSession session = request.getSession();


		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);

				Object obj = session.getAttribute("editUser.id");
		        String str = obj.toString();
		        int id = Integer.parseInt(str);

		        User user = new UserService().getUser(id);
		        List<Branches> branch = new BranchService().getBranches();
				request.setAttribute("branch", branch);
				//System.out.println(branch);

				List<Sections> section = new SectionService().getSections();
				request.setAttribute("section", section);
				session.setAttribute("editUser", user);
				request.getRequestDispatcher("setting.jsp").forward(request, response);

			}

			session.removeAttribute("editUser");
			response.sendRedirect("usermanage");
			//request.getRequestDispatcher("usermanage.jsp").forward(request, response);
		} else {

			List<Branches> branch = new BranchService().getBranches();
			request.setAttribute("branch", branch);
			//System.out.println(branch);

			List<Sections> section = new SectionService().getSections();
			request.setAttribute("section", section);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("setting.jsp").forward(request, response);
			//できた！！
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {



		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");


		int i = Integer.parseInt(request.getParameter("editBranchId"));
		int j = Integer.parseInt(request.getParameter("editSectionId"));

		editUser.setLoginId(request.getParameter("editLoginId"));
		editUser.setPassword(request.getParameter("editPassword"));
		editUser.setName(request.getParameter("editName"));
		editUser.setBranchId(i);
		editUser.setSectionId(j);
		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String loginId = request.getParameter("editLoginId");
		String password = request.getParameter("editPassword");
		String name = request.getParameter("editName");
		String repassword = request.getParameter("editRePassword");

		System.out.println(loginId);

		//変更元のユーザーIDを引数にしてユーザー情報呼び出し
		int i = Integer.parseInt(request.getParameter("checkLoginId"));
		System.out.println(i);
		User checkUser = new UserService().getUser(i);

		System.out.println(checkUser.getLoginId());

		//入力されたログインIDを引数にしてユーザー情報の呼び出し
		UserService userService = new UserService();
		User userCheck = userService.getUserCheck(loginId);

		//呼び出したユーザー情報のログインIDが等しいか、
		if (!(loginId.equals(checkUser.getLoginId()))) {
			//等しくなければすでに存在しているか否か確認
			if (userCheck != null) {
				messages.add("このログインIDはすでに使われています");
			}
		}

		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (!(loginId.matches("^[a-zA-Z0-9]{5,20}$"))) {
			messages.add("ログインIDは半角英数字5文字以上20文字以下です");
		}


		if (!(StringUtils.isEmpty(password)) && !(password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,}$"))) {
			messages.add("パスワードは記号を含む半角英数字6文字以上です");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (!(name.matches(".{0,10}"))) {
			messages.add("氏名は10文字以下です");
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
