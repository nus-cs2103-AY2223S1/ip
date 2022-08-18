import java.util.*;

public class Duke {

    public static void dukeBye() {
        System.out.println("  ----\n  Goodbye!\n  ----");
    }

    public static int dukeMark(String input) {
        String digits = input.substring(5);
        int index = Integer.parseInt(digits) - 1;
        return index;
    }

    public static int dukeUnmark(String input) {
        String digits = input.substring(7);
        int index = Integer.parseInt(digits) - 1;
        return index;
    }

    public static Task dukeTodo(String input) throws DukeTodoException {
        if (input.length() == 4 || input.substring(5).startsWith(" ") || input.length() == 5) {
            throw new DukeTodoException(input);
        } else {
            Todo task = new Todo(input.substring(5));
            System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
            return task;
        }
    }

    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int pointer = 0;
        System.out.println("Hello I'm Duke! What can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) { //bye
                dukeBye();
                break;
            } else if (input.equals("list")) { //list
                String toDisplay = "  ----\n";
                int temp = 1;
                for (int i = 0; i < pointer; i++) {
                    toDisplay += "  " + temp + ": " + taskList[i].toString() + "\n";
                    temp++;
                }
                toDisplay += "  ----";
                System.out.println(toDisplay);
            } else if (input.startsWith("mark")) { //mark
                taskList[dukeMark(input)].taskDone();
                System.out.println("  ----\n  I've marked this task as done!\n  " + taskList[dukeMark(input)] + "\n  ----");
            } else if (input.startsWith("unmark")) { //unmark
                taskList[dukeUnmark(input)].taskUndone();
                System.out.println("  ----\n  I've marked this task as not done..\n  " + taskList[dukeUnmark(input)] + "\n  ----");
            } else if (input.startsWith("todo")) { //todo
                try {
                    taskList[pointer] = dukeTodo(input);
                    pointer++;
                    System.out.println("  You currently have " + pointer + " tasks in the list.");
                } catch (DukeTodoException e){
                    System.out.println(e.toString());
                }
            } else if (input.startsWith("deadline")) { //deadline
                String[] segments = input.split("/");
                Deadline task = new Deadline(segments[0].substring(9), segments[1].substring(3));
                System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
                taskList[pointer] = task;
                pointer++;
                System.out.println("  You currently have " + pointer + " tasks in the list.");
            } else if (input.startsWith("event")) { //event
                String[] segments = input.split("/");
                Event task = new Event(segments[0], segments[1].substring(3));
                System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
                taskList[pointer] = task;
                pointer++;
                System.out.println("  You currently have " + pointer + " tasks in the list.");
            } else {
                System.out.println(new DukeUnknownException(input).toString());
            }
        }
    }
}


