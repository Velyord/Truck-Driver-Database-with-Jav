import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
	Connection conn=null;
	PreparedStatement state=null;
	ResultSet result=null;
	int id=-1;
	
	
	JTabbedPane tab=new JTabbedPane();
	
	JPanel personPanel=new JPanel();
	JPanel carPanel=new JPanel();
	JPanel rentaPanel=new JPanel();
	JPanel spravka1Panel=new JPanel();
	JPanel spravka2Panel=new JPanel();
	
	//Person
	JTable personTable=new JTable();
	JScrollPane personScroll=new JScrollPane(personTable);
	
	JPanel personTopPanel=new JPanel();
	JPanel personMidPanel=new JPanel();
	JPanel personDownPanel=new JPanel();
	
	JLabel namePersonL=new JLabel("Име и фамилия:");
	JLabel telPersonL=new JLabel("Телефон:");
	JLabel egnPersonL=new JLabel("ЕГН:");
	
	JTextField namePersonTF=new JTextField();
	JTextField telPersonTF=new JTextField();
	JTextField egnPersonTF=new JTextField();
	
	JButton personAdd=new JButton("Дабави");
	JButton personDel=new JButton("Изтрий");
	JButton personEdit=new JButton("Промени");
	JButton personSearch=new JButton("Търсене по ЕГН");
	JButton personRefresh=new JButton("Обнови");
	
	//Car
	
	JTable carTable=new JTable();
	JScrollPane carScroll=new JScrollPane(carTable);
	
	JPanel carTopPanel=new JPanel();
	JPanel carMidPanel=new JPanel();
	JPanel carDownPanel=new JPanel();
	
	JLabel markaCarL=new JLabel("Марка:");
	JLabel nomerCarL=new JLabel("Номер:");
	JLabel yearCarL=new JLabel("Година:");
	
	JTextField markaCarTF=new JTextField();
	JTextField nomerCarTF=new JTextField();
	JTextField yearCarTF=new JTextField();
	
	JButton carAdd=new JButton("Дабави");
	JButton carDel=new JButton("Изтрий");
	JButton carEdit=new JButton("Промени");
	JButton carSearch=new JButton("Търсене по марка");
	JButton carRefresh=new JButton("Обнови");
	
	//Renta
	
	JTable rentaTable=new JTable();
	JScrollPane rentaScroll=new JScrollPane(rentaTable);
	
	JPanel rentaTopPanel=new JPanel();
	JPanel rentaMidPanel=new JPanel();
	JPanel rentaDownPanel=new JPanel();
	
	JLabel personRentaL=new JLabel("Клиент:");
	JLabel carRentaL=new JLabel("Кола:");
	JLabel dateRentaL=new JLabel("Дата:");
	JLabel cenaRentaL=new JLabel("Цена:");
	
	JComboBox<String> comboPerson=new JComboBox<String>();
	JComboBox<String> comboCar=new JComboBox<String>();
	JTextField dateRentaTF=new JTextField();
	JTextField cenaRentaTF=new JTextField();
	
	JButton rentaAdd=new JButton("Дабави");
	JButton rentaDel=new JButton("Изтрий");
	JButton rentaEdit=new JButton("Промени");
	JButton rentaSearch=new JButton("Търсене по цена");
	JButton rentaRefresh=new JButton("Обнови");
	
	
	//Spravka 1
	
	JTable spravka1Table=new JTable();
	JScrollPane spravka1Scroll=new JScrollPane(spravka1Table);
	
	JPanel spravka1TopPanel=new JPanel();
	JPanel spravka1MidPanel=new JPanel();
	JPanel spravka1DownPanel=new JPanel();
	
	JLabel kr1Spravka1L=new JLabel("Име на клиент:");
	JLabel kr2Spravka1L=new JLabel("Марка на кола:");
	
	JTextField kr1spravka1TF=new JTextField();
	JTextField kr2spravka1TF=new JTextField();
	
	JButton showSpravka1=new JButton("Покажи");
	
	
	
	//Spravka 2
	
	JTable spravka2Table=new JTable();
	JScrollPane spravka2Scroll=new JScrollPane(spravka2Table);
	
	JPanel spravka2TopPanel=new JPanel();
	JPanel spravka2MidPanel=new JPanel();
	JPanel spravka2DownPanel=new JPanel();
	
	JLabel kr1Spravka2L=new JLabel("Година на колата:");
	JLabel kr2Spravka2L=new JLabel("Телефон на клиент:");
	
	JTextField kr1spravka2TF=new JTextField();
	JTextField kr2spravka2TF=new JTextField();
	
	JButton showSpravka2=new JButton("Покажи");
	
	

	public MyFrame() {
		this.setSize(600, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tab.add(personPanel,"Клиенти");
		tab.add(carPanel,"Коли");
		tab.add(rentaPanel,"Наем");
		tab.add(spravka1Panel,"Справка по име и марка");
		tab.add(spravka2Panel,"Справка по година и телефон");
		
		this.add(tab);
		
		//Person
		personPanel.setLayout(new GridLayout(3,1));
		
		personTopPanel.setLayout(new GridLayout(3,2));
		personTopPanel.add(namePersonL);
		personTopPanel.add(namePersonTF);
		personTopPanel.add(telPersonL);
		personTopPanel.add(telPersonTF);
		personTopPanel.add(egnPersonL);
		personTopPanel.add(egnPersonTF);
		
		personMidPanel.add(personAdd);
		personMidPanel.add(personDel);
		personMidPanel.add(personEdit);
		personMidPanel.add(personSearch);
		personMidPanel.add(personRefresh);
		
		personScroll.setPreferredSize(new Dimension(450, 130));
		personDownPanel.add(personScroll);
		
		
		personPanel.add(personTopPanel);
		personPanel.add(personMidPanel);
		personPanel.add(personDownPanel);
		
		personTable.addMouseListener(new MouseActionPerson());
		
		personAdd.addActionListener(new AddActionPerson());
		personDel.addActionListener(new DeleteActionPerson());
		personEdit.addActionListener(new UpdateActionPerson());
		personSearch.addActionListener(new SearchActionPerson());
		personRefresh.addActionListener(new RefreshActionPerson());
		
		refreshTable("person", personTable);
		// Car
		carPanel.setLayout(new GridLayout(3,1));
		
		carTopPanel.setLayout(new GridLayout(3,2));
		carTopPanel.add(markaCarL);
		carTopPanel.add(markaCarTF);
		carTopPanel.add(nomerCarL);
		carTopPanel.add(nomerCarTF);
		carTopPanel.add(yearCarL);
		carTopPanel.add(yearCarTF);
		
		carMidPanel.add(carAdd);
		carMidPanel.add(carDel);
		carMidPanel.add(carEdit);
		carMidPanel.add(carSearch);
		carMidPanel.add(carRefresh);
		
		carScroll.setPreferredSize(new Dimension(450, 130));
		carDownPanel.add(carScroll);
		
		carPanel.add(carTopPanel);
		carPanel.add(carMidPanel);
		carPanel.add(carDownPanel);
		
		carTable.addMouseListener(new MouseActionCar());
		
		carAdd.addActionListener(new AddActionCar());
		carDel.addActionListener(new DeleteActionCar());
		carEdit.addActionListener(new UpdateActionCar());
		carSearch.addActionListener(new SearchActionCar());
		carRefresh.addActionListener(new RefreshActionCar());
		
		refreshTable("car", carTable);
		
		//Renta----------------------------------------------------------------
		rentaPanel.setLayout(new GridLayout(3,1));
		
		rentaTopPanel.setLayout(new GridLayout(4,2));
		rentaTopPanel.add(personRentaL);
		rentaTopPanel.add(comboPerson);
		rentaTopPanel.add(carRentaL);
		rentaTopPanel.add(comboCar);
		rentaTopPanel.add(dateRentaL);
		rentaTopPanel.add(dateRentaTF);
		rentaTopPanel.add(cenaRentaL);
		rentaTopPanel.add(cenaRentaTF);
		
		rentaMidPanel.add(rentaAdd);
		rentaMidPanel.add(rentaDel);
		rentaMidPanel.add(rentaEdit);
		//rentaMidPanel.add(rentaSearch);
		//rentaMidPanel.add(rentaRefresh);
		
		rentaScroll.setPreferredSize(new Dimension(450, 130));
		rentaDownPanel.add(rentaScroll);
		
		rentaPanel.add(rentaTopPanel);
		rentaPanel.add(rentaMidPanel);
		rentaPanel.add(rentaDownPanel);
		
		rentaAdd.addActionListener(new AddActionRenta());
		rentaDel.addActionListener(new DeleteActionRenta());
		rentaEdit.addActionListener(new UpdateActionRenta());
		
		rentaTable.addMouseListener(new MouseActionRenta());
		
		refreshComboPerson();
		refreshComboCar();
		
		refreshTableRenta();
		
		
		//Spravka 1--------------------------------------------------------------------
		spravka1Panel.setLayout(new GridLayout(3,1));
		
		spravka1TopPanel.setLayout(new GridLayout(2,2));
		spravka1TopPanel.add(kr1Spravka1L);
		spravka1TopPanel.add(kr1spravka1TF);
		spravka1TopPanel.add(kr2Spravka1L);
		spravka1TopPanel.add(kr2spravka1TF);
		
		spravka1MidPanel.add(showSpravka1);
		
		spravka1Scroll.setPreferredSize(new Dimension(450, 130));
		spravka1DownPanel.add(spravka1Scroll);
		
		spravka1Panel.add(spravka1TopPanel);
		spravka1Panel.add(spravka1MidPanel);
		spravka1Panel.add(spravka1DownPanel);
		
		showSpravka1.addActionListener(new ActionSpravka1());
		
		//Spravka 2
		spravka2Panel.setLayout(new GridLayout(3,1));
		
		spravka2TopPanel.setLayout(new GridLayout(2,2));
		spravka2TopPanel.add(kr1Spravka2L);
		spravka2TopPanel.add(kr1spravka2TF);
		spravka2TopPanel.add(kr2Spravka2L);
		spravka2TopPanel.add(kr2spravka2TF);
		
		spravka2MidPanel.add(showSpravka2);
		
		spravka2Scroll.setPreferredSize(new Dimension(450, 130));
		spravka2DownPanel.add(spravka2Scroll);
		
		spravka2Panel.add(spravka2TopPanel);
		spravka2Panel.add(spravka2MidPanel);
		spravka2Panel.add(spravka2DownPanel);
		
		showSpravka2.addActionListener(new ActionSpravka2());
		
		
		this.setVisible(true);
	}// end construktor
	
	public void refreshTable(String name, JTable table) {
		conn=DBConnection.getConnection();
		String str="select * from "+name;
		
		try {
			state=conn.prepareStatement(str);
			result=state.executeQuery();
			table.setModel(new MyModel(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshTableRenta() {
		conn=DBConnection.getConnection();
		String str="SELECT R.ID, Name, marka, date, cena FROM Renta R JOIN Person P ON R.ID_person = P.ID JOIN Car C ON R.ID_car = C.ID";
		
		try {
			state=conn.prepareStatement(str);
			result=state.executeQuery();
			rentaTable.setModel(new MyModel(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshComboPerson() {
		comboPerson.removeAllItems();
		
		String sql="select id, name from person";
		conn=DBConnection.getConnection();
		String item="";
		
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				item=result.getObject(1).toString()+"."+result.getObject(2).toString();
				comboPerson.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshComboCar() {
		comboCar.removeAllItems();
		
		String sql="select id, marka from car";
		conn=DBConnection.getConnection();
		String item="";
		
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				item=result.getObject(1).toString()+"."+result.getObject(2).toString();
				comboCar.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class AddActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String sql="insert into person values(null,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, namePersonTF.getText());
				state.setString(2, telPersonTF.getText());
				state.setString(3, egnPersonTF.getText());
				state.execute();
				refreshTable("person", personTable);
				refreshComboPerson();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			namePersonTF.setText("");
			telPersonTF.setText("");
			egnPersonTF.setText("");
			
		}
		
	}
	
	class DeleteActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String sql="delete from person where id=?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				refreshTable("person", personTable);
				id=-1;
				refreshComboPerson();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			namePersonTF.setText("");
			telPersonTF.setText("");
			egnPersonTF.setText("");
			
		}
		
	}
	
	class UpdateActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String sql="update person set name=?, telefon=?, egn=? where id=?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, namePersonTF.getText());
				state.setString(2, telPersonTF.getText());
				state.setString(3, egnPersonTF.getText());
				state.setInt(4, id);
				state.execute();
				refreshTable("person", personTable);
				id=-1;
				refreshComboPerson();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			namePersonTF.setText("");
			telPersonTF.setText("");
			egnPersonTF.setText("");
			
		}
		
	}
	
	class SearchActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String str="select * from Person where egn=?";
			
			try {
				state=conn.prepareStatement(str);
				state.setString(1,egnPersonTF.getText());
				result=state.executeQuery();
				personTable.setModel(new MyModel(result));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	class RefreshActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			refreshTable("person", personTable);
		}
		
	}
	
	class MouseActionPerson implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row=personTable.getSelectedRow();
			id=Integer.parseInt(personTable.getValueAt(row, 0).toString());
			
			namePersonTF.setText(personTable.getValueAt(row, 1).toString());
			telPersonTF.setText(personTable.getValueAt(row, 2).toString());
			egnPersonTF.setText(personTable.getValueAt(row, 3).toString());
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class MouseActionCar implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row=carTable.getSelectedRow();
			id=Integer.parseInt(carTable.getValueAt(row, 0).toString());
			
			markaCarTF.setText(carTable.getValueAt(row, 1).toString());
			nomerCarTF.setText(carTable.getValueAt(row, 2).toString());
			yearCarTF.setText(carTable.getValueAt(row, 3).toString());
			
		
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class AddActionCar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="insert into car values(null,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, markaCarTF.getText());
				state.setString(2, nomerCarTF.getText());
				state.setString(3, yearCarTF.getText());
				state.execute();
				refreshTable("car", carTable);
				refreshComboCar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			markaCarTF.setText("");
			nomerCarTF.setText("");
			yearCarTF.setText("");
			
		}
		
	}
	
	class DeleteActionCar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String sql="delete from car where id=?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				refreshTable("car", carTable);
				id=-1;
				refreshComboCar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			markaCarTF.setText("");
			nomerCarTF.setText("");
			yearCarTF.setText("");
			
		}
		
	}
	
	class UpdateActionCar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="update car set marka=?, nomer=?, year=? where id=?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, markaCarTF.getText());
				state.setString(2, nomerCarTF.getText());
				state.setString(3, yearCarTF.getText());
				state.setInt(4, id);
				state.execute();
				refreshTable("car", carTable);
				id=-1;
				refreshComboCar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			markaCarTF.setText("");
			nomerCarTF.setText("");
			yearCarTF.setText("");
			
		}
		
	}
	
	class SearchActionCar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			conn=DBConnection.getConnection();
			String str="select * from car where marka=?";
			
			try {
				state=conn.prepareStatement(str);
				state.setString(1,markaCarTF.getText());
				result=state.executeQuery();
				carTable.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class RefreshActionCar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			refreshTable("car", carTable);
		}
		
	}
	
	class MouseActionRenta implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row=rentaTable.getSelectedRow();
			id=Integer.parseInt(rentaTable.getValueAt(row, 0).toString());
			
			dateRentaTF.setText(rentaTable.getValueAt(row, 3).toString());
			cenaRentaTF.setText(rentaTable.getValueAt(row, 4).toString());
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class AddActionRenta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String itemPersonCombo=comboPerson.getSelectedItem().toString();
			String itemCarCombo=comboCar.getSelectedItem().toString();
			conn=DBConnection.getConnection();
			String sql="insert into renta values(null,?,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setInt(1, Integer.parseInt(itemPersonCombo.substring(0, itemPersonCombo.indexOf('.'))));
				state.setInt(2, Integer.parseInt(itemCarCombo.substring(0, itemCarCombo.indexOf('.'))));
				state.setString(3, dateRentaTF.getText());
				state.setDouble(4, Double.parseDouble(cenaRentaTF.getText()));
				state.execute();
				refreshTableRenta();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dateRentaTF.setText("");
			cenaRentaTF.setText("");
			}
		}
	
	class DeleteActionRenta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="delete from renta where id=?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				refreshTableRenta();
				id=-1;
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dateRentaTF.setText("");
			cenaRentaTF.setText("");
			
		}
		
	}
	
	class UpdateActionRenta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String itemPersonCombo=comboPerson.getSelectedItem().toString();
			String itemCarCombo=comboCar.getSelectedItem().toString();
			
			conn=DBConnection.getConnection();
			String sql="update renta set id_person=?, id_car=?, date=?, cena=? where id=?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setInt(1, Integer.parseInt(itemPersonCombo.substring(0, itemPersonCombo.indexOf('.'))));
				state.setInt(2, Integer.parseInt(itemCarCombo.substring(0, itemCarCombo.indexOf('.'))));
				state.setString(3, dateRentaTF.getText());
				state.setDouble(4, Double.parseDouble(cenaRentaTF.getText()));
				state.setInt(5, id);
				state.execute();
				refreshTableRenta();
				id=-1;
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dateRentaTF.setText("");
			cenaRentaTF.setText("");
			
		}
		
	}
	
	class ActionSpravka1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String sql="SELECT Name, marka, date, cena FROM Renta R JOIN Person P ON R.ID_person = P.ID JOIN Car C ON R.ID_car = C.ID WHERE Name = ? And marka = ?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, kr1spravka1TF.getText());
				state.setString(2, kr2spravka1TF.getText());
				result=state.executeQuery();
				spravka1Table.setModel(new MyModel(result));
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			kr1spravka1TF.setText("");
			kr2spravka1TF.setText("");
			
		}
			
	}
	
	class ActionSpravka2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
			String sql="SELECT Name, marka, date, cena " + 
					"FROM Renta R JOIN Person P " + 
					"ON R.ID_person = P.ID " + 
					"JOIN Car C " + 
					"ON R.ID_car = C.ID " + 
					"WHERE year = ? AND telefon = ?";
			
			try {
				state=conn.prepareStatement(sql);
				state.setInt(1, Integer.parseInt(kr1spravka2TF.getText()));
				state.setString(2, kr2spravka2TF.getText());
				result=state.executeQuery();
				spravka2Table.setModel(new MyModel(result));
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			kr1spravka2TF.setText("");
			kr2spravka2TF.setText("");
			
			
		}
		
	}
		
	
	

}// end MyFrame
