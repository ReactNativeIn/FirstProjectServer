package vo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.FollowVO;

public class FollowDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String FOLLOW_INSERT = "insert into follow values(?, ?)";
	private final String FOLLOW_DELETE = "delete from follow where from_member = ? and to_member = ?";
	private final String FOLLOW_GET_FROM_MEMBER_LIST = "select * from follow where from_member = ?"; //팔로잉(내가 팔로우)
	private final String FOLLOW_GET_TO_MEMBER_LIST = "select * from follow where to_member = ?"; //팔로워(다른 사람이 날)
	
	//팔로우 등록
	public Boolean insertFollow(FollowVO vo) {
		System.out.println("팔로우 등록");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FOLLOW_INSERT);
			stmt.setString(1, vo.getFrom_member());
			stmt.setString(2, vo.getTo_member());
			if(stmt.executeUpdate() > 0 ) return true;
		}catch(Exception e) {
			System.out.println("팔로우 insert에러");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		return false;
	}
	
	//팔로우 삭제
	public Boolean deleteFollow(FollowVO vo) {
		System.out.println("팔로우 삭제");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FOLLOW_DELETE);
			stmt.setString(1, vo.getFrom_member());
			stmt.setString(2, vo.getTo_member());
			if(stmt.executeUpdate() > 0) return true;
		}catch(Exception e) {
			System.out.println("팔로우 delelte에러");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		return false;
	}
	
	//팔로잉(내가 다른 사람을 팔로우)
	public List<FollowVO> getFromFollowList(FollowVO vo) {
		System.out.println("팔로잉한 회원들의 정보");
		List<FollowVO> followList = new ArrayList<FollowVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FOLLOW_GET_FROM_MEMBER_LIST);
			stmt.setString(1, vo.getFrom_member());
			rs = stmt.executeQuery();
			if(rs.next()) {
				FollowVO follow = new FollowVO();
				follow.setFrom_member(rs.getString("from_member"));
				follow.setTo_member(rs.getString("to_member"));
				followList.add(follow);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return followList;
	}
	
	//팔로워(다른 사람이 날 팔로우)
	public List<FollowVO> getToFollowtList(FollowVO vo) {
		System.out.println("팔로워");
		List<FollowVO> followList = new ArrayList<FollowVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FOLLOW_GET_TO_MEMBER_LIST);
			stmt.setString(1, vo.getTo_member());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FollowVO follow = new FollowVO();
				follow.setFrom_member(rs.getString("from_member"));
				follow.setTo_member(rs.getString("to_member"));
				followList.add(follow);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return followList;
	}
}
