package car.rent.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import car.rent.db.DBConnection;
import car.rent.vo.RentUserInfo;
public class RentUser { 
	//렌트카 대여 클래스
	//구현이 불안정합니다 ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ

	private Scanner inputScan ;
	private Connection conn;  // DB 커넥션
	private PreparedStatement preSt;  // sql 전송문서
	
	public List<RentUserInfo> insertRentInfo() throws Exception{
		inputScan=new Scanner(System.in);
		conn=DBConnection.getConnection();
		List<RentUserInfo> list=new ArrayList<>();
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" insert into rent_user_info(rent_user_id, rent_car_id,rent_price,rent_status)	\n");
			sql.append(" values (?,?,?,?) \n");
			//대여하려는 차의 대수가 0일 때
			String sql3="select * from car_info where car_count = 0 ";
			String sql4="select * from user_info ";
			//대여할 경우 car_info 테이블에 있는 소유 대수의 수를 다운 시키고
			//렌트 대수를 1증가 시킨다.
			String sql2="update car_info set car_count = car_count -1 , rent_count = rent_count +1  where car_id = ? ";
			
			preSt=conn.prepareStatement(sql.toString());
			
			try {			
				System.out.print("렌트할 아이디를 입력해주세요 : ");
				String userid=inputScan.next();
				
				//아이디가 일치하는 것이 없을 때 강제 종료로 메뉴로 돌아가게끔 함.
				//잘 안돼서 오류 뜰 때도 있습니다..
				try {
					PreparedStatement pstmt4=conn.prepareStatement(sql4);
					ResultSet res4=pstmt4.executeQuery();
					
					while(res4.next()) {
						if(res4.getString("user_id").equals(userid)) {
							break;
						}
						if(!res4.next()) {
							System.out.println("일치하는 id가 없어 메뉴로 돌아갑니");
							return null;
						}
					}
				}catch(Exception e) {
					System.out.println("일치하는 id가 없어 메뉴로 돌아갑니다.");
					return null;
				}
				
				System.out.print("렌트할 차 아이디를 입력해주세요 : ");
				String carid=inputScan.next();
				
				try {
					PreparedStatement pstmt3=conn.prepareStatement(sql3);
					ResultSet res3=pstmt3.executeQuery();
					//소유 대수가 0일 경우 대여하지 못함
					while(res3.next()) {
						if(res3.getString("car_id").equals(carid)) {
							System.out.println("빌릴 수 있는 차량이 없습니다. 다른 차를 입력해주세요.");
							conn.rollback();
							System.out.print("차 아이디 : ");
							carid=inputScan.next();
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.print("렌트 가격를 입력해주세요 : ");
				int price=inputScan.nextInt();
	
				int valueIndex=1; //자바가 아니라 db에 적용하는 순서라 1부터 시작
				
				preSt.setString(valueIndex++, userid);
				preSt.setString(valueIndex++, carid);
				preSt.setInt(valueIndex++, price);
				preSt.setString(valueIndex++, "대여중"); //status를 대여중으로 입력
				
				int result=preSt.executeUpdate();
				
				//대여 성공해 executeUpdate에 넣으면 소유 대수와 렌트 대수를 변경시킴
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
					System.out.println("차량이 대여되었습니다.");
					
					
				}else {
					//원상태로 복구
					conn.rollback();
					System.out.println("차량 대여를 실패했습니다.");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}