import java.util.Scanner;

public class Duke {
    // Class Fields
    private static final String logo = " ___  ___  __ __ \n"
                                     + "| . \\| __>|  \\  \\\n"
                                     + "|   /| _> |     |\n"
                                     + "|_\\_\\|___>|_|_|_|";
    private static final String SPACER = "----------------------------------------------------";
    private static final String WELCOME = "こんにちは (Konnichiwa)! Rem だよ! (I'm Rem!) :>\n"
            + "今日は何ができますか? (What can I do for you today?)";

    // Class Methods
    private void start() {
        System.out.println(logo + "\n");
        System.out.println(WELCOME);
        System.out.println(SPACER);
    }

    private void getInput() {
        String input;
        Scanner sc = new Scanner(System.in);

        // Keep input on standby until user enters "bye"
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
        dk.getInput();
    }
}
