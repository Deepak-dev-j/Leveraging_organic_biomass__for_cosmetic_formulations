package order;

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

@WebServlet("/TempOrder")
public class TempOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		try
		{
			Connection con=Dbconn.getconnection();
			String pro_id=request.getParameter("id");
			String pro_name=request.getParameter("productName");
			String pro_dec=request.getParameter("productdec");
			String pro_type=request.getParameter("productType");
			String total=request.getParameter("price");
			String pro_volume=request.getParameter("vol");
			String pro_ingrediants=request.getParameter("ingredients");
			//String pro_purpose=request.getParameter("type");
			String pro_path=request.getParameter("img");
			String pro_quantity=request.getParameter("qua");
			String pro_email=request.getParameter("email");
			
			/*int a=Integer.parseInt(pro_quantity);
			int b=Integer.parseInt(total);
			int c=a*b;
			String z=String.valueOf(c);*/
			
			PreparedStatement ps=con.prepareStatement(
				"insert into temp_order (id,name,decc,type,total,volume,ingrediants,path,quantity,email) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, pro_id);
			ps.setString(2, pro_name);
			ps.setString(3, pro_dec);
			ps.setString(4, pro_type);
			ps.setString(5, total);
			ps.setString(6, pro_volume);
			ps.setString(7, pro_ingrediants);
			//ps.setString(8, pro_ingrediants);
			ps.setString(8, pro_path);
			ps.setString(9,  pro_quantity);
			ps.setString(10, pro_email);
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				 out.print("<html><body><script>alert('Added To Cart SucessFully');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("eco_homepage.jsp");
				 dd.include(request, response);
			}else
			{
				 out.print("<html><body><script>alert('Cannot Add To Cart');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("viewmore.jsp");
				 dd.include(request, response);
			}			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
