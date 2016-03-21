//package 
package chat.view;

//imports 
import javax.swing.*;

import chat.controller.ChatController;

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
	textPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	textPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
}
private void setupPanel()
{
	this.add(analyzeTwitterButton);
	this.add(tweetButton);
	this.add(textPane);//chatTextArea used to be here
	this.add(TextField);
	this.add(chatButton);
	this.setLayout(baseLayout);
	this.setPreferredSize(new Dimension(600,600));
	chatTextArea.setText(baseController.getChatView().queryWord());
	
}
	
private void setupLayout()
{
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
			}
		
		});
		tweetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clickEvent)
			{
				baseController.sendTweet("No text to send");
			}
		});
		
		analyzeTwitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String user = TextField.getText();
				String results = baseController.analyze(user);
				chatTextArea.setText(results);
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
