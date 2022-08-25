import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class represents a chat bot.
 */
public class Duke {
    private static final String LINE = "------------------------------------------";
    private static boolean isEnd;
    private static Memory memory;

    /*
    Wraps the text with lines on top and below
     */
    public static String wrapper(String content) {
        return LINE + "\n" + content + "\n" + LINE;
    }

    /*
    Decides on what the chatbox will respond with based on user input.
     */
    public void Response(String input) throws DukeException, DateTimeParseException {
        String done = "Got it. I've added this task:\n";
        if (input.equals("bye")) {
            isEnd = true;
            System.out.println(wrapper("Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            System.out.println(wrapper(memory.toString()));
        } else if (input.startsWith("mark")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The task you want to mark cannot be empty.");
            }
            String indexString = input.substring(5);
            int index = Integer.valueOf(indexString) - 1;
            memory.markTask(index);
        } else if (input.startsWith("unmark")) {
            if (input.length() == 6) {
                throw new DukeException("☹ OOPS!!! The task you want to unmark cannot be empty.");
            }
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            memory.unMarkTask(index);
        } else if(input.startsWith("delete")) {
            if (input.length() == 6) {
                throw new DukeException("☹ OOPS!!! The task you want to delete cannot be empty.");
            }
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            memory.deleteTask(index);
        } else if (input.startsWith("todo")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todo = new ToDo(input.substring(5), false);
            memory.saveTask(todo);
            String message = done + "  " + todo.toString() + memory.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("deadline")) {
            if (input.length() == 8) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("☹ OOPS!!! The deadline is missing.");
            }
            int divider = input.indexOf("/");
            LocalDateTime date = LocalDateTime.parse(input.substring(divider + 4));
            Task deadline = new Deadline(input.substring(9, divider), false, date);
            memory.saveTask(deadline);
            String message = done + "  " + deadline.toString() + memory.numOfTaskToString();
            System.out.println(wrapper(message));
        } else if (input.startsWith("event")) {
            if (input.length() == 5) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            if (!input.contains("/") || input.length() == input.indexOf("/") + 1) {
                throw new DukeException("☹ OOPS!!! The duration of the event is missing.");
            }
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), false, input.substring(divider + 4));
            memory.saveTask(event);
            String message = done + "  " + event.toString() + memory.numOfTaskToString();
            System.out.println(wrapper(message));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        isEnd = false;
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(duke.wrapper(logo + "Hello! I'm Duke\nWhat can I do for you?"));
        memory = new Memory();
        while (!isEnd) {
            try {
                duke.Response(myScanner.nextLine());
            } catch (DukeException e){
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Please input the date in the ISO-8601 format\n" +
                        "For example: 2022-08-20T12:00");
            }
        }
        myScanner.close();
    }
}
