package duke;

import java.io.IOException;
public class Parser {
    /**
     *
     * @param str description of the task
     * @param taskList the taskList where tasks are stored and loaded from
     * @param ui the interactive UI system
     * @param storage storage deals with the writing and loading of files
     * @return boolean
     * @throws DukeException
     */
    public static String parse(String str, TaskList taskList, Ui ui, Storage storage) throws DukeException {

            try {
                if (str.equals("bye")) {
                    return ui.bye();

                }
                else if (str.equals("list")) {
                    return ui.printList(taskList);
                }
                else if (str.split(" ")[0].equals("mark")) {
                    storage.writeToFile(taskList);
                    return taskList.markTask(str, taskList);
                }
                else if (str.split(" ")[0].equals("unmark")) {
                    storage.writeToFile(taskList);
                    return taskList.unmarkTask(str, taskList);
                }
                else if(str.split(" ", 2)[0].equals("todo")) {
                    storage.writeToFile(taskList);
                    return Task.makeTask(taskList, str);
                }
                else if(str.split(" ", 2)[0].equals("deadline")) {
                    storage.writeToFile(taskList);
                    return Task.makeTask(taskList, str);
                }
                else if(str.split(" ", 2)[0].equals("event")) {
                    storage.writeToFile(taskList);
                    return Task.makeTask(taskList, str);
                }
                else if (str.split(" ")[0].equals("delete")) {
                    storage.writeToFile(taskList);
                    return taskList.deleteTask(str, taskList);
                }
                else if (str.split(" ")[0].equals("find")) {
                    return Task.find(str, taskList);
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IOException d) {
                return d.getMessage();
            }
    }
}
