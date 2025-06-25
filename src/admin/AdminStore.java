package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.Dbconn;

@WebServlet("/AdminStore")
public class AdminStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try {
    		PrintWriter out = response.getWriter();
    		Connection conn = Dbconn.getconnection();
    		String path="E:/Project1-Saloon/Salon clients and owners keratin management processing";
    		String newPath=path+"//WebContent//dataset//salonproject.csv";
        	String qry =  "LOAD DATA INFILE '"+newPath+"'" +
        	        "INTO TABLE admin_files FIELDS TERMINATED BY ','" +
        	        "OPTIONALLY ENCLOSED by '\"'" +"IGNORE 1 LINES";
			PreparedStatement st= conn.prepareStatement(qry);
			int i = st.executeUpdate();
			 
			if(i>0) {
				
				out.print("<head><script>alert('Product Details - uploaded successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminhomepage.jsp");
				dd.include(request, response);
			}
			
			else {
				out.print("<head><script>alert('Product Details - uploaded unsuccessful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("adminfileupload.jsp");
				dd.include(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
