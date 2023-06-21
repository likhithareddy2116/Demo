package com.rgt.sample.RGTMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rgt.sample.RGTMessaging.Entity.Tweet;
import com.rgt.sample.RGTMessaging.Entity.User;
import com.rgt.sample.RGTMessaging.exception.UserAlreadyExistsException;
import com.rgt.sample.RGTMessaging.exception.UserNotFoundException;

public class RGTMessaging {
	private HashMap<String, User> users;
	private ArrayList<Tweet> tweets;
	private User currentUser;

	public RGTMessaging() {
		this.users = new HashMap<>();
		this.tweets = new ArrayList<>();
	}
	/**
	 * This method is used for Registration for User
	 * @param username
	 * @param password
	 * @param name
	 * @param bio
	 * @return
	 * @throws If user is already exist throws UserAlreadyExistsException
	 * 
	 */

	public boolean registerUser(String username, String password, String name, String bio)
			throws UserAlreadyExistsException {
		if (users.containsKey(username)) {
			throw new UserAlreadyExistsException("User with username '" + username + "' already exists");
		}
		User user = new User(username, password, name, bio);
		users.put(username, user);
		return true;
	}
	
	/**
	 * this method is used for login user
	 * @param username
	 * @param password
	 * @return
	 * @throws if user details not found throws UserNotFoundException
	 */

	public User login(String username, String password) throws UserNotFoundException {
		User user = users.get(username);

		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}

		if (user != null && user.getPassword().equals(password)) {
			currentUser = user;
			return user;
		}
		return null;
	}
	/**
	 * this method is used for post the tweet
	 * @param username
	 * @param content
	 */

	public void postTweet(String username, String content) {
		if (users.containsKey(username)) {
			User user = users.get(username);
			user.postTweet(content);
		}
	}
	/**
	 * this method is used for to follow the user with username
	 * @param follower
	 * @param username
	 * @return
	 */

	public boolean followUser(User follower, String username) {
		if (users.containsKey(username)) {
			User user = users.get(username);
			follower.follow(user.getUsername());
			user.getFollowers().add(follower.getUsername());
			return true;
		}
		return false;
	}
	/**
	 * this method to get the timeLine for User
	 * @param user
	 * @return
	 */

	public List<Tweet> getTimeline(User user) {
		List<Tweet> timeline = new ArrayList<>();
		for (String following : user.getFollowings()) {
			User followedUser = users.get(following);
			timeline.addAll(followedUser.getTweets());
		}
		timeline.sort((t1, t2) -> Long.compare(t2.getTimestamp(), t1.getTimestamp()));
		return timeline;
	}
	/**
	 * this method is used for search the user based on username
	 * @param username
	 * @return
	 */

	public User searchForUser(String username) {
		if (users.containsKey(username)) {
			User user = users.get(username);
			return user;
		}

		return null;
	}
	
	/**
	 * this method is used for search the tweets with username;
	 * @param username
	 * @return
	 */
	public List<Tweet> searchTweets(String username) {
		List<Tweet> searchResults = new ArrayList<>();

		for (User user : users.values()) {
			List<Tweet> userTweets = user.getTweets();
			for (Tweet tweet : userTweets) {
				if (tweet.getContent().toLowerCase().contains(username.toLowerCase())) {
					searchResults.add(tweet);
				}
			}
		}
		return searchResults;
	}
	/**
	 * this method is used for unfollow the user
	 * @param username
	 * @return
	 */
	public boolean unfollow(String username) {
		if (currentUser == null) {
			return false;
		}
		User user = users.get(username);
		if (user != null && currentUser.getFollowings().contains(username)) {
			currentUser.unfollow(username);
			user.getFollowers().remove(currentUser.getUsername());
			return true;
		}
		return false;
	}

   /**
    * get profile for user
    * @param user
    * @return
    */

	public User getProfile(User user) {
		return user;
	}

	

	
	public void saveData(String fileName) {
		// Implementation to save data to a file
		DataStore.saveData(users, tweets, fileName);
	}

	public void loadData(String fileName) {
		// Implementation to load data from a file
		DataStore.loadData(users, tweets, fileName);
	}

	/**
	 * user logout
	 */
	public void logOut() {
		currentUser=null;
		System.out.println("LogedOut successfully");
		
	}

}