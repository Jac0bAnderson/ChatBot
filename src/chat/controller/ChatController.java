package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatView;


/**
 * Application controller for the chatbot project
 * @author Jake
 * @version 1.11 10/21/15 fixed error
 */
public class ChatController {
private Chatbot myChatBot;
private ChatView myDisplay;

public ChatController()
{
	myDisplay =  new ChatView();
	String userName = myDisplay.grabInput("what is your name?");
	myChatBot = new Chatbot(userName);
}

	public void start()
	{
		myDisplay.showOutput("hello "  +myChatBot.getUserName());
		chat();
		
	}
	
	private void chat()
	{
		//myDisplay =display
		//myChatBot = simplebot
		//showOutput =displayText
		//grabInput = collectUserText
		
		String conversation = myDisplay.grabInput("what would you like to talk about today?");
		while(myChatBot.lengthChecker(conversation))
		{
			if(myChatBot.contentChecker(conversation))
			{
				myDisplay.showOutput("i did not know your interested in " +myChatBot.getContent());
			}
			
			else if(myChatBot.memeChecker(conversation))
			{
				
				myDisplay.showOutput("Is that even a meme?");
				
			}
			else if(myChatBot.politicalTopicChecker(conversation))
			{
				myDisplay.showOutput("intresting" );
			}
			conversation =myDisplay.grabInput(conversation);
			
			
			
			
			
//			if(!simpleBot.quitChecker(conversation))
//			{
//				conversation = simpleBot.processInput(conversation);
//			}
//			else
//			{
//				shutDown();
//			}
		}
	}
	
	private void shutdown()
	{
		
	}
	
}
