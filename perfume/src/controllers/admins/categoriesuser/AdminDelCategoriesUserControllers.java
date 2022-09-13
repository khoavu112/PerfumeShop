package controllers.admins.categoriesuser;

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
import models.CatUser;

public class AdminDelCategoriesUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelCategoriesUserControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		CatUserDao catUserDao = new CatUserDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}

		int del = catUserDao.del(id);
		if (del > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/categoriesuser?msg=OK");
			return;
		} else {
			

			List<CatUser> listCatUser = catUserDao.findAll();
			request.setAttribute("listCatUser", listCatUser);
			request.setAttribute("err", "Không thể loại xóa tài khoản này!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
