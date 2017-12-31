package com.my.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HotNews {

	private static HotNews hotnews;
	private List<String> hotlist;
	private HotNews(){}
	public static HotNews instance(){
		if(hotnews == null){
			hotnews = new HotNews();
			hotnews.init();
		}
		return hotnews;
	}
	private void init(){
		String strFile =null;
		try {
			strFile = HotNews.class.getResource("/").toURI().getPath()+"HotNews.xml"; 
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		strFile = strFile.substring(1);
		File file = new File(strFile);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			Element elt = doc.getDocumentElement();
			parseNode(elt);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void parseNode(Node node){
		if(node!=null){
			NodeList nodes = node.getChildNodes();
			hotlist = new ArrayList<String>();
			for(int i=0;i<nodes.getLength();i++){
				Node nd = nodes.item(i);
				if("news-id".equals(nd.getNodeName().trim())){
					String id  = nd.getTextContent().trim();
					hotlist.add(id);
					}
				}
			
		}
	}
	public void reload(){
		hotnews.init();
	}
	public List<String> getHotNids(){
		return hotlist;
	}
}
