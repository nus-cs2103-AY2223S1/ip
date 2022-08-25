package drivers;

import java.util.Scanner;

/**
 * Deals with interactions from the user.
 */
public class UI {
    private Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public void greeting() {
        /**
         * Greeting message to the user during chat-bot startup.
         */

        String logo = "" +
                "\t ▄▄▄▄▄▄▄ ▄▄   ▄▄ ▄▄   ▄▄ ▄▄   ▄▄ \n" +
                "\t█       █  █ █  █  █▄█  █  █ █  █\n" +
                "\t█▄     ▄█  █ █  █       █  █ █  █\n" +
                "\t  █   █ █  █▄█  █       █  █▄█  █\n" +
                "\t  █   █ █       █       █       █\n" +
                "\t  █   █ █       █ ██▄██ █       █\n" +
                "\t  █▄▄▄█ █▄▄▄▄▄▄▄█▄█   █▄█▄▄▄▄▄▄▄█\n\n";
        String greetingMessage = "\tHi! I am Tumu. Nice to meet you!\n" +
                "\tWhat is on your mind today?\n";

        System.out.println(logo + greetingMessage);
    }

    public void goodbye() {
        /**
         * Says goodbye to the user.
         * User exits the chat-bot.
         */

        notifyUser("Goodbye, and have a nice day ahead!\n");
        notifyUser("٩(ˊᗜˋ )و");
    }

    public void showLine() {
        notifyUser("_".repeat(60));
    }

    /**
     * Where all the messages seen by the user will pass through.
     * @param message The message to be printed, after additional
     *                formatting.
     */
    public void notifyUser(String message) {
        System.out.println("\t" + message);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void closeReader() {
        sc.close();
    }
}
