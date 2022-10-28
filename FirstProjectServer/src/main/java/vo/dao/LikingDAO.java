package vo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.LikingVO;

public class LikingDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String LIKING_INSERT = "insert into liking values(?, ?)";
	private final String LIKING_DELETE = "delete from liking where postIndex = ? and email = ?";
	private final String LIKING_GET_POST_LIST = "select * from liking where postIndex = ?"; //특정 게시물 좋아요 개수
	
	//좋아요 등록
	public void insertLiking(LikingVO vo) {
		System.out.println("좋아요 등록");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIKING_INSERT);
			stmt.setInt(1, vo.getPostIndex());
			stmt.setString(2, vo.getEmail());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//좋아요 삭제
	public void deleteLiking(LikingVO vo) {
		System.out.println("좋아요 삭제");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIKING_DELETE);
			stmt.setInt(1, vo.getPostIndex());
			stmt.setString(2, vo.getEmail());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//특정 게시물의 좋아요
	public List<LikingVO> getPostLiking(LikingVO vo) {
		System.out.println("특정 게시물의 좋아요");
		List<LikingVO> likingList = new ArrayList<LikingVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIKING_GET_POST_LIST);
			stmt.setInt(1, vo.getPostIndex());
			rs = stmt.executeQuery();
			if(rs.next()) {
				LikingVO liking = new LikingVO();
				liking.setPostIndex(rs.getInt("postIndex"));
				liking.setEmail(rs.getString("email"));
				likingList.add(liking);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return likingList;
	}
}
