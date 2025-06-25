package client;

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

@WebServlet("/ClientLogin")
public class ClientLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		try
		{
			String mail=request.getParameter("Email");
			String pwd=request.getParameter("Password");
			
			Connection con=Dbconn.getconnection();
			String lo="Select * from client_register where mail='"+mail+"'and password='"+pwd+"'";
			
			PreparedStatement ps = con.prepareStatement(lo);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				 HttpSession session = request.getSession();
				 session.setAttribute("userEmail", mail);
				out.print("<head><script>alert('Client - Login Successful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("eco_homepage.jsp");
				dd.include(request, response);
			}else
			{
				out.print("<head><script>alert('Client - Login Unsuccessful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("index.html");
				dd.include(request, response);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}	}
}
