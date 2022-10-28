package service.impl;

import java.util.List;

import service.FollowService;
import vo.FollowVO;
import vo.dao.FollowDAO;

public class FollowServiceImpl implements FollowService{

	private FollowDAO followDAO = new FollowDAO();
	
	@Override
	public Boolean insertFollow(FollowVO vo) {
		return followDAO.insertFollow(vo);	
	}

	@Override
	public Boolean deleteFollow(FollowVO vo) {
		return followDAO.deleteFollow(vo);
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
