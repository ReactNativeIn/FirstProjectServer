package service;

import java.util.List;

import vo.LikingVO;

public interface LikingService {

	//좋아요 등록
	void insertLiking(LikingVO vo);

	//좋아요 삭제
	void deleteLiking(LikingVO vo);

	//특정 게시물의 좋아요
	List<LikingVO> getPostLiking(LikingVO vo);

}