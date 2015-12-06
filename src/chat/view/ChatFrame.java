//package
package chat.view;
//imports
import javax.swing.JFrame;
import chat.controller.ChatController;


public class ChatFrame extends JFrame
{
	//declaration
private ChatPanel basePanel;
private ChatController baseController;

public ChatFrame(ChatController baseController)
{
	this.baseController = baseController;
	basePanel = new ChatPanel(baseController);
	setupFrame();
}
//sets the size, visibility, if its Resizable,  and the frames title
private void setupFrame()
{
	this.setContentPane(basePanel);
	this.setSize(600,600);        // sets the size of the frame when it first appears on the screen
	this.setVisible(true);		//makes the frame visible
    this.setResizable(true);    // lets the user change its size
    this.setTitle("Chat Bot");  // the frames title
}
public ChatController getBaseController()
{

  return baseController;	
}
}
