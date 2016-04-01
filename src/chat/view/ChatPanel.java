//package 
package chat.view;

//imports 
import javax.swing.*;

import chat.controller.ChatController;
import chat.controller.IOController;

import java.awt.Dimension;
import java.awt.event.*;

/**
 * 
 * @author Jake Anderson
 * @Version 2.0
 *
 */
public class ChatPanel extends JPanel
{
private ChatController baseController;
private SpringLayout baseLayout;
private JButton chatButton;
private JTextArea chatTextArea;
private JTextField TextField;
private JScrollPane textPane;
private JButton tweetButton;
private JButton saveButton;
private JButton loadButton;
private JButton saveText;
private JButton readText;
private JButton analyzeTwitterButton;
private JLabel promptLabel;
private JButton mostUsedWord;


public ChatPanel(ChatController baseController)
{
	this.baseController = baseController;
	baseLayout = new SpringLayout();
	chatButton = new JButton("Submit");
	
	chatButton.setToolTipText("submits text to Chat Bot");
	
	chatTextArea = new JTextArea("ChatBot: Hello");
	
	chatTextArea.setToolTipText("This is where the chat bot responds");
	chatTextArea.setEnabled(false);
	
	TextField = new JTextField("",25);
	
	TextField.setToolTipText("Type here");
	tweetButton = new JButton("Click to tweet");
	
	analyzeTwitterButton = new JButton("analyze Tweets");
	promptLabel = new JLabel("Welcome To The OGChatBot X-perience");
	
	saveButton= new JButton("Save");
	loadButton = new JButton("load");
	mostUsedWord = new JButton("Most Used Word");
	
	
	
	
	setupChatPane();
	setupPanel();
	setupLayout();
	setupListeners();
}
private void setupChatPane()
{
	chatTextArea.setLineWrap(true);
	chatTextArea.setWrapStyleWord(true);
	chatTextArea.setEditable(false);
	textPane = new JScrollPane(chatTextArea);
	baseLayout.putConstraint(SpringLayout.EAST, loadButton, -6, SpringLayout.WEST, textPane);
	textPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	textPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
}
private void setupPanel()
{
	this.add(mostUsedWord);
	this.add(loadButton);
	this.add(saveButton);
	this.add(promptLabel);
	this.add(analyzeTwitterButton);
	this.add(tweetButton);
	this.add(textPane);//chatTextArea used to be here
	this.add(TextField);
	this.add(chatButton);
	this.setLayout(baseLayout);
	this.setPreferredSize(new Dimension(600,600));
	//chatTextArea.setText(baseController.getChatView().queryWord());
	
}
	
private void setupLayout()
{
	baseLayout.putConstraint(SpringLayout.SOUTH, mostUsedWord, -23, SpringLayout.NORTH, analyzeTwitterButton);
	baseLayout.putConstraint(SpringLayout.EAST, mostUsedWord, 0, SpringLayout.EAST, analyzeTwitterButton);
	baseLayout.putConstraint(SpringLayout.NORTH, promptLabel, 5, SpringLayout.NORTH, tweetButton);
	baseLayout.putConstraint(SpringLayout.WEST, promptLabel, 59, SpringLayout.EAST, tweetButton);
	baseLayout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, loadButton);
	baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, -6, SpringLayout.NORTH, loadButton);
	baseLayout.putConstraint(SpringLayout.SOUTH, loadButton, -139, SpringLayout.NORTH, analyzeTwitterButton);
	baseLayout.putConstraint(SpringLayout.NORTH, textPane, 20, SpringLayout.NORTH, this);
	baseLayout.putConstraint(SpringLayout.WEST, textPane, 100, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.SOUTH, textPane, 250, SpringLayout.NORTH, this);
	baseLayout.putConstraint(SpringLayout.EAST, textPane, -20, SpringLayout.EAST, this);
	baseLayout.putConstraint(SpringLayout.NORTH, chatTextArea, 39, SpringLayout.NORTH, this);
	baseLayout.putConstraint(SpringLayout.WEST, chatTextArea, 120, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 1, SpringLayout.NORTH, TextField);
	baseLayout.putConstraint(SpringLayout.WEST, TextField, 10, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.SOUTH, TextField, -10, SpringLayout.SOUTH, this);
	baseLayout.putConstraint(SpringLayout.EAST, chatButton, -10, SpringLayout.EAST, this);
	baseLayout.putConstraint(SpringLayout.WEST, tweetButton, 21, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.SOUTH, tweetButton, -76, SpringLayout.NORTH, TextField);
	baseLayout.putConstraint(SpringLayout.WEST, analyzeTwitterButton, 0, SpringLayout.WEST, tweetButton);
	baseLayout.putConstraint(SpringLayout.SOUTH, analyzeTwitterButton, -38, SpringLayout.NORTH, tweetButton);
}
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clickEvent)
			{
				String userText = chatTextArea.getText(); 
				chatTextArea.append("\nUser: " + userText); 
				chatTextArea.setText("");
				String response = baseController.userToChatBot(userText); 
				chatTextArea.append("\nChatbot: " + response); 
				promptLabel.setText("Chatting");
				TextField.setText("");
			}
		
		});
		tweetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clickEvent)
			{
				String tweetToSend = TextField.getText();
				baseController.sendTweet(tweetToSend);
				promptLabel.setText("sent tweet: " + tweetToSend);
				TextField.setText("");
				
			}
		});
		
		analyzeTwitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String topic = TextField.getText();
				String results = baseController.investigateTweet(topic);
				chatTextArea.setText(results);
				promptLabel.setText("Analyzing twitter for " +topic);
				TextField.setText("");
			}
		});
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String file = IOController.saveFile(chatTextArea.getText());
				promptLabel.setText(file);
			}
		});
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String loadedText = IOController.readTextFromFile(promptLabel.getText());
				chatTextArea.setText(loadedText);
				promptLabel.setText("File loaded");
			}
		});
		mostUsedWord.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clickEvent)
			{
				
			}
		
		});
	}
	public ChatController getBaseController() {
		return baseController;
	}
	public void setBaseController(ChatController baseController) {
		this.baseController = baseController;
	}
	public SpringLayout getBaseLayout() {
		return baseLayout;
	}
	public void setBaseLayout(SpringLayout baseLayout) {
		this.baseLayout = baseLayout;
	}
	public JButton getChatButton() {
		return chatButton;
	}
	public void setChatButton(JButton chatButton) {
		this.chatButton = chatButton;
	}
	public JTextArea getChatTextArea() {
		return chatTextArea;
	}
	public void setChatTextArea(JTextArea chatTextArea) {
		this.chatTextArea = chatTextArea;
	}
	public JTextField getTextField() {
		return TextField;
	}
	public void setTextField(JTextField textField) {
		TextField = textField;
	}
	
	
}
