package car.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import car.rent.vo.CarVO;

/**
 * DB 에서  차량 (car_info)데이터를 가져오는 클래스 
 * @author shin
 *
 */
public class RentSqlDAO {
	
	private Connection conn;  // DB 커넥션
	private PreparedStatement pstmt;  // sql 전송문서
	private ResultSet res;  // 결과값을 받아오는 객체
	
	public List<CarVO> getCarAll() throws Exception{
		conn=DBConnection.getConnection();
		List<CarVO> list=new ArrayList<>();
		
		if(conn!=null) {
			StringBuilder sql=new StringBuilder();
			
			sql.append(" select *	\n");
			sql.append(" from car_info \n");
			
			pstmt=conn.prepareStatement(sql.toString());
			res=pstmt.executeQuery();
			
			while(res.next()) {
				CarVO car=new CarVO();
				car.setCarId(res.getString("car_id"));
				car.setCarCompany(res.getString("car_company"));
				car.setCarName(res.getString("car_name"));
				car.setCarYear(res.getInt("car_year"));
				car.setCarFuel(res.getString("car_fuel"));
				car.setCarCount(res.getInt("car_count"));
				car.setRentCount(res.getInt("rent_count"));
				list.add(car);
			}
		}
		return list;
	}
}
