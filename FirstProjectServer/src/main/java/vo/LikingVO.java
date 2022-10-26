package vo;

public class LikingVO {
	private int postIndex;
	private String email;
	
	public int getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Liking [postIndex=" + postIndex + ", email=" + email + "]";
	}
}
