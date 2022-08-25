package car.rent.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import car.rent.db.DBConnection;
import car.rent.vo.UserInfo;

public class RentCarUserJoin {
	//고객 메뉴에서 고객등록 클래스
	
	private Scanner inputScan ;
	private Connection conn;  // DB 커넥션
	private PreparedStatement preSt;  // sql 전송문서
	private ResultSet resultSet;  // 결과값을 받아오는 객체
	
	public List<UserInfo> insertUserInfo() throws Exception{
		inputScan=new Scanner(System.in);
		conn=DBConnection.getConnection();
		List<UserInfo> list=new ArrayList<>();
		String sql="insert into user_info(user_id,user_name,user_age,user_phone,user_licence_number) values(?,?,?,?,?)";
		String sql2="select user_id from user_info";
		String sql3="select user_licence_number from user_info";
		
		preSt=conn.prepareStatement(sql);
		
		try {			
			System.out.print("아이디 : ");
			String id=inputScan.next();
			//아이디 중복을 검사하기 위한 부분
			try {
				PreparedStatement pstmt2=conn.prepareStatement(sql2);
				resultSet=pstmt2.executeQuery();
				
				while(resultSet.next()) {
					if(resultSet.getString("user_id").equals(id)) {
						System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
						conn.rollback();
						System.out.print("아이디 : ");
						id=inputScan.next();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("이름 : ");
			String name=inputScan.next();
			System.out.print("나이 : ");
			int age=inputScan.nextInt();
			System.out.print("핸드폰번호 : ");
			String phone=inputScan.next();
			System.out.print("라이센스 번호 : ");
			int licence=inputScan.nextInt();
			//라이센스도 마찬가지로 중복확인
			try {
				PreparedStatement pstmt3=conn.prepareStatement(sql3);
				ResultSet res3=pstmt3.executeQuery();
				
				while(res3.next()) {
					if(res3.getInt("user_licence_number")==licence) {
						System.out.println("중복된 번호입니다. 다시 입력해주세요.");
						conn.rollback();
						System.out.print("라이센스 번호 : ");
						licence=inputScan.nextInt();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			int valueIndex=1; //자바가 아니라 db에 적용하는 순서라 1부터 시작
			
			
			//?에 대응하는 데이터 삽입
			preSt.setString(valueIndex++, id);
			preSt.setString(valueIndex++, name);
			preSt.setInt(valueIndex++, age);
			preSt.setString(valueIndex++, phone);
			preSt.setInt(valueIndex++, licence);
			//결과 -  1 : 정생실행  0 : 미실행  -1 : 에러
			//executeUpdate() : 등록 / 수정 / 삭제 SQL 문법 실행시 사용
			int result=preSt.executeUpdate();
			
			if(result>0) {
				//실제 데이터에 적용
				conn.commit();
				System.out.println("고객정보가 등록되었습니다.");
			}else {
				//원상태로 복구
				conn.rollback();
				System.out.println("고객정보 등록이 실패했습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
