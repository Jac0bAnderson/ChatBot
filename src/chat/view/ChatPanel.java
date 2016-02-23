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
	this.add(textPane);//chatTextArea used to be here
	this.add(TextField);
	this.add(chatButton);
	this.setLayout(baseLayout);
	
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
