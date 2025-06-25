/*package process.analysis;

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

import dbconnection.Dbconn;

@WebServlet("/KertainReport")
public class KertainReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			PrintWriter out=response.getWriter();
			double result=0;
			double hydrogen=1.008;
			double nitrogen=14.01;
			double oxygen=16.00;
			double carbon=12.01;
			double sulfer=32.07;
			String boiling=null;
			String h=null;
			
			
			String ker=null,hair=null,batch=null,id=null;
			Connection conn=Dbconn.getconnection();
			String report="select * from accepted_report";
			PreparedStatement ps=conn.prepareStatement(report);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				id=rs.getString(1);
				ker=rs.getString(4);
				hair=rs.getString(2);
				batch=rs.getString(5);
			}
			double MolecularWeight=(10*carbon)+(16*hydrogen)+(2*nitrogen)+(4*oxygen)+(2*sulfer);
			String kertainMolecular=Double.toString(MolecularWeight);
			double kertain=Double.parseDouble(ker);
			double amountOfILNeeded=0.01*kertain*0.01;
			double amountOfILNeededInKg = amountOfILNeeded / 1000.0;
			double hairQuantity=Double.parseDouble(hair);
			double amountOfMolecule=0.9;
			double ratio=hairQuantity*amountOfMolecule;
			String kertainIG=Double.toString( amountOfILNeededInKg);
			String proteinBefore=Double.toString(ratio);
			String apperance,solublity;
			String p="select * from ionic_liquid_kertain";
			
			PreparedStatement ps1=conn.prepareStatement(p);
			ResultSet rs1=ps1.executeQuery();
			int x=0;
			String name=null;
			while(rs1.next())
			{
				boiling=rs1.getString(4);
				name=rs1.getString(2);
				double temp=Double.parseDouble(boiling);
				double value=temp/10;
				
				
				if(temp>=20 && temp<=29)
				{
					result=kertain*(value)/1000;
					apperance="Shiny, smooth";
					solublity="Intact";	
					h="Normal strength";
				}
				else if(temp>=30 && temp <=39)
				{
					result=kertain*(value)/1000;
					apperance="Glossy, strong";
					solublity="Slightly ";
					h="Normal elasticity";
				}
				else if(temp>=40 && temp <=49)
				{
					result=kertain*(value)/1000;
					apperance="Slightly cloudy, soft";
					solublity="Moderately soluble";		
					h="Slight softening";
				}
				else if(temp>=50 && temp <=59)
				{
					result=kertain*(value)/1000;
					apperance="Cloudy, weaker";
					solublity="Soluble";	
					h="Reduced strength";
				}
				else if(temp>=60 && temp <=69)
				{
					result=kertain*(value)/1000;
					apperance="Rough, opaque";
					solublity="Highly ";	
					h="Decreased elasticity";
				}
				else if(temp>=70 && temp <=80)
				{
					result=kertain*(value)/1000;
					apperance="Brittle, translucent";
					solublity="Fully soluble";	
					h="Brittle, weak";
				}
				else
				{
					apperance="Not Valid";
					solublity="Not Valid";	
				}
				String res=Double.toString(result);
				PreparedStatement ps2=conn.prepareStatement("insert into kertain_report (batch,initial_protein,temperature,remaining_protein,solublity,apperance,result,formula_name,id) values (?,?,?,?,?,?,?,?,?)");
				
				ps2.setString(1, batch);
				ps2.setString(2, proteinBefore);
				ps2.setString(3, boiling);
				ps2.setString(4, res);
				ps2.setString(5, solublity);
				ps2.setString(6, apperance);
				ps2.setString(7, h);
				ps2.setString(8, name);
				ps2.setString(9, id);
				x=ps2.executeUpdate();
				
			}
			if(x>0)
			{
				out.print("<head><script>alert('Your Process On Data Have Done Sucessfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("processanalysishome.jsp");
				dd.include(request, response);
				
			}else
			{
				out.print("<head><script>alert('Your Process On Data Does Not Calculated');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
				dd.include(request, response);
			}		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}*/
