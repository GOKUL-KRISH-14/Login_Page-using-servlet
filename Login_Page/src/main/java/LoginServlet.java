import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		
		String url = "jdbc:mysql://localhost:3306/_Gokul";
        String username = "root";
        String pass = "gokul@14";
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
	        
	    // Hardcoded credentials for validation
	    String nameCheck = "admin";
	    String passCheck = "admin";
	    
	    // Set content type
	    res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        try
        {
        	 // Create a connection object and prepare the statement
    		Connection conn = DriverManager.getConnection(url, username, pass);
        	
	        // Check if email and password are not null to avoid NullPointerException
	        if (email != null && password != null) 
	        {
	            if (email.equals(nameCheck) && password.equals(passCheck)) 
	            {
	            	 // SQL insert query
	            	 String insertSQL = "INSERT INTO login VALUES (?, ?);";
	                 PreparedStatement stmt = conn.prepareStatement(insertSQL);
	                 stmt.setString(1,email);
	                 stmt.setString(2,password);
	                 stmt.executeUpdate();
	                 res.sendRedirect("login.html");
	            }
	            else 
	            {
	                out.print("<h3>Login Unsuccessful</h3>");
	                res.sendRedirect("index.html");
	            }
	        }
	        
	        else
	        {
	            out.print("<h3>Invalid input. Please provide both email and password.</h3>");
	        }
	        
        }
        catch(Exception ae)
        {
        	 out.print("<h3>EXCEPTION OCCURS....</h3>");
        }
        out.close();
	}
	
}
        

            

