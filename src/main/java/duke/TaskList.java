package duke;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 *  Contains the task list that is generated from our stored data, or newly created
 *  Has operations to update the status of our tasks or add/delete them
 */
public class TaskList {
    private int numOfTasks;
    private static List<Task> tasks;
    private String INDENT = "    ";
    private String PRINT_NUM_OF_TASKS = INDENT + "Now you have " + numOfTasks + " tasks in the list";

    public TaskList() {
        this.numOfTasks = 0;
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.numOfTasks = taskList.size();
        this.tasks = taskList;
    }

    String add(Task task) {
        String response = "";

        //update our taskList and numOfTasks
        tasks.add(task);
        numOfTasks += 1;

        //Print the statement to indicate we are adding a task
        response += INDENT + "Got it. I've added this task:" + "\n";
        response += "    " + task + "\n";
        response +=  PRINT_NUM_OF_TASKS;

        return response;
    }

    String delete(int taskIndex) {
        String response = "";

        //First update the number of tasks left
        numOfTasks -= 1;

        //Print the statement to indicate we are deleting a task
        response += "Noted. I've removed this task:" + "\n";
        response += "      " + tasks.get(taskIndex - 1) + "\n";
        response += PRINT_NUM_OF_TASKS;

        //We remove the task after printing our statements
        tasks.remove(taskIndex - 1);

        return response;
    }

    String mark(int taskIndex) {
        String markStatement = INDENT + "Nice! I've marked this task as done:";

        Task task = tasks.get(taskIndex - 1);
        task.mark();

        return  markStatement + "\n      " + task;
    }

    String unmark(int taskIndex) {
        String unmarkStatement = INDENT + "Ok, I've marked this task as not done yet:";

        Task task = tasks.get(taskIndex - 1);
        task.unmark();

        return unmarkStatement + "\n       " + task;
    }

    String printTasks() {
        String taskList = "Here are your tasks: \n";
        for (int j = 0; j < numOfTasks; j++) {
            int taskNum = j + 1;
            taskList += INDENT + taskNum + ". " + tasks.get(j) + "\n";
        }
        return taskList;
    }

    /**
     * Prints out all the tasks in our task list associated with the given keyword by the user
     *
     * @param keyword input user is trying to find
     * @throws DukeException if none of our tasks description contains the keyword
     */
    String findTasks(String keyword) throws DukeException{
        // initiate a boolean variable to check if the keyword exists in our task list
        String response = "";
        boolean isFindable = false;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                response += task + "\n";
                isFindable = true;
            }
        }

        //Throw a DukeException that there exist no such keyword if we cannot find it in our task list
        if (!isFindable) {
            throw new DukeException("There exists no such keyword in the task list!");
        }
        return response;
    }


    String printUpcomingTasks() {
        String taskList = "";
        LocalDateTime dateTime = LocalDateTime.now();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                //we specify that the task is of the Deadline class by type casting
                Deadline deadline = (Deadline) task;

                if (deadline.getTime().isBefore(dateTime.plusWeeks(1))) {
                    taskList += deadline + "\n";
                }
            } else if (task instanceof Event) {
                //we specify that the task is of the Event class by type casting
                Event event = (Event) task;

                if (event.getTime().isBefore(dateTime.plusWeeks(1))) {
                    taskList += event + "\n";
                }
            }
        }
        return taskList;
    }
}
