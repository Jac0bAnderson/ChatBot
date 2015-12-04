package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatController;


public class ChatFrame extends JFrame
{
private ChatPanel basePanel;

private ChatController baseController;
public ChatFrame(ChatController baseController)
{
	this.baseController = baseController;
	basePanel = new ChatPanel(baseController);
	setupFrame();
}
private void setupFrame()
{
	this.setContentPane(basePanel);
	this.setSize(600,600);        
	this.setVisible(true);		
    this.setResizable(true);   
    this.setTitle("Chat Bot");  
}
public ChatController getBaseController()
{

  return baseController;	
}
}
