package chat.model;

import java.util.ArrayList;
import java.util.List;

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
public void loadTweets(String twitterHandle)throws TwitterException
{
	Paging statusPage = new Paging(1,200);
	int page =1;
	while(page <= 10)
	{
		statusPage.setPage(page);
		statusList.addAll(chatbotTwitter.getUserTimeline(twitterHandle, statusPage));
		page++;
	}
	for(Status currentStatus : statusList)
	{
		String[] tweetText = currentStatus.getText().split(" ");
		for(String word : tweetText)
		{
			wordList.add(removePunctuation(word).toLowerCase());
		}
	}
	removeCommonEnglishWords(wordList);
	removeEmptyText();
}
/**
 * Goes through words and takes out .,'?!:;\"()^[]<>- from them.
 * @param word
 * @return scrubbedString
 */
private String removePunctuation(String word)
{
String punctuation = ".,'?!:;\"()^[]<>-";	
String scrubbedString = "";
for (int i =0; i < word.length(); i++)
{
	if(punctuation.indexOf(word.charAt(i)) == -1)
	{
		scrubbedString += word.charAt(i);
	}
}
	return scrubbedString;
}
private void removeCommonEnglishWords(ArrayList<String> text)
{
	
}
private void removeEmptyText()
{
	
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
