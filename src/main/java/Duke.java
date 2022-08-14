import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();
    public static void echo(String command) {
        if (command.toUpperCase().equals("BYE")) {
            String bye = "____________________________________________________________\n" +
                         "Bye. Hope to see you again soon!\n" +
                    "____________________________________________________________\n";
            System.out.println(bye);
        } else if (command.toUpperCase().equals("LIST")) {
            String newList = "";
            int count = 1;
            for (String item: list) {
                newList += (count + ". " + item + "\n");
                count++;
            }
            String updatedList = "____________________________________________________________\n" + newList +
                    "____________________________________" +
                    "________________________\n";
            System.out.println(updatedList);

        } else {
            list.add(command);
            String echo = "____________________________________________________________\n" +
                           "added: " + command + "\n" + "____________________________________________________________\n";
            System.out.println(echo);
        }
    }
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n" + "Hello from\n" +
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" +
                "How may I assist you? \n" +
                "____________________________________________________________\n";
        System.out.println(logo);

        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter your command: ");
            String command = myScanner.nextLine();
            echo(command);
            if (command.toUpperCase().equals("BYE")) {
                break;
            }

        }
    }
}
