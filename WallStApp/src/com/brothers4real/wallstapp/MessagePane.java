package com.brothers4real.wallstapp;


import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class MessagePane extends JPanel implements IObserver {
	
	private int cashAvailable = 5000;
	private int numOfStocks = 0;
	
	private final static String NO_STOCK = "You haven't got any stocks to sell.";
	private final static String NO_MONEY = "You haven't got enough money.";
	
	private JTextArea tradeInfo;
	private JLabel wallet;
	private JLabel stockPortfolio;
	
	// Label to be displayed in case user
	// tries to buy without enough resources
	// or sell stock that he/she doesn't own.
	//private JLabel errorMsg = new JLabel();
	
	public MessagePane() {
		
		tradeInfo = new JTextArea("Start trading when you smell the profit!", 9, 25);
		tradeInfo.setEditable(false);
		
		// Make trade info scrollable:
		JScrollPane scrollPane = new JScrollPane(tradeInfo);
		
		wallet = new JLabel("You have " + cashAvailable + "$ available for trading.", JLabel.LEFT);
		stockPortfolio = new JLabel(NO_STOCK, JLabel.LEFT);
		
		// populate this JPanel:
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(scrollPane);
		add(wallet);
		add(stockPortfolio);
	
	}
	
	static private void validateTrade(int clickedId, int money, int stockPrice, int numberOfStocks) 
							throws NotEnoughMoneyException, NoStock4SaleException {
		if(clickedId==1 && money < stockPrice){
			throw new NotEnoughMoneyException(NO_MONEY);
		} else if (clickedId==2 && numberOfStocks == 0){
			throw new NoStock4SaleException(NO_STOCK);
		}
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void update(int clickedButtonId, Date date, String symbol, int value) {
		// TODO Auto-generated method stub
		
		String action = null;
		
		try {
			validateTrade(clickedButtonId, cashAvailable, value, numOfStocks);
			
			if (clickedButtonId == 1){
				action= "BOUGHT";
				numOfStocks++;
				cashAvailable -= value;
			} else if (clickedButtonId == 2){ // I put "else if" in case I need another button in the future!
				action = "SOLD";
				numOfStocks--;
				cashAvailable += value;
			}
			tradeInfo.setText(tradeInfo.getText() + "\nOn " + date.toLocaleString() + 
					" you "+action+ " an " + symbol + " stock for $" + value);
			
			wallet.setText("You have " + String.valueOf(cashAvailable) + "$ available for trading.");
			stockPortfolio.setText("You have " + numOfStocks + " " + symbol + " stock in your portfolio.");
			
		} catch (NotEnoughMoneyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			wallet.setText(e.getMessage() + " All you've got is $" + cashAvailable);
		} catch (NoStock4SaleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stockPortfolio.setText(e.getMessage());
		}

	}

}