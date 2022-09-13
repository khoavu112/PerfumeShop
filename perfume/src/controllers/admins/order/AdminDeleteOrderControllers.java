package controllers.admins.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CommentDao;
import daos.ItemsDao;
import daos.OrderDao;
import models.Item;

public class AdminDeleteOrderControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteOrderControllers() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		OrderDao orderDao = new OrderDao();
		int idOrderDel = 0;
		try {
			idOrderDel = Integer.parseInt(request.getParameter("idOrderDel"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		int del = orderDao.delOrder(idOrderDel);
		
		ItemsDao itemDao = new ItemsDao();
		
		if(del > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/order?msg=SUCCESS");
			return;
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
