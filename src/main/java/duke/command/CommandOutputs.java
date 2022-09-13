package duke.command;

import duke.Client;
import duke.ClientList;
import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents UI of application
 */
public class CommandOutputs {

    /**
     * Returns String of a welcome message for the user.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\n"
                + "Peter's personal chatbot";
    }

    /**
     * Returns String of a goodbye message for the user.
     */
    public static String showGoodbye() {
        return "Bye Bye See You Next Time!";
    }

    /**
     * Returns String of list of the tasks in Duke.
     *
     * @param taskList list of tasks.
     * @return return String of list of the tasks in Duke.
     * @throws DukeException thrown if list is empty.
     */
    public static String showTaskList(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        assert taskList.size() > 0;
        return "Here are the tasks in your list:\n"
                + taskList;
    }

    /**
     * Returns String of deleted task and updated number of tasks.
     *
     * @param taskList list of tasks.
     * @param deletedTask deleted task
     */
    public static String showDelete(TaskList taskList, Task deletedTask) {
        String output = "I've removed this task:\n"
                + deletedTask.toString() + "\n";
        return taskList.size() == 1 ? output + "Now you have 1 task in the list"
                : output + String.format("Now you have %d tasks in the list", taskList.size());
    }

    /**
     * Returns String of added task and updated number of tasks.
     *
     * @param taskList list of tasks.
     * @param newTask new task added.
     */
    public static String showAdd(TaskList taskList, Task newTask) {
        String output = "I've added this task:\n"
                + newTask.toString() + "\n";
        return taskList.size() == 1 ? output + "Now you have 1 task in the list"
                : output + String.format("Now you have %d tasks in the list", taskList.size());
    }

    /**
     * Returns String of marked task.
     *
     * @param taskList list of tasks.
     * @param index index of marked task in list of tasks.
     */
    public static String showMark(TaskList taskList, int index) {
        return "Weeeee! I've marked this task as done:\n"
                + taskList.get(index).toString();
    }

    /**
     * Returns String of unmarked task.
     *
     * @param taskList list of tasks.
     * @param index index of unmarked task in list of tasks.
     */
    public static String showUnmark(TaskList taskList, int index) {
        return "Aw Mans... I've unmarked this task:\n"
                + taskList.get(index).toString();
    }

    /**
     * Returns String of list of tasks that contains the inputted keyword.
     *
     * @param taskList list of tasks that contains the inputted keyword.
     * @throws DukeException if no tasks contains the keyword.
     */
    public static String showKeywordList(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list that contains the keyword");
        }
        assert taskList.size() > 0;
        return "Here are matching tasks in your list:\n"
                + taskList;
    }

    /**
     * Returns String of added client and updated number of clients.
     *
     * @param client added client.
     * @param clientList client list added to.
     * @return String of added client and updated number of clients.
     */
    public static String showNewClient(Client client, ClientList clientList) {
        String output = "Nice! Added this client:\n"
                + client.toString() + "\n";
        return clientList.size() == 1 ? output + "Now you have 1 client"
                : output + String.format("Now you have %d clients", clientList.size());
    }

    /**
     * Returns String representation of client list.
     *
     * @param clientList client list.
     * @return String representation of client list.
     * @throws DukeException if there are no clients in the list.
     */
    public static String showClientList(ClientList clientList) throws DukeException {
        if (clientList.size() == 0) {
            throw new DukeException("You have no clients right now rip");
        }
        return "Clients:\n"
                + clientList;
    }


    /**
     * Returns String representation of deleted client.
     *
     * @param client deleted client.
     * @return String representation of deleted client.
     */
    public static String showDeletedClient(Client client) {
        return "Deleted Client:\n"
                + client;
    }
}
