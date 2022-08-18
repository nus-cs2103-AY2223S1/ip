import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static String formatList(ArrayList lst) {

        String result = "";
        int length = lst.size();
        for (int i = 0; i < length; i++) {
            result += (i + 1) + ". " + lst.get(i) + "\n";
        }
        return result;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner((System.in));
        System.out.println("Hello! I'm Duke \n What can i do for you? \n");
        ArrayList todo = new ArrayList<String>();


        loop: while (true) {
            String command = scanner.nextLine();
            switch (command) {
                case "Bye":
                    System.out.println("Bye. Hope to see you again soon");
                    break loop;
                case "list":
                    System.out.println(formatList(todo));
                    break;
                default:
                    todo.add(command);
                    System.out.println("added: " + command);
            }
        }



    }
}
