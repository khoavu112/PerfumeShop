package controllers.admins.perfume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PictureDao;

public class AdminEditPerfumeImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AdminEditPerfumeImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idImage");
		PictureDao pitureDao = new PictureDao();
		
	}

	

}
