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

import daos.CatPerfumeDao;
import daos.PerfumeDao;
import daos.PictureDao;
import models.CatPerfume;
import models.Perfume;
import models.Picture;
import util.DefineUtil;

public class PublicsSearchControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsSearchControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		PerfumeDao perfumeDao = new PerfumeDao();
		PictureDao pictureDao = new PictureDao();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		List<Perfume> perCatList = perfumeDao.FindCat();
		for(Perfume perCat : perCatList) {
			int idPF = perCat.getId();
			Picture picPerCat = pictureDao.viewsIDPF2(idPF);
			request.setAttribute("picPerCat" + perCat.getId(), picPerCat);
			
		}
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		int numberOfItems = perfumeDao.getCount();
		int numberOfPage = 1;
		
		if(currentPage > numberOfPage || currentPage < 1) {
			currentPage = 1;
		}
		int offsetAdmin = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE_INDEX;
		
		String nameSearch = (String) request.getParameter("nameSearch");
		
		
		List<Perfume> perfumeList = perfumeDao.search(nameSearch);
		
		for(Perfume pro : perfumeList) {
			int idPF = pro.getId();
			Picture pic = pictureDao.viewsIDPF2(idPF);
			request.setAttribute("pic" + pro.getId(), pic);
		}
		List<Perfume> perfumeListView = perfumeDao.findAllViews();
		for(Perfume proView : perfumeList) {
			int idPF = proView.getId();
			Picture picView = pictureDao.viewsIDPF2(idPF);
			request.setAttribute("picView" + proView.getId(), picView);
		}
		List<Perfume> proNews = perfumeDao.findProNew();
		
		
//		List<Picture> picList = pictureDao.findAll();
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("proNews", proNews);
		request.setAttribute("perCatList", perCatList);
		request.setAttribute("catPFList", catPFList);
		request.setAttribute("perfumeList", perfumeList);
		request.setAttribute("perfumeListView", perfumeListView);
		
//		request.setAttribute("picList", picList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
