package car.rent.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import car.rent.db.DBConnection;
import car.rent.vo.UserInfo;

public class RentCarUserUpdate {
	//고객 메뉴에서 고객 정보 수정 클래스

	private Scanner inputScan ;
	private Connection conn;  // DB 커넥션
	private PreparedStatement preSt;  // sql 전송문서
	
	public List<UserInfo> updateUserInfo() throws Exception{
		inputScan=new Scanner(System.in);
		conn=DBConnection.getConnection();
		List<UserInfo> list=new ArrayList<>();
		
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" update user_info  \n");
			sql.append(" set user_name = ?, user_age = ?, user_phone = ? \n");
			sql.append(" where user_id = ?  \n"); 
			//아이디로 고객을 먼저확인하고
			//이름, 나이, 휴대폰 번호를 수정
			
			preSt=conn.prepareStatement(sql.toString());
			try {
				System.out.print("아이디를 입력해주세요 : ");
				String id=inputScan.next();
				
				System.out.print("이름 : ");
				String name=inputScan.next();
				System.out.print("나이 : ");
				int age=inputScan.nextInt();
				System.out.print("핸드폰번호 : ");
				String phone=inputScan.next();
				
				int valueIndex=1; //자바가 아니라 db에 적용하는 순서라 1부터 시작
				//?에 대응하는 데이터 삽입
				preSt.setString(valueIndex++, name);
				preSt.setInt(valueIndex++, age);
				preSt.setString(valueIndex++, phone);
				preSt.setString(valueIndex++, id);
				//결과 -  1 : 정생실행  0 : 미실행  -1 : 에러
				//executeUpdate() : 등록 / 수정 / 삭제 SQL 문법 실행시 사용
				int result=preSt.executeUpdate();
				
				if(result>0) {
					//실제 데이터에 적용
					conn.commit();
					System.out.println("고객정보가 수정되었습니다.");
				}else {
					//원상태로 복구
					conn.rollback();
					System.out.println("고객정보 수정이 실패했습니다.");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
