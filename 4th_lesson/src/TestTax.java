
public class TestTax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			double grossIncome;
			String state;
			int dependents;
			
			grossIncome=50000;
			dependents=2;
			state="NJ";
			
			Tax t = new Tax(grossIncome, state, dependents);
			Tax t2 = new Tax(65000, "TX", 4);
			double yourTax = t.calcTax();
			double hisTax = t2.calcTax();
			//by now, it's too late to use counter
			//and the next two lines seemed lame:
			//Tax.convertAndPrint(yourTax);
			//Tax.convertAndPrint(hisTax);
			
			//these two also looked like an "easy-way-out"
			//System.out.println("Your tax is " + yourTax);
			//System.out.println("His tax is " + hisTax);
			
	}

}
