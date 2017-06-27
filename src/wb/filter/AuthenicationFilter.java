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

@WebFilter({"/usermanage","/setting","/newuser"})
public class AuthenicationFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String url = ((HttpServletRequest) request).getServletPath();
		if(url.contains("css")) {//現在のURLがログインじゃなければ元のサーブレット実行
			chain.doFilter(request, response);
		}


		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User)session.getAttribute("loginUser");
		if(user == null) {
			chain.doFilter(request, response);
			return;
		}
		List<String> errorMessages = new ArrayList<String>();

		User checkUser = new UserService().getUser(user.getId());
		if((checkUser.getBranchId() == 1) && (checkUser.getSectionId() == 1)) {
			chain.doFilter(request, response);
			return;
		}else {
			errorMessages.add("ページへのアクセス権がありません");
			session.setAttribute("errorMessages", errorMessages);
			((HttpServletResponse) response).sendRedirect("./");
		}



		}

	@Override
	public void init(FilterConfig config) {

	}

	@Override
	public void destroy() {
	}

}
