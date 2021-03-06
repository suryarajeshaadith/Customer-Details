package org.TestDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class TestDb
 */
@WebServlet("/TestDb")
public class TestDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl ="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String user="springstudent";
		String pass="springstudent";
		String driver="com.mysql.jdbc.Driver";
		
		
		try
		{
			PrintWriter out=response.getWriter();
			out.println("Connect to database:"+jdbcUrl);
			Class.forName(driver);
			Connection myConn = (Connection) DriverManager.getConnection(jdbcUrl,user,pass);
			
			System.out.println("Connection sucessfull...!!!");
			
			myConn.close();
		}
		
		catch(Exception exe){
			
			exe.printStackTrace();
			throw new ServletException(exe);
		}

	}

}
