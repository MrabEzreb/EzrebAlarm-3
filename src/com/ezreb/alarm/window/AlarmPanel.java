package com.ezreb.alarm.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ezreb.alarm.dataTEMPNAME.Time;
import com.ezreb.alarm.dataTEMPNAME.action.Action;
import com.ezreb.alarm.dataTEMPNAME.alarm.Alarm;

public class AlarmPanel extends JPanel {

	private String name;
	private Time time;
	private Action action;
	private Alarm alarm;
	private JSlider slider;
	private boolean editable;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblEnableEditing;
	private JSeparator separator;
	private JLabel lblAlarmEditor;
	private EditableField editableField;
	/**
	 * Create the panel.
	 */
	public AlarmPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		add(panel_1, BorderLayout.NORTH);
		
		Hashtable<Integer, JLabel> dict = new Hashtable<Integer, JLabel>();
		dict.put(0, new JLabel("Off"));
		dict.put(1, new JLabel("On"));
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMinimumSize(new Dimension(36, 21));
		slider.setPreferredSize(new Dimension(50, 41));
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setMaximum(1);
		slider.setLabelTable(dict);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(slider.getValue() == 0) {
					editable = false;
				} else if(slider.getValue() == 1) {
					editable = true;
				} 
				setEditorsEnabled(editable);
			}
		});
		
		lblAlarmEditor = new JLabel("Alarm Editor");
		panel_1.add(lblAlarmEditor);
		
		separator = new JSeparator();
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(200, 25));
		panel_1.add(separator);
		
		lblEnableEditing = new JLabel("Editing");
		lblEnableEditing.setLabelFor(slider);
		panel_1.add(lblEnableEditing);
		panel_1.add(slider);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		
		editableField = new EditableField("Name", "(none)");
		panel_2.add(editableField);
	}
	
	
	public AlarmPanel(Alarm alarm) {
		this();
		this.setAlarm(alarm);
		this.editableField.setValue(alarm.getName());
	}
	
	
	
	/**
	 * @param alarm the alarm to set
	 */
	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
		this.name = alarm.getName();
		this.time = alarm.getTime();
		this.action = alarm.getAction();
	}
	
	private void setEditorsEnabled(boolean b) {
		Component[] c = panel_2.getComponents();
		for (Component component : c) {
			if(component instanceof EditableField) {
				((EditableField) component).setEditingEnabled(editable);
			}
		}
		
	}

	public JPanel getPanel_2() {
		return panel_2;
	}
}