import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ls = new ArrayList<>();
        String line = "-----------------------------";
        String answer = "";

        // Duke's self-intro
        System.out.println(line + "\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you\n" +
                line + "\n");

        while (true) {
            answer = sc.nextLine();
            if (answer.equals("bye")) {
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" +
                        line + "\n");
                break;
            } else if (answer.equals("list")) { // Task 2
                System.out.println(line + "\n");
                list(ls);
                System.out.println(line + "\n");
            } else {
                System.out.println(line + "\n" + "added: " + answer + "\n" +
                        line + "\n");ls.add(answer);
            }
        }
    }

    private static void list(ArrayList ls) {
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + ls.get(i));
        }
    }
}
