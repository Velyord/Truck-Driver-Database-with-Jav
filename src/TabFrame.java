import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

	public class TabFrame extends JFrame {
	
	JTabbedPane tab = new JTabbedPane();
	
	JPanel tab1 = new JPanel();
//	JPanel tab2 = new JPanel();
//	JPanel tab3 = new JPanel();
	JPanel tab4 = new JPanel();
	JPanel tab5 = new JPanel();
	JPanel tab6 = new JPanel();
	
	Shofiori shofiori = new Shofiori();
//	Klienti klienti = new Klienti();
//	Raboti_s raboti_s = new Raboti_s();
	Kamioni kamioni = new Kamioni();
	Proizvoditel proizvoditel = new Proizvoditel();
	Search search = new Search();
	public TabFrame(){
		
		this.setSize(1280,720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tab1.add(shofiori.getContentPane());
//		tab2.add(klienti.getContentPane());
//		tab3.add(raboti_s.getContentPane());
		tab4.add(kamioni.getContentPane());
		tab5.add(proizvoditel.getContentPane());
		tab6.add(search.getContentPane());
		
		tab.addTab("�������", tab1);
//		tab.addTab("�������", tab2);
//		tab.addTab("������_�", tab3);
		tab.addTab("�������", tab4);
		tab.addTab("������������", tab5);
		tab.addTab("�������", tab6);
			
		this.add(tab);
		this.setVisible(true);	
	}
}