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
     * @throws DukeException error message for DukeException
     */
    public static String parse(String str, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String keyword = str.split(" ")[0];
        String taskFirstWord = str.split(" ", 2)[0];

        try {
            if (str.equals("bye")) {
                return ui.bye();
            }
                else if (str.equals("list")) {
                    return ui.printList(taskList);
                }
                else if (taskFirstWord.equals("todo") || taskFirstWord.equals("event") || taskFirstWord.equals("deadline")) {
                    return parseTaskType(str, storage, taskList);
                } else {
                    return parseTaskChanger(str, storage, taskList);
                }
            } catch (DukeException | IOException d) {
                return d.getMessage();
            }
    }

    /**
     *
     * @param str description of the task
     * @param storage storage deals with the writing and loading of files
     * @param taskList the taskList where tasks are stored and loaded from
     * @return
     * @throws IOException error message for cases of writing to file
     * @throws DukeException error message for DukeException
     */
    public static String parseTaskType(String str, Storage storage, TaskList taskList) throws IOException, DukeException {
        storage.writeToFile(taskList);
        String taskFirstWord = str.split(" ", 2)[0];

        if (taskFirstWord.equals("todo")) {
            return Task.makeTask(taskList, str);
        }
        else if(taskFirstWord.equals("deadline")) {
            return Task.makeTask(taskList, str);
        } else {
            return Task.makeTask(taskList, str);
        }
    }

    /**
     *
     * @param str description of the task
     * @param storage storage deals with the writing and loading of files
     * @param taskList the taskList where tasks are stored and loaded from
     * @return
     * @throws IOException error message for cases of writing to file
     * @throws DukeException error message for DukeException
     */
    public static String parseTaskChanger(String str, Storage storage, TaskList taskList) throws IOException, DukeException {
        String keyword = str.split(" ")[0];

        if (keyword.equals("mark")) {
            storage.writeToFile(taskList);
            return taskList.markTask(str, taskList);
        }
        else if (keyword.equals("unmark")) {
            storage.writeToFile(taskList);
            return taskList.unmarkTask(str, taskList);
        }
        else if (keyword.equals("delete")) {
            storage.writeToFile(taskList);
            return taskList.deleteTask(str, taskList);
        }
        else if (keyword.equals("find")) {
            return Task.find(str, taskList);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
