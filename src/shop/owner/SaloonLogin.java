package shop.owner;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dbconnection.Dbconn;
@WebServlet("/SaloonLogin")
public class SaloonLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String a = request.getParameter("Email");
		String b = request.getParameter("Password");
		
		session.setAttribute("ownerMail", a);
		String c = "Accepted";
		PrintWriter out = response.getWriter();  		
		try {			
			Connection conn = Dbconn.getconnection();
			String qry = "select * from saloon_owner where email='"+a+"'and password='"+b+"' and status='"+c+"'";
			PreparedStatement ps = conn.prepareStatement(qry);
			ResultSet rs = ps.executeQuery();			
			if(rs.next())  {
				out.print("<head><script>alert('Saloon - Owner login successful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("saloonhome.jsp");
				dd.include(request, response);
			}else {
				out.print("<head><script>alert('Saloon - Owner login unsuccessful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("index.html");
				dd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
