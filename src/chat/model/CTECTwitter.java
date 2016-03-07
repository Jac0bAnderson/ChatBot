package chat.model;

import java.util.ArrayList;

import twitter4j.*;
/**
 * 
 * @author jand6944
 *@version 0.4
 */
public class CTECTwitter 
{
private ArrayList<Status> statusList;
private ArrayList<String> wordList;
private Twitter chatbotTwitter;
public CTECTwitter()
{
	statusList = new ArrayList <Status>();
	wordList = new  ArrayList <String>();
	
}
public void sendTweet(String tweet)
{
	try {
		chatbotTwitter.updateStatus("I just tweeted from my Java Chatbot program! #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen!");
	} catch (TwitterException e) {
		
		e.printStackTrace();
	}
}
}
