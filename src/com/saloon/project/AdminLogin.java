package com.saloon.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();   
		String em = request.getParameter("Email");
		String pass = request.getParameter("Password");
		String us = "admin";
		String pss = "admin";
		
		if(em.equalsIgnoreCase(us) && pass.equalsIgnoreCase(pss)) {
		   out.print("<html><body><script>alert('Admin - Login Successful');</script></body></html>");
		  RequestDispatcher rs = request.getRequestDispatcher("adminhomepage.jsp");
		  rs.include(request, response); 
		}else {
		   out.print("<html><body><script>alert('Admin - Login unsuccessful');</script></body></html>");
		  RequestDispatcher rs = request.getRequestDispatcher("admin.html");
		  rs.include(request, response); 
		}
	
	}

}
