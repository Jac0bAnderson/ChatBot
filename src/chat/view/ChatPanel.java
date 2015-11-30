package chat.view;

import javax.swing.*;
import chat.controller.ChatController;

public class ChatPanel extends JPanel
{
private ChatController baseController;
private SpringLayout baseLayout;

public ChatPanel(ChatController baseController)
{
	this.baseController = baseController;
	
	setupPanel();
	setupLayout();
	setupListeners();
}
private void setupPanel()
{
	this.setLayout(baseLayout);
	
}
	
private void setupLayout()
{
	
}
	private void setupListeners()
	{
		
	}
	
	
}
