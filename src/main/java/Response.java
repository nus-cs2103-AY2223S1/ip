/**
 * Encapsulate the Response function of the chatbot
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

    private ToDoList userList;

    /**
     * Class constructor for Response
     */
    public Response() {
        this.userList = new ToDoList();
    }

    /**
     * Handles user's input into chatbot
     * @param input User input into chatbot
     */
    public void handleUserInput(String input) {
        System.out.println(line);
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            System.exit(0);
        } else if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            System.out.println(userList);
        } else if (input.startsWith("unmark")) {
            int itemNumber = Integer.parseInt(input.split(" ", 2)[1]);
            userList.mark(itemNumber);
        } else if (input.startsWith("mark")) {
            int itemNumber = Integer.parseInt(input.split(" ", 2)[1]);
            userList.unmark(itemNumber);
        } else {
            ListItem newListItem = new ListItem(input);
            userList.add(newListItem);
            System.out.println("added: " + input);
        }
        System.out.println(line);
    }

    /**
     * Handles what to show during start up of chatbot
     */
    public void startUp() {
        String temp = line + "\n" + logo + "\n" + "Hello! I'm Duke\nWhat can I do for you?\n" + line;
        System.out.println(temp);
    }

}
