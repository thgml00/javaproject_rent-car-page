package car.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import car.rent.vo.UserInfo;
/**
 * DB 에서 고객(user_info)데이터를 가져오는 클래스 
 * @author shin
 *
 */
public class RentCarUserDAO {

	private Connection conn;  // DB 커넥션
	private PreparedStatement pstmt;  // sql 전송문서
	private ResultSet res;  // 결과값을 받아오는 객체
	
	public List<UserInfo> getUserAll() throws Exception{
		conn=DBConnection.getConnection();
		List<UserInfo> list=new ArrayList<>();
		
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" select *	\n");
			sql.append(" from user_info us \n");
			
			pstmt=conn.prepareStatement(sql.toString());
			res=pstmt.executeQuery();
			
			while(res.next()) {
				UserInfo us=new UserInfo();
				us.setUserId(res.getString("user_id"));
				us.setUserName(res.getString("user_name"));
				us.setUserAge(res.getInt("user_age"));
				us.setUserPhone(res.getString("user_phone"));
				us.setUserLicenceNumber(res.getInt("user_licence_number"));
				list.add(us);
			}
		}
		return list;
	}
}
