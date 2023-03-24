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

public class Kamioni extends JFrame{
	
	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;
	int tid = -1;

	JTable tableKamioni = new JTable();
	JScrollPane scroller = new JScrollPane(tableKamioni);

	JPanel upPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel truckBrandLabel = new JLabel(" Марка:");
	JLabel truckYearLabel = new JLabel(" Години:");
	JLabel truckStructureLabel = new JLabel(" Структура:");

	JTextField truckBrandTextField = new JTextField(20);
	JTextField truckYearTextField = new JTextField(20);
	JTextField truckStructureTextField = new JTextField(20);

	JButton addButton = new JButton("add");
	JButton delButton = new JButton("del");
	JButton editButton = new JButton("edit");
	JButton searchButton = new JButton("search");
	
	public Kamioni() {

		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new GridLayout(3,1));
		this.add(upPanel); // горна част
		this.add(midPanel); // средна част
		this.add(downPanel); // долна част

		upPanel.setLayout(new GridLayout(5,2));
		upPanel.add(truckBrandLabel);
		upPanel.add(truckBrandTextField);
		upPanel.add(truckYearLabel);
		upPanel.add(truckYearTextField);
		upPanel.add(truckStructureLabel);
		upPanel.add(truckStructureTextField);

		midPanel.add(addButton);
		midPanel.add(delButton);
		midPanel.add(editButton);
		midPanel.add(searchButton);
		
		addButton.addActionListener(new AddAction());
		delButton.addActionListener(new DelAction());
		editButton.addActionListener(new EditAction());
		searchButton.addActionListener(new SearchAction());
		
		scroller.setPreferredSize(new Dimension(720,200));
		refreshTable("trucks");
		downPanel.add(scroller);
		tableKamioni.addMouseListener(new MouseClick());

		this.setVisible(false);
	}

	// чисти полетата
	public void clearForm() {
		
		truckBrandTextField.setText("");
		truckYearTextField.setText("");
		truckStructureTextField.setText("");
	}
	
	// рефрешва таблицата
	public void refreshTable(String tableName) {

		conn = DBConnector.getConnection();
		String sql = "SELECT * FROM " + tableName;

		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			tableKamioni.setModel(new MyModel(result));
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

			int row = tableKamioni.getSelectedRow();
			Object content = tableKamioni.getValueAt(row, 0);

			// получава се id-то на записа след като се кликне веднъж върху него
			if (e.getClickCount() == 1) {
				tid = Integer.parseInt(content.toString());
			}
			// попълват се данните от таблицата в горните полета след като се кликне 2 пъти върху запис
			if (e.getClickCount() == 2) {
				truckBrandTextField.setText(tableKamioni.getValueAt(row, 1).toString());
				truckYearTextField.setText(tableKamioni.getValueAt(row, 2).toString());
				truckStructureTextField.setText(tableKamioni.getValueAt(row, 3).toString());
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

			String truckBrand = truckBrandTextField.getText();
			int truckYear = Integer.parseInt(truckYearTextField.getText());
			String truckStructure = truckStructureTextField.getText();

			conn = DBConnector.getConnection();
			String sql = "INSERT INTO trucks VALUES(null,?,?,?);";

			try {
				state = conn.prepareStatement(sql);
				state.setString(1, truckBrand);
				state.setInt(2, truckYear);
				state.setString(3, truckStructure);
				state.execute();
				clearForm();
				refreshTable("trucks");
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
			String sql = "DELETE FROM trucks WHERE tid=?;";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, tid);;
				state.execute();
				tid = -1;
				refreshTable("trucks");
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
			String sql = "UPDATE trucks SET brand = '" + truckBrandTextField.getText() + 
						 "', year = '" + truckYearTextField.getText() + 
						 "', structure = '" + truckStructureTextField.getText() +
						 "' WHERE tid = ?;";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, tid);
				state.execute();
				clearForm();
				refreshTable("trucks");
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
			
			String brandS = "", yearS = "", structureS = "";
			boolean isEmpty = true;
			
			if(!truckBrandTextField.getText().equals("")) {
				brandS = "brand LIKE '%" + truckBrandTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!truckYearTextField.getText().equals("")) {
				yearS = "year LIKE '%" + truckYearTextField.getText() + "%' AND "; isEmpty = false;
			}
			if(!truckStructureTextField.getText().equals("")) {
				structureS = "structure LIKE '%" + truckStructureTextField.getText() + "%' "; isEmpty = false;
			}

			conn = DBConnector.getConnection();
			String sql = "SELECT * FROM trucks";
			
			if(!isEmpty) {
				sql = sql + " WHERE " + brandS + yearS + structureS;
			}			
			if(sql.substring(sql.length() - 4).equals("AND ")) {
				sql = sql.substring(0, sql.length() - 4);
			}
			
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				tableKamioni.setModel(new MyModel(result));
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