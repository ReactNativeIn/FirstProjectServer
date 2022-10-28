package service;

import java.util.List;

import vo.MemberVO;

public interface MemberService {

	//회원가입
	String insertMember(MemberVO vo);

	//회원정보 수정
	String updateMember(MemberVO vo);

	//회원 탈퇴(삭제)
	void deleteMember(MemberVO vo);

	//회원 1명 조회
	MemberVO getMember(MemberVO vo);

	//전체 회원(회원가입한 사람들)
	List<MemberVO> getMemberList();
	
	//회원 로그인
	MemberVO getMemberLogin(MemberVO vo);

}