package duke.helper;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.NoDescriptionException;
import duke.task.ErrorTask;
import duke.task.Task;
import duke.task.TaskCreator;
import duke.task.TaskList;

/**
 * Class to parse inputs
 */
public class Parser {

    /**
     * Method to parse any of command given and call the respective functions
     *
     * @param in the input command line given
     * @param list the taskList being used
     */
    public static void parse(String in, TaskList list) {
        //"find": Find tasks based on keywords
        if (in.startsWith("find")) {
            String keywords = in.split(" ", 2)[1];
            list.find(keywords);

        //"clear": Clears the list
        } else if (in.equals("clear")) {
            list.clear();

        //"list": Shows current list
        } else if (in.equals("list")) {
            list.printTasks();

        //"mark": Mark a task as done
        } else if (in.startsWith("mark")) {
            int index = Integer.valueOf(in.split(" ")[1]) - 1;
            list.mark(index);

        //"unmark": Unmark a task
        } else if (in.startsWith("unmark")) {
            int index = Integer.valueOf(in.split(" ")[1]) - 1;
            list.unmark(index);

        //"delete": Delete a task
        } else if (in.startsWith("delete")) {
            int index = Integer.valueOf(in.split(" ")[1]) - 1;

            try {
                if (index >= list.getSize()) {
                    throw new InvalidCommandException();
                }

                Task task = list.getTask(index);
                Ui.delete(task);
                list.delete(index);
                Ui.countTasks(list);
            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
            }

        //Create a task
        } else {
            try {
                Task task = TaskCreator.createTask(in);
                if (task == null) {
                    throw new InvalidCommandException();
                } else if (task.getClass() == ErrorTask.class) {
                    throw new InvalidDateException();
                } else if (task.getDescription().length() < 1) {
                    throw new NoDescriptionException();
                } else {
                    list.add(task);
                    Ui.add(task);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        Ui.line();
        //Update the save file
        FileWriting.update("./data/duke.txt", list);
    }
}
