package admin;

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


@WebServlet("/PayAmount")
public class PayAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			PrintWriter out=response.getWriter();
			String amount=request.getParameter("total");
			String status="paid";
			Connection conn=Dbconn.getconnection();
			
			String qry="update employee_status set payment_status='"+status+"'";
			
			PreparedStatement ps=conn.prepareStatement(qry);
			
			int x=ps.executeUpdate();
			
			if(x>0)
			{
				out.print("<head><script>alert('Amount Sent successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
				dd.include(request, response);
			}
			else
			{
				out.print("<head><script>alert('Amount Not Sent successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
				dd.include(request, response);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
