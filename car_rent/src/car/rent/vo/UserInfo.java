package car.rent.vo;

public class UserInfo {
	
	private String userId;
	private String userName;
	private int userAge;
	private String userPhone;
	private int userLicenceNumber;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public int getUserLicenceNumber() {
		return userLicenceNumber;
	}
	public void setUserLicenceNumber(int userLicenceNumber) {
		this.userLicenceNumber = userLicenceNumber;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("아이디 : "+this.getUserId()+", ");
		sb.append("이름 : "+this.getUserName()+", ");
		sb.append("나이 : "+this.getUserAge()+", ");
		sb.append("전화번호 : "+this.getUserPhone()+", ");
		sb.append("라이센스 번호 : "+this.getUserLicenceNumber());
		
		return sb.toString();
		
	}

}
