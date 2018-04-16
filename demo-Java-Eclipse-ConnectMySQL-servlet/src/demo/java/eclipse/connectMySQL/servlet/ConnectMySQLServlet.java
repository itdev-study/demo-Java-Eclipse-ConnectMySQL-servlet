package demo.java.eclipse.connectMySQL.servlet;

import java.io.*;

import java.sql.*;
import javax.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectMySQLServlet
 */
@WebServlet("/ConnectMySQLServlet")
public class ConnectMySQLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectMySQLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("Test Connection to MySQL");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading jdbc driver");
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost/testdb";
		String user = "testdbuser";
		String password = "dbpass";
		
		Connection con =null;
		try {
			con=DriverManager.getConnection(url, user, password);
			System.out.println("Connection to DB created");
			
			Statement stat = con.createStatement();
			System.out.println("Statement created");
			
			PrintWriter out = response.getWriter();
			
			//String insertSQL = "insert into items values (1, 'TEST-555')";			
			//statment.executeUpdate(insertSQL);		
			
			String sql;
		    sql = "SELECT id, name FROM items";
		    ResultSet rs = stat.executeQuery(sql);
			
		    while(rs.next()){		       
		         int id  = rs.getInt("id");
		         String name = rs.getString("name");  
		         System.out.print("ID: " + id);		        
		         System.out.println("Name" + name);
		         out.print("ID: " + id + "   ");
		         out.println("Name: " + name);
		    }		      
		    rs.close();
		    stat.close();
		    con.close();
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
