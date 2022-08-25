package car.rent.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import car.rent.db.DBConnection;
import car.rent.vo.UserInfo;

public class RentCarUserDelete {
	//사용자 메뉴에서 고객 삭제를 위한 클래스

	private Scanner inputScan ;
	private Connection conn;  // DB 커넥션
	private PreparedStatement preSt;  // sql 전송문서
	
	public List<UserInfo> deleteUserInfo() throws Exception{
		inputScan=new Scanner(System.in);
		conn=DBConnection.getConnection();
		List<UserInfo> list=new ArrayList<>();
		
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" delete from user_info  \n");
			sql.append(" where user_id = ?  \n");
			
			preSt=conn.prepareStatement(sql.toString());
			try {
				System.out.print("삭제할 아이디를 입력해주세요 : ");
				String id=inputScan.next();
				
				preSt.setString(1, id);
				
				//결과 -  1 : 정생실행  0 : 미실행  -1 : 에러
				int result=preSt.executeUpdate();
				
				if(result>0) {
					//실제 데이터에 적용
					conn.commit();
					System.out.println("고객정보가 삭제되었습니다.");
				}else {
					//원상태로 복구
					conn.rollback();
					System.out.println("고객정보 삭제 실패했습니다.");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
