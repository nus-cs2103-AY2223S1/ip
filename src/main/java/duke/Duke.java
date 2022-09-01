package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a robot which can react to different commands.
 */
public class Duke {
    private TaskList tasks;
    ArrayList<String> arrayList = new ArrayList<>();
    int num = 1;

    public String hello = "Hello from\n" + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String command, Duke duke, Storage storage) {
//
//        System.out.println("Hello from\n" + logo);

            try {
                if (command.equals("bye")) {
                    return "Bye. Hope to see you again soon!";

                }
                if (command.equals("list")) {
                    return duke.getList();
                }
                else {
                    storage.push(duke.getList());
                    duke.tasks.set(storage.load());
                    return duke.printCommand(command);
                }

            } catch (DukeException | IOException e) {
                return e.toString();

            }

    }

    public Duke() {
        Ui ui = new Ui();
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

//    public static void main(String[] args) {
//        String command;
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        Scanner sc = new Scanner(System.in);
//        command = sc.nextLine();
//        Duke duke = new Duke();
//
//        while (true) {
//            try {
//                if (command.equals("bye")) {
//                    System.out.println("Bye. Hope to see you again soon!");
//                    break;
//                }
//                if (command.equals("list")) {
//                    System.out.println(duke.tasks.get());
//                }
//                else {
//                    Storage storage = new Storage("D:\\cs2103t\\duke.txt");
//                    duke.printCommand(command);
//
//                    storage.push(duke.getList());
//                    duke.tasks.set(storage.load());
//                }
//                command = sc.nextLine();
//            } catch (DukeException | IOException e) {
//                System.out.println(e);
//                command = sc.nextLine();
//            }
//        }
//    }

    /**
     * PrintCommand function to print out the current command.
     *
     * @param command
     */
    public String printCommand(String command) {
        if (command.split(" ")[0].equals("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            num--;
            Delete task = new Delete(arrayList.get(number), num);
            arrayList.remove(number);
            return task.toString();
        }
        else {
            Task task = Task.of(command, arrayList, num);
            if (task.AddToList()) {
                num++;
            }

            return task.toString();

        }
    }

    /**
     * Returns a String representation of the command list.
     *
     * @return String
     */
    public String getList() {
        String list = "";
        for (int k = 1; k < arrayList.size() + 1; k++) {
            list += k + "." + arrayList.get(k - 1) + "\n";
        }
        return list;
    }
}