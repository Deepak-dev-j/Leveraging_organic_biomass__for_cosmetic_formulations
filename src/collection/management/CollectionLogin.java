package collection.management;

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

@WebServlet("/CollectionLogin")
public class CollectionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("Email");
		HttpSession session=request.getSession();
		session.setAttribute("cmmail", a);
		String b = request.getParameter("Password");
		String c = "Accepted";
		PrintWriter out = response.getWriter();  
		
		try {
			
			Connection conn = Dbconn.getconnection();
			String qry = "select * from collection_management where emp_mail='"+a+"'and password='"+b+"' and emp_status='"+c+"'";
			PreparedStatement ps = conn.prepareStatement(qry);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())  {
				//HttpSession session = request.getSession();
				//String a1 = rs.getString(1);
				//session.setAttribute("id",a1);
				out.print("<head><script>alert('Collection Management - Login Successful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("collection.jsp");
				dd.include(request, response);
			}else {
				out.print("<head><script>alert('Collection Management - Login unsuccessful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("index.html");
				dd.include(request, response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
