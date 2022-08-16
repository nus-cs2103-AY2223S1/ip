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

        String cat = "  /\\_/\\\n" +
                " ( o.o ) < Yo! I'm " + CHATBOX_NAME + "!\n" +
                "  > ^ <    What can I do for you? :)\n";
        String startMessage = cat;
        printMessage(startMessage);

        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            try {
                validate(input);
                inputIsValid = true;
            } catch (DukeException ex) {
                // logging and handling the situation
                inputIsValid = false;
                System.out.println(PARTITION + ex.getMessage() + PARTITION);
            }
            if (inputIsValid) {
                String[] segments = input.split(" ");

                if(input.equals("list")) {
                    printMessage(listToString());
                } else if (input.equals("bye")) {
                    printMessage("Gone so soon? :( Bye\n");
                } else if (input.startsWith("unmark")){
                    Task curr = list.get(Integer. parseInt(segments[1]) - 1);
                    curr.markAsUndone();
                    printMessage("OK, I've marked this task as not done yet:\n" +curr + "\n");
                } else if (input.startsWith("mark")){
                    Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                    curr.markAsDone();
                    printMessage("Yay! You've completed a task!\n" + curr + "\n");
                } else if (input.startsWith("delete")){
                    Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                    list.remove(Integer.parseInt(segments[1]) - 1);
                    printMessage("Noted. I've removed this task:\n" +curr + "\nNow you have " + list.size() + " tasks in the list\n");

                } else if (input.startsWith("todo")){
                    String description = input.substring(5, input.length());
                    Todo todo = new Todo(description);
                    list.add(todo);
                    printMessage("Got it. I've added this task:\n" + todo + "\nNow you have " + list.size() + " tasks in the list\n");
                } else if (input.startsWith("deadline")){
                    String description = input.substring(9, input.indexOf(" /"));
                    String by = input.substring(input.indexOf("/by ") + 4);
                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);
                    printMessage("Got it. I've added this task:\n" + deadline + "\nNow you have " + list.size() + " tasks in the list\n");
                } else if (input.startsWith("event")){
                    String description = input.substring(6, input.indexOf(" /"));
                    String at = input.substring(input.indexOf("/at ") + 4);
                    Event event = new Event(description, at);
                    list.add(event);
                    printMessage("Got it. I've added this task:\n" + event + "\nNow you have " + list.size() + " tasks in the list\n");
                }
            }
        }
    }

    static void validate (String input) throws DukeException {
        String[] segments = input.split(" ");
        String[] tasks = {"todo", "deadline", "event", "mark", "unmark", "delete"};
        String[] commands = {"todo", "deadline", "event", "list", "bye", "mark", "unmark", "delete"};
        if(!Arrays.asList(commands).contains(segments[0])) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } else if(Arrays.asList(tasks).contains(segments[0])){
            if(segments.length <= 1){
                if(segments[0].equals("mark") || segments[0].equals("unmark")){
                    throw new DukeException("☹ OOPS!!! The value of " + segments[0] + " cannot be empty.\n");
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a " + segments[0] + " cannot be empty.\n");
                }
            }
        } if(segments[0].equals("deadline") || segments[0].equals("event")){
            if(!input.contains("/")){
                throw new DukeException("☹ OOPS!!! The date of " + segments[0] + " cannot be empty.\n");
            }
        }
        //todo: handle edge cases of missing date
    }

    static String listToString() {
        if (list.size() == 0) {
            return "List is empty!\n";
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
