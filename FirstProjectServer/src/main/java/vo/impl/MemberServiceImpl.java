package vo.impl;

import java.util.List;

import vo.MemberService;
import vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	
	private MemberDAO memberDAO = new MemberDAO();
	
	@Override
	public void insertMember(MemberVO vo) {
		memberDAO.insertMember(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		memberDAO.updateMember(vo);
	}

	@Override
	public void deleteMember(MemberVO vo) {
		memberDAO.deleteMember(vo);
	}

	@Override
	public MemberVO getMember(MemberVO vo) {
		System.out.println("확" + vo);
		return memberDAO.getMember(vo);
	}

	@Override
	public List<MemberVO> getMemberList(MemberVO vo) {
		return memberDAO.getMemberList(vo);
	}

}
