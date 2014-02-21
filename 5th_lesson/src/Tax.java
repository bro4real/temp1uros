
public class Tax {
	
	double grossIncome;
	String state;
	int dependents;
	static int customerCount;
	
	//constructor
	Tax (double gi, String st, int depen) {
		grossIncome = gi;
		state = st;
		dependents = depen;
		customerCount++;
		System.out.println("Preparing the tax data for "
				+ "customer #" + customerCount);
	}
	
	double calcTax(){
		double stateTax = 0;
		if (grossIncome < 30000) {
			stateTax = grossIncome*0.05;
		} else {
			stateTax = grossIncome*0.06;
		}
		return stateTax;
	}
	
	static void convertAndPrint(double tax) {
		System.out.println("Tax converted to euros equals: " + tax/1.25 + "€");
	}
}
