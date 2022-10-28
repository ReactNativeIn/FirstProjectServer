package service.impl;

import java.util.List;

import service.CommentService;
import vo.CommentVO;
import vo.dao.CommentDAO;


public class CommentServiceImpl implements CommentService{

	private CommentDAO commentDAO = new CommentDAO();
	
	@Override
	public void insertComment(CommentVO vo) {
		commentDAO.insertComment(vo);
	}

	@Override
	public List<CommentVO> getPostIndexComment(CommentVO vo) {
		// TODO Auto-generated method stub
		return commentDAO.getPostIndexComment(vo);
	}

}
