//package 
package chat.view;

//imports 
import javax.swing.*;
import chat.controller.ChatController;
import java.awt.event.*;
import java.awt.Color;
import java.awt.color.*;

public class ChatPanel extends JPanel
{
//declarations
private ChatController baseController;
private SpringLayout baseLayout;
private JButton chatButton;
private JButton colorChanger;
private JTextArea chatTextArea;
private JTextField TextField;
private int red;
private int green;
private int blue;

public ChatPanel(ChatController baseController)
{
	this.baseController = baseController;
	baseLayout = new SpringLayout();
	
	//gives the chatButton text and a tooltip
	chatButton = new JButton("Submit");
	chatButton.setToolTipText("submits text to Chat Bot");
	
	//sets the starting text for the chat bots text area, gives it a tooltip and makes it so the user cant edit the chat bots text
	chatTextArea = new JTextArea("ChatBot: Hello");
	chatTextArea.setToolTipText("This is where the chat bot responds");
	chatTextArea.setEnabled(false);
	
	//sets the size of the users textfield and also gives it a tooltip
	TextField = new JTextField("",25);
	TextField.setToolTipText("Type here");
	
	//the text  and the tooltip of the colorChanger Button
	colorChanger = new JButton("Click for Colors");
	colorChanger.setToolTipText("Click to change colors");
	
	// starting RGB colors
	red = 255;
	green = 255;
	blue = 255;
	
	//calls the panel, layout, and listeners methods
	setupPanel();
	setupLayout();
	setupListeners();
}
private void setupPanel()
{//puts GUI components in the panel
	this.add(colorChanger);
	this.add(chatTextArea);
	this.add(TextField);
	this.add(chatButton);
	this.setLayout(baseLayout);
	
}
	
private void setupLayout()
{// tells the GUI components where to be on the panel
	baseLayout.putConstraint(SpringLayout.NORTH, chatTextArea, 39, SpringLayout.NORTH, this);
	baseLayout.putConstraint(SpringLayout.WEST, chatTextArea, 120, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 1, SpringLayout.NORTH, TextField);
	baseLayout.putConstraint(SpringLayout.WEST, TextField, 10, SpringLayout.WEST, this);
	baseLayout.putConstraint(SpringLayout.SOUTH, TextField, -10, SpringLayout.SOUTH, this);
	baseLayout.putConstraint(SpringLayout.EAST, chatButton, -10, SpringLayout.EAST, this);
	baseLayout.putConstraint(SpringLayout.NORTH, colorChanger, -1, SpringLayout.NORTH, TextField);
	baseLayout.putConstraint(SpringLayout.EAST, colorChanger, -6, SpringLayout.WEST, chatButton);
}
	private void setupListeners()
	{//sends the users texts and lets the chat bot respond
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
		// changes the color of the panel when the changecolors button it clicked
		colorChanger.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent clickEvent)
					{
						colorChange();
						setBackground(new Color(red, green, blue));
						
						
					}
				});
	}
	//method that determines the RGB code  
	private void colorChange()
	{
		
		red = (int) (Math.random()*255);
		green  = (int) (Math.random()*255);
		blue  = (int) (Math.random()*255);
	}
	//getters and setters
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
