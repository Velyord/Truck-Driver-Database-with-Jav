import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Search extends JFrame{
	
	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;

	JTable tableSearch = new JTable();
	JScrollPane scroller = new JScrollPane(tableSearch);

	JPanel upPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel firstNameLabel = new JLabel(" Име на шофьор:");
	JLabel lastNameLabel = new JLabel(" Фамилия:");
	JLabel ageLabel = new JLabel(" Години:");
	JLabel salaryLabel = new JLabel(" Заплата:");
	JLabel truckBrandLabel = new JLabel(" Марка на камион:");
	JLabel truckYearLabel = new JLabel(" Години:");
	JLabel truckStructureLabel = new JLabel(" Структура:");
	JLabel companyLabel = new JLabel(" Име на производител:");
	JLabel productLabel = new JLabel(" Продукт:");

	JTextField firstNameTextField = new JTextField(20);
	JTextField lastNameTextField = new JTextField(20);
	JTextField ageTextField = new JTextField(20);
	JTextField salaryTextField = new JTextField(20);
	JTextField truckBrandTextField = new JTextField(20);
	JTextField truckYearTextField = new JTextField(20);
	JTextField truckStructureTextField = new JTextField(20);
	JTextField companyTextField = new JTextField(20);
	JTextField productTextField = new JTextField(20);

	JButton searchButton = new JButton("search");
	
	public Search() {

		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new GridLayout(3,1));
		this.add(upPanel); // горна част
		this.add(midPanel); // средна част
		this.add(downPanel); // долна част

		upPanel.setLayout(new GridLayout(9,2));
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameTextField);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameTextField);
		upPanel.add(ageLabel);
		upPanel.add(ageTextField);
		upPanel.add(salaryLabel);
		upPanel.add(salaryTextField);
		upPanel.add(truckBrandLabel);
		upPanel.add(truckBrandTextField);
		upPanel.add(truckYearLabel);
		upPanel.add(truckYearTextField);
		upPanel.add(truckStructureLabel);
		upPanel.add(truckStructureTextField);
		upPanel.add(companyLabel);
		upPanel.add(companyTextField);
		upPanel.add(productLabel);
		upPanel.add(productTextField);

		midPanel.add(searchButton);

		searchButton.addActionListener(new SearchAction());
		
		scroller.setPreferredSize(new Dimension(1080,200));
		downPanel.add(scroller);

		this.setVisible(false);
	}

	// чисти полетата
	public void clearForm() {

		firstNameTextField.setText("");
		lastNameTextField.setText("");
		ageTextField.setText("");
		salaryTextField.setText("");
		truckBrandTextField.setText("");
		truckYearTextField.setText("");
		truckStructureTextField.setText("");
		companyTextField.setText("");
		productTextField.setText("");
	}
	
	// търсене според въведената информация в горните полета
	class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String firstNameS = "", lastNameS = "", ageS = "", salaryS = "", brandS = "", yearS = "", structureS = "", companyS = "", productS = "";
			boolean isEmpty = true;
			
			if(!firstNameTextField.getText().equals("")) {
				firstNameS = "fname LIKE '%" + firstNameTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!lastNameTextField.getText().equals(""))	{
				lastNameS = "lname LIKE '%" + lastNameTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!ageTextField.getText().equals("")) {
				ageS = "years LIKE '%" + ageTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!salaryTextField.getText().equals("")) {
				salaryS = "salary >= '" + salaryTextField.getText() + "' AND "; isEmpty = false;
			}
			if(!truckBrandTextField.getText().equals("")) {
				brandS = "brand LIKE '%" + truckBrandTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!truckYearTextField.getText().equals("")) {
				yearS = "year LIKE '%" + truckYearTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!truckStructureTextField.getText().equals("")) {
				structureS = "structure LIKE '%" + truckStructureTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!companyTextField.getText().equals("")) {
				companyS = "companyName LIKE '%" + companyTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!productTextField.getText().equals("")) {
				productS = "product LIKE '%" + productTextField.getText() + "%' "; isEmpty = false;
			}
			
			conn = DBConnector.getConnection();
			String sql = "SELECT fname, lname, years, salary, brand, year, structure, companyname, product " + 
						 "FROM people " + 
						 "JOIN trucks " + 
						 "ON people.truck_id = trucks.tid " + 
						 "JOIN company " + 
						 "ON people.company_id = company.company_id ";
			
			if(!isEmpty) { 
				sql = sql + " WHERE " + firstNameS + lastNameS + ageS + salaryS + brandS + yearS + structureS + companyS + productS;
			}
			if(sql.substring(sql.length() - 4).equals("AND ")) {
				sql = sql.substring(0, sql.length()-4);
			}
			if(!salaryS.equals("")) {
				sql = sql + " ORDER BY salary;";
			}
			
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				tableSearch.setModel(new MyModel(result));
				state.execute();
				clearForm();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}