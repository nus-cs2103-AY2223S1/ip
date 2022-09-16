package gina;

import gina.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Returns the word with the correct plural or singular form
     *
     * @param taskAndContactList The list of tasks
     * @return The word with the correct plural or singular form
     */
    private static String showTaskTense(TaskAndContactList taskAndContactList) {
        return taskAndContactList.tasksSize() == 1 ? " task" : " tasks";
    }

    /**
     * Displays greeting
     */
    public String showGreeting() {
        return "Hi. I'm Gina Linetti, the human form of the 100 emoji.\n"
            + "What do you need?\n";
    }

    /**
     * Displays exit message
     */
    public String showExit() {
        return "Bye. Gina Linetti out.";
    }

    /**
     * Displays error message
     *
     * @param message The error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays added task message for the specified task
     *
     * @param task The specified task
     * @param taskAndContactList The list of tasks
     */
    public String showAddTask(Task task, TaskAndContactList taskAndContactList) {
        return "Got it. I've added this task:\n"
                + "  " + task.toString()
                + "\nNow you have " + taskAndContactList.tasksSize()
                + showTaskTense(taskAndContactList) + " in the list.";
    }

    public String showAddContact(Contact contact) {
        return "Wow you actually have friends. Good for you!\n"
                + "I've added this contact: \n" + contact.toString();
    }

    /**
     * Displays the deleted task message for the specified task
     *
     * @param task The specified task
     * @param taskAndContactList The list of tasks
     */
    public String showDeleteTask(Task task, TaskAndContactList taskAndContactList) {
        return "Done! " + task.toString()
                + " has been deleted :(" + "\nNow you have " + taskAndContactList.tasksSize()
                + showTaskTense(taskAndContactList) + " left.";
    }

    public String showDeleteContact(Contact contact) {
        return "Aw, what did they do to offend you?\n" + contact.toString()
                + " has been deleted from your contacts.";
    }

    /**
     * Displays the marked task message for the specified task
     *
     * @param task The specified task
     */
    public String showMarkedTask(Task task) {
        return "Finally, you did something useful!\n "
                + "I've marked this task as done:\n" + task.toString();
    }

    /**
     * Displays the unmarked task message for the specified task
     *
     * @param task The specified task
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Displays the specified task list
     *
     * @param taskAndContactList The specified task list
     */
    public String showList(TaskAndContactList taskAndContactList) {
        String list = "Tasks: \n";
        if (taskAndContactList.tasksSize() == 0) {
            list += "You have nothing to do right now...\n"
                + "Maybe it's time for you to get a life.";
        } else {
            list += taskAndContactList.getTaskList()
                + "\nYou have " + taskAndContactList.tasksSize()
                    + showTaskTense(taskAndContactList) + "!\n";
        }
        list += "\nContacts:\n";
        if (taskAndContactList.contactsSize() == 0) {
            list += "I guess you have no friends. Sucks for you.";
        } else {
            list += taskAndContactList.getContactsList();
        }
        return list;
    }

    /**
     * Displays the list of tasks on a specified date
     *
     * @param taskAndContactList The list of tasks on a specified date
     * @param dateStr The specified date
     */
    public String showTasksOnDate(TaskAndContactList taskAndContactList, String dateStr) {
        if (taskAndContactList.tasksSize() != 0) {
            return "These are the tasks on "
                    + dateStr + ":\n" + taskAndContactList.getTaskList()
                    + "\nYou have " + taskAndContactList.tasksSize()
                    + showTaskTense(taskAndContactList) + " on " + dateStr + ".";
        } else {
            return "Congrats, there are no tasks on this date!";
        }
    }

    /**
     * Displays the list of tasks with the relevant keyword if there are matches.
     *
     * @param taskAndContactList The list of tasks with the keyword.
     * @param input The keyword.
     */
    public String showFindings(TaskAndContactList taskAndContactList, String input) {
        if (taskAndContactList.tasksSize() != 0) {
            return "I found " + taskAndContactList.tasksSize() + showTaskTense(taskAndContactList)
                    + " for \"" + input + "\":\n" + taskAndContactList.getTaskList()
                    + "\nDid you find what you were looking for?";
        } else {
            return "I couldn't find any tasks for \""
                    + input + "\""
                    + "\nAre you sure that's what you're looking for?";
        }
    }
}
