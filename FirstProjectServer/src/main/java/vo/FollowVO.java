package vo;

public class FollowVO {
	private String from_member;
	private String to_member;
	
	public String getFrom_member() {
		return from_member;
	}
	public void setFrom_member(String from_member) {
		this.from_member = from_member;
	}
	public String getTo_member() {
		return to_member;
	}
	public void setTo_member(String to_member) {
		this.to_member = to_member;
	}
	
	@Override
	public String toString() {
		return "Follow [from_member=" + from_member + ", to_member=" + to_member + "]";
	}
	
}
