import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Duke {

    private enum Tasks {
        TODO, EVENT, DEADLINE
    }

    private static void dukeFormat(String input) {
        System.out.println("  ----");
        System.out.println("  " + input);
        System.out.println("  ----");
    }

    private static void createTask(ArrayList<Task> list, String input, Tasks enumTask) throws DukeException {
        String segments[];
        Task task;
        switch (enumTask) {
            case TODO:
                task = new Todo(input);
                break;

            case DEADLINE:
                segments = input.split("/");
                if (segments.length != 2) {
                    throw new DukeException("Error with deadline input");
                }
                task = new Deadline(segments[0], segments[1]);
                break;

            case EVENT:
                segments = input.split("/");
                if (segments.length != 2) {
                    throw new DukeException("Error with event input");
                }
                task = new Event(segments[0], segments[1].substring(3));
                break;

            default:
                throw new DukeException("Error with input");
        }
        list.add(task);
        System.out.println("  ----\n  added: " + task.toString() + "\n  ----");
    }

    private static void dukeBye() {
        dukeFormat("Goodbye!");
    }

    private static int dukeMark(String input) {
        String digits = input.substring(5);
        int index = Integer.parseInt(digits) - 1;
        return index;
    }

    private static int dukeUnmark(String input) {
        String digits = input.substring(7);
        int index = Integer.parseInt(digits) - 1;
        return index;
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        dukeFormat("Hello I'm Duke! What can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                dukeBye();
                break;
            }
            String[] segments = input.split(" ", 2);
            try {
                switch (segments[0]) {

                    case "list":
                        String toDisplay = "";
                        int temp = 1;
                        for (int i = 0; i < taskList.size(); i++) {
                            toDisplay += (i + 1) + ": " + taskList.get(i) + "\n  ";
                            temp++;
                        }
                        dukeFormat(toDisplay);
                        break;

                    case "mark":
                        taskList.get(dukeMark(input)).taskDone();
                        dukeFormat("I've marked this task as done! \n  "
                                + taskList.get(dukeMark(input)).toString());
                        break;

                    case "unmark":
                        taskList.get(dukeUnmark(input)).taskUndone();
                        dukeFormat("I've marked this task as not done.. \n  "
                                + taskList.get(dukeUnmark(input)).toString());
                        break;

                    case "todo":
                        if (segments.length == 1) {
                            throw new DukeException("Description of a todo cannot be empty!");
                        }
                        createTask(taskList, segments[1], Tasks.TODO);
                        break;

                    case "event":
                        if (segments.length == 1) {
                            throw new DukeException("Description of a event cannot be empty!");
                        }
                        createTask(taskList, segments[1], Tasks.EVENT);
                        break;

                    case "deadline":
                        if (segments.length == 1) {
                            throw new DukeException("Description of a deadline cannot be empty!");
                        }
                        createTask(taskList, segments[1], Tasks.DEADLINE);
                        break;

                    case "delete":
                        int index = Integer.parseInt(segments[1]) - 1;
                        System.out.println("  ----\n  Done! I have deleted this task:\n  " + taskList.get(index)
                                + "\n  Now you have " + (taskList.size() - 1) + " tasks in the list.\n  ----");
                        taskList.remove(index);
                        break;

                    default:
                        throw new DukeException(":( I have no idea what you are telling me to do.");
                }
            } catch (DukeException e) {
                dukeFormat(e.toString());
            }
        }
    }
}