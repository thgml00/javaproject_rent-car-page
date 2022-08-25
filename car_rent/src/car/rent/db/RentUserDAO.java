package car.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import car.rent.vo.RentUserInfo;
/**
 * DB 에서 렌트 현황 데이터를 가져오는 클래스 
 * @author shin
 *
 */
public class RentUserDAO {

	private Connection conn;  // DB 커넥션
	private PreparedStatement pstmt;  // sql 전송문서
	private ResultSet res;  // 결과값을 받아오는 객체
	
	public List<RentUserInfo> getRentUserAll() throws Exception{
		conn=DBConnection.getConnection();
		List<RentUserInfo> list=new ArrayList<>();
		
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" select *	\n");
			sql.append(" from rent_user_info \n");
			
			pstmt=conn.prepareStatement(sql.toString());
			res=pstmt.executeQuery();
			
			while(res.next()) {
				RentUserInfo rent=new RentUserInfo();
				rent.setRentSeq(res.getInt("rent_seq"));
				rent.setRentUserId(res.getString("rent_user_id"));
				rent.setRentCarId(res.getString("rent_car_id"));
				rent.setRentDays(res.getInt("rent_days"));
				rent.setRentPrice(res.getInt("rent_price"));
				rent.setRentStatus(res.getString("rent_status"));
				list.add(rent);
			}
		}
		return list;
	}
}
