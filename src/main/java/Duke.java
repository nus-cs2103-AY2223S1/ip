import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction message
        String logo = "　　　　　　　　　 　　　　　  ＿＿＿＿＿\n" +
                "　　　　　　　　　　　　　　／ －､ －､　　 ＼\n" +
                "　　　　　　　　　　　　　/  | ・|・ |  ､　　＼\n" +
                "　　　　　　　　　　　　/ ／　`-●－  ′　  ＼   ヽ\n" +
                "　　　　　　　　　　　　|.　──　|　 ──　 　 |　 |\n" +
                "　　　　　　　　　　　　|　──　 |　 ── 　 　| 　l\n" +
                "　　　　　　　　　　　　ヽ （＿＿|＿＿＿＿)　/　 /\n" +
                "　　　　　　　　　　　 　＼　　　　　　　　 /　/\n" +
                "　　　　　　　　　　　 　 　l━━（ｔ）━━━━┥＿___";
        System.out.println(output("Hello I'm\n" + logo + "\tDoraemon!"));

        // Handling inputs
        Scanner sc = new Scanner(System.in);

        while (true) {
            String inputStr = sc.nextLine();
            if (inputStr.equals("bye")) {
                System.out.println(output("I'm going to sleep now...See you again soon!"));
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
