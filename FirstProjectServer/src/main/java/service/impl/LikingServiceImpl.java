package service.impl;

import java.util.List;

import service.LikingService;
import vo.LikingVO;
import vo.dao.LikingDAO;

public class LikingServiceImpl implements LikingService{

	private LikingDAO likingDAO = new LikingDAO();
	
	@Override
	public void insertLiking(LikingVO vo) {
		likingDAO.insertLiking(vo);
	}

	@Override
	public void deleteLiking(LikingVO vo) {
		likingDAO.deleteLiking(vo);
	}

	@Override
	public List<LikingVO> getPostLiking(LikingVO vo) {
		return likingDAO.getPostLiking(vo);
	}

}
