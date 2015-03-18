package com.ezreb.testing.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Item {

	private String date;
	private String mode;
	private String unit;
	private String current;
	private String interactive;
	
	public Item(Element element) {
		this.date = getTextAttribute(element, "date");
		this.mode = getTextValue(element, "mode");
		this.unit = getTextValue(element, "unit");
		this.current = getTextValue(element, "current");
		this.interactive = getTextValue(element, "interactive");
	}
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	private String getTextAttribute(Element ele, String attribute) {
		return ele.getAttribute(attribute);
	}
	/**
	 * @param date
	 * @param mode
	 * @param unit
	 * @param current
	 * @param interactive
	 */
	public Item(String date, String mode, String unit, String current,
			String interactive) {
		this.date = date;
		this.mode = mode;
		this.unit = unit;
		this.current = current;
		this.interactive = interactive;
	}
	
	@Override
	  public String toString() {
	    return "Item [current=" + current + ", date=" + date + ", interactive="
	        + interactive + ", mode=" + mode + ", unit=" + unit + "]";
	  }
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @return the current
	 */
	public String getCurrent() {
		return current;
	}
	/**
	 * @return the interactive
	 */
	public String getInteractive() {
		return interactive;
	}
	
}
