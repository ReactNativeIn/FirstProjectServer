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

import com.fasterxml.jackson.databind.ObjectMapper;

import jdbc.JDBCUtil;
import util.ServletUtil;
import vo.MemberVO;
import vo.impl.MemberServiceImpl;

@WebServlet("/test")
public class test extends HttpServlet {
	
	ObjectMapper objectMapper = new ObjectMapper();
	MemberVO member = new MemberVO();
	MemberServiceImpl service = new MemberServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtil.setting(request, response);
		
		System.out.println("요청 수신");
		member.setEmail(request.getParameter("email"));

		member = service.getMember(member);
		String result = objectMapper.writeValueAsString(member);
		System.out.println(result);
		response.getWriter().write(result);
		System.out.println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		System.out.println(request.getParameter("email"));
		doGet(request, response);
	}

}
