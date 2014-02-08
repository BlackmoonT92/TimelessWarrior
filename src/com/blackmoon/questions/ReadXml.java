package com.blackmoon.questions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.blackmoon.runtime.Managers.ResourceManager;

import android.util.Log;

public class ReadXml {
	public static List<QuestionObject> listQuestion = new ArrayList<QuestionObject>();
	public ReadXml() {
		QuestionObject object = new QuestionObject();
		object.setTxtQuestion("Cô ấy rất xinh đẹp và tinh tế");
		object.setTxtAnswer1E("She so beautiful");
		object.setTxtAnswer1V("Cô ấy rất xinh đẹp");
		
		object.setTxtAnswer2E("She so lovely");
		object.setTxtAnswer2V("Cô ấy dễ thương");
		
		object.setTxtAnswer3E("She so wonderfull");
		object.setTxtAnswer3V("Cô ấy rất tuyệt vời");
		
		object.setTxtAnswer4E("She so beautiful and precise");
		object.setTxtAnswer4V("Cô ấy rất xinh đẹp và tinh tế");
		object.setRightAnswer("D");
		listQuestion.add(object);
		QuestionObject object1 = new QuestionObject();
		object1.setTxtQuestion("Anh ấy là người xấu");
		object1.setTxtAnswer1E("He is a dumb person");
		object1.setTxtAnswer1V("Anh ấy là người câm.");
		
		object1.setTxtAnswer2E("He is a bad company.");
		object1.setTxtAnswer2V("Anh ấy là bạn xấu");
		
		object1.setTxtAnswer3E("He is a direct person.");
		object1.setTxtAnswer3V("Anh ấy là người thẳng thắn.");
		
		object1.setTxtAnswer4E("He is a skeptical person.");
		object1.setTxtAnswer4V("Anh ấy là người đa nghi.");
		object1.setRightAnswer("B");
		listQuestion.add(object1);
		
		QuestionObject object2 = new QuestionObject();

		
		object2.setTxtQuestion("Anh ta đang tán tỉnh tôi.");
		object2.setTxtAnswer1E("He's hitting on me.");
		object2.setTxtAnswer1V("Anh ta đang tán tỉnh tôi.");
		
		object2.setTxtAnswer2E("he is chatting with his friend. ");
		object2.setTxtAnswer2V("Anh ấy đang tán gẫu với bạn anh ấy.");
		
		object2.setTxtAnswer3E("he is taking advantage of me..");
		object2.setTxtAnswer3V("Anh ấy đang lợi dụng tôi.");
		
		object2.setTxtAnswer4E("he is having an affair with his boss.");
		object2.setTxtAnswer4V("Anh ấy đang ngoại tình với cô chủ.");
		object2.setRightAnswer("A");
		listQuestion.add(object2);
		
		
		QuestionObject object3 = new QuestionObject();

		object3.setTxtQuestion("Tôi là người rộng lượng.");
		object3.setTxtAnswer1E("I am a conscientious person.");
		object3.setTxtAnswer1V("Tôi là người có lương tâm.");
		
		object3.setTxtAnswer2E("I'm a straight person. ");
		object3.setTxtAnswer2V("Tôi là người thẳng tính.");
		
		object3.setTxtAnswer3E("I am a servant.");
		object3.setTxtAnswer3V("tôi là người hầu.");
		
		object3.setTxtAnswer4E("I am a generous person.");
		object3.setTxtAnswer4V("Tôi là người rộng lượng.");
		object3.setRightAnswer("D");
		listQuestion.add(object3);
		
		QuestionObject object4 = new QuestionObject();

		object4.setTxtQuestion("Who is the master of KungFu");
		object4.setTxtAnswer1E("Burce Lee.");
		object4.setTxtAnswer1V("Lý Tiểu Long.");
		
		object4.setTxtAnswer2E("Dicky Cheung ");
		object4.setTxtAnswer2V("Trương Vệ Kiện.");
		
		object4.setTxtAnswer3E("Jacky chan.");
		object4.setTxtAnswer3V("Thành Long.");
		
		object4.setTxtAnswer4E("BlackMoonT92.");
		object4.setTxtAnswer4V("Phan Văn Tâm.");
		object4.setRightAnswer("C");
		listQuestion.add(object4);
		
	}
	public QuestionObject getQuestion(){
		QuestionObject question = new QuestionObject();
		try {
			InputStream is = ResourceManager.getActivity().getAssets().open("question/1.bl");
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = docBuilder.parse(is);
			NodeList listWord = document.getElementsByTagName("Word");
			
			Random random = new Random();
			int key =random.nextInt(listWord.getLength()) + 1;
				Node nodeWord = listWord.item(key);
				
				try{
					String txtQuestion = nodeWord.getChildNodes().item(1).getTextContent();
					question.setTxtQuestion(txtQuestion);
					
					//nodeWord.getNextSibling();
					String txtAnswer1E = nodeWord.getChildNodes().item(3).getTextContent();
					question.setTxtAnswer1E(txtAnswer1E);
					String txtAnswer1V = nodeWord.getChildNodes().item(5).getTextContent();
					question.setTxtAnswer1V(txtAnswer1V);
					
					String txtAnswer2E = nodeWord.getChildNodes().item(7).getTextContent();
					question.setTxtAnswer2E(txtAnswer2E);
					String txtAnswer2V = nodeWord.getChildNodes().item(9).getTextContent();
					question.setTxtAnswer2V(txtAnswer2V);
					
					String txtAnswer3E = nodeWord.getChildNodes().item(11).getTextContent();
					question.setTxtAnswer3E(txtAnswer3E);
					String txtAnswer3V = nodeWord.getChildNodes().item(13).getTextContent();
					question.setTxtAnswer3V(txtAnswer3V);
					

					String txtAnswer4E = nodeWord.getChildNodes().item(15).getTextContent();
					question.setTxtAnswer4E(txtAnswer4E);
					String txtAnswer4V = nodeWord.getChildNodes().item(17).getTextContent();
					question.setTxtAnswer4V(txtAnswer4V);
					
					String rightAnswer = nodeWord.getChildNodes().item(19).getTextContent();
					question.setRightAnswer(rightAnswer);
					
				}catch(NullPointerException e){
					
				}
				
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return question;

	}
	
	

}
