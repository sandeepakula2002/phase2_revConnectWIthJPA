package com.revconnect.console;

import com.revconnect.models.Post;
import com.revconnect.models.User;
import com.revconnect.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private Scanner sc = new Scanner(System.in);

    @Autowired private UserService userService;
    @Autowired private PostService postService;
    @Autowired private LikeService likeService;
    @Autowired private CommentService commentService;
    @Autowired private ConnectionService connectionService;

    private User loggedInUser = null;

    // Add this getter method
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void start() {
        System.out.println("=== RevConnect ===");

        while (true) {
            if (loggedInUser == null) {
                authMenu();
            } else {
                mainMenu();
            }
        }
    }

    private void authMenu() {
        System.out.println("\n1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice");
        }
    }

    private void register() {
        System.out.print("\nEmail: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Account Type (PERSONAL/CREATOR/BUSINESS): ");
        String type = sc.nextLine().toUpperCase();

        try {
            User user = new User(email, password, type);
            userService.register(user);
            System.out.println("Account created!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void login() {
        System.out.print("\nEmail: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Optional<User> user = userService.login(email, password);

        if (user.isPresent()) {
            loggedInUser = user.get();

            System.out.println("Welcome, " + loggedInUser.getEmail() + "!");
        } else {
            System.out.println("Invalid login");
        }
    }

    private void mainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Post");
        System.out.println("2. View My Posts");
        System.out.println("3. Like Post");
        System.out.println("4. Add Comment");
        System.out.println("5. Send Connection");
        System.out.println("6. View Requests");
        System.out.println("7. View Connections");
        System.out.println("8. Logout");
        System.out.print("Choose: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> createPost();
            case 2 -> viewMyPosts();
            case 3 -> likePost();
            case 4 -> addComment();
            case 5 -> sendRequest();
            case 6 -> viewPendingRequests();
            case 7 -> viewConnections();
            case 8 -> logout();
            default -> System.out.println("Invalid choice");
        }
    }

    private void createPost() {
        System.out.print("\nPost content: ");
        String content = sc.nextLine();

        try {
            Post post = postService.createPost(loggedInUser.getId(), content);
            System.out.println("Post created! ID: " + post.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewMyPosts() {
        List<Post> posts = postService.getPostsByUserId(loggedInUser.getId());

        if (posts.isEmpty()) {
            System.out.println("No posts");
            return;
        }

        for (Post p : posts) {
            System.out.println("\nID: " + p.getId());
            System.out.println("Content: " + p.getContent());
            int likes = likeService.getLikeCount(p.getId());
            System.out.println("Likes: " + likes);
        }
    }

    private void likePost() {
        viewMyPosts();
        System.out.print("\nPost ID to like: ");
        long postId = sc.nextLong();
        sc.nextLine();

        try {
            likeService.likePost(loggedInUser.getId(), postId);
            System.out.println("Liked!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addComment() {
        viewMyPosts();
        System.out.print("\nPost ID: ");
        long postId = sc.nextLong();
        sc.nextLine();

        System.out.print("Comment: ");
        String text = sc.nextLine();

        try {
            commentService.addComment(loggedInUser.getId(), postId, text);
            System.out.println("Comment added!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sendRequest() {
        System.out.print("\nUser ID to connect: ");
        long receiverId = sc.nextLong();
        sc.nextLine();

        try {
            connectionService.sendRequest(loggedInUser.getId(), receiverId);
            System.out.println("Request sent!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewPendingRequests() {
        List<com.revconnect.models.Connection> requests =
                connectionService.getPendingRequests(loggedInUser.getId());

        if (requests.isEmpty()) {
            System.out.println("No pending requests");
            return;
        }

        for (com.revconnect.models.Connection r : requests) {
            System.out.println("\nRequest ID: " + r.getId());
            System.out.println("From: User " + r.getSender().getId());
        }

        System.out.print("\nAccept request ID (0 to skip): ");
        long requestId = sc.nextLong();
        sc.nextLine();

        if (requestId > 0) {
            try {
                connectionService.acceptRequest(requestId);
                System.out.println("Accepted!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void viewConnections() {
        List<com.revconnect.models.Connection> connections =
                connectionService.getConnections(loggedInUser.getId());

        if (connections.isEmpty()) {
            System.out.println("No connections");
            return;
        }

        for (com.revconnect.models.Connection c : connections) {
            User other = c.getSender().getId().equals(loggedInUser.getId())
                    ? c.getReceiver() : c.getSender();
            System.out.println("\nUser ID: " + other.getId());
            System.out.println("Email: " + other.getEmail());
        }
    }

    private void logout() {
        loggedInUser = null;
        System.out.println("\nLogged out");
    }
}