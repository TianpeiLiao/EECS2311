package venn;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.event.*;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	ArrayList<JLabel> list = new ArrayList<JLabel>();
	//private static JLabel lbl;
	private static int count = 0;
	private static int num = 0;
	public static JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox_1;
	private JLabel lblColor;
	private JLabel c1;
	private JLabel c3;
	private JLabel c2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					frame.setTitle("GoVenn");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(10, 11, 450, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		panel = new JPanel();
		panel.setBounds(10, 90, 1160, 200);
		panel.setOpaque(true);
		panel.setBackground(new Color(153, 204, 204));
		
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(153, 204, 204));
		panel_1.setBounds(470, 11, 700, 74);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("Shape:");
		lblNewLabel.setForeground(new Color(0, 51, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 53, 25);
		panel_1.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Circle", "Squre"}));
		comboBox.setBounds(73, 13, 74, 20);
		panel_1.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Create");
		
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		btnNewButton_1.setForeground(new Color(0, 51, 51));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(601, 40, 89, 23);
		panel_1.add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setForeground(new Color(0, 51, 51));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(194, 11, 74, 25);
		panel_1.add(lblNewLabel_1);
		
		comboBox_1 = new JComboBox();
		
		comboBox_1.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e)
			{
				try {
				if (comboBox_1.getSelectedIndex() == 1)
				{
					c3.setVisible(true);
				}
				else if(comboBox_1.getSelectedIndex() == 0){
					c3.setVisible(false);
				}
				}catch(Exception ecd) {}
			}
		});
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2", "3"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		comboBox_1.setBounds(266, 13, 89, 20);
		panel_1.add(comboBox_1);
		
		lblColor = new JLabel("Color:");
		lblColor.setForeground(new Color(0, 51, 51));
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblColor.setBounds(407, 11, 62, 25);
		panel_1.add(lblColor);
		
		c1 = new JLabel("c1");
		c1.setHorizontalAlignment(SwingConstants.CENTER);
		c1.setBackground(new Color(204, 204, 153));
		c1.setBounds(475, 11, 25, 25);
		c1.setOpaque(true);
		panel_1.add(c1);
		
		c3 = new JLabel("c3");
		c3.setHorizontalAlignment(SwingConstants.CENTER);
		c3.setBackground(new Color(255, 204, 102));
		c3.setBounds(565, 11, 25, 25);
		c3.setOpaque(true);
		panel_1.add(c3);
		c3.setVisible(false);
		
		c2 = new JLabel("c2");
		c2.setHorizontalAlignment(SwingConstants.CENTER);
		c2.setBackground(new Color(204, 204, 255));
		c2.setOpaque(true);
		c2.setBounds(520, 11, 25, 25);
		panel_1.add(c2);
		
				
				//Image img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
				
				JButton btnNewButton = new JButton("Add Statements");
				btnNewButton.setBounds(303, 55, 155, 30);
				contentPane.add(btnNewButton);
				
				btnNewButton.setForeground(new Color(0, 51, 51));
				btnNewButton.setBackground(new Color(204, 204, 204));
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				panel_2 = new JPanel();
				panel_2.setBackground(new Color(153, 204, 204));
				panel_2.setBounds(10, 301, 1160, 449);
				contentPane.add(panel_2);
				
				btnNewButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						String str = textField.getText();
						if(!str.equals("")) 
						{
							JButton bt = new JButton();
							bt.setText(count + "-" + textField.getText());
							bt.setSize(150,30);
							bt.setLocation(num, 10);
							bt.setBackground(new Color(153, 204, 153));
							panel.add(bt);
							num += 160;
							count += 1;
						}
						
						textField.setText("  ");  
						
					}
				});
	
	}
	
/*	public void paint(Graphics g)
	{
		//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem(), "InfoBox: " + "combo", JOptionPane.OK_OPTION);
		try {
			g.setColor(Color.BLUE);
			g.fillOval(30, 400, 350, 350);
			panel_2.getGraphicsConfiguration();
		}catch(Exception exc)
		{
		}
	} */
}
