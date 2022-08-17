import java.util.Scanner;

public class Duke {
    // Class Fields
    private static final String SPACER = "----------------------------------------------------";
    private static final String WELCOME = "こんにちは (Konnichiwa)! Rem だよ! :>\n"
            + "今日は何ができますか? (What can I do for you today?)\n"
            + SPACER;

    // Class Methods
    private void start() {
        String logo2 = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
        String logo = " _____\n"
                    + "|  _  \\ \n"
                    + "| |_| / \n"
                    + "| | \\ \\ \n"
                    + "|_|  \\_\\ \n";

        System.out.println("Hello from\n" + logo2);
        System.out.println(logo);
        System.out.println(SPACER);
        System.out.println(WELCOME);
    }

    private void getInput() {
        String input;
        Scanner sc = new Scanner(System.in);

        // Keep
        while (true) {
            System.out.print(">> ");
            input = sc.nextLine();
            if (input.equals("bye")) break;
            System.out.println(SPACER);
            System.out.println("Rem: " + input);
            System.out.println(SPACER);
        }
        System.out.println("またね! (See you soon!) <3");
    }
    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.start();
        //dk.getInput();
    }
}
