import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFrame extends JFrame {

	JButton btn = new JButton("Show");
	JButton del = new JButton("Delete");
	
	JPanel panel = new JPanel();
	
	JTextField tekst = new JTextField(20);
		
		public NewFrame() {
		
			this.setVisible(true);
			this.setSize(420,420);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			// this.setLayout(new FlowLayout());
			this.setLayout(new BorderLayout());
			
			panel.setLayout(new GridLayout(1,2));
			panel.add(btn);
			panel.add(del);
			
			this.add(tekst, BorderLayout.NORTH);
			this.add(panel, BorderLayout.SOUTH);
			
			btn.addActionListener(new ShowAction());
			
		}
		
		class ShowAction implements ActionListener {
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				System.out.println(tekst.getText());
		
		}
	
	}

}