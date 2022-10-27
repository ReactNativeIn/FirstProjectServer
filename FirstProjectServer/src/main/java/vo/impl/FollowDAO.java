package vo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;
import vo.FollowVO;

public class FollowDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String FOLLOW_INSERT = "insert into follow values(?, ?)";
	private final String FOLLOW_DELETE = "delete from follow where from_member = ? and to_member = ?";
	private final String FOLLOW_GET_FROM_EMAIL_LIST = "select * from post where email = ?"; //팔로잉(내가 팔로우)
	private final String FOLLOW_GET_TO_EMAIL_LIST = "select * from post where email = ?"; //팔로워(다른 사람이 날)
	
	//팔로우 등록
	public void insertPost(FollowVO vo) {
		System.out.println("팔로우 등록");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FOLLOW_INSERT);
			stmt.setString(1, vo.getFrom_member());
			stmt.setString(2, vo.getTo_member());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//팔로우 삭제
	public void deletePost(FollowVO vo) {
		System.out.println("팔로우 삭제");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FOLLOW_DELETE);
			stmt.setString(1, vo.getFrom_member());
			stmt.setString(2, vo.getTo_member());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//특정 회원이 올린 게시물들 조회
	public List<PostVO> getEmailPostList(PostVO vo) {
		System.out.println("특정 회원이 올린 게시물들 조회");
		List<PostVO> postList = new ArrayList<PostVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_GET_EMAIL_LIST);
			stmt.setString(1, vo.getEmail());
			rs = stmt.executeQuery();
			if(rs.next()) {
				PostVO post = new PostVO();
				post.setPostIndex(rs.getInt("postIndex"));
				post.setPhotoURL(rs.getString("photoURL"));
				post.setNickname(rs.getString("nickname"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getDate("date"));
				post.setEmail(rs.getString("email"));
				postList.add(post);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return postList;
	}
	
	//전체 게시물 조회
	public List<PostVO> getPostList(PostVO vo) {
		System.out.println("전체 게시물");
		List<PostVO> postList = new ArrayList<PostVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				PostVO post = new PostVO();
				post.setPostIndex(rs.getInt("postIndex"));
				post.setPhotoURL(rs.getString("photoURL"));
				post.setNickname(rs.getString("nickname"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getDate("date"));
				post.setEmail(rs.getString("email"));
				postList.add(post);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return postList;
	}
}
