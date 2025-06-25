package collection.management;

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

import org.apache.struts2.dispatcher.Dispatcher;

import dbconnection.Dbconn;

@WebServlet("/ConfirmReport")
public class ConfirmReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			PrintWriter out=response.getWriter();
			HttpSession s=request.getSession();
			String mail=(String)s.getAttribute("ownerMail");
			String status="Accepted";
			String batch=(String)s.getAttribute("batch");
			Connection conn=Dbconn.getconnection();
			Double melanin=(Double) s.getAttribute("totalMelanin");
			Double kertain=(Double)s.getAttribute("totalKeratin");
			Integer hair=(Integer)s.getAttribute("totalhair");
			String mel=Double.toString(melanin);
			String ker=Double.toString(kertain);
			String hairkg=Integer.toString(hair);
			
			PreparedStatement ps=conn.prepareStatement("insert into accepted_report (id,total_hair_kg,total_melanin,total_kertain,batch,status) values(?,?,?,?,?,?)");
			ps.setString(1, mail);
			ps.setString(2, hairkg);
			ps.setString(3, mel);
			ps.setString(4, ker);
			ps.setString(5, batch);
			ps.setString(6, status);
			int rs=ps.executeUpdate();
			
			if(rs>0)
			{
				 out.print("<html><body><script>alert('Report Accepted Sucessfully');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
					dd.include(request, response);
			
			}else
			{
				 out.print("<html><body><script>alert('Report Not Accepted');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
					dd.include(request, response);
			}	
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
