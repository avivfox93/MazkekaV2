package com.aei.MazkekaV2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Configuration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMethanol;
	private JTextField textEthanol;
	private JTextField textTails;
	private JTextField textFinish;
	
	private boolean editing = false;
	private JTextField textName;

	/**
	 * Create the frame.
	 */
	public Configuration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox<TempObj> comboBox = new JComboBox<TempObj>();
		for(TempObj t : Main.getTemps())
			comboBox.addItem(t);
		if(!Main.getTemps().isEmpty())
			comboBox.setSelectedIndex(0);
		JButton btnEdit = new JButton("Edit");
		
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		
		textMethanol = new JTextField();
		textMethanol.setEnabled(false);
		textMethanol.setColumns(10);
		
		textEthanol = new JTextField();
		textEthanol.setEnabled(false);
		textEthanol.setColumns(10);
		
		textTails = new JTextField();
		textTails.setEnabled(false);
		textTails.setColumns(10);
		
		textFinish = new JTextField();
		textFinish.setEnabled(false);
		textFinish.setColumns(10);
		
		JLabel lblMethanol = new JLabel("Methanol");
		
		JLabel lblEthanol = new JLabel("Ethanol");
		
		JLabel lblTails = new JLabel("Tails");
		
		JLabel lblFinish = new JLabel("Finish");
		
		textName = new JTextField();
		textName.setEnabled(false);
		textName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		JButton btnSave_1 = new JButton("Save");
		
		JButton btnCancel = new JButton("Cancel");
		
		JButton btnReset = new JButton("RESET");
		btnReset.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblEthanol)
									.addComponent(lblMethanol)
									.addComponent(lblTails)
									.addComponent(lblFinish)
									.addComponent(lblName)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFinish, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTails, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textEthanol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textMethanol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSave_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
							.addComponent(btnReset)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEdit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMethanol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMethanol))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textEthanol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEthanol))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textTails, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTails))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFinish, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFinish))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave_1)
						.addComponent(btnCancel)
						.addComponent(btnReset))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		if(!Main.getTemps().isEmpty())
			update((TempObj)comboBox.getSelectedItem());
		
		btnEdit.addActionListener(e -> {
			editing = !editing;
			textMethanol.setEnabled(editing);
			textEthanol.setEnabled(editing);
			textTails.setEnabled(editing);
			textFinish.setEnabled(editing);
			btnSave.setEnabled(editing);
			textName.setEnabled(editing);
			comboBox.setEditable(editing);
			btnReset.setEnabled(editing);
		});
		
		btnReset.addActionListener(e -> {
			Main.getTemps().clear();
			textMethanol.setText("");
			textEthanol.setText("");
			textTails.setText("");
			textFinish.setText("");
		});
		
		btnSave.addActionListener(e -> {
			if(comboBox.getSelectedIndex() >= 0)
				save((TempObj)comboBox.getSelectedItem());
			else {
				saveNew(textName.getText());
				updateCombo(comboBox);
			}
		});
		
		comboBox.addItemListener(e -> {
			if(comboBox.getSelectedIndex() >= 0)
				update((TempObj)comboBox.getSelectedItem());
		});
		
		btnSave_1.addActionListener(e -> {
			Main.save();
			Main.gui.setCombo();
			setVisible(false);
		});
		
		btnCancel.addActionListener(e -> {
			setVisible(false);
		});
	}
	
	private void update(TempObj t) {
		textMethanol.setText(Double.toString(t.getMethanol()));
		textEthanol.setText(Double.toString(t.getEthanol()));
		textTails.setText(Double.toString(t.getTails()));
		textFinish.setText(Double.toString(t.getFinish()));
		textName.setText(t.getName());
	}
	
	private void save(TempObj t) {
		try {
			t.setName(textName.getText());
			t.setMethanol(Double.parseDouble(textMethanol.getText()));
			t.setEthanol(Double.parseDouble(textEthanol.getText()));
			t.setTails(Double.parseDouble(textTails.getText()));
			t.setFinish(Double.parseDouble(textFinish.getText()));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Saving!", "Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void saveNew(String name) {
		try {
			if(name.isEmpty())
				throw new Exception();
			Main.getTemps().add(new TempObj(name,Double.parseDouble(textMethanol.getText()), Double.parseDouble(textEthanol.getText()),
					Double.parseDouble(textTails.getText()), Double.parseDouble(textFinish.getText())));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Saving!", "Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private static void updateCombo(JComboBox<TempObj> comb) {
		comb.removeAllItems();
		for(TempObj t : Main.getTemps())
			comb.addItem(t);
		if(!Main.getTemps().isEmpty())
			comb.setSelectedIndex(0);
	}
}
