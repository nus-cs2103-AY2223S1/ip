import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "-------------------------";
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println("Greetings, human! I am Sam.\n" + "How may I serve you today?" + "\n" + horizontalLine);

        ArrayList<String> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();

        while (!echo.equals("bye")) {
            if (echo.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < store.size(); i++) {
                    System.out.println((i + 1) + ". " + store.get(i));
                }
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine + "\n" + "added: " + echo + "\n" + horizontalLine);
                store.add(echo);
            }
            echo = sc.nextLine();
        }
        System.out.println(horizontalLine + "\n" + "Goodbye human. See you again!" + "\n" + horizontalLine);
    }
}
