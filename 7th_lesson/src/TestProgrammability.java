import com.practicaljava.lesson6.Phone;
import com.practicaljava.lesson6.Programmable.Device;
import com.practicaljava.lesson7.Android;
import com.practicaljava.lesson7.Mobile;
import com.practicaljava.lesson7.StationaryPhone;
import com.practicaljava.lesson7.iPhone;


public class TestProgrammability {

	private static final String NAME_OF_THE_BEAST = "iPhone 5";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Phone[] p = new Phone[8];
		p[0] = new StationaryPhone("Homephone");
		p[1] = new Mobile("Old Brick");
		p[2] = new Android("Huawei");
		p[3] = new iPhone("iPhone 4");
		p[4] = new StationaryPhone("Bedroomphone");
		p[5] = new Mobile("Nokia_3210");
		p[6] = new Android("Sony Z1");
		p[7] = new iPhone("iPhone 5");
		
		for(int i=0 ; i<8 ; i++){
			
			Android currentAndroid;
			iPhone currentiPhone;
			
			if(p[i] instanceof Android){
				currentAndroid = (Android) p[i];
				currentAndroid.program(Device.Dell);
			} else if (p[i] instanceof iPhone && p[i].getName()==NAME_OF_THE_BEAST) {
				currentiPhone = (iPhone) p[i];
				currentiPhone.program(Device.Dell);
			} else if (p[i] instanceof iPhone) {
				currentiPhone = (iPhone) p[i];
				currentiPhone.program(Device.Mac);
			} else {
				System.out.println("Sorry, cannot program " + p[i].getName());
			}
			
		}
		
	}

}
