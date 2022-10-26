package vo;

import java.util.List;

public interface MemberService {

	//회원가입
	void insertMember(MemberVO vo);

	//회원정보 수정
	void updateMember(MemberVO vo);

	//회원 탈퇴(삭제)
	void deleteMember(MemberVO vo);

	//회원 1명 조회
	MemberVO getMember(MemberVO vo);

	//전체 회원(회원가입한 사람들)
	List<MemberVO> getMemberList(MemberVO vo);

}