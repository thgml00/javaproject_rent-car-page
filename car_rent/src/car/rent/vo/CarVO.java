package car.rent.vo;

public class CarVO {

	private String carId;
	private String carCompany;
	private String carName;
	private int carYear;
	private String carFuel;
	private int carCount;
	private int rentCount;
	
	
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarCompany() {
		return carCompany;
	}
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getCarYear() {
		return carYear;
	}
	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}
	public String getCarFuel() {
		return carFuel;
	}
	public void setCarFuel(String carFuel) {
		this.carFuel = carFuel;
	}
	public int getCarCount() {
		return carCount;
	}
	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}
	public int getRentCount() {
		return rentCount;
	}
	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("차 아이디 : "+this.getCarId()+", ");
		sb.append("회사 : "+this.getCarCompany()+", ");
		sb.append("이름 : "+this.getCarName()+", ");
		sb.append("연식 : "+this.getCarYear()+", ");
		sb.append("연료 : "+this.getCarFuel()+", ");
		sb.append("소유 대수 : "+this.getCarCount()+", ");
		sb.append("렌트 대수 : "+this.getRentCount());
		
		return sb.toString();
		
	}
	
	
}
