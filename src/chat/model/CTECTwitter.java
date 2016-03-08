package chat.model;

import java.util.ArrayList;
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
	try 
	{
		chatbotTwitter.updateStatus("  ");
	} 
	catch (TwitterException error) 
	{
		
baseController.handleErrors(error.getErrorMessage());
	}
}



}
