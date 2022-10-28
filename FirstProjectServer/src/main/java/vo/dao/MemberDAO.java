package vo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.MemberVO;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String MEMBER_INSERT = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String MEMBER_UPDATE = "update member set name = ?, birthday = ?, "
			+ "nickname = ?, introduce = ?, phone = ?, profileImage = ?"
			+ "where email = ?";
	private final String MEMBER_DELETE = "delete from member where email = ?";
	private final String MEMBER_GET = "select * from member where email = ?";
	private final String MEMBER_LIST = "select * from member";
	private final String MEMBER_LOGIN = "select * from member where email = ? and password = ?";
	
	//회원가입
	public void insertMember(MemberVO vo) {
		System.out.println("회원가입, member insert");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, vo.getEmail());
			stmt.setString(2, vo.getName());
			stmt.setString(3, vo.getPassword());
			stmt.setString(4, vo.getBirthday());
			stmt.setString(5, vo.getNickname());
			stmt.setString(6, vo.getIntroduce());
			stmt.setString(7, vo.getGender());
			stmt.setString(8, vo.getPhone());
			stmt.setString(9, vo.getProfileImage());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//회원정보 수정
	public void updateMember(MemberVO vo) {
		System.out.println("회원정보 수정, member update");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getBirthday());
			stmt.setString(3, vo.getNickname());
			stmt.setString(4, vo.getIntroduce());
			stmt.setString(5, vo.getPhone());
			stmt.setString(6, vo.getProfileImage());
			stmt.setString(7, vo.getEmail());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//회원 탈퇴(삭제)
	public void deleteMember(MemberVO vo) {
		System.out.println("회원탈퇴, member delete");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);
			stmt.setString(1, vo.getEmail());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//회원 1명 조회
	public MemberVO getMember(MemberVO vo) {
		System.out.println("회원 1명 조회");
		MemberVO member = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_GET);
			stmt.setString(1, vo.getEmail());
			rs = stmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setBirthday(rs.getString("birthday"));
				member.setNickname(rs.getString("nickname"));
				member.setIntroduce(rs.getString("introduce"));
				member.setGender(rs.getString("gender"));
				member.setPhone(rs.getString("phone"));
				member.setProfileImage(rs.getString("profileImage"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return member;
	}
	
	//전체 회원(회원가입한 사람들)
	public List<MemberVO> getMemberList() {
		System.out.println("전체 회원 조회");
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setBirthday(rs.getString("nickname"));
				member.setIntroduce(rs.getString("introduce"));
				member.setGender(rs.getString("gender"));
				member.setPhone(rs.getString("phone"));
				member.setProfileImage(rs.getString("profileImage"));
				memberList.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return memberList;
	}
	
	//회원 로그인
	public MemberVO getMemberLogin(MemberVO vo) {
		MemberVO member = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LOGIN);
			stmt.setString(1, vo.getEmail());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setBirthday(rs.getString("birthday"));
				member.setNickname(rs.getString("nickname"));
				member.setIntroduce(rs.getString("introduce"));
				member.setGender(rs.getString("gender"));
				member.setPhone(rs.getString("phone"));
				member.setProfileImage(rs.getString("profileImage"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return member;
	}
}
