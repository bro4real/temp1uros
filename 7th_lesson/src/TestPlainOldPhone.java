import com.practicaljava.lesson6.Phone;
import com.practicaljava.lesson7.*;


public class TestPlainOldPhone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Phone[] p = new Phone[4];
		
		p[0] = new StationaryPhone("Homephone");
		p[1] = new Mobile("Old Brick");
		p[2] = new Android("Sony Z1");
		p[3] = new iPhone("iPhone 4");
		
		for (Phone phone : p){
			phone.call(5551234);
			//who actually owns this number?!?
		}

	}

}
