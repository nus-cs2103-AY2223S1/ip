package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;


import java.util.*;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns a help message with instructions on how to use PAL.
     *
     * @return a welcome message.
     */
    public String helpMsg() {
        return "I got you~ I'm capable of doing the following:\n\n"
                + "See all the tasks you've added:\nlist\n\n"
                + "Sort all the tasks by date and time:\nsort\n\n"
                + "Add a ToDo task:\ntodo <your task>\n\n"
                + "Add a Deadline task:\ndeadline <your deadline> /by <2022-01-28>\n\n"
                + "Add an Event task:\nevent <your event> /at <2022-01-28> <2359>\n\n"
                + "Filter task list according to keyword:\nfind <keyword>\n\n"
                + "Mark a task as done:\nmark <index>\n\n"
                + "Mark a task as undone:\nunmark <index>\n\n"
                + "Delete a task:\ndelete <INDEX>\n\n"
                + "Exit the program:\nbye";
    }

    public String welcomeMsg() {
        return "Hello! I am PAL, your best PAL when it comes to task managing!\nStart by adding any one of the task below:\n\n"
                + "todo borrow books\ndeadline homework /by 2022-01-28\n"
                + "event furniture expo /at 2022-01-28 1700";
    }

    /**
     * Returns an ending message with format when user exit Duke.
     *
     * @return an ending message.
     */
    public String endingMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a numbered task list with format.
     *
     * @param list to be printed.
     * @return strings representation of the lists.
     */
    public String taskListString(TaskList list) {
        String tasks = list.getAllTask();

        return tasks.isEmpty()? "There is no tasks in your list." : "Here are the tasks in your list:\n" + tasks;
    }

    public String clearList() {
        return "All tasks are removed.";
    }

    /**
     * Returns a message to indicate a task is done.
     *
     * @param task to be marked done.
     * @return a message to indicate a task is done.
     */
    public String taskMarkedMsgString(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task;
    }

    /**
     * Returns a message to indicate a task is not done yet.
     *
     * @param task to be marked un-done.
     * @return a message to indicate the task is not done yet.
     */
    public String taskUnmarkedMsgString(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task;
    }

    /**
     * Returns a message to indicate a task is deleted from the task list.
     *
     * @param task        to be deleted.
     * @param listOfTasks task list that the task will be removed from.
     * @return a messageto indicate a task is deleted from the task list.
     */
    public String deleteTaskMsgString(Task task, TaskList listOfTasks) {
        return "Noted. I've removed this task:\n"
                + task
                + "\nNow you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.";
    }

    /**
     * Returns a message to indicate a task is added to the task list.
     *
     * @param task        to be added.
     * @param listOfTasks task list that the task will be added to.
     * @return a message to indicate a task is added to the task list.
     */
    public String addTaskMsgString(Task task, TaskList listOfTasks) {
        return "Got it, I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.";
    }

    /**
     * Returns a message to indicate loading error.
     *
     * @return string representation of loading error.
     */
    public String loadingErrorString() {
        return"There is some problem loading your task(s)";
    }

    /**
     * Returns a message to indicate unknown commands
     *
     * @return a message to indicate unknown commands.
     */
    public String invalidCommandErrorString() {
        return "Invalid command!\nTry typing \"help\"";
    }

    /**
     * Returns a message to indicate that there is missing description of a command.
     *
     * @param command with missing description.
     * @return a message to indicate missing decription.
     */
    public String showNoDescriptionError(String command) {

       String commandFormat = "";
       if (command.equals("deadline")) {
           commandFormat = "deadline <your deadline> /by <2022-01-28>";
       } else if (command.equals("todo")) {
           commandFormat = "todo <your task>";
       } else if (command.equals("event")){
           commandFormat = "event <your event> /at <2022-01-28> <2359>";
       }

       commandFormat = commandFormat.isEmpty() ? "" : "Try this: " + commandFormat;
       return "OOPS!!! The description of a "
                + command
                + " cannot be empty.\n"
                + commandFormat;
    }

    /**
     * Returns a filtered numbered task list with format.
     *
     * @param filteredListString list of filtered tasks in strings.
     * @return filtered numbered task list with format.
     */
    public String printFilteredList(String filteredListString) {
        String text = "Here are the matching tasks in your list:\n"
                + filteredListString;
        return text;
    }

    /**
     * Returns a sorted task list.
     *
     * @param taskList list to be sorted.
     * @return sorted list in string form.
     */
    public String sortedListString(TaskList taskList) {

        List<Task> resultList = new ArrayList<>();
        List<Event> eventList = new ArrayList<>();
        List<ToDos> todosList = new ArrayList<>();
        List<Deadline> deadlineList = new ArrayList<>();

        for (int i = 1; i <= taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Event) {
                eventList.add((Event) task);
            } else if (task instanceof  Deadline) {
                deadlineList.add((Deadline) task);
            } else {
                todosList.add((ToDos) task);
            }
        }
        Collections.sort(eventList);
        Collections.sort(deadlineList);

        resultList.addAll(eventList);
        resultList.addAll(deadlineList);
        resultList.addAll(todosList);

        return "The following tasks are sorted by task type then date and time\n"
                + TaskList.convertListToString(resultList);
    }
}
