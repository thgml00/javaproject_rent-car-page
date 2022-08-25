package car.rent.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import car.rent.db.DBConnection;
import car.rent.vo.UserInfo;

public class RentCarUserSearch {
	//고객 메뉴에서 고객 아이디로 검색

	private Scanner inputScan ;
	private Connection conn;  // DB 커넥션
	private PreparedStatement preSt;  // sql 전송문서
	private ResultSet res;  // 결과값을 받아오는 객체
	
	public List<UserInfo> searchUserInfo() throws Exception{
		inputScan=new Scanner(System.in);
		conn=DBConnection.getConnection();
		List<UserInfo> list=new ArrayList<>();
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" select *	\n");
			sql.append(" from user_info \n");
			sql.append(" where user_id = ? \n");
			
			preSt=conn.prepareStatement(sql.toString());
			
			try {			
				System.out.print("검색할 아이디를 입력해주세요 : ");
				String id=inputScan.next();
				preSt.setString(1, id);
				res=preSt.executeQuery();
				
				while(res.next()) {
					UserInfo us=new UserInfo();
					us.setUserId(res.getString("user_id"));
					us.setUserName(res.getString("user_name"));
					us.setUserAge(res.getInt("user_age"));
					us.setUserPhone(res.getString("user_phone"));
					us.setUserLicenceNumber(res.getInt("user_licence_number"));
					list.add(us);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
