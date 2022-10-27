package vo;

import java.util.List;

public interface CommentService {

	//댓글 등록
	void insertComment(CommentVO vo);

	//특정 게시물 댓글들
	List<CommentVO> getPostIndexComment(CommentVO vo);

}