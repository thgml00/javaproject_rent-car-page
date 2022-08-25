package car.rent.contrl;


import java.util.List;

import car.rent.db.RentCarUserDAO;
import car.rent.db.RentSqlDAO;
import car.rent.db.RentUserDAO;
import car.rent.user.RentCarUserSearch;
import car.rent.vo.CarVO;
import car.rent.vo.RentUserInfo;
import car.rent.vo.UserInfo;

/**
 * 실제로 데이터를 가져오고 
 * 메뉴에 대한 실제 로직을 가지고있는 매니저클래스
 * @author shin
 *
 */
public class RentCarService {

	private RentSqlDAO dao;
	private RentCarUserDAO userDao;
	private RentCarUserSearch userSearch;
	private RentUserDAO rentDao;
	
	public RentCarService(){
		dao = new RentSqlDAO();
		userDao=new RentCarUserDAO();
		userSearch=new RentCarUserSearch();
		rentDao=new RentUserDAO();
	}
	
	public void getCarAll() throws Exception { //모든 차량 정보 출력
		List<CarVO> list=dao.getCarAll();
		
		if(list==null || list.size()==0) {
			System.out.println("데이터가 없습니다.");
		}else {
			for(CarVO car : list) {
				System.out.println(car);
			}
		}
	}
	
	public void getUserAll() throws Exception { //모든 고객 정보 출력
		List<UserInfo> list=userDao.getUserAll();
		
		if(list==null || list.size()==0) {
			System.out.println("데이터가 없습니다.");
		}else {
			for(UserInfo us : list) {
				System.out.println(us);
			}
		}
	}
	public void getUser() throws Exception { //검색한 고객만 출력
		List<UserInfo> list=userSearch.searchUserInfo();
		
		if(list==null || list.size()==0) {
			System.out.println("일치하는 데이터가 없습니다.");
		}else {
			for(UserInfo us : list) {
				System.out.println(us);
			}
		}
	}
	
	public void getRentInfo() throws Exception { //대여현황출력
		List<RentUserInfo> list=rentDao.getRentUserAll();
		
		if(list==null || list.size()==0) {
			System.out.println("일치하는 데이터가 없습니다.");
		}else {
			for(RentUserInfo rent : list) {
				System.out.println(rent);
			}
		}
	}
}
