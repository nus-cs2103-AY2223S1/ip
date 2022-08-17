import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);   // Create Scanner object
        String[] strLst = new String[100];

        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);

        while (true) {
            String act = scanObj.nextLine();            // read user input

            if (act.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (strLst[i] == null) {
                        break;
                    }
                    int index = i + 1;
                    System.out.println(index + ". " + strLst[i]);
                }
            } else if (act.equals("bye")) {
                System.out.println("Goodbye, hope to see you again soon!");
                break;
            } else {
                for (int i = 0; i < 100; i++) {
                    if (strLst[i] == null) {
                        strLst[i] = act;
                        break;
                    }
                }
                System.out.println("added: " + act);
            }
        }
    }
}
