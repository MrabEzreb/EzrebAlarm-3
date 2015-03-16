package com.ezreb.alarm.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Confirmation extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205839933531530982L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Confirmation dialog = new Confirmation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Confirmation() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel lblAreYouSure = new JLabel("Are you sure?");
			lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
			lblAreYouSure.setFont(new Font("Tahoma", Font.PLAIN, 30));
			contentPanel.add(lblAreYouSure);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setPreferredSize(new Dimension(10, 75));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] {160, 100, 160};
			gbl_buttonPane.rowHeights = new int[] {60};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0};
			gbl_buttonPane.rowWeights = new double[]{0.0};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton okButton = new JButton("I am sure.");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.fill = GridBagConstraints.BOTH;
				gbc_okButton.insets = new Insets(0, 0, 0, 5);
				gbc_okButton.gridx = 0;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Let me think about it.");
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.BOTH;
				gbc_cancelButton.gridx = 2;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
	}

}
