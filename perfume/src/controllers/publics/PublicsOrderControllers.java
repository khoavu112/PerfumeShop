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

import daos.CatPerfumeDao;
import daos.ItemsDao;
import daos.OrderDao;
import daos.PerfumeDao;
import daos.UserDao;
import models.CatPerfume;
import models.Comment;
import models.Item;
import models.Order;
import models.Perfume;
import models.User;

public class PublicsOrderControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsOrderControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		User userInfor = (User) session.getAttribute("userInfor");
		OrderDao orderDao = new OrderDao();
		ItemsDao itemDao = new ItemsDao();
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		if (request.getParameter("orderid") != null) {
			int orderID = Integer.parseInt(request.getParameter("orderid"));
			List<Item> itemListOrder = itemDao.findByOrderID(orderID);
			request.setAttribute("itemListOrder", itemListOrder);
		}

		int orderID = orderDao.getIdDonHang(userInfor.getId());

		List<Order> itemListOrderDaDat = orderDao.findByID(userInfor.getId());
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		request.setAttribute("itemListOrderDaDat", itemListOrderDaDat);
		request.setAttribute("catPFList", catPFList);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/order.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		OrderDao orderDao = new OrderDao();
		ItemsDao itemDao = new ItemsDao();
		PerfumeDao perDao = new PerfumeDao();
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();

		request.setAttribute("catPFList", catPFList);

		int userID = Integer.parseInt(request.getParameter("userID"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String adress = request.getParameter("adress");
		String note = request.getParameter("note");
		int hangTonKho;
		int addItem = 0;
		if (!phone.equals("") || !email.equals("") || !adress.equals("") || !note.equals("")) {
			HttpSession session = request.getSession();
			
			if (session.getAttribute("order") != null) {
				Order order = new Order(new User(userID), phone, email, adress, note);

				Order orderItem = (Order) session.getAttribute("order");
				List<Item> ListItem = (List<Item>) orderItem.getItem();
				int index = 0;
				int addOrder = 0;
				if (ListItem.size() > 0) {
					for (Item item : ListItem) {						
						int quantity = item.getQuantity();

						long price = item.getPrice();
						int per_id = item.getProduct().getId();

						if (item.getProduct().getAmount() >= quantity) {
							if (index == 0) {
								addOrder = orderDao.order(order);
								
								index++;
							}
							int order_id = addOrder;
							hangTonKho = item.getProduct().getAmount() - quantity;
							perDao.editSoluongTonKho(per_id, hangTonKho);
							Item items = new Item(new Perfume(per_id), quantity, price, new Order(order_id));

							addItem = itemDao.add(items);
							System.out.println("co add item ko?"+addItem);
						} else {
							response.sendRedirect(request.getContextPath() + "/order?msg=ERRORHetHang");
							return;
						}

					}
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/order?msg=ERRORDonHang");
				return;
			}
			if (addItem > 0) {
				response.sendRedirect(request.getContextPath() + "/order?msg=SUCCESS");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/order?msg=ERRORDonHang");
				return;
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/order?msg=ERROR");
			return;
		}

	}

}
