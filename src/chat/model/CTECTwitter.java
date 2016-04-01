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
	private ArrayList<String> wordsList;
	private Twitter chatbotTwitter;
	private ChatController baseController;

	public CTECTwitter(ChatController baseController) {
		this.baseController = baseController;
		chatbotTwitter = TwitterFactory.getSingleton();
		statusList = new ArrayList<Status>();
		wordsList = new ArrayList<String>();

	}

	public void loadTweets(String twitterHandle) throws TwitterException 
	{
		statusList.clear();
		wordsList.clear();
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
				wordsList.add(removePunctuation(word).toLowerCase());
			}
		}
		removeCommonEnglishWords(wordsList);
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
/**
 * Goes through the commonwords list and removes common words.
 * @param wordsList
 * @return wordsList
 */
	@SuppressWarnings("unchecked")
	private List removeCommonEnglishWords(List<String> wordsList) {
		String[] boringWords = importWordsToArray();
		for (int count = 0; count < wordsList.size(); count++) {
			for (int removeSpot = 0; removeSpot < boringWords.length; removeSpot++) {
				if (wordsList.get(count).equalsIgnoreCase(
						boringWords[removeSpot])) {
					wordsList.remove(count);
					count--;
					removeSpot = boringWords.length;
				}
			}
		}

		// removeTwitterUsernamesFromList(wordsList)
		return wordsList;
	}
/**
 * takes the words from the commonWords and puts them in an Array.
 * @return boringWords
 */
	private String[] importWordsToArray() {
		String[] boringWords;
		int wordCount = 0;
		
			Scanner wordFile = new Scanner(getClass().getResourceAsStream("commonWords.txt"));
			
			while (wordFile.hasNext()) {
				wordCount++;
				wordFile.next();
			}
			wordFile = new Scanner(getClass().getResourceAsStream("commonWords.txt"));
			boringWords = new String[wordCount];
			int boringWordCount = 0;
			
			while (wordFile.hasNext()) {
				boringWords[boringWordCount] = wordFile.next();
				boringWordCount++;
			}
			wordFile.close();
		
		return boringWords;
	}
/**
 * removes white space
 */
	private void removeEmptyText() {
		for (int spot = 0; spot < wordsList.size(); spot++) {
			if (wordsList.get(spot).equals("")) {
				wordsList.remove(spot);
				spot--;// goes one so it does not miss anything in the list.ie
						// if there are 2 things that need to be removed that
						// are next to each other
			}
		}
	}

	public void sendTweet(String tweet) {
		//String tweetTExt = JOptionPane.showInputDialog("");
		String tWeet = tweet;
		try {
			chatbotTwitter.updateStatus(tWeet);
		} catch (TwitterException error) {

			baseController.handleErrors(error.getErrorMessage());
		}
	}
	/**
	 * Finds the most used word.
	 * @return tweetResults
	 */
	public String topResults()
	{
		String tweetResults = "";
		int topWordLocation = 0;
		int topCount = 0;
		
		for (int index = 0; index < wordsList.size(); index ++ )
		{
			int wordUseCount = 1;
			for (int spot = index + 1; spot < wordsList.size(); spot++)
			{
				if(wordsList.get(index).equals(wordsList.get(spot)))
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
		tweetResults = "The most used word was "+ wordsList.get(topWordLocation) + " and it was use   " +topCount+ "   times.";
		return tweetResults;
	     
	}//String topic
	public String tweetInvestigation(String topic)
	{
		String results = "";
		//Query query = new Query("Alta");
		Query query = new Query(topic);
		System.out.println(topic);
		query.setCount(100);
		query.setGeoCode(new GeoLocation(40.53992370784733, -111.88577681779861), 50, Query.MILES);
		query.setSince("2015-1-1");
		try
		{
			QueryResult result = chatbotTwitter.search(query);
			results +=("count: "+result.getTweets().size());
			for(Status tweet : result.getTweets())
			{
				results += ("@" +tweet.getUser().getName() + ": "+tweet.getText()+"\n");
			}
		}
		catch(TwitterException error)
		{
			error.printStackTrace();
		}
		return results;
	}

}
