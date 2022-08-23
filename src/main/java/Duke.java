import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import tasks.*;
import exceptions.*;


public class Duke {

    private static final TaskList TASK_LIST = new TaskList();

    public static void saveFile() {
        try {
            FileWriter myWriter = new FileWriter("tasklist.txt");
            myWriter.write(TASK_LIST.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void handle(String command) {
        String action;
        String desc;
        String time;

        try {
            Pattern pattern = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: ([^/]*))?$");
            Matcher matcher = pattern.matcher(command);
            matcher.find();
            action = matcher.group(1);
            desc = matcher.group(2);
            time = matcher.group(4);

            switch (action) {
                case "list":
                    TASK_LIST.printList();
                    break;
                case "todo":
                    TASK_LIST.addToDo(desc);
                    break;
                case "event":
                    TASK_LIST.addEvent(desc, time);
                    break;
                case "deadline":
                    TASK_LIST.addDeadline(desc, time);
                    break;
                case "delete":
                    TASK_LIST.delete(desc);
                    break;
                case "done":
                    TASK_LIST.markDone(desc);
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            handle(command);
            saveFile();
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
