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

public class Raboti_s extends JFrame{

//	Connection conn = null;
//	PreparedStatement state = null;
//	ResultSet result = null;
//	int id = -1;
//
//	JTable tableRaboti = new JTable();
//	JScrollPane scroller = new JScrollPane(tableRaboti);
//
//	JPanel upPanel = new JPanel();
//	JPanel midPanel = new JPanel();
//	JPanel downPanel = new JPanel();
//
//	JLabel idLabel = new JLabel(" Шофьор:");
//	JLabel cidLabel = new JLabel(" Клиент:");
//	JLabel prodajbiLabel = new JLabel(" Продажби:");
//
//	JTextField idTextField = new JTextField(20);
//	JTextField cidTextField = new JTextField(20);
//	JTextField prodajbiTextField = new JTextField(20);
//
//	JButton addButton = new JButton("add");
//	JButton delButton = new JButton("del");
//	JButton editButton = new JButton("edit");
//	JButton searchButton = new JButton("search");
//
//	public Raboti_s() {
//
//		this.setSize(500,500);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//		this.setLayout(new GridLayout(3,1));
//		this.add(upPanel); // горна част
//		this.add(midPanel); // средна част
//		this.add(downPanel); // долна част
//
//		upPanel.setLayout(new GridLayout(5,2));
//		upPanel.add(idLabel);
//		upPanel.add(idTextField);
//		upPanel.add(cidLabel);
//		upPanel.add(cidTextField);
//		upPanel.add(prodajbiLabel);
//		upPanel.add(prodajbiTextField);
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
//		refreshTable("raboti_s");
//		downPanel.add(scroller);
//		tableRaboti.addMouseListener(new MouseClick());
//
//		this.setVisible(false);
//	}
//
//	// чисти полетата
//	public void clearForm() {
//
//		idTextField.setText("");
//		cidTextField.setText("");
//		prodajbiTextField.setText("");
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
//			tableRaboti.setModel(new MyModel(result));
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
//			int row = tableRaboti.getSelectedRow();
//			Object content = tableRaboti.getValueAt(row, 0);
//
//			// получава се id-то на записа след като се кликне веднъж върху него
//			if (e.getClickCount() == 1) {
//				id = Integer.parseInt(content.toString());
//			}
//
//			// попълват се данните от таблицата в горните полета след като се кликне 2 пъти върху запис
//			if (e.getClickCount() == 2) {
//				idTextField.setText(tableRaboti.getValueAt(row, 0).toString());
//				cidTextField.setText(tableRaboti.getValueAt(row, 1).toString());
//				prodajbiTextField.setText(tableRaboti.getValueAt(row, 2).toString());
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
//
//	}
//
//	// добавяне на нов запис
//	class AddAction implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			int id = Integer.parseInt(idTextField.getText());
//			int cid = Integer.parseInt(cidTextField.getText());
//			int prodajbi = Integer.parseInt(prodajbiTextField.getText());
//
//			conn = DBConnector.getConnection();
//			String sql = "INSERT INTO raboti_s VALUES(?,?,?);";
//
//			try {
//				state = conn.prepareStatement(sql);
//				state.setInt(1, id);
//				state.setInt(2, cid);
//				state.setInt(3, prodajbi);
//				state.execute();
//				clearForm();
//				refreshTable("raboti_s");
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
//			String sql = "DELETE FROM raboti_s WHERE id=?";
//			try {
//				state = conn.prepareStatement(sql);
//				state.setInt(1, id);;
//				state.execute();
//				id = -1;
//				refreshTable("raboti_s");
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
//			String sql = "UPDATE raboti_s SET id = '" + idTextField.getText() + 
//						 "', cid = '" + cidTextField.getText() + 
//						 "', prodajbi = '" + prodajbiTextField.getText() + 
//						 "' WHERE id = ?;";
//			
//			try {
//				state = conn.prepareStatement(sql);
//				state.setInt(1, id);
//				state.execute();
//				clearForm();
//				refreshTable("raboti_s");
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
//			String shofiorS = "", klientS = "", prodajbiS = "";
//			boolean isEmpty = true;
//			
//			if(!idTextField.getText().equals("")) {
//				shofiorS = "id LIKE '%" + idTextField.getText() + "%' AND "; isEmpty = false;
//			}
//			if(!cidTextField.getText().equals("")) {
//				klientS = "cid LIKE '%" + cidTextField.getText() + "%' AND "; isEmpty = false;
//			}
//			if(!prodajbiTextField.getText().equals("")) {
//				prodajbiS = "prodajbi >= '" + prodajbiTextField.getText() + "' "; isEmpty = false;
//			}
//
//			conn = DBConnector.getConnection();
//			String sql = "SELECT * FROM RABOTI_S";
//			
//			if(!isEmpty) {
//				sql = sql + " WHERE " + shofiorS + klientS + prodajbiS;
//			}
//			if(sql.substring(sql.length() - 4).equals("AND ")) {
//				sql = sql.substring(0, sql.length() - 4);
//			}
//			
//			try {
//				state = conn.prepareStatement(sql);
//				result = state.executeQuery();
//				tableRaboti.setModel(new MyModel(result));
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