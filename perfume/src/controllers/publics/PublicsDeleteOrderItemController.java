package controllers.publics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ItemsDao;
import daos.OrderDao;
import daos.PerfumeDao;
import models.Item;

public class PublicsDeleteOrderItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsDeleteOrderItemController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PerfumeDao perDao = new PerfumeDao();
		ItemsDao itemDao = new ItemsDao();
		
		int OrderID = Integer.parseInt(request.getParameter("idOrderDelete"));
		List<Item> itemList = itemDao.findByOrderID(OrderID);
		for(Item item : itemList) {
			int idPer = item.getProduct().getId();
			int tonKho = item.getProduct().getAmount() + item.getQuantity();
			perDao.editSoluongTonKho(idPer, tonKho);
		}
		OrderDao orderDao = new OrderDao();
		int result = orderDao.editOrder(OrderID);
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/order?msg=SUCCESS");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/order");
			return;
		}
	}

}
