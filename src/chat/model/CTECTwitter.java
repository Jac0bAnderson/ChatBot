package chat.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import chat.controller.ChatController;
import twitter4j.*;

/**
 * 
 * @author jand6944
 * @version 0.5
 */
public class CTECTwitter {
	private ArrayList<Status> statusList;
	private ArrayList<String> wordList;
	private Twitter chatbotTwitter;
	private ChatController baseController;

	public CTECTwitter(ChatController baseController) {
		this.baseController = baseController;
		chatbotTwitter = TwitterFactory.getSingleton();
		statusList = new ArrayList<Status>();
		wordList = new ArrayList<String>();

	}

	public void loadTweets(String twitterHandle) throws TwitterException {
		Paging statusPage = new Paging(1, 200);
		int page = 1;
		while (page <= 10) {
			statusPage.setPage(page);
			statusList.addAll(chatbotTwitter.getUserTimeline(twitterHandle,
					statusPage));
			page++;
		}
		for (Status currentStatus : statusList) {
			String[] tweetText = currentStatus.getText().split(" ");
			for (String word : tweetText) {
				wordList.add(removePunctuation(word).toLowerCase());
			}
		}
		removeCommonEnglishWords(wordList);
		removeEmptyText();
	}

	/**
	 * Goes through words and takes out .,'?!:;\"()^[]<>- from them.
	 * 
	 * @param word
	 * @return scrubbedString
	 */
	private String removePunctuation(String word) {
		String punctuation = ".,'?!:;\"()^[]<>-";
		String scrubbedString = "";
		for (int i = 0; i < word.length(); i++) {
			if (punctuation.indexOf(word.charAt(i)) == -1) {
				scrubbedString += word.charAt(i);
			}
		}
		return scrubbedString;
	}

	@SuppressWarnings("unchecked")
	private List removeCommonEnglishWords(List<String> wordList) {
		String[] boringWords = importWordsToArray();
		for (int count = 0; count < wordList.size(); count++) {
			for (int removeSpot = 0; removeSpot < boringWords.length; removeSpot++) {
				if (wordList.get(count).equalsIgnoreCase(
						boringWords[removeSpot])) {
					wordList.remove(count);
					count--;
					removeSpot = boringWords.length;
				}
			}
		}

		// removeTwitterUsernamesFromList(wordList)
		return wordList;
	}

	private String[] importWordsToArray() {
		String[] boringWords;
		int wordCount = 0;
		try {
			Scanner wordFile = new Scanner(new File("commonWords.txt"));
			while (wordFile.hasNext()) {
				wordCount++;
				wordFile.next();
			}
			wordFile.reset();
			boringWords = new String[wordCount];
			int boringWordCount = 0;
			while (wordFile.hasNext()) {
				boringWords[boringWordCount] = wordFile.next();
				boringWordCount++;
			}
			wordFile.close();
		} catch (FileNotFoundException e) {
			return new String[0];
		}
		return boringWords;
	}

	private void removeEmptyText() {
		for (int spot = 0; spot < wordList.size(); spot++) {
			if (wordList.get(spot).equals("")) {
				wordList.remove(spot);
				spot--;// goes one so it does not miss anything in the list.ie
						// if there are 2 things that need to be removed that
						// are next to each other
			}
		}
	}

	public void sendTweet(String tweet) {
		String tweetTExt = JOptionPane.showInputDialog("");
		try {
			chatbotTwitter.updateStatus(tweetTExt);
		} catch (TwitterException error) {

			baseController.handleErrors(error.getErrorMessage());
		}
	}
	public String topResults(List <String> wordList)
	{
		String tweetResults = "";
		int topWordLocation = 0;
		int topCount = 0;
		
		for (int index = 0; index < wordList.size(); index ++ )
		{
			int wordUseCount = 1;
			for (int spot = index + 1; spot < wordList.size(); spot++)
			{
				if(wordList.get(index).equals(wordList.get(spot)))
				{
					wordUseCount++;
				}
				if(wordUseCount > topCount)
				{
					topCount= wordUseCount;
					topWordLocation = index;
				}
			}
		}
		tweetResults = "The most used word was "+ wordList.get(topWordLocation) + "and it was use " + topCount + " times.";
		return tweetResults;
	     
	}

}
