package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import java.awt.event.*;

public class ChatPanel extends JPanel
{
private ChatController baseController;
private SpringLayout baseLayout;
private JButton chatButton;
private JTextArea chatTextArea;
private JTextField TextField;

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
	
	setupPanel();
	setupLayout();
	setupListeners();
}
private void setupPanel()
{
	this.add(chatTextArea);
	this.add(TextField);
	this.add(chatButton);
	this.setLayout(baseLayout);
	
}
	
private void setupLayout()
{
	baseLayout.putConstraint(SpringLayout.NORTH, chatTextArea, 39, SpringLayout.NORTH, this);
	baseLayout.putConstraint(SpringLayout.WEST, chatTextArea, 120, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 1, SpringLayout.NORTH, TextField);
	baseLayout.putConstraint(SpringLayout.WEST, TextField, 10, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.SOUTH, TextField, -10, SpringLayout.SOUTH, this);
	baseLayout.putConstraint(SpringLayout.EAST, chatButton, -10, SpringLayout.EAST, this);
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
