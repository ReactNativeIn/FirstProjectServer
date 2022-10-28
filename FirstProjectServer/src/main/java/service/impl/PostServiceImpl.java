package service.impl;

import java.util.Date;
import java.util.List;

import service.PostService;
import vo.PostVO;
import vo.dao.PostDAO;

public class PostServiceImpl implements PostService{

	private PostDAO postDAO = new PostDAO();
	
	@Override
	public void insertPost(PostVO vo) {
		postDAO.insertPost(vo);
	}

	@Override
	public void updatePost(PostVO vo) {
		vo.setDate(new Date());
		postDAO.updatePost(vo);
		
	}

	@Override
	public void deletePost(PostVO vo) {
		postDAO.deletePost(vo);
	}

	@Override
	public List<PostVO> getEmailPostList(PostVO vo) {
		return postDAO.getEmailPostList(vo);
	}

	@Override
	public List<PostVO> getPostList(PostVO vo) {
		return postDAO.getPostList(vo);
	}

}
