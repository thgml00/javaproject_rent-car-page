package car.rent.vo;

public class RentUserInfo {

	private int rentSeq;
	private String rentUserId;
	private String rentCarId;
	private int rentDays;
	private int rentPrice;
	private String rentStatus;
	
	public int getRentSeq() {
		return rentSeq;
	}
	public void setRentSeq(int rentSeq) {
		this.rentSeq = rentSeq;
	}
	public String getRentUserId() {
		return rentUserId;
	}
	public void setRentUserId(String rentUserId) {
		this.rentUserId = rentUserId;
	}
	public String getRentCarId() {
		return rentCarId;
	}
	public void setRentCarId(String rentCarId) {
		this.rentCarId = rentCarId;
	}
	public int getRentDays() {
		return rentDays;
	}
	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}
	public int getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getRentStatus() {
		return rentStatus;
	}
	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("랜트 번호 : "+this.getRentSeq()+", ");
		sb.append("고객 아이디 : "+this.getRentUserId()+", ");
		sb.append("렌터카 아이디: "+this.getRentCarId()+", ");
		sb.append("랜트기간 : "+this.getRentDays()+", ");
		sb.append("랜트 가격 : "+this.getRentPrice()+", ");
		sb.append("랜트상태 : "+this.getRentStatus());
		
		return sb.toString();
		
	}
	
	
}
