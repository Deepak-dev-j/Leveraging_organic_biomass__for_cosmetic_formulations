package order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Dbconn;

@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Connection con=Dbconn.getconnection();
		String id=request.getParameter("id");
		String qua=request.getParameter("quantity");
		HttpSession session = request.getSession();
		 String email = (String) session.getAttribute("userEmail");
		try
		{
			String update = "update temp_order set quantity='" + qua + "' WHERE id='" + id + "'AND email='" + email + "'";
			PreparedStatement ps = con.prepareStatement(update);
			int status = ps.executeUpdate();
			
			if(status>0){
				RequestDispatcher dd=request.getRequestDispatcher("displaycart.jsp");
				dd.include(request, response);
				out.print("<head><script>alert('Updated successful');</script></head>");
			}
			else {
				RequestDispatcher dd=request.getRequestDispatcher("displaycart.jsp");
				dd.include(request, response);
				out.print("<head><script>alert('Updated successful');</script></head>");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
