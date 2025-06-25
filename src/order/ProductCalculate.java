package order;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Dbconn;

@WebServlet("/ProductCalculate")
public class ProductCalculate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			double sum=0,quantity=0,price=0,total=0,balance=0,initial=0;
			Connection con=Dbconn.getconnection();
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("userEmail");
			
			String qry = "select * FROM temp_order WHERE email = '"+email+"'";
			PreparedStatement ps = con.prepareStatement(qry);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				String qua=rs.getString(9);
				quantity=Double.parseDouble(qua);
				String pro=rs.getString(5);
				price=Double.parseDouble(pro);
				total=quantity*price;
				sum=sum+total;
			}
			
			initial=0.10*sum;
			balance=sum-initial;
			
			String totalAmount=String.valueOf(sum);
			String initialAmount=String.valueOf(initial);
			String balanceAmount=String.valueOf(balance);
			
			
			request.setAttribute("totalAmount", totalAmount);
			request.setAttribute("initialAmount", initialAmount);
			request.setAttribute("balanceAmount", balanceAmount);
			
			request.getRequestDispatcher("mesg.jsp").forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
