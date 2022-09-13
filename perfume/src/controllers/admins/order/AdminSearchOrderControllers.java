package controllers.admins.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ItemsDao;
import daos.OrderDao;
import models.Item;
import models.Order;

public class AdminSearchOrderControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSearchOrderControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String orderId = request.getParameter("orderID");
		int statusOrder = Integer.parseInt(request.getParameter("statusOrder"));
		ItemsDao itemDao = new ItemsDao();

		if (!orderId.equals("")) {
			if (statusOrder == 0) {
				List<Item> itemList = itemDao.search(Integer.parseInt(orderId));
				request.setAttribute("itemList", itemList);
			} else if (statusOrder == 1) {
				List<Item> itemList = itemDao.searchDaDuyet(Integer.parseInt(orderId));
				request.setAttribute("itemList", itemList);
			} else if (statusOrder == 2) {
				List<Item> itemList = itemDao.searchChuaDuyet(Integer.parseInt(orderId));
				request.setAttribute("itemList", itemList);
			} else if (statusOrder == 3) {
				List<Item> itemList = itemDao.searchDaHuy(Integer.parseInt(orderId));
				request.setAttribute("itemList", itemList);
			}
		} else {
			if (statusOrder == 0) {
				List<Item> itemList = itemDao.findAll();
				request.setAttribute("itemList", itemList);
			} else if (statusOrder == 1) {
				List<Item> itemList = itemDao.searchDaDuyetRong();
				request.setAttribute("itemList", itemList);
			} else if (statusOrder == 2) {
				List<Item> itemList = itemDao.searchChuaDuyetRong();
				request.setAttribute("itemList", itemList);
			} else if (statusOrder == 3) {
				List<Item> itemList = itemDao.searchDaHuyRong();
				request.setAttribute("itemList", itemList);
			}
		}

		// if (!orderId.equals("")) {
//			
//			List<Item> itemList = itemDao.search(Integer.parseInt(orderId));
//			request.setAttribute("itemList", itemList);
//		} else {
//			
//			List<Item> itemList = itemDao.findAll();
//
//			request.setAttribute("itemList", itemList);
//		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
