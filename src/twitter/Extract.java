/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {               
        Timespan timestampOfTweets = null;
        
        if(tweets.isEmpty()){
            timestampOfTweets = new Timespan(Instant.EPOCH, Instant.EPOCH);            
        }else {
            final int startIndex = getMinimumTimestampIndex(tweets);
            final int endIndex = getMaximumTimestampIndex(tweets);
            timestampOfTweets = new Timespan(tweets.get(startIndex).getTimestamp(),tweets.get(endIndex).getTimestamp());
        }
        return timestampOfTweets;
    }
    
    /**
     * Get a index of tweet with the minimum timestamp within list.
     * 
     * @param tweets
     *            a non-empty list of tweets with distinct ids, not modified by this method.
     * @return the index of the tweet which has the minimum timestamp of all tweets in the list.
     */
    public static int getMinimumTimestampIndex(List<Tweet> tweets){
        Instant minimumTimestamp = Instant.MAX;
        int minIndex = -1;
        
        int index = 0;
        while(index < tweets.size()){
            if(tweets.get(index).getTimestamp().isBefore(minimumTimestamp) == true){
                minimumTimestamp = tweets.get(index).getTimestamp();
                minIndex = index;
                }
            index ++;
            }
        return minIndex;
    }
    
    /**
     * Get a index of tweet with the maximum timestamp within list.
     * 
     * @param tweets
     *            a non-empty list of tweets with distinct ids, not modified by this method.
     * @return the index of the tweet which has the maximum timestamp of all tweets in the list.
     */
    public static int getMaximumTimestampIndex(List<Tweet> tweets){
        Instant maximumTimestamp = Instant.MIN;
        int maxIndex = tweets.size();
        
        int index = 0;
        while(index < tweets.size()){
            if(tweets.get(index).getTimestamp().isAfter(maximumTimestamp) == true){
                maximumTimestamp = tweets.get(index).getTimestamp();
                maxIndex = index;
                }
            index ++;
            }
        return maxIndex;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        throw new RuntimeException("not implemented");
    }

}
