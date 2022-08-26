package duke;

import java.io.IOException;
public class Parser {
    public static boolean parse(String str, TaskList taskList, Ui ui, Storage storage) throws DukeException {

            try {
                if (str.equals("bye")) {
                    ui.bye();
                    return true;
                }
                else if (str.equals("list")) {
                    ui.printList(taskList);
                }
                else if (str.split(" ")[0].equals("mark")) {
                    taskList.markTask(str, taskList);
                    storage.writeToFile(taskList);
                }
                else if (str.split(" ")[0].equals("unmark")) {
                    taskList.unmarkTask(str, taskList);
                    storage.writeToFile(taskList);
                }
                else if(str.split(" ", 2)[0].equals("duke.todo")) {
                    Task.makeTask(taskList, str);
                    storage.writeToFile(taskList);
                }
                else if(str.split(" ", 2)[0].equals("duke.deadline")) {
                    Task.makeTask(taskList, str);
                    storage.writeToFile(taskList);
                }
                else if(str.split(" ", 2)[0].equals("duke.event")) {
                    Task.makeTask(taskList, str);
                    storage.writeToFile(taskList);
                }
                else if (str.split(" ")[0].equals("delete")) {
                    taskList.deleteTask(str, taskList);
                    storage.writeToFile(taskList);
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IOException d) {
                System.out.println(d.getMessage());
            }
            return false;
    }
}
