package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.impl.MemberServiceImpl;
import util.ServletUtil;
import vo.MemberVO;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ObjectMapper objectMapper = new ObjectMapper();
	MemberServiceImpl service = new MemberServiceImpl();
	MemberVO member = new MemberVO();
	String json = "{";
	String result = "";
	int choice = 0;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member 요청 수신 - service");
		ServletUtil.setting(request, response);
		json = "{"; //초기화
		
		request.getParameterNames().asIterator()
		.forEachRemaining(paramName -> json += ("\"" + paramName + "\"" + ":" + "\"" +request.getParameter(paramName) + "\"" + ","));
		json = json.substring(0, json.length() - 14); //choice, 마지막 인자 , 제거
		json += "}";
		System.out.println("json 확인 : \n" + json);
		try {
			choice = Integer.parseInt(request.getParameter("choice"));
		}catch(Exception e) {
			System.out.println("숫자 변환 에러");
		}
		member = objectMapper.readValue(json, MemberVO.class);
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member 요청 수신 - get");
		String [] re;
		String res;
		
		switch(choice) {
			case 1: //회원가입
				result = service.insertMember(member);
				re = result.split(",");
				
				//응답
				res = objectMapper.writeValueAsString(re);
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				break;
				
			case 2: //로그인
				result = objectMapper.writeValueAsString(service.getMemberLogin(member));
				
				//응답
				response.getWriter().write(result);
				System.out.println("응답 : \n" + result);
				break;
				
			case 3: //특정 회원 조회
				result = objectMapper.writeValueAsString(service.getMember(member));
				
				//응답
				response.getWriter().write(result);
				System.out.println("응답 : \n" + result);
				break;
				
			case 4: // 회원 정보 수정
				result = service.updateMember(member);
				re = result.split(",");
				
				//응답
				res = objectMapper.writeValueAsString(re);
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				break;
				
			case 5: //전체 회원 조회
				result = objectMapper.writeValueAsString(service.getMemberList());
				
				//응답
				response.getWriter().write(result);
				System.out.println("응답 : \n" + result);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
