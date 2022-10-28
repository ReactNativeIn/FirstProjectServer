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

import service.impl.MemberServiceImpl;
import util.JDBCUtil;
import util.ServletUtil;
import vo.MemberVO;

@WebServlet("/test")
public class test extends HttpServlet {
	
	ObjectMapper objectMapper = new ObjectMapper();
	MemberVO member = new MemberVO();
	MemberServiceImpl service = new MemberServiceImpl();
	String result = "";
	String json = "{";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtil.setting(request, response);
		
		System.out.println("요청 수신");
//		request.getParameterNames().asIterator()
//		.forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));

//		member.setEmail(request.getParameter("email"));
//
//		member = service.getMember(member);
//		
//		String result = objectMapper.writeValueAsString(member);
//		response.getWriter().write(result);
//		System.out.println(result);
		
//		request.getParameterNames().asIterator()
//		.forEachRemaining(paramName -> json += ("\"" + paramName + "\"" + ":" + "\"" +request.getParameter(paramName) + "\"" + ","));
//		json = json.substring(0, json.length() - 14);
//		json += "}";
//
//		System.out.println("최종 : " + json);
//		System.out.println(request.getParameter("choice"));
//
//		MemberVO rMember = objectMapper.readValue(json, MemberVO.class);
//		
//		String result = objectMapper.writeValueAsString(rMember);
//		response.getWriter().write(result);
		
//		result = service.insertMember(rMember);
//		String [] re = result.split(",");
//
//		String result = objectMapper.writeValueAsString(re);
//		response.getWriter().write(result);
//		System.out.println(result);
		response.sendRedirect("/follow");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
