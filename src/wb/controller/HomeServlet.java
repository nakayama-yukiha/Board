package wb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wb.beans.UserComment;
import wb.beans.UserContribute;
import wb.service.NewTextService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
	ServletException {

		//カテゴリーの取得
		HashSet<String> categorys = new HashSet<String>();
		List<UserContribute> contributecategory = new NewTextService().getCategory();

		for(UserContribute contribute : contributecategory) {
			categorys.add(contribute.getCategory());
		}
		//カテゴリ表示
		request.setAttribute("categorys", categorys);



		//日付の取得
		System.out.println("request.getParameter(first)");
		String first = request.getParameter("first");
		if ( first == null || first.length() == 0 ){
			first = "2017/05/01";
		}

		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		String end = request.getParameter("end");
		if ( end == null || end.length() == 0 ){
			end = sdf1.format(date).toString();
		}
		end += " 23:59:59";

		System.out.println(first);
		System.out.println(end);

		//カテゴリが全部指定するかチェック

		System.out.println(request.getParameter("category"));
		String category = request.getParameter("category");
		if (category != null) {
			if (category.equals("allCategory")) {
				category = null;
			}
		}

		request.setAttribute("reFirst", request.getParameter("first"));
		request.setAttribute("reEnd", request.getParameter("end"));
		request.setAttribute("reCategory", request.getParameter("category"));



		//投稿文を表示させる
		List<UserContribute> contributes = new NewTextService().
				getContribute(first, end, category);

//		List<String> test = new ArrayList<String>();
//		for(UserContribute tests : contributes) {
//			test.add(tests.getText());
//			System.out.println(test);
//		}

		request.setAttribute("contribute", contributes);

		//コメントを表示させる
		List<UserComment> comments = new NewTextService().getComment();

		request.setAttribute("comments", comments);




		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}

