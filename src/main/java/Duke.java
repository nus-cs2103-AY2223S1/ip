import java.util.Scanner;

public class Duke {
    private static final Task[] tasks = new Task[100];
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
            if (userInput.equals("bye")) {
                System.out.println("Goodbye!");
                return false;
            }
            if (userInput.equals("list")) {
                listOut();
                return true;
            }
            if (!userInput.contains(" ")) {
                if (userInput.equals("mark") || userInput.equals("unmark")) {
                    throw new DukeException("Choose which index to mark.");
                }
                if (userInput.equals("todo") || userInput.equals("deadline") || userInput.equals("event")) {
                    throw new DukeException(String.format("The description of a %s cannot be empty.", userInput));
                }
                throw new DukeException("Sorry I do not understand what that means :(");
            }
            String[] input = userInput.split(" ", 2);
            if (input[0].equals("mark") || input[0].equals("unmark")) {
                marking(input[0], input[1]);
                return true;
            }
            if (input[0].equals("todo") || input[0].equals("deadline") || input[0].equals("event")) {
                addList(input);
                return true;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n");
        }
        return true;
    }

    public static void marking(String name, String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);
            if (name.equals("mark")) {
                if (!tasks[index - 1].getDone()) {
                    tasks[index - 1].markTask();
                } else {
                    throw new DukeException("Task is already marked.");
                }
            } else {
                if (tasks[index - 1].getDone()) {
                    tasks[index - 1].unmarkTask();
                } else {
                    throw new DukeException("Task is already unmarked");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("marking/unmarking requires an integer as index");
        } catch (NullPointerException e) {
            throw new DukeException(String.format("Index %d does not exist on the list.",Integer.parseInt(num)));
        }
    }
    public static void addList(String[] input) {
        Task newTask;
        if (input[0].equals("todo")) {
            newTask = new ToDos(input[1]);
        } else if (input[0].equals("deadline")) {
            String[] tempOne = input[1].split(" /by ", 2);
            newTask = new Deadlines(tempOne[0], tempOne[1]);
        } else {
            String[] tempTwo = input[1].split(" /at ", 2);
            newTask = new Events(tempTwo[0], tempTwo[1]);
        }
        tasks[Task.getNumTasks() - 1] = newTask;
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        if (Task.getNumTasks() == 1) {
            System.out.println("Now you have 1 task in the list.\n");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n%n", Task.getNumTasks());
        }
    }

    public static void listOut() throws DukeException {
        if (Task.getNumTasks() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < Task.getNumTasks(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks[i].toString());
        }
        System.out.println();
    }
}
