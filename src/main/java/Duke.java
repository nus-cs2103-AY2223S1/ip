import java.util.Scanner;

public class Duke {

    private static Utilities util;

    Duke() {
        util = new Utilities();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }

    public void startDuke() {
        sendMessageIntro();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                sendMessageExit();
                break;
            }
            util.printMsg(input);
        }
        sc.close();
    }

    public void sendMessageIntro() {
        util.printMsg("Hello! I'm Duke. \n\tWhat can I do for you?");
    }

    public void sendMessageExit() {
        util.printMsg("Bye. Hope to see you again soon!");
    }

}