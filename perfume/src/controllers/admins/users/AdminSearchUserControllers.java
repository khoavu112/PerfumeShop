package controllers.admins.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CatUserDao;
import daos.ItemsDao;
import daos.UserDao;
import models.CatUser;
import models.Item;
import models.User;
import util.DefineUtil;
import util.StringUtil;

public class AdminSearchUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSearchUserControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// cat user
		CatUserDao catUserDao = new CatUserDao();
		List<CatUser> catUserList = catUserDao.findAll();
		request.setAttribute("catUserList", catUserList);

		String nameUser = request.getParameter("nameUser");
		String catUser = request.getParameter("catUser");

		UserDao userDao = new UserDao();
		List<User> item;
		int currentPage = 1;
		int numberOfPage = 1;
		int offsetAdmin = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		if (catUser.equals("0")) {
			item = userDao.searchName(nameUser);
			if (nameUser.equals("")) {
				int numberOfItems = userDao.getCount();
				numberOfPage = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE_ADMIN);
				
				if (currentPage > numberOfPage || currentPage < 1) {
					currentPage = 1;
				}
				offsetAdmin = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE_ADMIN;
			} else {
				int numberOfItems = userDao.getCount();
				numberOfPage = 1;

				if (currentPage > numberOfPage || currentPage < 1) {
					currentPage = 1;
				}
				offsetAdmin = 0;
			}
		}else {
			item = userDao.searchNameAndCat(nameUser,Integer.parseInt(catUser));
			
			int numberOfItems = userDao.getCount();
			numberOfPage = 1;

			if (currentPage > numberOfPage || currentPage < 1) {
				currentPage = 1;
			}
			offsetAdmin = 0;
		}
		
		request.setAttribute("userList", item);

		List<User> userList = userDao.findAllAdmin(offsetAdmin);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("userList", item);
		request.setAttribute("STT", offsetAdmin);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
