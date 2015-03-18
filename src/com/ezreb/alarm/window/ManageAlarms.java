package com.ezreb.alarm.window;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ezreb.alarm.dataTEMPNAME.alarm.Alarm;
import com.ezreb.alarm.util.OnStart;

public class ManageAlarms extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5962683781047212386L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAlarms frame = new ManageAlarms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static JComboBox<String> comboBox = new JComboBox<String>();
	private AlarmPanel alarmPanel;

	@OnStart
	public static void init() {
		System.out.println("ran iunit");
		comboBox.setPreferredSize(new Dimension(80, 20));
		comboBox.setModel(Alarm.getComboBox());
		comboBox.setEditable(true);
	}
	/**
	 * Create the frame.
	 */
	public ManageAlarms() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {84, 84, 84, 84, 84, 0};
		gbl_contentPane.rowHeights = new int[] {21, 21, 21, 63, 63, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		comboBox.setPreferredSize(new Dimension(160, 20));
		comboBox.addItem("");
		contentPane.add(comboBox, gbc_comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Alarm a = Alarm.get(comboBox.getSelectedItem());
				if(a != null) {
					
				}
			}
		});
		
		alarmPanel = new AlarmPanel(Alarm.get(comboBox.getSelectedItem()));
		GridBagConstraints gbc_alarmPanel = new GridBagConstraints();
		gbc_alarmPanel.gridheight = 4;
		gbc_alarmPanel.gridwidth = 5;
		gbc_alarmPanel.insets = new Insets(0, 0, 5, 5);
		gbc_alarmPanel.fill = GridBagConstraints.BOTH;
		gbc_alarmPanel.gridx = 0;
		gbc_alarmPanel.gridy = 1;
		contentPane.add(alarmPanel, gbc_alarmPanel);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 2;
	}

	
}
