package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	//create query
	private static final String INSERT_QUERY =
			"INSERT INTO userdetail(name,email,address,password,mobile) VALUES(?,?,?,?,?)"; 
			
//	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the form data
		
		String name =request.getParameter("name");
		String email =request.getParameter("email");
		String mobile =request.getParameter("mobile");
		String address =request.getParameter("address");
		String password =request.getParameter("password");
		//load the jdbc driver
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//connection
		try{
			Connection con=DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/onlinemeal", "postgres", "password");
					PreparedStatement ps= con.prepareStatement(INSERT_QUERY);
			//set the values
				
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, address);
			ps.setString(4, password);
			ps.setString(5, mobile);
			System.out.println(ps.toString());
			//execute the query
			int count= ps.executeUpdate();
//			if(count==0) {
//				System.out.println("record not submitted");
//			}
//			else {
//				System.out.println("record submitted");
//			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
				
		//pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
