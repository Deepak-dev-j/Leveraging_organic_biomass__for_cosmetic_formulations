package order;

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

@WebServlet("/PaymentOrder")
public class PaymentOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		try
		{
			int res=0;
			PrintWriter out=response.getWriter();
			HttpSession session = request.getSession();
			Connection conn=Dbconn.getconnection();
			String total_amount=(String) session.getAttribute("totalAmount");
			String total=request.getParameter("total");
				try
				{
					
					String card_no=request.getParameter("cardnumber");
					String expiration = request.getParameter("expiration");
					String name=request.getParameter("name");
					String product_name=(String) session.getAttribute("product_name");
					String product_quantity=(String) session.getAttribute("product_quantity");
					
					String mail=(String) session.getAttribute("userEmail");
					int min = 10000; 
				    int max = 99999; 
				    int ss = (int)Math.floor(Math.random() * (max - min + 1) + min);
				    String ordid="ORD_ID"+ss;
				    
				    String sql = "SELECT name, quantity FROM temp_order where email='"+mail+"'";
				    PreparedStatement ps = conn.prepareStatement(sql);
		            ResultSet rs = ps.executeQuery();
		            
		            
		            rs.last();
		            int rowCount = rs.getRow();
		            rs.beforeFirst();
		            String[] productNames = new String[rowCount];
		            String[] quantities = new String[rowCount];
		            int index = 0;
		            
		            while (rs.next()) {
		                productNames[index] = rs.getString("name");
		                quantities[index] = rs.getString("quantity");
		                index++;
		            }
		            
		            PreparedStatement ps2=conn.prepareStatement(
			        		"insert into payment (order_id, email,date, account_number,total_amount) VALUES (?, ?, ?,?,?)");
			        ps2.setString(1, ordid);
					ps2.setString(2, mail);
					ps2.setString(4, card_no);
					ps2.setString(3, expiration);
					ps2.setString(5, total_amount);        
			        
					int x=ps2.executeUpdate();
					if(x>0)
					{
						 out.print("<html><body><script>alert('payment Successful');</script></body></html>");
						
					}else
					{
						 out.print("<html><body><script>alert('Payment unsuccessful');</script></body></html>");
						 RequestDispatcher dd=request.getRequestDispatcher("eco_homepage.jsp");
						dd.include(request, response);
					}
		            
		            String insertSql = "INSERT INTO permanent_order (order_id, email,product_name, product_quantity) VALUES (?, ?, ?,?)";
		            PreparedStatement ps3 = conn.prepareStatement(insertSql);
		            
		            for (int i = 0; i < productNames.length; i++) {
		                ps3.setString(1, ordid);
		                ps3.setString(2, mail);
		                ps3.setString(3, productNames[i]); 
		                ps3.setString(4, quantities[i]); 
		              res=  ps3.executeUpdate(); 
		            }
		            
		            if(res>0)
		            {
		            	String del="delete from temp_order where email='"+mail+"'";
		            	PreparedStatement delete_temp = conn.prepareStatement(del);
		            	int rowsAffected = delete_temp.executeUpdate();
		            	out.print("<head><script>alert('Your Order Have Been Placed Sucessfully ');</script></head>");
		            	RequestDispatcher dd=request.getRequestDispatcher("eco_homepage.jsp");
						dd.include(request, response);
		            }
		            else
		            {
		            	out.print("<head><script>alert('Your Order Not Accepted');</script></head>");
		            	RequestDispatcher dd=request.getRequestDispatcher("eco_homepage.jsp");
		            	dd.include(request, response);
		            }
		            
				}catch(Exception e)
				{
					e.printStackTrace();
				}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
