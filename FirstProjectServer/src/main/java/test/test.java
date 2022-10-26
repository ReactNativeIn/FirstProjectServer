package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jdbc.JDBCUtil;

@WebServlet("/test")
public class test extends HttpServlet {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	String select = "select * from member where email = ?";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청 수신");
		String a =request.getParameter("email");
		System.out.println(a);
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(select);
			stmt.setString(1, a);
			rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		System.out.println(request.getParameter("email"));
		doGet(request, response);
	}

}
