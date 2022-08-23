package parser;

import java.util.Scanner;
import exceptions.DukeException;
import tasklist.TaskList;

public class Parser {


    public void handleInput() {
        TaskList tasklist = TaskList.getInstance();
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String[] temp = in.nextLine().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim();
            }

            next[0] = next[0].toLowerCase();
            String command = next[0];

            try {

                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case "list":
                        tasklist.list();
                        break;
                    case "unmark":
                        tasklist.unmark(next[1]);
                        break;
                    case "mark":
                        tasklist.mark(next[1]);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        tasklist.addTask(next);
                        break;
                    case "delete":
                        tasklist.delete(next[1]);
                        break;
                    default:
                        throw new DukeException("Invalid command");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

