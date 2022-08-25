package car.rent.contrl;

import java.util.Scanner;

import car.rent.status.RentCarRe;
import car.rent.status.RentUser;
import car.rent.user.RentCarUserDelete;
import car.rent.user.RentCarUserJoin;
import car.rent.user.RentCarUserUpdate;
import car.rent.utils.CartRentConstants;

/**
 * 메뉴 및 프로그램에서 보여지는 
 * 화면단을 만드는 클래스 
 * @author shin
 *
 */
public class ManageClassControl {

	  private Scanner inputScan ;
	  private int inputNum;
	 
	  /**
	   * 생성자
	   */
	  public ManageClassControl(RentCarService carMg){
		 inputScan = new Scanner(System.in);
	 }
	  /**
	   * 렌트카프로그램 시작 메서드
	 * @throws Exception 
	   */
	  public void mainDisplay() throws Exception{
		  printMain();
	  }
	 
	  /**
	   * 메인메뉴 시작 
	 * @throws Exception 
	   */
	  public void printMain() throws Exception{		  
		  System.out.println("\n==============MENU==============");
		  System.out.println("==         1. 고객정보                        ==");
		  System.out.println("==         2. 차량정보                        ==");
		  System.out.println("==         3. 차량대여                        ==");
		  System.out.println("==         4. 차량반납                        ==");
		  System.out.println("==         5. 대여현황보기                     ==");
		  System.out.println("==         6. 나가기                         ==");
		  System.out.println("==================================");
		  System.out.print("원하는 메뉴를 입력하시오   : ");
			 
		  inputNum  = inputScan.nextInt();  // 메뉴 번호 입력
		  printSubMenu(inputNum);  // 번호에 따른 하위 메뉴 실행 
	  }
	  
	  /**
	    * sub  메뉴 depth -1
	 * @throws Exception 
	  */
	  public  void printSubMenu(int menu_num) throws Exception{

		  switch(menu_num){
		   case CartRentConstants.Main_CUS_INFO: 
			   printCustomerSubMenu(); 
			   break;// 고객정보 하위 메뉴 출력
		   case CartRentConstants.Main_CAR_INFO:  
			   RentCarService carinfo=new RentCarService();
			   carinfo.getCarAll();
			   printMain();
			   break; // 차량정보
		   case CartRentConstants.Main_RENT_CAR:
			   RentUser rent=new RentUser();
			   rent.insertRentInfo();
			   printMain();
			   break; // 차량대여
		   case CartRentConstants.Main_Return_CAR:
			   RentCarRe rentre=new RentCarRe();
			   rentre.returnRentInfo();
			   printMain();
			   break; // 차량반납
		   case CartRentConstants.Main_Show_RENT_STATUS:
			   RentCarService rentinfo=new RentCarService();
			   rentinfo.getRentInfo();
			   printMain();
			   break; // 대여현황보기
		   case 6:System.exit(0);break;
		   default:errorMessage(menu_num);break;	
		  }
	  }
	  /**
	   * 고객정보 서브메뉴
	 * @throws Exception 
	   */
	  public void printCustomerSubMenu() throws Exception{
			
		  System.out.println("\n=============MENU=============");
		  System.out.println("== 1.고객등록 2.고객목록 3.고객검색 4.고객정보수정 5.고객정보삭제 0 메인메뉴  ==");

		  inputNum  = inputScan.nextInt();  // 메뉴 번호 입력		 
			
		  switch(inputNum){
			
		  case CartRentConstants.INSERT_CUSTOMER:
			  //고객등록 실행
			  RentCarUserJoin join=new RentCarUserJoin();
			  join.insertUserInfo();
			  printCustomerSubMenu(); //서브메뉴 다시 출력
			  break; // 고객정보 하위 메뉴 출력 
		  case CartRentConstants.SHOW_CUS_INFO: 
			  //고객목록 출력
			  RentCarService service=new RentCarService();
			  service.getUserAll();
			  printCustomerSubMenu();
			  break;
		  case CartRentConstants.SEARCH_CUSTOMER:
			  //고객검색 실행
			  RentCarService search=new RentCarService();
			  search.getUser();
			  printCustomerSubMenu();
			  break;
		  case CartRentConstants.MODIFIY_CUSTOMER:
			  //고객정보 수정 실행
			  RentCarUserUpdate update=new RentCarUserUpdate();
			  update.updateUserInfo();
			  printCustomerSubMenu();
			  break;
		  case CartRentConstants.DELETE_CUSTOMER:
			  //고객삭제 실행
			  RentCarUserDelete delete=new RentCarUserDelete();
			  delete.deleteUserInfo();
			  printCustomerSubMenu();
			  break;
		  case 6:
			  System.exit(0);
			  break;
		  default:
			  errorMessage(inputNum);
			  
		  }		
			
		  mainDisplay();
	  }
	  
	  
	  ////////////에러 메세지 ///////////////////////////////////
	  private  void  errorMessage(int errorMenu) throws Exception{			
		  String errorLine = errorMenu + " 번은 메뉴에 없습니다.다시한번 정확한 입력 바랍니다!";
		  System.out.println();
		  System.out.println(errorLine);
		  System.out.println();
		  mainDisplay();
	  }
	 
}
