package com.my.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class HotNewsIn {

	/**
	 * 添加新的热点新闻
	 * @param hotids
	 * @throws Exception
	 */
	public static void addHot(String [] hotids) throws Exception{
		String xmlfile = null;
		xmlfile = HotNewsIn.class.getResource("/").toURI().getPath()+"HotNews.xml";
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.parse(new File(xmlfile));
	    doc.normalize();
	    Element emp = (Element) doc.getElementsByTagName("hot-news").item(0);
//	    System.out.println(emp.getTagName());
	    for(int i = 0; i < hotids.length; i++){
	    	Element hotnewsel = doc.createElement("news-id");
	    	hotnewsel.appendChild(doc.createTextNode(hotids[i]));
	    	emp.appendChild(hotnewsel);
	    }
	    deletHot(hotids.length , doc);
	   
	    TransformerFactory tff = TransformerFactory.newInstance();
	    Transformer tf = tff.newTransformer();
	    tf.setOutputProperty(OutputKeys.INDENT,"yes");
	    File file = new File(HotNewsIn.class.getResource("/").toURI().getPath()+"HotNews.xml");
	    tf.transform(new DOMSource(doc),new StreamResult(file));
	}
	
	/**
	 * 删除相应数目的结点
	 * @param num
	 * @param doc
	 */
	private static void deletHot(int num, Document doc) {
		Node el = doc.getElementsByTagName("hot-news").item(0);
		NodeList childnodes = el.getChildNodes();
		//删除相应数目的结点
		for(int i = 0;i < childnodes.getLength();i++){
			Node node = childnodes.item(i);
//			System.out.println(node.getNodeName()+"==="+node.getNodeValue());
			 if(node.getNodeName().equals("news-id")){
					node.getParentNode().removeChild(node);
					num--;
				}
				if(num == 0)
					break;
			}
		//删除空白结点
		for(int i = 0;i < childnodes.getLength();i++){
			Node node = childnodes.item(i);
			if(!node.getNodeName().equals("news-id")){
				node.getParentNode().removeChild(node);
			}
		}
	}

}
