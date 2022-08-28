package duke;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    Parser() {

    }

    private static void parseToDo(String str, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        try {
            str = str.split(" ", 2)[1].trim();
            ToDo tasktoDo = new ToDo(str);
            taskList.addTask(tasktoDo);
            System.out.println(ui.printAddedTask(tasktoDo.toString()));

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description of a duke.ToDo cannot be empty."));
        }

    }
    private static void parseDeadline(String str, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        try {
            //splits away deadline
            str = str.split(" ", 2)[1];
            String desc = str.split("/by")[0].trim();
            String by = str.split("/by")[1].trim();
            Deadline taskDeadline = new Deadline(desc, by);
            taskList.addTask(taskDeadline);
            System.out.println(ui.printAddedTask(taskDeadline.toString()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description of a duke.Deadline cannot be empty/incomplete."));
        }
    }
    private static void parseEvent(String str, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        try {
            //splits away event
            str = str.split(" ", 2)[1];
            String desc = str.split("/at")[0].trim();
            String at = str.split("/at")[1].trim();
            Event taskEvent = new Event(desc, at);
            taskList.addTask(taskEvent);
            System.out.println(ui.printAddedTask(taskEvent.toString()));
            ;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description of an duke.Event cannot be empty/incomplete."));
        }
    }

    private static void parseDelete(String str, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        str = str.split(" ", 2)[1].trim();
        int index = Integer.valueOf(str) - 1;
        //duke.Task content = inputs.get(index);
        String msg = taskList.getTask(index).toString();
        System.out.println(ui.printRemovedTask(msg));
        taskList.removeTask(index);
    }

    public void parseInstruction(Storage storage, TaskList taskList) throws DukeException {
        //if i change it into a if then how does a while loop iter?
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Ui ui = new Ui(taskList);
        while (!str.equals("bye")) {
            //for listing
            if (str.equals("list")) {
                System.out.println("Here are all the tasks in your list\n");
                System.out.println("_______________________________________________________" +
                        "\n" + ui.printAllTasks() + "\n" +
                        "_______________________________________________________");
            } else {
                String instruction = str.split(" ")[0];
                if (instruction.equals("mark")) {
                    //this is as index is greater than array index by 1
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    taskList.getTask(index).setDone();
                    System.out.println(ui.printMarkDone(taskList.getTask(index).toString()));

                } else if ((instruction.equals("unmark"))) {
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    taskList.getTask(index).setUndone();
                    System.out.println(ui.printMarkUndone(taskList.getTask(index).toString()));

                } else if (instruction.equals("todo")) {
                    parseToDo(str, taskList);

                } else if (instruction.equals("deadline")) {
                    parseDeadline(str, taskList);

                } else if (instruction.equals("event")) {
                    parseEvent(str, taskList);

                } else if (instruction.equals("delete")) {
                    parseDelete(str, taskList);

                } else {
                    throw new DukeException("I have no idea what you are saying, this is not a task >_<");
                }
            }
            try {
                storage.updateFile("data/duke.txt", taskList);//updates dukeFile after each input
            } catch (IOException e) {
                System.out.println("Error in saving the file");
            }
            str = sc.nextLine();
        }
    }
}
