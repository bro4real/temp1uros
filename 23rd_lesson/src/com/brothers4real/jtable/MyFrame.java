package com.brothers4real.jtable;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class MyFrame extends JFrame implements TableModelListener {

	MyTableModel myTableModel;
	JTable myTable;

	MyFrame(String winTitle) {
		super(winTitle);

		myTableModel = new MyTableModel();
		myTable = new JTable(myTableModel);

		TableColumn myColumn = myTable.getColumnModel().getColumn(1);
		// Add the JTable to frame and enable scrolling
		add(myTable);

		// Register an event listener
		myTableModel.addTableModelListener(this);

	}

	public static void main(String args[]) {
		MyFrame myFrame = new MyFrame("Homework step-by-step");
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	class MyTableModel extends AbstractTableModel {
		
		ArrayList<HomeworkTask> myData = new ArrayList<HomeworkTask>();

		String[] orderColNames = { "task No.", "Description", "Duration" };

		MyTableModel() {
			
			myData.add(new HomeworkTask(1, "coding annotations and proper processor (HW for Lesson 24)" , "an hour"));
			myData.add(new HomeworkTask(2, "writing code to validate the length of command line arguments array " , "10 minutes"));
			myData.add(new HomeworkTask(3, "checking if the class given in cmd line actually exists in our package " , "six hours"));
			myData.add(new HomeworkTask(4, "realizing that try/catch block does part no.3 & feeling like a donkey " , "ever since"));
		}

		public int getColumnCount() {
			int i = 0;
			try {
				Class valueObject = Class.forName("HomeworkTask");
				i = valueObject.getFields().length;
			} catch (Exception e) {
				e.printStackTrace();
			return 3;
			}
			return i;
			
		}

		public int getRowCount() {
			return myData.size();

		}

		public Object getValueAt(int row, int col) {

			switch (col) {
			case 0: // col 1
				return myData.get(row).taskID;
			case 1: // col 2
				return myData.get(row).taskDescription;
			case 2: // col 3
				return myData.get(row).minutesNeeded;
			default:
				return "";
			}

		}

		public String getColumnName(int col) {
			return orderColNames[col];
		}

		public boolean isCellEditable(int row, int col) {

				return false;
			
		}

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
