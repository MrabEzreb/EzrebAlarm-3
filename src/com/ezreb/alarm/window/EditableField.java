package com.ezreb.alarm.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class EditableField extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2036072359416064938L;
	private String value;
	private JToggleButton editToggle;
	private JLabel label;
	private JTextField editor;
	private JLabel title;
	private static final Dimension size = new Dimension(40, 25);
	public EditableField(String title2, String startingValue) {
		this.value = startingValue;
//		this.setPreferredSize(size);
//		this.setSize(size);
		label = new JLabel(this.value);
		editor = new JTextField(this.value);
		editToggle = new JToggleButton("Edit");
		editToggle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleEdit();
			}
		});
		editToggle.setPreferredSize(size);
		editToggle.setSize(size);
		editToggle.setSelected(false);
		editToggle.setBorder(new EmptyBorder(new Insets(15, 0, 15, 0)));
		editToggle.setBorder(new BevelBorder(BevelBorder.RAISED));
		editToggle.setBackground(null);
		editToggle.setContentAreaFilled(false);
		label.setBorder(new EmptyBorder(10, 10, 10, 10));
		title = new JLabel(title2);
		editor.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		this.setRightComponent(label);
		this.setResizeWeight(0);
		this.setOneTouchExpandable(false);
		this.setDividerSize(0);
		this.setBorder(new LineBorder(Color.BLACK, 1, false));
		this.setLeftComponent(title);
	}
	public void toggleEdit() {
		if(editToggle.isSelected()) {
			this.value = label.getText();
			this.setRightComponent(editor);
			editor.setText(label.getText());
			editToggle.setBorder(new BevelBorder(BevelBorder.LOWERED));
		} else {
			label.setText(editor.getText());
			this.value = editor.getText();
			this.setRightComponent(label);
			editToggle.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		if(editToggle.isSelected()) {
			this.value = editor.getText();
		} else {
			this.value = label.getText();
		}
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	public void setEditingEnabled(boolean b) {
		if(b == true) {
			this.setLeftComponent(editToggle);
		} else if(b == false) {
			this.setLeftComponent(title);
		}
	}

}
