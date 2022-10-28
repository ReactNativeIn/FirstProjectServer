package service.impl;

import java.util.List;

import service.MemberService;
import vo.MemberVO;
import vo.dao.MemberDAO;

import java.util.regex.Pattern; //자바 정규표현식
/*
 * Pattern.matches(정규표현식, 값); return값 boolean
 * */
public class MemberServiceImpl implements MemberService{
	
	private MemberDAO memberDAO = new MemberDAO();
	
	@Override
	public String insertMember(MemberVO vo) {
		String emailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		String phonePattern = "^\\d{10,11}$";
		String passwordPattern = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
		
		if(vo.getEmail().equals("") || vo.getEmail() == null) {
			return "실패,이메일을 입력해주세요,false";
		}else if(!Pattern.matches(emailPattern, vo.getEmail())) {
			return "실패,이메일 형식이 틀렸습니다,false";
		}else if(vo.getPassword() == null || vo.getPassword().equals("")) {
			return "실패,비밀번호를 입력해주세요,false";
		}else if(!Pattern.matches(passwordPattern, vo.getPassword())) {
			return "실패,비밀번호 형식이 틀렸습니다.(8~15자리),false";
		}else if(vo.getConfirmPassword() == null) {
			return "실패,비밀번호 확인란을 입력해주세요,false";
		}else if(!vo.getPassword().equals(vo.getConfirmPassword())) {
			return "실패,비밀번호가 일치하지 않습니다,false";
		}else if(vo.getName() == null || vo.getName().equals("")) {
			return "실패,이름을 입력해주세요,false";
		}else if(vo.getNickname() == null || vo.getNickname().equals("")) {
			return "실패,닉네임을 입력해주세요,false";
		}else if(vo.getPhone() == null || vo.getPhone().equals("")) {
			return "실패,전화번호를 입력해주세요,false";
		}else if(!Pattern.matches(phonePattern, vo.getPhone())) {
			return "실패,전화번호 형식이 틀렸습니다.(-없이),false";
		}else if(vo.getBirthday() == null || vo.getBirthday().equals("")) {
			return "실패,생일을 입력해주세요,false";
		}
		
		List<MemberVO> memberList = memberDAO.getMemberList();
		
		for(MemberVO m : memberList) {
			if(m.getEmail().equals(vo.getEmail())) {
				return "실패,중복된 이메일입니다,false";
			}
		}
		
		memberDAO.insertMember(vo);
		return "성공,회원가입 성공,true";
	}

	@Override
	public String updateMember(MemberVO vo) {
		String phonePattern = "^\\d{10,11}$";
		
		if(!Pattern.matches(phonePattern, vo.getPhone())) {
			return "실패,핸드폰 번호를 입력해주세여(-없이),false";
		}
		
		memberDAO.updateMember(vo);
		return "성공,회원정보 수정,true";
	}

	@Override
	public void deleteMember(MemberVO vo) {
		memberDAO.deleteMember(vo);
	}

	@Override
	public MemberVO getMember(MemberVO vo) {
		return memberDAO.getMember(vo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberDAO.getMemberList();
	}

	
	@Override
	public MemberVO getMemberLogin(MemberVO vo) {
		return memberDAO.getMemberLogin(vo);
	}

}
