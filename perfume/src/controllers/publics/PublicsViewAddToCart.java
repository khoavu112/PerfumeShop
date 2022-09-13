package controllers.publics;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PublicsViewAddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PublicsViewAddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("idpro: "+request.getParameter("idpro"));
		response.getWriter().print("<form method=\"GET\" action=\"/perfume/addtocart\" >"+ 
									"<input type=\"number\" name=\"quantity\" value=\"\"class=\"form-control\" placeholder=\"Nhập số lượng\" style=\"float:right; width: 300px;\" />"+
									"<input type=\"text\" name=\"id\" value=\""+request.getParameter("idpro") +"\"class=\"form-control\"  style=\"float:right; width: 300px;\" />"+
									"<input type=\"submit\" name=\"search\" value=\"Xác nhận\" class=\"btn btn-warning\" style=\"float:right\" />"+
									"<div style=\"clear:both\"></div>"+
									"</form>");
									
	}

}
