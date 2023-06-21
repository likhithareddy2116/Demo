package com.rgt.sample.RGTMessaging.Main;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.rgt.sample.RGTMessaging.RGTMessaging;
import com.rgt.sample.RGTMessaging.Entity.Tweet;
import com.rgt.sample.RGTMessaging.Entity.User;
import com.rgt.sample.RGTMessaging.exception.UserAlreadyExistsException;
import com.rgt.sample.RGTMessaging.exception.UserNotFoundException;

public class RGTMain {

	private static User currentUser;
	private static Scanner scanner;
	private static RGTMessaging messaging;
	private static HashMap<String, User> users;
	private static String DATA_FILE = "data.json";

	public static void main(String[] args) throws UserNotFoundException {
		messaging = new RGTMessaging();
		messaging.loadData(DATA_FILE);
		scanner = new Scanner(System.in);
		users = new HashMap<>();
		currentUser = null;
		boolean exit = false;
		while (!exit) {
			System.out.println("Welcome to RGTMessaging");
			System.out.println("1.Register");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				registerUser();
				break;
			case 2:
				loginUser();
				break;
			case 3:
				exit = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
			messaging.saveData(DATA_FILE);
		}
		System.out.println("Thank you for using RGTMessaging!");

	}

	private static void registerUser() throws UserAlreadyExistsException {
		System.out.println("Enter userName");
		String userName = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		System.out.println("Enter your name");
		String name = scanner.next();
		System.out.println("Enter bio");
		String bio = scanner.next();
		try {
			if (messaging.registerUser(userName, password, name, bio)) {
				System.out.println("Registration Successfull");
			}
		} catch (UserAlreadyExistsException e) {
			System.out.println("Already User Registred" + e.getMessage());
		}

	}

	private static void loginUser() throws UserNotFoundException {
		System.out.println("Enter username:");
		String username = scanner.nextLine();

		System.out.println("Enter password:");
		String password = scanner.nextLine();

		User login = messaging.login(username, password);
		if (login != null) {
			currentUser = login;
			System.out.println("Login successful. Welcome, " + username + "!");
			loggedInMenu(username);
		} else {
			System.out.println("Login failed. Invalid username or password.");
		}
	}

	private static void loggedInMenu(String username) {
		boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("\n WELCOME USER : " + username);
			System.out.println("Please choose an option:");
			System.out.println("1. Follow user");
			System.out.println("2. Post tweet");
			System.out.println("3. View your timeline");
			System.out.println("4. Search for users");
			System.out.println("5. Search for tweets");
			System.out.println("6. Delete a tweet");
			System.out.println("7. Display profile");
			System.out.println("8. Unfollow user");
			System.out.println("9. Logout");
			int choice = scanner.nextInt();
			
			scanner.nextLine();

			switch (choice) {
			case 1:
				followUser();
				break;
			case 2:
				postTweet();
				break;
			case 3:
				viewTimeLine();
				break;
			case 4:
				searchForUsers();
				break;
			case 5:
				searchForTweets();
				break;
			case 6:
				deleteTweet();
				break;
			case 7:
				displayProfile();
				break;
			case 8:
				unFollowUser();
				break;
			case 9:
				logout();
				isLoggedIn = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private static void followUser() {
		if (currentUser == null) {
			System.out.println("Please login to follow a user.");
			return;
		}

		System.out.println("Enter the username of the user you want to follow:");
		String username = scanner.next();

		Boolean success = messaging.followUser(currentUser, username);
		if (success) {
			System.out.println("You are now following the user." + username);
		} else {
			System.out.println("Failed to follow the user.");
		}
	}

	private static void postTweet() {
		if (currentUser == null) {
			System.out.println("Please login to post a tweet.");
			return;
		}

		System.out.println("Enter your tweet content:");
		String content = scanner.next();
		System.out.println("Enter the username");
		String username = scanner.next();
		messaging.postTweet(username, content);
		System.out.println("Tweet posted successfully!");

	}

	private static void viewTimeLine() {
		if (currentUser == null) {
			System.out.println("Please login to display the timeline.");
			return;
		}

		List<Tweet> timeline = messaging.getTimeline(currentUser);
		if (timeline.isEmpty()) {
			System.out.println("No tweets to display.");
		} else {
			System.out.println("Your TimeLine:");
			for (Tweet tweet : timeline) {
				long times = tweet.getTimestamp();
				Timestamp timestamp = new Timestamp(times);
				System.out.println("Tweet Id:" + tweet.getId());
				System.out.println("Author:@" + tweet.getAuthor());
				System.out.println("Content:" + tweet.getContent());
				System.out.println("Timestamp:" + timestamp);
				System.out.println();
			}
		}
	}

	private static void searchForUsers() {
		System.out.println("Enter the username you want");
		String username = scanner.next();
		User user = messaging.searchForUser(username);
		if (user != null) {
			System.out.println("User found:");
			System.out.println("Username: " + user.getUsername());
			System.out.println("Name: " + user.getName());
			System.out.println("Bio: " + user.getBio());
			System.out.println("Followers:" + user.getFollowers());
			System.out.println("Followings:" + user.getFollowings());
			System.out.println("Tweets:" + user.getTweets());
		} else {
			System.out.println("User not found.");
		}
	}

	private static void searchForTweets() {
		System.out.print("Enter the tweet content you want to search for: ");
		String searchContent = scanner.next();
		List<Tweet> tweets = messaging.searchTweets(searchContent);
		if (!tweets.isEmpty()) {
			System.out.println("Tweets found:");
			for (Tweet tweet : tweets) {
				long times = tweet.getTimestamp();
				Timestamp timestamp = new Timestamp(times);
				System.out.println("ID: " + tweet.getId());
				System.out.println("Content: " + tweet.getContent());
				System.out.println("Author: " + tweet.getAuthor());
				System.out.println("Timestamp: " + timestamp);
				System.out.println();
			}
		} else {
			System.out.println("No tweets found.");
		}
	}

	private static void deleteTweet() {
		if (currentUser == null) {
			System.out.println("You are not logged in. Please log in first.");
			return;
		}
		System.out.println("Enter tweet ID:");
		String tweetId = scanner.next();
		currentUser.deleteTweet(tweetId);
		System.out.println("Tweet deleted successfully.");
	}

	private static void displayProfile() {
		if (currentUser == null) {
			System.out.println("Please login to display your profile.");
			return;
		}
		User user = messaging.getProfile(currentUser);
		System.out.println("Your profile:");
		System.out.println("Username: " + user.getUsername());
		System.out.println("Name: " + user.getName());
		System.out.println("Bio: " + user.getBio());
		System.out.println("Followers: " + user.getFollowers());
		System.out.println("Followings: " + user.getFollowings());
		// System.out.println("Tweets: " + user.getTweets());
	}

	private static void unFollowUser() {
		System.out.println("Enter the username of the user you want to unfollow:");
		String username = scanner.next();

		if (messaging.unfollow(username)) {
			System.out.println("User unfollowed successfully with username" + username);
		} else {
			System.out.println("User not found or not followed.");
		}
	}

	private static void logout() {

		messaging.logOut();
	}

}
