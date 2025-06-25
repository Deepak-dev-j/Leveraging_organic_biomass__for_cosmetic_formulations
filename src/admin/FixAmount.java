package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Dbconn;

@WebServlet("/FixAmount")
public class FixAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			HttpSession session = request.getSession();
			PrintWriter out=response.getWriter();
			Connection conn=Dbconn.getconnection();
			String email=(String)session.getAttribute("ownerMail");
			String amount=request.getParameter("amount");
			session.setAttribute("amount",amount);
			String batch=null;
			
			String update="select * from hair_dataset where id='"+email+"'";
			PreparedStatement ps=conn.prepareStatement(update);
			ResultSet x=ps.executeQuery();
			
			while(x.next()) {
				batch=x.getString(9);
			}
			
			PreparedStatement ps1=conn.prepareStatement("insert into fixed_amount(id,batch,amount) values(?,?,?)");
			ps1.setString(1, email);;
			ps1.setString(2, batch);
			ps1.setString(3, amount);
			int y=ps1.executeUpdate();
			
			if(y>0)
			{
				out.print("<head><script>alert('Amount Request Sent successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
				dd.include(request, response);
			}
			else
			{
				out.print("<head><script>alert('Amount Request Not Sent successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
				dd.include(request, response);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
