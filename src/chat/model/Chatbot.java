package chat.model;

import java.util.ArrayList;

import chat.view.ChatView;

/**
 * added a constructor 
 * @author Jake
 * @version 1.2 10/21/15
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;
	private ChatView myDisplay;
	
	/**
	 * Creates an instance of the Chatbot with the supplied username.
	 * @param userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.memesList = new ArrayList<String>();
		this.politicalTopicList =new ArrayList<String>();
		this.userName = userName;
		this.content = "content";
		
		
		buildMemesList();
		buildPoliticalTopicsList();
	}
	
	private void buildMemesList()
	{
		//cute animals
	this.memesList.add("cute andimals");
	this.memesList.add("kittens");
	this.memesList.add("puppies");
	//doge
	this.memesList.add("doge");
	this.memesList.add("much");
	this.memesList.add("wow");
	//what if it told you
	this.memesList.add("what if i told you");
	
	//aliens
	this.memesList.add("aliens");
	//bad luck brian
	this.memesList.add("bad luck brian");
	//pepe
	this.memesList.add("rare pepe");
	this.memesList.add("pepe");
	//spoderman
	this.memesList.add("spoderman");
	//highschool teacher
	this.memesList.add("highschool teacher");
	//me gusta
	this.memesList.add("me gusta");
	this.memesList.add("rage faces");
	//troll face
	this.memesList.add("troll");
	this.memesList.add("rage faces");
	this.memesList.add("troll face");
	this.memesList.add("Lol");
	this.memesList.add("lolololol");
	//hank hill
	this.memesList.add("hank hill");
	this.memesList.add("propane");
	this.memesList.add("dang it bobby");
	this.memesList.add("america");
	}
	
	private void buildPoliticalTopicsList()
	{
		this.politicalTopicList.add("trump");
		this.politicalTopicList.add("election");
		this.politicalTopicList.add("democrat");
		this.politicalTopicList.add("republican");
		this.politicalTopicList.add("liberal");
		this.politicalTopicList.add("conservative");
		this.politicalTopicList.add("clinton");
		this.politicalTopicList.add("biden");
		this.politicalTopicList.add("carson");
		this.politicalTopicList.add("rubio");
		this.politicalTopicList.add("fiorina");
		this.politicalTopicList.add("sanders");
		this.politicalTopicList.add("vote");
		this.politicalTopicList.add("11/8/2016");
		
	}
	
	/**
	 * Checks the length of the supplied string. Returns false if the supplied String is empty or null,
	 * otherwise returns true. 
	 * @param currentInput
	 * @return A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		if(currentInput != null)
		{
			if(currentInput.length() >= 1)
			{
				hasLength = true;
			}
		}
		return hasLength;
	}
	
	/**
	 * Checks if the supplied String matches the content area for this Chatbot instance.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
boolean hasContent = false;
		
		if(currentInput.contains(content.toLowerCase()))
		{
			hasContent = true;
		}
		
		return hasContent;
	}
	
	/**
	 * Checks if supplied String matches ANY of the topics in the politicalTopicsList. Returns
	 * true if it does find a match and false if it does not match.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean political = false;
				for(String currentTopic : politicalTopicList)
				{
					if(currentTopic.equalsIgnoreCase(currentInput))
					{
						political = true;
					}
				}
		return political;
	}
	
	
	/**
	 * Checks to see that the supplied String value is in the current memesList variable.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMeme = false;
		for(String currentMeme:memesList)
		{
			if(currentMeme.equalsIgnoreCase(currentInput))
					{
						hasMeme = true;
					}
		}
		return hasMeme;
	}
	
	/**
	 * Returns the username of this Chatbot instance.
	 * @return The username of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Returns the content area for this Chatbot instance.
	 * @return The content area for this Chatbot instance.
	 */
	public String getContent()
	{
		
		return content;
	}
	
	/**
	 * Getter method for the memesList object.
	 * @return The reference to the meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}
	
	/**
	 * Getter method for the politicalTopicList object.
	 * @return The reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}
	
	/**
	 * Updates the content area for this Chatbot instance.
	 * @param content The updated value for the content area.
	 */
	public void setContent(String content)
	{
		
	}
	
	public String processConveration(String currentInput)
	{
		String nextConversation ="what would you like to talk bout m8";
		int randomTopic = (int) (Math.random()*5);// makes a random number between 0-4
		
		switch (randomTopic)
		{
		case 0:
			if(memeChecker(currentInput))
			{
				nextConversation = "That is very popular meme this year. what else are you"
						+"wanting to talk about?";
			}
			else
			{
				System.out.println("failed case 0");
			}
			break;
		case 1:
			if(politicalTopicChecker(currentInput))
			{
				nextConversation = "oh yea thats political.what else do you want to?"
						+"talk about";
			}
			else
			{
				System.out.println("failed case 1");
			}
			break;
		case 2:
			if(contentChecker(currentInput))
			{
				nextConversation = "oh yea. what else"
						+"do you  to talk about";
			}
			else
			{
				System.out.println("failed case 2");
			}
			break;
		case 3:
			if(currentInput.length()>20)
			{
				nextConversation = "idk. what else"
						+"do you want to ask";
			}
			else
			{
				System.out.println("failed case 3");
			}
			break;
		case 4:
			nextConversation = "yeaaaaaaaaaaaa.waht else"
			+"do you want to talk about";
			break;
			default:
				nextConversation = "what";
				break;
		}//every line after a "case" line needs a break; the default is incase nothing else matches
		return nextConversation;
	}
	
	
	
}
	