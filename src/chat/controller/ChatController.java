package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatView;
import chat.view.ChatFrame;


/**
 * Application controller for the chatbot project
 * @author Jake
 * @version 1.11 10/21/15 fixed error
 */
public class ChatController {
private Chatbot myChatBot;
private ChatView myDisplay;
private ChatFrame baseFrame;

public ChatController()
{
	myDisplay =  new ChatView();
	String userName = myDisplay.grabInput("what is your name?");
	myChatBot = new Chatbot(userName);
	baseFrame = new ChatFrame(this);
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
		
		//String conversation = myDisplay.grabInput("what would you like to talk about today?");
		//while(myChatBot.lengthChecker(conversation))
		//{
		//	conversation = myDisplay.grabInput(conversation);
		//}
				}
	public String userToChatBot(String userText)
	{
String response = "";
		
		if(myChatBot.quitChecker(userText))
		{
			shutdown();
		}
		
		response = myChatBot.processConversation(userText);
		
		return response;
	}
	private void shutdown()
	{
		myDisplay.showOutput("Goodbye" + myChatBot.getUserName() + " i will probs forget you");
		System.exit(0);
	}

	public Chatbot getChatbot() {
		return myChatBot;
	}

	public void setMyChatBot(Chatbot myChatBot) {
		this.myChatBot = myChatBot;
	}

	public ChatView getChatView() {
		return myDisplay;
	}

	public void setMyDisplay(ChatView myDisplay) {
		this.myDisplay = myDisplay;
	}

	public ChatFrame getBaseFrame() {
		return baseFrame;
	}

	public void setBaseFrame(ChatFrame baseFrame) {
		this.baseFrame = baseFrame;
	}
	
}
