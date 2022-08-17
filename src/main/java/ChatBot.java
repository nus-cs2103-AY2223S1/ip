import java.util.*;

public class ChatBot {
    private final Scanner sc = new Scanner(System.in);
    private final String initialGreeting = "Hello, I'm Logits. What can I do for you?";
    private final String goodbyeGreeting = "Bye. Hope to see you soon!";
    private final String listString = "list";
    private final String splitLine = " ".repeat(5) + "*".repeat(50);
    private ArrayList<String> items = new ArrayList<>();

    /**
     * Check if the given string is a "bye".
     * @param s The string that requires checking
     * @return if s is any version of "bye", then true; Otherwise false.
     */
    private Boolean checkBye(String s) {
        return s.toLowerCase().equals("bye");
    }

    /**
     * Check if the given string is a "list".
     * @param s The string that requires checking
     * @return if s is any version of "bye", then true; Otherwise false.
     */
    private Boolean checkList(String s) {
        return s.toLowerCase().equals("list");
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
        } else if (checkList(userInput)){
            return listString;
        } else {
            return userInput;
        }
    }

    /**
     * Add user's input to the list
     * @param s user's input
     */
    private void addToList(String s) {
        items.add(s);
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
     * Display chatbot reply with list command
     * @param reply chatbot reply
     * @param isList boolean value to show if the reply is a list command
     */
    private void display(String reply, boolean isList) {
        if (isList) {
            System.out.println(splitLine);
            for(int i = 0; i < items.size(); ++i) {
                System.out.println(" ".repeat(10) + (i + 1) + ". " + items.get(i));
            }
            System.out.println(splitLine);
        } else {
            System.out.println(splitLine);
            System.out.println(" ".repeat(10) + "added: " + reply);
            System.out.println(splitLine);
        }
    }

    /**
     * The main control flow of the chatbot's behaviour.
     */
    public void greet() {
        display(initialGreeting);

        while (true) {
            String userInput = inputFromUser();
            if (userInput.length() == 0) {
                continue;
            }
            boolean isList = userInput.equalsIgnoreCase("list");
            boolean isBye = userInput.equalsIgnoreCase("bye");
            if (!isList) {
                addToList(userInput);
            }
            String reply = replyToUser(userInput);
            if (isBye) {
                display(reply);
                break;
            }
            display(reply, isList);
        }
    }

}
