import java.util.*;

public class ChatBot {
    private final Scanner sc = new Scanner(System.in);
    private final String initialGreeting = "Hello, I'm Logits. What can I do for you?";
    private final String goodbyeGreeting = "Bye. Hope to see you soon!";
    private final String splitLine = " ".repeat(5) + "*".repeat(50);

    /**
     * Check if the given string is a "bye".
     * @param s The string that requires checking
     * @return if s is any version of "bye", then true; Otherwise false.
     */
    private Boolean checkBye(String s) {
        return s.toLowerCase().equals("bye");
    }

    /**
     * Retrieve input from the user
     * @return user's input string
     */
    private String inputFromUser() {
        return sc.nextLine().strip().replaceAll("(?m)^\\s*$[\n\r]+", "");
    }

    /**
     * Reply to the user based on his input.
     * @param userInput user's input string.
     * @return chatbot reply
     */
    private String replyToUser(String userInput) {
        if (checkBye(userInput)) {
            return goodbyeGreeting;
        } else {
            return userInput;
        }
    }

    /**
     * Display chatbot reply
     * @param reply chatbot reply
     */
    private void display(String reply) {
        System.out.println(splitLine);
        System.out.println(" ".repeat(10) + reply);
        System.out.println(splitLine);
    }

    /**
     * The main control flow of the greeting behaviour.
     */
    public void greet() {
        display(initialGreeting);

        while (true) {
            String userInput = inputFromUser();
            String reply = replyToUser(userInput);
            if (reply.length() == 0) {
                continue;
            }
            display(reply);
            if (reply.equals(goodbyeGreeting)) {
                break;
            }
        }
    }

}
