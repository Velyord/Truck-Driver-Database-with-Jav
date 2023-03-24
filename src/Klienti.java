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

public class Klienti extends JFrame{
	
//	Connection conn = null;
//	PreparedStatement state = null;
//	ResultSet result = null;
//	int cid = -1;
//
//	JTable tableKlienti = new JTable();
//	JScrollPane scroller = new JScrollPane(tableKlienti);
//
//	JPanel upPanel = new JPanel();
//	JPanel midPanel = new JPanel();
//	JPanel downPanel = new JPanel();
//
//	JLabel clientNameLabel = new JLabel(" Име на клиент:");
//
//	JTextField clientNameTextField = new JTextField(20);
//
//	JButton addButton = new JButton("add");
//	JButton delButton = new JButton("del");
//	JButton editButton = new JButton("edit");
//	JButton searchButton = new JButton("search");
//	
//	public Klienti() {
//
//		this.setSize(500,500);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//		this.setLayout(new GridLayout(3,1));
//		this.add(upPanel); // горна част
//		this.add(midPanel); // средна част
//		this.add(downPanel); // долна част
//
//		upPanel.setLayout(new GridLayout(9,1));
//		upPanel.add(clientNameLabel);
//		upPanel.add(clientNameTextField);
//
//		midPanel.add(addButton);
//		midPanel.add(delButton);
//		midPanel.add(editButton);
//		midPanel.add(searchButton);
//		
//		addButton.addActionListener(new AddAction());
//		delButton.addActionListener(new DelAction());
//		editButton.addActionListener(new EditAction());
//		searchButton.addActionListener(new SearchAction());
//		
//		scroller.setPreferredSize(new Dimension(720,200));
//		refreshTable("clients");
//		downPanel.add(scroller);
//		tableKlienti.addMouseListener(new MouseClick());
//
//		this.setVisible(false);
//	}
//
//	// чисти полетата
//	public void clearForm() {
//
//		clientNameTextField.setText("");
//	}
//	
//	// рефрешва таблицата
//	public void refreshTable(String tableName) {
//
//		conn = DBConnector.getConnection();
//		String sql = "SELECT * FROM " + tableName;
//
//		try {
//			state = conn.prepareStatement(sql);
//			result = state.executeQuery();
//			tableKlienti.setModel(new MyModel(result));
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	class MouseClick implements MouseListener {
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//
//			int row = tableKlienti.getSelectedRow();
//			Object content = tableKlienti.getValueAt(row, 0);
//
//			// получава се id-то на записа след като се кликне веднъж върху него
//			if (e.getClickCount() == 1) {
//				cid = Integer.parseInt(content.toString());
//			}
//			// попълват се данните от таблицата в горните полета след като се кликне 2 пъти върху запис
//			if (e.getClickCount() == 2) {
//				clientNameTextField.setText(tableKlienti.getValueAt(row, 1).toString());
//			}
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent arg0) {}
//		@Override
//		public void mouseExited(MouseEvent arg0) {}
//		@Override
//		public void mousePressed(MouseEvent arg0) {}
//		@Override
//		public void mouseReleased(MouseEvent arg0) {}		
//	}
//
//	// добавяне на нов запис
//	class AddAction implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			String clientName = clientNameTextField.getText();
//
//			conn = DBConnector.getConnection();
//			String sql = "INSERT INTO clients VALUES(null,?);";
//
//			try {
//				state = conn.prepareStatement(sql);
//				state.setString(1, clientName);
//				state.execute();
//				clearForm();
//				refreshTable("clients");
//			} 
//			catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	// изтриване на запис
//	class DelAction implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//
//			conn = DBConnector.getConnection();
//			String sql = "DELETE FROM clients WHERE cid=?;";
//			
//			try {
//				state = conn.prepareStatement(sql);
//				state.setInt(1, cid);;
//				state.execute();
//				cid = -1;
//				refreshTable("clients");
//			}			
//			catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//	
//	// редактиране на запис
//	class EditAction implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {		
//			
//			conn = DBConnector.getConnection();
//			String sql = "UPDATE clients SET cname = '" + clientNameTextField.getText() + 
//						 "' WHERE cid = ?;";
//			
//			try {
//				state = conn.prepareStatement(sql);
//				state.setInt(1, cid);
//				state.execute();
//				clearForm();
//				refreshTable("clients");
//			}
//			catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//	
//	// търсене според въведената информация в горните полета
//	class SearchAction implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			
//			String clientName = clientNameTextField.getText();
//
//			conn = DBConnector.getConnection();
//			String sql = "SELECT * FROM clients WHERE cname LIKE '%" + clientName + "%' ;";
//			
//			try {
//				state = conn.prepareStatement(sql);
//				result = state.executeQuery();
//				tableKlienti.setModel(new MyModel(result));
//				state.execute();
//				clearForm();
//			}
//			catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
}