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

    private TaskList userList;

    /**
     * Class constructor for Response
     */
    public Response() {
        this.userList = new TaskList();
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
        } else if (input.startsWith("todo")){
            String description = input.split(" ", 2)[1];
            Task newListItem = new ToDo(description);
            userList.add(newListItem);
        } else if (input.startsWith("deadline")){
            String[] descriptionWithBy = input.split(" ", 2);
            String description = descriptionWithBy[1].split(" /by ", 2)[0];
            String by = descriptionWithBy[1].split(" /by ", 2)[1];
            Task newListItem = new Deadline(description, by);
            userList.add(newListItem);
        } else if (input.startsWith("event")){
            String[] descriptionWithAt = input.split(" ", 2);
            String description = descriptionWithAt[1].split(" /at ", 2)[0];
            String at = descriptionWithAt[1].split(" /at ", 2)[1];
            Task newListItem = new Event(description, at);
            userList.add(newListItem);
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
