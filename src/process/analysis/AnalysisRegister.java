package process.analysis;

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

@WebServlet("/AnalysisRegister")
public class AnalysisRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();  
		
		try
		{		
			int min = 10000; 
		    int max = 99999; 
		    int ss = (int) Math.floor(Math.random() * (max - min + 1) + min);
			
			String name=request.getParameter("empName");
			String mail=request.getParameter("empEmail");
			String number=request.getParameter("empPhone");
			String id=request.getParameter("empid");
			String status="pending";	
			String pwd=String.valueOf(ss);
			
			Connection con=Dbconn.getconnection();
			PreparedStatement ps=con.prepareStatement(
				"insert into process_analysis (empname,empmail,empphone,empid,password,empstatus) values(?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, number);
			ps.setString(4, id);
			ps.setString(5, pwd);
			ps.setString(6, status);
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				 out.print("<html><body><script>alert('Process Analysis - Employee Registred Successful');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("index.html");
					dd.include(request, response);
			}else
			{
				 out.print("<html><body><script>alert('Process Analysis - Employee Registred unsuccessful');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("index.html");
					dd.include(request, response);
			}	
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
