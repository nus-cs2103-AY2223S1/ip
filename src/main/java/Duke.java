import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List<String> list = new ArrayList<String>();

        System.out.println(reply("Hello! I'm Duke. What can I do for you?"));
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String userText = userInput.nextLine();
            if (userText.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                break;
            } else if (userText.equals("list")) {
                System.out.println(reply(getList(list)));
            } else {
                list.add(userText);
                System.out.printf(reply("added: " + userText));
            }
        }
    }

    public static String getList(List<String> list) {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            listString += String.format("%d. %s", i, list.get(i));
            if (i != list.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    public static String reply(String s) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + s +'\n' + horizontalLine;
    }
}
