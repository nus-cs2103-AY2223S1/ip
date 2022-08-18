import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
        String userInput = sc.nextLine();
        while(instructionReader(userInput) && sc.hasNextLine()) {
            userInput = sc.nextLine();
        }
    }

    public static boolean instructionReader(String userInput)  {
        try {
            if (!userInput.contains(" ")) {
                switch (userInput) {
                    case ("bye"):
                        System.out.println("Goodbye!");
                        return false;
                    case ("list"):
                        listOut();
                        return true;
                    case ("mark"):
                    case ("unmark"):
                        throw new DukeException(String.format("Choose which index to %s.", userInput));
                    case ("todo"):
                    case ("deadline"):
                    case ("event"):
                        throw new DukeException(String.format("The description of a %s cannot be empty.", userInput));
                    case ("delete"):
                        throw new DukeException("Choose which index to delete.");
                    default:
                        throw new DukeException("Sorry I do not understand what that means :(");
                }
            }
            String[] input = userInput.split(" ", 2);
            switch (input[0]) {
                case ("delete"):
                    delete(input[1]);
                    return true;
                case ("mark"):
                case ("unmark"):
                    marking(input[0], input[1]);
                    return true;
                case ("todo"):
                case ("deadline"):
                case ("event"):
                    addList(input);
                    return true;
                default:
                    throw new DukeException("Sorry I do not understand what that means :(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n");
        }
        return true;
    }

    public static void delete(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index - 1));
            tasks.remove(index - 1);
            System.out.printf("Now you have %d tasks in the list\n%n", tasks.size());
        } catch (NumberFormatException e) {
            throw new DukeException("deleting requires an integer as index");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %d does not exist on the list.", Integer.parseInt(num)));
        }
    }

    public static void marking(String name, String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);
            switch (name) {
                case ("mark"):
                    if (!tasks.get(index - 1).getDone()) {
                        tasks.get(index - 1).markTask();
                        break;
                    } else {
                        throw new DukeException("Task is already marked.");
                    }
                case ("unmark"):
                    if (tasks.get(index - 1).getDone()) {
                        tasks.get(index - 1).unmarkTask();
                        break;
                    } else {
                        throw new DukeException("Task is already unmarked");
                    }
            }
        } catch (NumberFormatException e) {
            throw new DukeException(name + "ing requires an integer as index");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %s does not exist on the list.",num));
        }
    }

    public static void addList(String[] input) throws DukeException {
        Task newTask;
        switch (input[0]) {
            case ("todo"):
                newTask = new ToDos(input[1]);
                break;
            case ("deadline"):
                if (input[1].contains(" /by ")) {
                    String[] tempOne = input[1].split(" /by ", 2);
                    newTask = new Deadlines(tempOne[0], tempOne[1]);
                    break;
                } else {
                    throw new DukeException("Deadline does not have proper format.");
                }
            case ("event"):
                if (input[1].contains(" /at ")) {
                    String[] tempTwo = input[1].split(" /at ", 2);
                    newTask = new Events(tempTwo[0], tempTwo[1]);
                    break;
                } else {
                    throw new DukeException("Event does not have proper format.");
                }
            default:
                throw new IllegalStateException("Unexpected value: " + input[0]);
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);

        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.\n");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n%n", tasks.size());
        }
    }


    public static void listOut() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i).toString());
        }
        System.out.println();
    }
}
