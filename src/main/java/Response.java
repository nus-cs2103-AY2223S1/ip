/**
 * Encapsulate the Response function of the chatbot.
 *
 * @author: Jonas Png
 */
public class Response {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String line = "_______________________________";

    private TaskList userList;

    /**
     * Class constructor for Response.
     */
    public Response() {
        this.userList = new TaskList();
    }

    /**
     * Handles user's input into chatbot.
     * @param input User input into chatbot.
     */
    public void handleUserInput(String input) {
        String[] inputList = input.split(" ");
        try {
            System.out.println(line);
            if (inputList[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                System.exit(0);
            } else if (inputList[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(userList);
            } else if (inputList[0].equals("unmark")) {
                userList.unmark(getIntegerInUserInput(inputList));
            } else if (inputList[0].equals("mark")) {
                userList.mark(getIntegerInUserInput(inputList));
            } else if (inputList[0].equals("todo")){
                Task newListItem = new ToDo(getToDoDescription(inputList, input));
                userList.add(newListItem);
            } else if (inputList[0].equals("deadline")){
                Task newListItem = new Deadline(getDeadlineDescription(inputList, input),
                        getDeadlineBy(inputList, input));
                userList.add(newListItem);
            } else if (inputList[0].equals("event")){
                Task newListItem = new Event(getEventDescription(inputList, input),
                        getEventAt(inputList, input));
                userList.add(newListItem);
            } else if (inputList[0].equals("delete")) {
                userList.delete(getIntegerInUserInput(inputList));
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(line);
    }

    /**
     * Handles what to show during start up of chatbot.
     */
    public void startUp() {
        String temp = line + "\n" + logo + "\n" + "Hello! I'm Duke\nWhat can I do for you?\n" + line;
        System.out.println(temp);
    }

    /**
     * gets the todo description from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public String getToDoDescription(String[] inputList, String input) {
        if (inputList.length >= 2) {
            return input.split(" ", 2)[1];
        }
        return " ";
    }

    /**
     * gets the Deadline's description from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public String getDeadlineDescription(String[] inputList, String input) {
        if (inputList.length >= 2) {
            String[] descriptionWithBy = input.split(" ", 2);
            return descriptionWithBy[1].split(" /by ", 2)[0];
        }
        return " ";
    }

    /**
     * gets the Deadline's by from user input
     *
     * @param inputList user input after spliting by " "
     * @param input user input
     */
    public String getDeadlineBy(String[] inputList, String input) {
        if (inputList.length > 2) {
            String[] descriptionWithBy = input.split(" ", 2);
            return descriptionWithBy[1].split(" /by ", 2)[1];
        }
        return " " ;
    }

    /**
     * gets the Event's description from user input
     *
     * @param inputList user input after spliting by " "
     * @param input user input
     */
    public String getEventDescription(String[] inputList, String input) {
        if (inputList.length >= 2) {
            String[] descriptionWithAt = input.split(" ", 2);
            return descriptionWithAt[1].split(" /at ", 2)[0];
        }
        return " ";

    }

    /**
     * gets the Event's at from user input
     *
     * @param inputList user input after spliting by " "
     * @param input user input
     */
    public String getEventAt(String[] inputList, String input) {
        if (inputList.length > 2) {
            String[] descriptionWithAt = input.split(" ", 2);
            return descriptionWithAt[1].split(" /at ", 2)[1];
        }
        return " ";
    }

    public int getIntegerInUserInput(String[] inputList) throws DukeException{
        if (inputList.length > 2) {
            throw new DukeException("Please provide only 1 task number!");
        } else if (inputList.length < 2) {
            throw new DukeException("Please provide a task number!");
        }
        try {
            return Integer.parseInt(inputList[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide an actual number!");
        }
    }

}
