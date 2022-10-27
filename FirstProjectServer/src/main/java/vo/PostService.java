package vo;

import java.util.List;

public interface PostService {

	//게시물 등록
	void insertPost(PostVO vo);

	//게시물 수정
	void updatePost(PostVO vo);

	//게시물 삭제
	void deletePost(PostVO vo);

	//특정 회원이 올린 게시물들 조회
	List<PostVO> getEmailPostList(PostVO vo);

	//전체 게시물 조회
	List<PostVO> getPostList(PostVO vo);

}