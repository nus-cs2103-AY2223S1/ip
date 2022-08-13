import java.util.Scanner;

public class Chatbot {

    private String name;
    private boolean isActive;

    public Chatbot(String name) {
        this.name = name;
        this.isActive = true;
    }

    public void start() {

        System.out.println("Hey, I am " + this.name + ". What can I do for you today?"); // Welcome text

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
        } else {
            return msg;
        }

    }

}
