package chat.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import chat.controller.ChatController;
import twitter4j.*;
/**
 * 
 * @author jand6944
 *@version 0.5
 */
public class CTECTwitter 
{
private ArrayList<Status> statusList;
private ArrayList<String> wordList;
private Twitter chatbotTwitter;
private ChatController baseController;
public CTECTwitter(ChatController baseController)
{
	this.baseController = baseController;
	chatbotTwitter = TwitterFactory.getSingleton();
	statusList = new ArrayList <Status>();
	wordList = new  ArrayList <String>();
	
}
public void sendTweet(String tweet)
{
	String tweetTExt = JOptionPane.showInputDialog("");
	try 
	{
		chatbotTwitter.updateStatus(tweetTExt);
	} 
	catch (TwitterException error) 
	{
		
baseController.handleErrors(error.getErrorMessage());
	}
}



}
