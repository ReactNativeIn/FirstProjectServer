package service;

import java.util.List;

import vo.FollowVO;

public interface FollowService {

	//팔로우 등록
	Boolean insertFollow(FollowVO vo);

	//팔로우 삭제
	Boolean deleteFollow(FollowVO vo);

	//팔로잉(내가 다른 사람을 팔로우)
	List<FollowVO> getFromFollowList(FollowVO vo);

	//팔로워(다른 사람이 날 팔로우)
	List<FollowVO> getToFollowtList(FollowVO vo);

}