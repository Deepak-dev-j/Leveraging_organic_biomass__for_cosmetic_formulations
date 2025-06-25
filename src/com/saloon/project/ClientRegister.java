package com.saloon.project;

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
@WebServlet("/ClientRegister")
public class ClientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		
		try
		{
			
			
			String name=request.getParameter("Name");
			String mail=request.getParameter("Email");
			String number=request.getParameter("Phone");
			String pwd=request.getParameter("Password");
			String cpwd=request.getParameter("confirmPassword");
			
			
			
			Connection con=Dbconn.getconnection();
			PreparedStatement ps=con.prepareStatement(
				"insert into client_register (username,mail,phone_number,password,confirm_password) values(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, number);
			ps.setString(4, pwd);
			ps.setString(5,cpwd);
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				 out.print("<html><body><script>alert('Client - Registred Successful');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("index.html");
	 			 dd.include(request, response);
			}else
			{
				 out.print("<html><body><script>alert('Client - Registred unsuccessful');</script></body></html>");
				 RequestDispatcher dd=request.getRequestDispatcher("index.html");
	 			 dd.include(request, response);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
