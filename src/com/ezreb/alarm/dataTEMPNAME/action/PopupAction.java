package com.ezreb.alarm.dataTEMPNAME.action;

import javax.swing.JFrame;

public class PopupAction implements Action {

	public PopupAction(JFrame popup) {
		this.popup = popup;
		this.popup.setVisible(false);
	}
	private JFrame popup;
	@Override
	public void run() {
		this.popup.setVisible(true);
	}

}
