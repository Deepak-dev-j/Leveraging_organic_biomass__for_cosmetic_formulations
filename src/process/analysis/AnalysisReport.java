package process.analysis;

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

@WebServlet("/AnalysisReport")
public class AnalysisReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			int x=0;
			String colour=null;
			String pos=null;
			double atomic=12.0;
			double hydrogen=1.008;
			double nitrogen=14.01;
			double oxygen=16.00;
			double carbon=12.01;
			double sulfer=32.07;	
			String melaninFormula="C6H11NO2";
			
			PrintWriter out=response.getWriter();
			String mel=null,ker=null,hair=null,batch=null,id=null;
			Connection con=Dbconn.getconnection();
			String report="select * from accepted_report";
			PreparedStatement ps=con.prepareStatement(report);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				id=rs.getString(1);
				mel=rs.getString(3);
				ker=rs.getString(4);
				hair=rs.getString(2);
				batch=rs.getString(5);
			}
			double melanine=Double.parseDouble(mel);
			
			double melanineInMl=melanine*1000;
			double melIG=melanineInMl*(10.0/1000.0);
			double melIGInKg = melIG / 1000.0;				
			
			double melaninmolecularWeight=(6*atomic)+(11*hydrogen)+(nitrogen)+(2*oxygen);
			String molecularWeightOfMelanin=Double.toString(melaninmolecularWeight);
			
			String AmountIG=Double.toString(melIGInKg);
			
			
			Connection conn=Dbconn.getconnection();			
			
			String heat="select * from ionic_liquid_melanin";
			
			PreparedStatement ps2=con.prepareStatement(heat);
			ResultSet rs1=ps2.executeQuery();
			String temp=null;
			double result;
			double res=0;
			String name=null;
			
			while(rs1.next())
			{
				temp=rs1.getString(4);
				name=rs1.getString(2);
				res=Double.parseDouble(temp);
				result=Math.round((melanineInMl/res)/1000.0);
				if(res>=60 && res<=69)
				{
					colour="Colorless, transparent liquid.";
					pos="appearance and properties are stable and uniform ";
				}
				else if(res>=70 && res<=79)
				{
					colour="No specific appearance noted ";
					pos="appearance and properties are stable and uniform ";
				}
				else if(res>=80 && res<=89)
				{
					colour="Slight yellow tint observed.";
					pos="requires a specific color or minimal odor ";
				}
				else if(res>=90 && res<=100)
				{
					colour="Pale yellow coloration observed.";		
					pos="preferable for viscosity ";
				}
				else
				{
					colour="no clue";
					pos="no clue";
;				}
				PreparedStatement ps1=conn.prepareStatement("insert into melanin_report (batch,melanin_ionic_amount,molecular_weight_melanin,melanin_temperature,appreance,result,formula_name,id) values (?,?,?,?,?,?,?,?)");
				
				ps1.setString(1, batch);
				ps1.setString(2, AmountIG);
				ps1.setString(3, molecularWeightOfMelanin);
				ps1.setString(4, temp);
				ps1.setString(5, colour);
				ps1.setString(6, pos);
				ps1.setString(7, name);
				ps1.setString(8, id);
				x=ps1.executeUpdate();
			}
			
			//kertain process
			double result1=0;
			double hydrogen1=1.008;
			double nitrogen1=14.01;
			double oxygen1=16.00;
			double carbon1=12.01;
			double sulfer1=32.07;
			String boiling1=null;
			String h=null;
			
			int q=0;
			String ker1=null,hair1=null,batch1=null,id1=null;
			String report1="select * from accepted_report";
			PreparedStatement ps3=conn.prepareStatement(report1);
			ResultSet rs2=ps.executeQuery();
			
			while(rs2.next())
			{
				id1=rs2.getString(1);
				ker1=rs2.getString(4);
				hair1=rs2.getString(2);
				batch1=rs2.getString(5);
			}
			double MolecularWeight=(10*carbon1)+(16*hydrogen1)+(2*nitrogen1)+(4*oxygen1)+(2*sulfer1);
			String kertainMolecular=Double.toString(MolecularWeight);
			double kertain=Double.parseDouble(ker1);
			double amountOfILNeeded=0.01*kertain*0.01;
			double amountOfILNeededInKg = amountOfILNeeded / 1000.0;
			double hairQuantity=Double.parseDouble(hair);
			double amountOfMolecule=0.9;
			double ratio=hairQuantity*amountOfMolecule;
			String kertainIG=Double.toString( amountOfILNeededInKg);
			String proteinBefore=Double.toString(ratio);
			String apperance,solublity;
			String p="select * from ionic_liquid_kertain";
			PreparedStatement ps11=conn.prepareStatement(p);
			ResultSet rs3=ps11.executeQuery();
			int y=0;
			String name1=null;
			while(rs3.next())
			{
				boiling1=rs3.getString(4);
				name1=rs3.getString(2);
				double temp1=Double.parseDouble(boiling1);
				double value=temp1/10;
				
				
				if(temp1>=20 && temp1<=29)
				{
					result1=kertain*(value)/1000;
					apperance="Shiny, smooth";
					solublity="Intact";	
					h="Normal strength";
				}
				else if(temp1>=30 && temp1 <=39)
				{
					result1=kertain*(value)/1000;
					apperance="Glossy, strong";
					solublity="Slightly ";
					h="Normal elasticity";
				}
				else if(temp1>=40 && temp1 <=49)
				{
					result1=kertain*(value)/1000;
					apperance="Slightly cloudy, soft";
					solublity="Moderately soluble";		
					h="Slight softening";
				}
				else if(temp1>=50 && temp1 <=59)
				{
					result1=kertain*(value)/1000;
					apperance="Cloudy, weaker";
					solublity="Soluble";	
					h="Reduced strength";
				}
				else if(temp1>=60 && temp1 <=69)
				{
					result1=kertain*(value)/1000;
					apperance="Rough, opaque";
					solublity="Highly ";	
					h="Decreased elasticity";
				}
				else if(temp1>=70 && temp1 <=80)
				{
					result1=kertain*(value)/1000;
					apperance="Brittle, translucent";
					solublity="Fully soluble";	
					h="Brittle, weak";
				}
				else
				{
					apperance="Not Valid";
					solublity="Not Valid";	
				}
				String res1=Double.toString(result1);
				PreparedStatement ps21=conn.prepareStatement("insert into kertain_report (batch,initial_protein,temperature,remaining_protein,solublity,apperance,result,formula_name,id) values (?,?,?,?,?,?,?,?,?)");
				
				ps21.setString(1, batch1);
				ps21.setString(2, proteinBefore);
				ps21.setString(3, boiling1);
				ps21.setString(4, res1);
				ps21.setString(5, solublity);
				ps21.setString(6, apperance);
				ps21.setString(7, h);
				ps21.setString(8, name1);
				ps21.setString(9, id);
				q=ps21.executeUpdate();				
			
		}
			if(q>0)
			{
				out.print("<head><script>alert('Your Process On Data Have Done Sucessfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("processanalysishome.jsp");
				dd.include(request, response);
				
			}else
			{
				out.print("<head><script>alert('Your Process On Data Does Not Calculated');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("processanalysishome.jsp");
				dd.include(request, response);
			}		
			}catch(Exception e)
		{
			e.printStackTrace();
		}	
}
}
