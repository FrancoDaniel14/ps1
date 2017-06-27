/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is the main program.
 * 
 * You may change this class if you wish, but you don't have to.
 */
public class Main {
    
    /**
     * URL of a server that produces a list of tweets sampled from Twitter
     * within the last hour. This server may take up to a minute to respond, if
     * it has to refresh its cached sample of tweets.
     */
    public static final URL SAMPLE_SERVER = makeURLAssertWellFormatted("http://courses.csail.mit.edu/6.005/ps1_tweets/tweetPoll.py");
    
    private static URL makeURLAssertWellFormatted(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException murle) {
            throw new AssertionError(murle);
        }
    }
    
    /**
     * Main method of the program. Fetches a sample of tweets and prints some
     * facts about it.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            assert false;
            throw new Error("Always run main and tests with assertions enabled");
        } catch (AssertionError ae) { }
        
        final List<Tweet> tweets;
        final List<Tweet> emptyList;
        final List<Tweet> oneElementList;
        final List<Tweet> nonEmptyList;
        
        try {
            emptyList = new ArrayList<Tweet>();
            System.out.println(Extract.getTimespan(emptyList));

            final Instant d1 = Instant.parse("2017-06-23T10:00:00Z");
            final Tweet tweet1 = new Tweet(1, "daniel", "is it reasonable to talk about rivest so much?", d1);
            
            final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");   
            final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
            
            final Instant d3 = Instant.parse("2017-06-23T10:00:00Z");   
            final Tweet tweet3 = new Tweet(3, "raul", "Jesus is Lord", d3);
            
            oneElementList = new ArrayList<Tweet>();
            oneElementList.add(tweet1);
            System.out.println(Extract.getTimespan(oneElementList));
            
            nonEmptyList = new ArrayList<Tweet>();    
            nonEmptyList.add(tweet1);
            nonEmptyList.add(tweet2);
            nonEmptyList.add(tweet3);            
            System.out.println(Extract.getTimespan(nonEmptyList));
            
            tweets = TweetReader.readTweetsFromWeb(SAMPLE_SERVER);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        
        // display some characteristics about the tweets
        System.err.println("fetched " + tweets.size() + " tweets");
        
        final Timespan span = Extract.getTimespan(tweets);
        System.err.println("ranging from " + span.getStart() + " to " + span.getEnd());
        
        final Set<String> mentionedUsers = Extract.getMentionedUsers(tweets);
        System.err.println("covers " + mentionedUsers.size() + " Twitter users");
        
        // infer the follows graph
        final Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        System.err.println("follows graph has " + followsGraph.size() + " nodes");
        
        // print the top-N influencers
        final int count = 10;
        final List<String> influencers = SocialNetwork.influencers(followsGraph);
        for (String username : influencers.subList(0, Math.min(count, influencers.size()))) {
            System.out.println(username);
        }
    }
    
}
