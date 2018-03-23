package parsedemo;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ParseXml {
	
	
	public Document parse(File file) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}
	
	//迭代器
	public void bar(Document document) throws DocumentException {
		Element root = document.getRootElement();
		
		for(Iterator<Element> it = root.elementIterator();it.hasNext();){
			Element element = it.next();
		}
		
		for(Iterator<Element> it = root.elementIterator("foo");it.hasNext();){
			Element foo = it.next();
		}
		
		for(Iterator<Attribute> it = root.attributeIterator();it.hasNext();){
			Attribute attribute = it.next();
		}
		
	}
	
	public static void main(String[] args){
		ParseXml parseXml = new ParseXml();
		try {
			Document doc = parseXml.parse(new File("D:\\xmldocumentparsedemo\\spring-common.xml"));
			System.out.println("encoding:"+doc.getXMLEncoding());
			Element el = doc.getRootElement();
			Iterator<Element> it = el.elementIterator();
			while(it.hasNext()){
				Element temp = it.next();
				System.out.println(temp.hasContent());
			}
			//"a".replace("sound\\", "")
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
