package vo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.PostVO;

public class PostDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String POST_INSERT = "insert into post values(?, ?, ?, ?, now(), ?)";
	private final String POST_UPDATE = "update post set content = ? date = now()"
			+ "where postIndex = ?";
	private final String POST_DELETE = "delete from post where postIndex = ?";
	private final String POST_GET_EMAIL_LIST = "select * from post where email = ?"; //특정 회원이 올린 post
	private final String POST_LIST = "select * from member";
	
	//게시물 등록
	public void insertPost(PostVO vo) {
		System.out.println("게시물 등록");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_INSERT);
			stmt.setInt(1, vo.getPostIndex());
			stmt.setString(2, vo.getPhotoURL());
			stmt.setString(3, vo.getNickname());
			stmt.setString(4, vo.getContent());
			stmt.setString(5, vo.getEmail());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//게시물 수정
	public void updatePost(PostVO vo) {
		System.out.println("게시물 수정");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_UPDATE);
			stmt.setString(1, vo.getContent());
			stmt.setInt(2, vo.getPostIndex());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//게시물 삭제
	public void deletePost(PostVO vo) {
		System.out.println("게시물 삭제");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_DELETE);
			stmt.setInt(1, vo.getPostIndex());
			stmt.executeUpdate();
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
