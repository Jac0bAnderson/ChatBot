package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * GUI for the popup window
 * @author Jake anderso
 *@version 1.3 11/5/15 Added Icons to popup windows
 */
public class ChatView 
{
	private String windowMessage;
	private ImageIcon chatIcon;
	
	public ChatView()
	{
		windowMessage = "Hello from your friendly Pepe Bot";
		chatIcon = new ImageIcon(getClass().getResource("images/PepeBot.png"));
	}
	/**
	 * Displays the input from the user.
	 * @param userInput
	 */
	public void showOutput(String userInput)
	{
		JOptionPane.showMessageDialog(null, userInput,windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon);
		
	}
	
	/**
	 * Grabs the user input and uses JOptionPane to display it.
	 * @param displayText
	 * @return userAnswer
	 */
	public String grabInput(String displayText)
	{
		String userAnswer = JOptionPane.showInputDialog(null, displayText, windowMessage, JOptionPane.PLAIN_MESSAGE, chatIcon, null, "Answer here " ).toString();
		
		return userAnswer;
	} 
	

	
	
}
