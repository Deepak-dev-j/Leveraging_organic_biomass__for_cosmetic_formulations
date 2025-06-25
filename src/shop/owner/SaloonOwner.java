package shop.owner;

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

@WebServlet("/SaloonOwner")
public class SaloonOwner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter out = response.getWriter();  
		
		try
		{			
			
			int min = 10000; 
		    int max = 99999; 
		    int ss = (int)Math.floor(Math.random() * (max - min + 1) + min);
		    
			String name=request.getParameter("Name");
			String mail=request.getParameter("Email");
			String number=request.getParameter("Phone");
			String shopname=request.getParameter("shopname");
			String address=request.getParameter("shopaddress");
			String status="pending";
			String pwd=String.valueOf(ss);
			
			Connection con=Dbconn.getconnection();
			PreparedStatement ps=con.prepareStatement(
				"insert into saloon_owner (name,email,phone_number,shop_name,shop_Address,password,status) values(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, number);
			ps.setString(4, shopname);
			ps.setString(5, address);
			ps.setString(6, pwd);
			ps.setString(7, status);
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				 out.print("<html><body><script>alert('Saloon - Owner Registred Successful');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("saloonowner.jsp");
				dd.include(request, response);
			}else
			{
				 out.print("<html><body><script>alert('Saloon - Owner Registred unsuccessful');</script></body></html>");
				RequestDispatcher dd=request.getRequestDispatcher("saloonowner.jsp");
				dd.include(request, response);
			}			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
