package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		//read the data
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		
		//load the driver
		try {
			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		//connection
		try {
			Connection con=DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/onlinemeal", "postgres", "password");
			Statement stm= con.createStatement();
			ResultSet rs=stm.executeQuery("Select * from userdetail where email='"+email+"' and password='"+password+"'");
			if(rs.next()) {
				//if details are correct
				resp.sendRedirect("index.html");
			
			}
			else {
				System.out.println("wrong details");
			}
			//close connection
			con.close();

		}catch(SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
