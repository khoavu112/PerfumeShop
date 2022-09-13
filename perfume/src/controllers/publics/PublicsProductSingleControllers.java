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
import daos.CommentDao;
import daos.PerfumeDao;
import daos.PictureDao;
import models.CatPerfume;
import models.Comment;
import models.Perfume;
import models.Picture;

public class PublicsProductSingleControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsProductSingleControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		request.setAttribute("catPFList", catPFList);
		
		PerfumeDao perfumeDao = new PerfumeDao();
		PictureDao pictureDao = new PictureDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		int count = perfumeDao.getCount(id);
		
		Perfume product = perfumeDao.viewsID(id);
		int catPer = product.getCatPer().getId();
		int idImage = product.getId();
		Picture picID = pictureDao.viewsIDPF2(idImage);
		
		List<Perfume> ortherProList = perfumeDao.ortherList(catPer);
		for(Perfume ortherPic : ortherProList) {
			int idPro = ortherPic.getId();
			Picture picPro = pictureDao.viewsIDPF2(idPro);
			request.setAttribute("picPro" + ortherPic.getId(), picPro);
		}
		CommentDao commentDao = new CommentDao();
		ArrayList<Comment> listCMT = new ArrayList<Comment>();
		listCMT = (ArrayList<Comment>) commentDao.findByIdPer(id);
		
		request.setAttribute("listCMT", listCMT);
		request.setAttribute("ortherProList", ortherProList);
		request.setAttribute("picID", picID);
		request.setAttribute("product", product);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/product-single.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
