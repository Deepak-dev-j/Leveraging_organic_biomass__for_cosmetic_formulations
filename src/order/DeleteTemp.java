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

import dbconnection.Dbconn;

@WebServlet("/DeleteTemp")
public class DeleteTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=Dbconn.getconnection();
		String id=request.getParameter("id");
		
		try
		{
			PrintWriter out=response.getWriter();
			String qry = "delete from temp_order where id='"+id+"'";
			PreparedStatement ps = con.prepareStatement(qry);
			int rs = ps.executeUpdate();
			
			if(rs>0){
				out.print("<html><body><script>alert('Deleted Successfully');</script></body></html>");
				RequestDispatcher rss = request.getRequestDispatcher("displaycart.jsp");
				rss.include(request, response);
				
			}
			else {
				out.print("<html><body><script>alert('Delete Unsuccessful');</script></body></html>");	
				RequestDispatcher rss = request.getRequestDispatcher("displaycart.jsp");
				rss.include(request, response);	
			
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
