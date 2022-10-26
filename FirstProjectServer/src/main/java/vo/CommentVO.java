package vo;

import java.sql.Date;

public class CommentVO {
	private int commentIndex;
	private String nickname;
	private String content;
	private Date date;
	private String email;
	private int postIndex;
	
	public int getCommentIndex() {
		return commentIndex;
	}
	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
	}
	
	@Override
	public String toString() {
		return "Comment [commentIndex=" + commentIndex + ", nickname=" + nickname + ", content=" + content + ", date="
				+ date + ", email=" + email + ", postIndex=" + postIndex + "]";
	}
}
