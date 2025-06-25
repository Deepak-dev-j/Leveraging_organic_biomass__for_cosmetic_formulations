package collection.management;

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
import javax.servlet.http.HttpSession;

import dbconnection.Dbconn;

@WebServlet("/CollectionUpload")
public class CollectionUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session=request.getSession();
			String mail=(String) session.getAttribute("cmmail");
			PrintWriter out=response.getWriter();
			Connection conn = Dbconn.getconnection();
			String path="E:/Project1-Saloon/Salon clients and owners keratin management processing";
    		String newPath=path+"//WebContent//dataset//collection.csv";
    		
    		String qry =  "LOAD DATA INFILE '"+newPath+"'" +
        	        "INTO TABLE calculation FIELDS TERMINATED BY ','" +
        	        "OPTIONALLY ENCLOSED by '\"'" +"IGNORE 1 LINES "+"set mail_id=?";
			PreparedStatement st= conn.prepareStatement(qry);
			st.setString(1, mail);
			int i = st.executeUpdate();
			 
			if(i>0) {
				out.print("<head><script>alert('Collection Management DataSet uploaded successfully');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("collection.jsp");
				dd.include(request, response);
			}
			else {
				out.print("<head><script>alert('Collection Management DataSet upload unsuccessful');</script></head>");
				RequestDispatcher dd=request.getRequestDispatcher("collection.jsp");
				dd.include(request, response);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
