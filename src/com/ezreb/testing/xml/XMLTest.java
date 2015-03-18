package com.ezreb.testing.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTest {

	public static void main(String[] args) {
		Item[] items = parseXML(readXML());
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}
	
	public static Document readXML() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document dom = db.parse(new File("C:\\Users\\bram.zerbe\\Desktop\\Programming\\EzrebAlarm-3\\examples\\testAlarm.xml"));
			return dom;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Item[] parseXML(Document dom) {
		Item[] retVal = new Item[3];
		Element root = dom.getDocumentElement();
		NodeList nodes = root.getElementsByTagName("item");
		if(nodes != null && nodes.getLength() > 0) {
			for(int i = 0; i < nodes.getLength(); i++) {
				Element item = (Element) nodes.item(i);
				Item item2 = new Item(item);
				retVal[i] = item2;
			}
		}
		return retVal;
	}

}
