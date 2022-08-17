import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String breakLine = "____________________________________________________________\n";

    public static void printResponse(String response) {
        System.out.println(breakLine);
        System.out.println(response);
        System.out.println(breakLine);
    }

    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void printTaskList() {
        System.out.println(breakLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); ++i) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
        System.out.println(breakLine);
    }

    public static void markTaskAsDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("I cannot mark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot mark this task because task does not exist");
        }

        System.out.println(breakLine);
        taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index));
        System.out.println(breakLine);
    }

    public static void markTaskAsNotDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("I cannot unmark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot unmark this task because task does not exist");
        }

        System.out.println(breakLine);
        taskList.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(index));
        System.out.println(breakLine);
    }

    public static ToDo generateToDoFromInput(String input) {
        String[] commands = input.split(" ");
        String description = "";
        for (int i = 1; i < commands.length; ++i) {
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);
        return new ToDo(description);
    }

    public static Deadline generateDeadlineFromInput(String input) {
        String[] commands = input.split(" ");
        String description = "";
        String timeQualifier = "";
        String timeDescription = "";

        int timeQualifierIndex = 0;

        for (int i = 1; i < commands.length; ++i) {
            if (commands[i].charAt(0) == '/') {
                timeQualifierIndex = i;
                break;
            }
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);

        timeQualifier = commands[timeQualifierIndex].substring(1);

        for (int i = timeQualifierIndex + 1; i < commands.length; ++i) {
            timeDescription += commands[i] + " ";
        }

        timeDescription = timeDescription.substring(0, timeDescription.length() - 1);

        return  new Deadline(description, timeQualifier, timeDescription);
    }

    public static Event generateEventFromInput(String input) {
        String[] commands = input.split(" ");
        String description = "";
        String timeQualifier = "";
        String timeDescription = "";

        int timeQualifierIndex = 0;

        for (int i = 1; i < commands.length; ++i) {
            if (commands[i].charAt(0) == '/') {
                timeQualifierIndex = i;
                break;
            }
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);

        timeQualifier = commands[timeQualifierIndex].substring(1);

        for (int i = timeQualifierIndex + 1; i < commands.length; ++i) {
            timeDescription += commands[i] + " ";
        }

        timeDescription = timeDescription.substring(0, timeDescription.length() - 1);

        return new Event(description, timeQualifier, timeDescription);
    }

    /*
    public static void deleteTask(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot delete this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot delete this task because task does not exist");
        }

        System.out.println(breakLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index));
        System.out.println("Now you have " + (taskList.size() - 1)+ " tasks in the list.\n");
        System.out.println(breakLine);

        taskList.remove(index);

    }
    */

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        boolean stopLish = false;
        String greeting = "Hello! I'm Lish\n" + "What can I do for you?\n";
        printResponse(greeting);

        while (!stopLish) {
            String input = sc.nextLine();

            switch (input) {
                case "":
                    break;
                case "bye":
                    printResponse(input);
                    stopLish = true;
                    break;
                case "list":
                    printTaskList();
                    break;
                default:
                    try {
                        String[] commands = input.split(" ");

                        if (commands.length < 2) {
                            throw new DukeException("Task description cannot be empty!");
                        }

                        if (commands[0].equals("mark") || commands[0].equals("unmark") || commands[0].equals("delete")) {
                            int index = Integer.parseInt(commands[1]) - 1;

                            if (commands[0].equals("mark")) {
                                markTaskAsDone(index);
                            } else if (commands[0].equals("unmark")) {
                                markTaskAsNotDone(index);
                            } /*else if (commands[0].equals("delete")) {
                                deleteTask(index);
                            } */else {
                                throw new DukeException("I do not understand that command :(");
                            }
                        } else {
                            Task newTask;
                            if (commands[0].equals("todo")) {
                                newTask = generateToDoFromInput(input);
                            } else if (commands[0].equals("deadline")) {
                                newTask = generateDeadlineFromInput(input);
                            } else if (commands[0].equals("event")) {
                                newTask = generateEventFromInput(input);
                            } else {
                                throw new DukeException("I do not understand that command :(");
                            }



                            taskList.add(newTask);
                            printResponse("Got it. I've added this task:\n" + newTask.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.\n");
                        }
                    } catch (DukeException e) {
                        printResponse(e.toString());
                    }

            }
        }
    }
}
