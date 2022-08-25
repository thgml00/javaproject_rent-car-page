package car.rent.main;

import car.rent.contrl.ManageClassControl;

public class RentCarMain {

	public static void main(String[] args) throws Exception {
		
		ManageClassControl service = new ManageClassControl(null);
		service.mainDisplay();
	}

}
