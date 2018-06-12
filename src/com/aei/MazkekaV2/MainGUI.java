package com.aei.MazkekaV2;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JToggleButton;

public class MainGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLayeredPane contentPane;

	private JComboBox<TempObj> programSelector;
	private ArrayList<TempObj> temps;
	private TempObj activeTemp;
	private boolean fullScreen = false;
	private int notFullScreen;
	private JLabel lblc;
	private JLabel lblNewLabel;
	private JToggleButton tglbtnStartstop;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainGUI(String name, ArrayList<TempObj> temps, TempObj activeTemp) {
		this.temps = temps;
		this.setActiveTemp(activeTemp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("MazkekaIOT V4.0");
		notFullScreen = getExtendedState();
		programSelector = new JComboBox<TempObj>();
		contentPane.setLayer(programSelector, 1);
		
		JLabel lblProgram = new JLabel("Program:");
		lblProgram.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnConfig = new JButton("Config");
		btnConfig.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(MainGUI.class.getResource("/com/aei/MazkekaV2/Resources/logo.png")));
		
		JLabel tempTextLable = new JLabel("Temp:");
		tempTextLable.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblc = new JLabel("0.00C");
		lblc.setForeground(Color.GREEN);
		lblc.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblNewLabel = new JLabel("OFF");
		contentPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel idLabel = new JLabel(name);
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnFullScreen = new JButton("Full Screen");
		
		tglbtnStartstop = new JToggleButton("Start/Stop");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProgram)
								.addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(tempTextLable)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblc)
								.addComponent(lblNewLabel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnStartstop, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnFullScreen))
						.addComponent(programSelector, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idLabel)
							.addGap(323))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(logo)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(idLabel))
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(logo, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblProgram))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(tempTextLable)
										.addComponent(lblc))
									.addGap(18)
									.addComponent(lblNewLabel)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(programSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(155)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnStartstop, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFullScreen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))))
					.addGap(26))
		);
		
		contentPane.setLayout(gl_contentPane);
		if(programSelector.getSelectedIndex() >= 0) {
			Main.setActiveTemp((TempObj)programSelector.getSelectedItem());
		}
		setCombo();
		programSelector.addActionListener(e -> {
			this.setActiveTemp((TempObj)programSelector.getSelectedItem());
		});
		
		tglbtnStartstop.addActionListener(e -> {
			handleTgl(tglbtnStartstop.isSelected());
		});
		
		btnFullScreen.addActionListener(e -> {
			if(!fullScreen)
				setExtendedState(JFrame.MAXIMIZED_BOTH);
			else 
				setExtendedState(notFullScreen);
		});
		
		btnConfig.addActionListener(e -> {
			new Configuration().setVisible(true);;
		});
	}
	
	public JLabel getTempLabel() {
		return this.lblc;
	}
	
	public JLabel getSitLabel() {
		return this.lblNewLabel;
	}
	
	public void setCombo() {
		programSelector.removeAllItems();
		for(TempObj t : temps)
			programSelector.addItem(t);
	}
	
	public void setActiveTemp(int i) {
		programSelector.setSelectedIndex(i);
	}

	public TempObj getActiveTemp() {
		return activeTemp;
	}

	public void setActiveTemp(TempObj activeTemp) {
		this.activeTemp = activeTemp;
	}
	
	public void handleTgl(boolean sit) {
		if(sit) {
			Main.setActiveTemp((TempObj)programSelector.getSelectedItem());
			Main.startDistilling();
		}else {
			Main.stopDistilling();
		}
	}
	
	public void setTgl(boolean sit) {
		tglbtnStartstop.setSelected(sit);
		handleTgl(sit);
	}
}
