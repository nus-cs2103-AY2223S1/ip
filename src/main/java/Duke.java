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
        System.out.println("Hello I'm\n" + logo + "\tDoraemon!");

        // Handling inputs
        Scanner sc = new Scanner(System.in);

        while (true) {
            String inputStr = sc.nextLine();
            if (inputStr.equals("bye")) {
                System.out.println("I'm going to sleep now...See you again soon!");
                break;
            }
            System.out.println(inputStr);
        }
    }
}
