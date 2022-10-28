package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.impl.FollowServiceImpl;
import service.impl.MemberServiceImpl;
import util.ServletUtil;
import vo.FollowVO;
import vo.MemberVO;


@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ObjectMapper objectMapper = new ObjectMapper();
	String json = "{";
	int choice = 0;
	
	FollowVO follow = new FollowVO();
	FollowServiceImpl service = new FollowServiceImpl();
	MemberServiceImpl mService = new MemberServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("follow 요청 수신 - service");
		ServletUtil.setting(request, response);
		json = "{"; //초기화
		
		request.getParameterNames().asIterator()
		.forEachRemaining(paramName ->  json += ("\"" + paramName + "\"" + ":" + "\"" +request.getParameter(paramName) + "\"" + ","));
		json = json.substring(0, json.length() - 14); //choice, 마지막 인자 , 제거
		json += "}";
		
		System.out.println("json 확인 : \n" + json);
		try {
			choice = Integer.parseInt(request.getParameter("choice"));
//			json.replace("email", 0)
		}catch(Exception e) {
			System.out.println("숫자 변환 에러");
		}
		
		follow = objectMapper.readValue(json, FollowVO.class);
		
		doGet(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("follow 요청 수신 - get");
		String res;
		boolean rb;
		List<FollowVO> followList = new ArrayList<FollowVO>();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		switch(choice) {
			case 1: //팔로우(팔로우 등록)
				rb = service.insertFollow(follow);
				
				//응답
				res = objectMapper.writeValueAsString(rb);
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				break;
				
			case 2: //팔로잉(팔로우 삭제)
				rb = service.deleteFollow(follow);
				
				//응답
				res = objectMapper.writeValueAsString(rb);
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				break;
				
			case 3: //팔로잉(내가 다른 사람을 팔로우) 회원 정보 조회
				followList = service.getFromFollowList(follow);
				
				FollowVO fow = new FollowVO();
				MemberVO member = new MemberVO();
				
				for(int i = 0; i < followList.size(); i++) {
					fow = followList.get(i);
					member.setEmail(fow.getTo_member());
					memberList.add(mService.getMember(member));
				}
				
				res = objectMapper.writeValueAsString(memberList);
				
				//응답
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				System.out.println(response);
				break;
				
			case 4: //팔로잉(개수) 조회
				followList = service.getFromFollowList(follow);
				
				res = objectMapper.writeValueAsString(followList.size());
				
				//응답
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				break;
				
			case 5: //팔로워(다른 사람이 날 팔로우) 개수 조회
				followList = service.getToFollowtList(follow);
				
				//응답
				res = objectMapper.writeValueAsString(followList.size());
				response.getWriter().write(res);
				System.out.println("응답 : \n" + res);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
