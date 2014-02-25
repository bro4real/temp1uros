import com.practicaljava.lesson7.Smartphone;
import com.practicaljava.lesson7.Android;
import com.practicaljava.lesson7.iPhone;


public class TestSmartphone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Smartphone[] p = new Smartphone[3];
		
		p[0] = new Android("Sony Z1");
		p[1] = new iPhone("iPhone 5");
		p[2] = new Android("Huawei");
		
		for (Smartphone s: p){
			s.browseWeb();
		}

	}

}
