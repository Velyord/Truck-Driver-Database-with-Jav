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

public class Proizvoditel extends JFrame{

	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;
	int pid = -1;

	JTable tableProizvoditel = new JTable();
	JScrollPane scroller = new JScrollPane(tableProizvoditel);

	JPanel upPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel companyLabel = new JLabel(" Производител:");
	JLabel productLabel = new JLabel(" Продукт:");

	JTextField companyTextField = new JTextField(20);
	JTextField productTextField = new JTextField(20);

	JButton addButton = new JButton("add");
	JButton delButton = new JButton("del");
	JButton editButton = new JButton("edit");
	JButton searchButton = new JButton("search");

	public Proizvoditel() {

		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new GridLayout(3,1));
		this.add(upPanel); // горна част
		this.add(midPanel); // средна част
		this.add(downPanel); // долна част

		upPanel.setLayout(new GridLayout(9,2));
		upPanel.add(companyLabel);
		upPanel.add(companyTextField);
		upPanel.add(productLabel);
		upPanel.add(productTextField);

		midPanel.add(addButton);
		midPanel.add(delButton);
		midPanel.add(editButton);
		midPanel.add(searchButton);
			
		addButton.addActionListener(new AddAction());
		delButton.addActionListener(new DelAction());
		editButton.addActionListener(new EditAction());
		searchButton.addActionListener(new SearchAction());

		scroller.setPreferredSize(new Dimension(720,200));
		refreshTable("company");
		downPanel.add(scroller);
		tableProizvoditel.addMouseListener(new MouseClick());

		this.setVisible(false);
	}

	// чисти полетата
	public void clearForm() {

		companyTextField.setText("");
		productTextField.setText("");
	}	
	// рефрешва таблицата
	public void refreshTable(String tableName) {

		conn = DBConnector.getConnection();
		String sql = "SELECT * FROM " + tableName;

		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			tableProizvoditel.setModel(new MyModel(result));
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

			int row = tableProizvoditel.getSelectedRow();
			Object content = tableProizvoditel.getValueAt(row, 0);

			// получава се id-то на записа след като се кликне веднъж върху него
			if (e.getClickCount() == 1) {
				pid = Integer.parseInt(content.toString());
			}
			// попълват се данните от таблицата в горните полета след като се кликне 2 пъти върху запис
			if (e.getClickCount() == 2) {
				companyTextField.setText(tableProizvoditel.getValueAt(row, 1).toString());
				productTextField.setText(tableProizvoditel.getValueAt(row, 2).toString());
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

			String company = companyTextField.getText();
			String product = productTextField.getText();

			conn = DBConnector.getConnection();
			String sql = "INSERT INTO company VALUES(null,?,?);";

			try {
				state = conn.prepareStatement(sql);
				state.setString(1, company);
				state.setString(2, product);
				state.execute();
				clearForm();
				refreshTable("company");
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
			String sql = "DELETE FROM company WHERE company_id=?";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, pid);;
				state.execute();
				pid = -1;
				refreshTable("company");
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
			String sql = "UPDATE company SET companyName = '" + companyTextField.getText() + 
						 "', product = '" + productTextField.getText() +  
						 "' WHERE company_id = ?;";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, pid);
				state.execute();
				clearForm();
				refreshTable("company");
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
			
			String companyS = "", productS ="";
			boolean isEmpty = true;
			
			if(!companyTextField.getText().equals("")) {
				companyS = "companyName LIKE '%" + companyTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!productTextField.getText().equals("")) {
				productS = "product LIKE '%" + productTextField.getText() + "%' "; isEmpty = false;
			}
				
			conn = DBConnector.getConnection();
			String sql = "SELECT * FROM company";
			
			if(!isEmpty) {
				sql = sql + " WHERE " + companyS +  productS;
			}			
			if(sql.substring(sql.length() - 4).equals("AND ")) {
				sql = sql.substring(0, sql.length() - 4);
			}
			
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				tableProizvoditel.setModel(new MyModel(result));
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