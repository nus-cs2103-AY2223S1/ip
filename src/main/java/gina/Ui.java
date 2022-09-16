package gina;

import gina.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /**
     * Returns the word with the correct plural or singular form.
     *
     * @param taskAndContactList The list of tasks.
     * @return The word with the correct plural or singular form.
     */
    private static String showTaskTense(TaskAndContactList taskAndContactList) {
        return taskAndContactList.tasksSize() == 1 ? " task" : " tasks";
    }

    /**
     * Returns the greeting.
     * 
     * @return The greeting message.
     */
    public String showGreeting() {
        return "Hi. I'm Gina Linetti, the human form of the 100 emoji.\n"
            + "What do you need?\n";
    }

    /**
     * Returns exit message.
     * 
     * @return Returns exit message.
     */
    public String showExit() {
        return "Bye. Gina Linetti out.";
    }

    /**
     * Returns added task message for the specified task.
     *
     * @param task The specified task.
     * @param taskAndContactList The list of tasks.
     * @return The add task message.
     */
    public String showAddTask(Task task, TaskAndContactList taskAndContactList) {
        return "Got it. I've added this task:\n"
                + "  " + task.toString()
                + "\nNow you have " + taskAndContactList.tasksSize()
                + showTaskTense(taskAndContactList) + " in the list.";
    }

    /**
     * Returns the add contact message.
     *
     * @param contact The contact to be added.
     * @return The add contact message.
     */
    public String showAddContact(Contact contact) {
        return "Wow you actually have friends. Good for you!\n"
                + "I've added this contact: \n" + contact.toString();
    }

    /**
     * Returns the deleted task message for the specified task.
     *
     * @param task The specified task.
     * @param taskAndContactList The list of tasks.
     * @return The delete task message.
     */
    public String showDeleteTask(Task task, TaskAndContactList taskAndContactList) {
        return "Done!\n" + task.toString()
                + " has been deleted :(" + "\nNow you have " + taskAndContactList.tasksSize()
                + showTaskTense(taskAndContactList) + " left.";
    }

    /**
     * Returns the 'delete contact' message.
     *
     * @param contact
     * @return The 'delete contact' message.
     */
    public String showDeleteContact(Contact contact) {
        return "Aw, what did they do to offend you?\n" + contact.toString()
                + " has been deleted from your contacts.";
    }

    /**
     * Returns the marked task message for the specified task.
     *
     * @param task The specified task.
     * @return The marked task message.
     */
    public String showMarkedTask(Task task) {
        return "Finally, you did something useful!\n "
                + "I've marked this task as done:\n" + task.toString();
    }

    /**
     * Returns the unmarked task message for the specified task.
     *
     * @param task The specified task.
     * @return The unmark task message.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Returns the specified task and contact list.
     *
     * @param taskAndContactList The specified task list.
     * @return The task and contact list.
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
     * Returns the list of tasks on a specified date.
     *
     * @param taskAndContactList The list of tasks on a specified date.
     * @param dateStr The specified date.
     * @return The list of tasks on the specified date.
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
     * Returns the list of tasks with the relevant keyword if there are matches.
     *
     * @param taskAndContactList The list of tasks with the keyword.
     * @param input The keyword.
     * @return The list of tasks with the relevant keyword.
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
