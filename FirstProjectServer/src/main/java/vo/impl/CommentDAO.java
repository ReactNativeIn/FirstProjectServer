package vo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;
import vo.CommentService;
import vo.CommentVO;

public class CommentDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String COMMENT_INSERT = "insert into comment values(?, ?, ?, ?, ?, ?)";
	private final String COMMENT_GET_POST_LIST = "select * from comment where postIndex = ?"; //특정 post 댓글들

	//댓글 등록
	public void insertComment(CommentVO vo) {
		System.out.println("댓글 등록");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_INSERT);
			stmt.setInt(1, vo.getCommentIndex());
			stmt.setString(2, vo.getNickname());
			stmt.setString(3, vo.getContent());
			stmt.setDate(4, vo.getDate());
			stmt.setString(5, vo.getEmail());
			stmt.setInt(6, vo.getPostIndex());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//특정 게시물 댓글들
	public List<CommentVO> getPostIndexComment(CommentVO vo) {
		System.out.println("특정 게시물 댓글들");
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_GET_POST_LIST);
			stmt.setInt(1, vo.getPostIndex());
			rs = stmt.executeQuery();
			if(rs.next()) {
				CommentVO comment = new CommentVO();
				comment.setCommentIndex(rs.getInt("commentIndex"));
				comment.setNickname(rs.getString("nickname"));
				comment.setContent(rs.getString("content"));
				comment.setDate(rs.getDate("date"));
				comment.setEmail(rs.getString("email"));
				comment.setPostIndex(rs.getInt("postIndex"));
				commentList.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return commentList;
	}
}
