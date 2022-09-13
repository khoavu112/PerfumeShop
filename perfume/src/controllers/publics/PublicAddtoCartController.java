package controllers.publics;

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

import daos.PerfumeDao;
import models.Item;
import models.Order;
import models.Perfume;

public class PublicAddtoCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicAddtoCartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessionUserIF = request.getSession();
		if (sessionUserIF.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		PerfumeDao productDao = new PerfumeDao();
		int quantity = 1;
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));

			Perfume product = productDao.viewsID(id);
			if (product != null) {
				if (request.getParameter("quantity") != null) {
					quantity = Integer.parseInt(request.getParameter("quantity"));
				}
				HttpSession session = request.getSession();
				if (session.getAttribute("order") == null) {
					Order order = new Order();
					List<Item> listItems = new ArrayList<Item>();
					Item item = new Item();
					item.setQuantity(quantity);
					item.setProduct(product);
					item.setPrice(product.getMoney());
					listItems.add(item);
					order.setItem(listItems);
					session.setAttribute("order", order);
				} else {
					Order order = (Order) session.getAttribute("order");
					List<Item> listItems = order.getItem();
					boolean check = false;
					for (Item item : listItems) {
						if (item.getProduct().getId() == product.getId()) {
							item.setQuantity(item.getQuantity() + quantity);
							check = true;
						}
					}
					if (check == false) {
						Item item = new Item();
						item.setQuantity(quantity);
						item.setProduct(product);
						item.setPrice(product.getMoney());
						listItems.add(item);
					}
					session.setAttribute("order", order);
				}
			}
			response.sendRedirect(request.getContextPath() + "/order");
		} else if (request.getParameter("idGH") != null) {
			id = Integer.parseInt(request.getParameter("idGH"));

			Perfume product = productDao.viewsID(id);
			if (product != null) {
				if (request.getParameter("quantity") != null) {
					quantity = Integer.parseInt(request.getParameter("quantity"));
				}
				HttpSession session = request.getSession();
				if (session.getAttribute("order") == null) {
					Order order = new Order();
					List<Item> listItems = new ArrayList<Item>();
					Item item = new Item();
					item.setQuantity(quantity);
					item.setProduct(product);
					item.setPrice(product.getMoney());
					listItems.add(item);
					order.setItem(listItems);
					session.setAttribute("order", order);
				} else {
					Order order = (Order) session.getAttribute("order");
					List<Item> listItems = order.getItem();
					boolean check = false;
					for (Item item : listItems) {
						if (item.getProduct().getId() == product.getId()) {
							item.setQuantity(item.getQuantity() + quantity);
							check = true;
						}
					}
					if (check == false) {
						Item item = new Item();
						item.setQuantity(quantity);
						item.setProduct(product);
						item.setPrice(product.getMoney());
						listItems.add(item);
					}
					session.setAttribute("order", order);
				}
			}

			if (request.getParameter("page") != null) {
				String page = request.getParameter("page");
				response.sendRedirect(request.getContextPath() + "/home?page=" + page);
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");

		List<Item> ListItem = (List<Item>) order.getItem();

		int idOrder;
		if (request.getParameter("idOrder") != null) {
			idOrder = Integer.parseInt(request.getParameter("idOrder"));
			ListItem.remove(idOrder - 1);

			order.setItem(ListItem);
			session.setAttribute("order", order);
		}
		response.sendRedirect(request.getContextPath() + "/order");
	}

}
