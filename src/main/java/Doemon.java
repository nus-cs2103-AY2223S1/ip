import java.util.Scanner;

public class Doemon {
    /**
     * Picture of Doemon made using ASCII art
     */
    private static String logo = "　　　　　　　　　 　　　　　  ＿＿＿＿＿\n" +
            "　　　　　　　　　　　　　　／ －､ －､　　 ＼\n" +
            "　　　　　　　　　　　　　/  | ・|・ |  ､　　＼\n" +
            "　　　　　　　　　　　　/ ／　`-●－  ′　  ＼   ヽ\n" +
            "　　　　　　　　　　　　|.　──　|　 ──　 　 |　 |\n" +
            "　　　　　　　　　　　　|　──　 |　 ── 　 　| 　l\n" +
            "　　　　　　　　　　　　ヽ （＿＿|＿＿＿＿)　/　 /\n" +
            "　　　　　　　　　　　 　＼　　　　　　　　 /　/\n" +
            "　　　　　　　　　　　 　 　l━━（ｔ）━━━━┥＿___";

    /**
     * Introduction string that is printed when Doemon is started.
     */
    private static String introStr = "Hello I'm\n" + logo + "\tDoemon!";

    /**
     * The string that is printed when Doemon is exited.
     */
    private static String exitStr = "I'm going to sleep now...See you again soon!";

    public static void main(String[] args) {
        // Introduction
        System.out.println(output(introStr));

        new Doemon().start();
    }

    /**
     * Starts the Doemon chatbot.
     */
    private void start() {
        // Handling inputs
        Scanner sc = new Scanner(System.in);

        while (true) {
            String inputStr = sc.nextLine();
            if (inputStr.equals("bye")) {
                System.out.println(output(exitStr));
                break;
            }
            System.out.println(output(inputStr));
        }
    }

    /**
     * Returns a formatted string to display the given text.
     *
     * @param text the text to be formatted
     * @return the formatted string
     */
    private static String output(String text) {
        String line = "____________________________________________________________";
        String outputStr = "\t" + line + "\n" +
                "\t" + text + "\n" +
                "\t" + line;
        return outputStr;
    }
}
