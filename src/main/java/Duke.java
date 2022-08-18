import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n";
    static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        boolean inputIsValid;

        String startMessage = "  /\\_/\\\n" +
                " ( o.o ) < Yo! I'm " + CHATBOX_NAME + "!\n" +
                "  > ^ <    What can I do for you? :)\n";
        printMessage(startMessage);

        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            try {
                validate(input.toLowerCase());
                inputIsValid = true;
            } catch (DukeException ex) {
                // logging and handling the situation
                inputIsValid = false;
                printMessage(ex.getMessage());
            }

            if (inputIsValid) {
                String[] segments = input.split(" ");

                if (input.equalsIgnoreCase("list")) {
                    printMessage(listToString());
                } else if (input.equalsIgnoreCase("bye")) {
                    printMessage("Gone so soon? Bye (._.)\n");
                } else if (input.toLowerCase().startsWith("unmark")) {
                    Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                    curr.markAsUndone();
                    printMessage("[ ] I've marked this task as not done yet:\n" +curr + "\n");
                } else if (input.toLowerCase().startsWith("mark")) {
                    Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                    curr.markAsDone();
                    printMessage("[X] You've completed a task!\n" + curr + "\n");
                } else if (input.toLowerCase().startsWith("delete")) {
                    Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                    list.remove(Integer.parseInt(segments[1]) - 1);
                    printMessage(" - Removed this task:\n" +curr + "\nNow you have " + list.size()
                            + " tasks in the list\n");
                } else if (input.toLowerCase().startsWith("todo")) {
                    String description = input.substring(5);
                    Todo todo = new Todo(description);
                    list.add(todo);
                    printMessage("+ Added this todo:\n" + todo + "\nNow you have " + list.size()
                            + " tasks in the list\n");
                } else if (input.toLowerCase().startsWith("deadline")) {
                    String description = input.substring(9, input.indexOf(" /"));
                    String by = input.substring(input.indexOf("/by") + 3);
                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);
                    printMessage("+ Added this deadline:\n" + deadline + "\nNow you have " + list.size()
                            + " tasks in the list\n");
                } else if (input.toLowerCase().startsWith("event")){
                    String description = input.substring(6, input.indexOf(" /"));
                    String at = input.substring(input.indexOf("/at") + 3);
                    Event event = new Event(description, at);
                    list.add(event);
                    printMessage("+ Added this event:\n" + event + "\nNow you have " + list.size()
                            + " tasks in the list\n");
                }
            }
        } //end while loop
    }

    static void validate (String input) throws DukeException {
        String[] segments = input.split(" ");
        String[] tasks = {"todo", "deadline", "event", "mark", "unmark", "delete"};
        String[] allCommands = {"todo", "deadline", "event", "list", "bye", "mark", "unmark", "delete"};
        if (!Arrays.asList(allCommands).contains(segments[0])) {
            //handles invalid commands
            throw new DukeException("(´･_･`) I don't know what that means\n");
        } else if (Arrays.asList(tasks).contains(segments[0])) {
            if (segments[0].equals("mark") || segments[0].equals("unmark") || segments[0].equals("delete")) {
                if (segments.length <= 1) {
                    //handles empty list index
                    throw new DukeException("The value of " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
                } else if (Integer.parseInt(segments[1]) > list.size()) {
                    //handles invalid list index
                    throw new DukeException("There is no " + Integer.parseInt(segments[1])
                            + " index in the list. （ﾟДﾟ ）\n");
                }
            } else if (segments.length <= 1) {
                //handles empty description
                throw new DukeException("The description of a " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
            } else if (segments[0].equals("deadline")) {
                if (!input.contains("/by")) {
                    //handles empty date/time
                    throw new DukeException("The date of " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
                }
            } else if (segments[0].equals("event")) {
                if (!input.contains("/at")) {
                    //handles empty date/time error
                    throw new DukeException("The date of " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
                }
            }
        }
    }

    static String listToString() {
        if (list.size() == 0) {
            return "List is empty ~\n";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: \n");
        for (int i =0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString();
    }

     static void printMessage(String message) {
        String print = PARTITION + message + PARTITION;
        System.out.println(print);
    }

}
