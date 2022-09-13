package controllers.admins.perfume;

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
import models.CatPerfume;
import models.Item;
import models.Order;
import models.Perfume;

public class AdminSearchPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSearchPerfumeControllers() {
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
		PerfumeDao perDao = new PerfumeDao();
		CatPerfumeDao catPerDao= new CatPerfumeDao();
		String nameCat = request.getParameter("nameCat");
		String catID = request.getParameter("catPer");
		List<CatPerfume> listCatPer = catPerDao.findAllBrand();
		if (catID.equals("0")) {
			
			List<Perfume> perList = perDao.search(nameCat);
			request.setAttribute("perList", perList);
		}else {
			List<Perfume> perList = perDao.searchCatID(Integer.parseInt(catID), nameCat);
			request.setAttribute("perList", perList);
		}
		request.setAttribute("listCatPer", listCatPer);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
