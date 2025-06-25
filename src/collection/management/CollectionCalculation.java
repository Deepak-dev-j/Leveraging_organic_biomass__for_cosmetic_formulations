package collection.management;

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

@WebServlet("/CollectionCalculation")
public class CollectionCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			String hairmail=(String)session.getAttribute("ownerMail");
			String collectionmail=(String)session.getAttribute("cmmail");
			Connection conn=Dbconn.getconnection();
			String batch=(String)session.getAttribute("batch");
			String pbatch=null,em=null;
			String qry="select * from hair_dataset where id='"+hairmail+"'";
			PreparedStatement ps=conn.prepareStatement(qry);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				pbatch=rs.getString(9);
 				em=rs.getString(1);
				String hairInKg=rs.getString(3);
				int hairquantity=Integer.parseInt(hairInKg);
				int hairGram=hairquantity*1000;
				
				String cm="select * from calculation where mail_id='"+collectionmail+"'";
				PreparedStatement ps1=conn.prepareStatement(cm);
				ResultSet rs1=ps1.executeQuery();
				
				
				while(rs1.next())
				{
					String mratio=rs1.getString(2);
					double melaninratio=Double.parseDouble(mratio);
					
					String kratio=rs1.getString(3);
					double kertainratio=Double.parseDouble(kratio);
					
					String eamount=rs1.getString(4);
					int ethanolamount=Integer.parseInt(eamount);
					
					String h2amount=rs1.getString(5);
					int h202amount=Integer.parseInt(h2amount);
					
					String naamount=rs1.getString(6);
					int naohamount=Integer.parseInt(naamount);
					
					double initial_melanin=hairGram*melaninratio;
					String im=Double.toString(initial_melanin);
					
					double initial_kertain=hairGram*kertainratio;
					String ik=Double.toString(initial_kertain);
					
					double melaninYield = (hairGram * melaninratio / ethanolamount) * h202amount;
					 
					double kertainyield = (hairGram * kertainratio / ethanolamount) * naohamount;
					 
					String my=Double.toString(melaninYield);
					String ky=Double.toString(kertainyield);
					String hair=Integer.toString(hairquantity);
					
					PreparedStatement ps2=conn.prepareStatement("insert into collection_result (mail_id,initial_melanin,initial_kertain,melanin_yield,kertain_yield,amount_of_hair,batch_details) values (?,?,?,?,?,?,?)");
					 ps2.setString(1, em);
					 ps2.setString(2, im);
					 ps2.setString(3, ik);
					 ps2.setString(4, my);
					 ps2.setString(5, ky);
					 ps2.setString(6, hair);
					 ps2.setString(7, pbatch);
					
					 int x=ps2.executeUpdate();
				}
			}	
			String totalcalc="SELECT * from collection_result where mail_id='"+collectionmail+"'";
			PreparedStatement pst=conn.prepareStatement(totalcalc);
			double totalMelanin=0.0,totalKeratin=0.0;
			int totalhair=0;
			ResultSet io=pst.executeQuery();
			while(io.next())
			{
				String mel=io.getString(4);
				String ker=io.getString(5);
				totalMelanin +=Double.parseDouble(mel);
				totalKeratin +=Double.parseDouble(ker); 				
			}
			String melanineSum=Double.toString(totalMelanin);
			String kertainSum=Double.toString(totalKeratin);
			String upd="update collection_result set batch_details='"+pbatch+"'where mail_id='"+collectionmail+"'";
             
             PreparedStatement q=conn.prepareStatement(upd);
             
             int y=q.executeUpdate();
             if(y>0)
 			{
 				out.print("<head><script>alert('Performed Calculation');</script></head>");
 				RequestDispatcher dd=request.getRequestDispatcher("collection.jsp");
 				dd.include(request, response);
 			}else
 			{
 				out.print("<head><script>alert('No Data Available To Perform Operation');</script></head>");
 				RequestDispatcher dd=request.getRequestDispatcher("collection.jsp");
 				dd.include(request, response);
 			}	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
