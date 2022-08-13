import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {

    private String name;
    private boolean isActive;

    private List<String> msgLog;

    public Chatbot(String name) {
        this.name = name;
        this.isActive = true;
        this.msgLog = new ArrayList<>();
    }

    public void start() {

        System.out.println("Hey, I am " + this.name + ". What can I do for you today?"); // Welcome text
        System.out.println("Type 'list' to list out all the previous messages.");
        System.out.println("Type 'bye' to finish the conversation.");

        Scanner sc = new Scanner(System.in);

        while (this.isActive) {

            System.out.print("Type your message to " + this.name + ": ");
            String msgToChatbot = sc.nextLine();
            System.out.println(this.name + " says: " + receiveMsg(msgToChatbot));

        }

    }

    private String receiveMsg(String msg) {

        if (msg.equals("bye")) {
            this.isActive = false;
            return "Goodbye, see you soon!";
        } else if (msg.equals("list")) {
            // Converts the list of messages to a human-readable multiline String
            return msgLog.stream().map(m -> "- " + m).reduce("", (acc, m) -> acc + "\n" + m);
        } else {
            msgLog.add(msg);
            return "Added: [" + msg + "]";
        }

    }

}
