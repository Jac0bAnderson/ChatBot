package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatView;


/**
 * Application controller for the chatbot project
 * @author Jake
 * @version 1.11 10/21/15 fixed error
 */
public class ChatController {
private Chatbot myChatbot;
private ChatView myDisplay;

public ChatController()
{
	myDisplay =  new ChatView();
	String userName = myDisplay.grabInput("what is your name?");
	myChatbot = new Chatbot(userName);
}

	public void start()
	{
		myDisplay.showOutput("hello " a +myChatbot.getUserName());
	}
	
}
