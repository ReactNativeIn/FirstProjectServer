package vo;

import java.sql.Date;

public class PostVO {
	private int postIndex;
	private String photoURL;
	private String nickname;
	private String content;
	private Date date;
	private String email;
	public int getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
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
	
	@Override
	public String toString() {
		return "Post [postIndex=" + postIndex + ", photoURL=" + photoURL + ", nickname=" + nickname + ", content="
				+ content + ", date=" + date + ", email=" + email + "]";
	}
	
}
