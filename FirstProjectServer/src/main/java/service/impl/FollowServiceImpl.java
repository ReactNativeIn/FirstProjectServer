package service.impl;

import java.util.List;

import service.FollowService;
import vo.FollowVO;
import vo.dao.FollowDAO;

public class FollowServiceImpl implements FollowService{

	private FollowDAO followDAO = new FollowDAO();
	
	@Override
	public void insertFollow(FollowVO vo) {
		followDAO.insertFollow(vo);
		
	}

	@Override
	public void deleteFollow(FollowVO vo) {
		followDAO.deleteFollow(vo);
	}

	@Override
	public List<FollowVO> getFromFollowList(FollowVO vo) {
		return followDAO.getFromFollowList(vo);
	}

	@Override
	public List<FollowVO> getToFollowtList(FollowVO vo) {
		return followDAO.getToFollowtList(vo);
	}

}
