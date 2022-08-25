package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a robot which can react to different commands.
 */
public class Duke {
    private TaskList tasks;
    int num = 1;

    ArrayList<String> arrayList = new ArrayList<>();

    public Duke() {
        Ui ui = new Ui();
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();
        Duke duke = new Duke();

        while (true) {
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                if (command.equals("list")) {
                    System.out.println(duke.tasks.get());
                }
                else {
                    Storage storage = new Storage("D:\\cs2103t\\duke.txt");
                    duke.printCommand(command);

                    storage.push(duke.getList());
                    duke.tasks.set(storage.load());
                }
                command = sc.nextLine();
            } catch (DukeException | IOException e) {
                System.out.println(e.toString());
                command = sc.nextLine();
            }
        }
    }

    /**
     * PrintCommand function to print out the current command.
     *
     * @param command
     */
    public void printCommand(String command) {
        if (command.split(" ")[0].equals("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            num--;
            Delete task = new Delete(arrayList.get(number), num);
            arrayList.remove(number);
            System.out.println(task.toString());
        }
        else {
            Task task = Task.of(command, arrayList, num);
            System.out.println(task.toString());
            if (task.AddToList()) {
                num++;
            }
        }
    }

    public String getList() {
        String list = "";
        for (int k = 1; k < arrayList.size() + 1; k++) {
            list += k + "." + arrayList.get(k - 1) + "\n";
        }
        return list;
    }
}