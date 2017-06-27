package wb.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wb.beans.User;
import wb.service.UserService;

@WebFilter("/*")
public class LogingFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {


		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User)session.getAttribute("loginUser");
		List<String> errorMessages = new ArrayList<String>();

		String url = ((HttpServletRequest) request).getServletPath();
		System.out.println(url);
		//System.out.println(!(url.equals("/login")));
		System.out.println(!(url.matches("/css/login")));
		System.out.println(!url.contains("*css*"));


//		if(!(url.equals("/login") && !(url.matches(".*.css")))) {
//
//			if(user == null) {//user情報が空白の場合ログインページへ遷移
//				errorMessages.add("ログインしてください");
//				session.setAttribute("errorMessages", errorMessages);
//				((HttpServletResponse) response).sendRedirect("login");
//				return;
//
//			}else {
//
//				User checkUser = new UserService().getUser(user.getId());
//				if(checkUser.getIsStopped() == 0) {
//					errorMessages.add("現在このアカウントはお使いできません");
//					errorMessages.add("管理者にお問い合わせください");
//					session.setAttribute("errorMessages", errorMessages);
//					((HttpServletResponse) response).sendRedirect("login");
//					return;
//				}
//			}
////			return;
//
//		}

		if(url.equals("/login") || url.contains("css") || url.equals("/logout")) {//現在のURLがログインじゃなければ元のサーブレット実行
			chain.doFilter(request, response);//サーブレットの実行
		}else{
			if(user == null) {//user情報が空白の場合ログインページへ遷移
				errorMessages.add("ログインしてください");
				session.setAttribute("errorMessages", errorMessages);
				((HttpServletResponse) response).sendRedirect("login");
			}else {
				User checkUser = new UserService().getUser(user.getId());
				System.out.println(checkUser.getIsStopped());

				if(checkUser.getIsStopped() == 1) {//アカウント停止チェック
					chain.doFilter(request, response);
				}else {


					errorMessages.add("現在このアカウントはお使いできません");
					errorMessages.add("管理者にお問い合わせください");					session.setAttribute("errorMessages", errorMessages);
					((HttpServletResponse) response).sendRedirect("login");
				}


			}
		}
//		chain.doFilter(request, response);

		System.out.println("LoginFilterが実行されました。");


	}


	@Override
	public void init(FilterConfig config) {

	}

	@Override
	public void destroy() {
	}

}
