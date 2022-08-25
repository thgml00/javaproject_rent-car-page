package car.rent.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import car.rent.db.DBConnection;
import car.rent.vo.RentUserInfo;

public class RentCarRe { //렌트카 반납 클래스

	private Scanner inputScan ;
	private Connection conn;  // DB 커넥션
	private PreparedStatement preSt;  // sql 전송문서
	
	public List<RentUserInfo> returnRentInfo() throws Exception{
		inputScan=new Scanner(System.in);
		conn=DBConnection.getConnection();
		List<RentUserInfo> list=new ArrayList<>();
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" update rent_user_info	\n");
			sql.append(" set rent_status = ?	\n");
			sql.append(" where rent_user_id = ? and rent_car_id = ? \n"); //차를 반납하면 대여중에서 반납완료로 바꾸기 위한 구문
			//반납할 경우 대여 대수를 감소시키고 소유 대수를 증가시킴
			String sql2=" update car_info set car_count = car_count +1 , rent_count = rent_count -1  where car_id = ? ";
			
			preSt=conn.prepareStatement(sql.toString());
			
			try {			
				System.out.print("반납하기 위해 아이디를 입력해주세요 : ");
				String userid=inputScan.next();
				System.out.print("반납할 차 아이디를 입력해주세요 : ");
				String carid=inputScan.next();
				String status="반납완료";
	
				int valueIndex=1; //자바가 아니라 db에 적용하는 순서라 1부터 시작
				
				preSt.setString(valueIndex++, status);
				preSt.setString(valueIndex++, userid);
				preSt.setString(valueIndex++, carid);
				
				int result=preSt.executeUpdate();
				
				//update로 대수 변경
				PreparedStatement preSt2=conn.prepareStatement(sql2);
				try {
					preSt2.setString(1, carid);
					preSt2.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				if(result>0) {
					//실제 데이터에 적용
					conn.commit();
					System.out.println("차량 반납이 완료되었습니다.");
				}else {
					//원상태로 복구
					conn.rollback();
					System.out.println("차량 반납을 실패했습니다.");
				}				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
