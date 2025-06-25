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
import javax.servlet.http.HttpSession;

import dbconnection.Dbconn;

@WebServlet("/AcceptAmount")
public class AcceptAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			PrintWriter out=response.getWriter();
			
			String status="Accepted";
			HttpSession s=request.getSession();
			Connection conn=Dbconn.getconnection();
			String name=request.getParameter("name");
			String email=request.getParameter("Email");
			String accno=request.getParameter("accnumber");
			String batch=request.getParameter("batch");
			String amount=(String)s.getAttribute("amount");
			String payment="not paid";
			
			PreparedStatement ps=conn.prepareStatement("insert into employee_status (id,batch,amount,status,account_number,name,payment_status) values (?,?,?,?,?,?,?)");
			ps.setString(1, email);
			ps.setString(2, batch);
			ps.setString(3, amount);
			ps.setString(4, status);
			ps.setString(5, accno);
			ps.setString(6, name);
			ps.setString(7, payment);
			
			int x=ps.executeUpdate();
			
			if(x>0)
			{
				out.print("<head><script>alert('Your Messeage Sent successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("saloonhome.jsp");
				dd.include(request, response);
			}else
			{
				out.print("<head><script>alert('Your Messeage Not Sent');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("saloonhome.jsp");
				dd.include(request, response);
			}			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
