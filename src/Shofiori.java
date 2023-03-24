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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Shofiori extends JFrame{

	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;
	int id = -1;

	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);

	JPanel upPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel firstNameLabel = new JLabel(" Име:");
	JLabel lastNameLabel = new JLabel(" Фамилия:");
	JLabel ageLabel = new JLabel(" Години:");
	JLabel salaryLabel = new JLabel(" Заплата:");
	JLabel truckIdLabel = new JLabel(" Камион:");
	JLabel companyIdLabel = new JLabel(" Производител:");

	JTextField firstNameTextField = new JTextField(20);
	JTextField lastNameTextField = new JTextField(20);
	JTextField ageTextField = new JTextField(20);
	JTextField salaryTextField = new JTextField(20);
	JTextField truckIdTextField = new JTextField(20);
	JTextField companyIdTextField = new JTextField(20);
	
	JComboBox jComboBox = new JComboBox();

	JButton addButton = new JButton("add");
	JButton delButton = new JButton("del");
	JButton editButton = new JButton("edit");
	JButton searchButton = new JButton("search");

	public Shofiori() {

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
		upPanel.add(truckIdLabel);
		upPanel.add(jComboBox);
//		upPanel.add(truckIdTextField);
		upPanel.add(companyIdLabel);
		upPanel.add(companyIdTextField);


		midPanel.add(addButton);
		midPanel.add(delButton);
		midPanel.add(editButton);
		midPanel.add(searchButton);
		
		addButton.addActionListener(new AddAction());
		delButton.addActionListener(new DelAction());
		editButton.addActionListener(new EditAction());
		searchButton.addActionListener(new SearchAction());

		scroller.setPreferredSize(new Dimension(720,200));
		refreshTable("people");
		downPanel.add(scroller);
		table.addMouseListener(new MouseClick());

		this.setVisible(false);
	}

	// чисти полетата
	public void clearForm() {
		
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			ageTextField.setText("");
			salaryTextField.setText("");
			truckIdTextField.setText("");
			companyIdTextField.setText("");
	}
	
	// рефрешва таблицата
	public void refreshTable(String tableName) {

		conn = DBConnector.getConnection();
		String sql = "SELECT * FROM " + tableName;

		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			table.setModel(new MyModel(result));
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	class MouseClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			int row = table.getSelectedRow();
			Object content = table.getValueAt(row, 0);

			// получава се id-то на записа след като се кликне веднъж върху него
			if (e.getClickCount() == 1) {
				id = Integer.parseInt(content.toString());
			}
			// попълват се данните от таблицата в горните полета след като се кликне 2 пъти върху запис
			if (e.getClickCount() == 2) {
				firstNameTextField.setText(table.getValueAt(row, 1).toString());
				lastNameTextField.setText(table.getValueAt(row, 2).toString());
				ageTextField.setText(table.getValueAt(row, 3).toString());
				salaryTextField.setText(table.getValueAt(row, 4).toString());
				truckIdTextField.setText(table.getValueAt(row, 5).toString());
				companyIdTextField.setText(table.getValueAt(row, 6).toString());
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}		
	}

	// добавяне на нов запис
	class AddAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			int age = Integer.parseInt(ageTextField.getText());
			float salary = Float.parseFloat(salaryTextField.getText());
			int truckId = Integer.parseInt(truckIdTextField.getText());
			int companyId = Integer.parseInt(companyIdTextField.getText());

			conn = DBConnector.getConnection();
			String sql = "INSERT INTO people VALUES(null,?,?,?,?,?,?);";

			try {
				state = conn.prepareStatement(sql);
				state.setString(1, firstName);
				state.setString(2,  lastName);
				state.setInt(3, age);
				state.setFloat(4, salary);
				state.setInt(5, truckId);
				state.setInt(6, companyId);
				state.execute();
				clearForm();
				refreshTable("people");
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// изтриване на запис
	class DelAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			conn = DBConnector.getConnection();
			String sql = "DELETE FROM people WHERE id=?";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);;
				state.execute();
				id = -1;
				refreshTable("people");
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// редактиране на запис
	class EditAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {		
			
			conn = DBConnector.getConnection();
			String sql = "UPDATE people SET fname = '" + firstNameTextField.getText() + 
						 "' , lname = '" + lastNameTextField.getText() + 
						 "', years = '" + ageTextField.getText() + 
						 "', salary = '" + salaryTextField.getText() +
						 "', truck_id = '" + truckIdTextField.getText() +
						 "', company_id = '" + companyIdTextField.getText() +
						 "' WHERE id = ?;";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				clearForm();
				refreshTable("people");
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// търсене според въведената информация в горните полета
	class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String firstNameS = "", lastNameS = "", ageS = "", salaryS = "", truckS = "", companyS = "";
			boolean isEmpty = true;
			
			if(!firstNameTextField.getText().equals("")) {
				firstNameS = "fname LIKE '%" + firstNameTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!lastNameTextField.getText().equals("")) {
				lastNameS = "lname LIKE '%" + lastNameTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!ageTextField.getText().equals("")) {
				ageS = "years LIKE '%" + ageTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!salaryTextField.getText().equals("")) { 
				salaryS = "salary >= '" + salaryTextField.getText() + "' AND "; isEmpty = false;
			}
			if(!truckIdTextField.getText().equals("")) {
				truckS = "truck_id LIKE '%" + truckIdTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!companyIdTextField.getText().equals("")) { 
				companyS = "company_id LIKE '%" + companyIdTextField.getText() + "%'"; isEmpty = false;
			}

			conn = DBConnector.getConnection();
			String sql = "SELECT * FROM people";
			
			if(!isEmpty) {
				sql = sql + " WHERE " + firstNameS + lastNameS + ageS + salaryS + truckS + companyS;
			}
			if(sql.substring(sql.length() - 4).equals("AND ")) {
				sql = sql.substring(0, sql.length() - 4);
			}
			if(!salaryS.equals("")) {
				sql = sql + " ORDER BY salary;";
			}
			
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				table.setModel(new MyModel(result));
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
	
	private void dropMenu() {
		String sql = "select * from people";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while (result.next()) {
				jComboBox.addItem(result.getString("trucks.TID"));
			}
		}
		catch (Exception e) {
		}
	}
}