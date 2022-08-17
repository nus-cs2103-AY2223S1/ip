import java.util.Scanner;

public class Duke {
    private static final String line = "------------------------------------------";
    private static boolean END;
    private static Memory memory;

    /*
    Wraps the text with lines on top and below
     */
    public String wrapper(String content) {
        return line + "\n" + content + "\n" + line;
    }
    /*
    Decides on what the chatbox will respond with based on user input.
     */
    public void Response(String input) throws DukeException {
        String done = "Got it. I've added this task:\n";
        if (input.equals("bye")) {
            END = true;
            System.out.println(wrapper("Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            System.out.println(wrapper(memory.toString()));
        } else if (input.length() > 3 && input.substring(0, 4).equals("mark")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The task you want to mark cannot be empty.");
            }
            String indexString = input.substring(5);
            int index = Integer.valueOf(indexString) - 1;
            if (index > -1 && index < memory.getNumOfTask()) {
                Task current = memory.getTask(index);
                if (current.getStatus()) {
                    throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
                }
                current.markasDone();
                String content = "Nice! I've marked this task as done:\n" + current.toString();
                System.out.println(wrapper(content));
            } else {
                throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
            }
        } else if (input.length() > 5 && input.substring(0, 6).equals("unmark")) {
            if (input.length() == 6) {
                throw new DukeException("☹ OOPS!!! The task you want to unmark cannot be empty.");
            }
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            if (index > -1 && index < memory.getNumOfTask()) {
                Task current = memory.getTask(index);
                if (!current.getStatus()) {
                    throw new DukeException("☹ OOPS!!! The task you want to unmark is already unmarked.");
                }
                current.markasNotDone();
                String content = "OK, I've marked this task as not done yet:\n" + current.toString();
                System.out.println(wrapper(content));
            } else {
                throw new DukeException("☹ OOPS!!! The The task you want to unmark is not here.");
            }
        } else if(input.length() > 5 && input.substring(0, 6).equals("delete")) {
            if (input.length() == 6) {
                throw new DukeException("☹ OOPS!!! The task you want to delete cannot be empty.");
            }
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            if (index > -1 && index < memory.getNumOfTask()) {
                Task deletedTask = memory.getTask(index);
                memory.deleteTask(index);
                String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                        + memory.numOfTaskToString();
                System.out.println(wrapper(content));
            } else {
                throw new DukeException("☹ OOPS!!! The The task you want to delete is not here.");
            }
        } else if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todo = new ToDo(input.substring(5));
            memory.saveTask(todo);
            String message = done + "  " + todo.toString() + memory.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.length() > 7 && input.substring(0, 8).equals("deadline")) {
            if (input.length() == 8) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("☹ OOPS!!! The deadline is missing.");
            }
            int divider = input.indexOf("/");
            Task deadline = new Deadline(input.substring(9, divider), input.substring(divider + 4));
            memory.saveTask(deadline);
            String message = done + "  " + deadline.toString() + memory.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.length() > 4 && input.substring(0, 5).equals("event")) {
            if (input.length() == 5) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("☹ OOPS!!! The duration of the event is missing.");
            }
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), input.substring(divider + 4));
            memory.saveTask(event);
            String message = done + "  " + event.toString() + memory.numOfTaskToString();
            System.out.println(wrapper(message));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        memory = new Memory();
        END = false;
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(duke.wrapper("Hello! I'm Duke\nWhat can I do for you?"));
        while (!END) {
            try {
                duke.Response(myScanner.nextLine());
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
        myScanner.close();
    }
}
