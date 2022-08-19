import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static int index = 1;
    private final static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm SoCCat\nWhat can I do for you?");
        new Duke().start();
    }

    private static String numberOfTasks() {
        return "Now you have " + index +  (index < 2 ? " task" : " tasks") + " in your list.";
    }

    private static void getList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i < index; i++) {
            System.out.println(i + "." + listOfTasks.get(i - 1));
        }
    }
    
    private void start() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] words = input.split(" ", 2);
            String keyword = words[0];

            try {
                if (keyword.equals("bye")) {
                    bye();
                    return;
                } else if (keyword.equals("list")) {
                    getList();
                } else if (keyword.equals("todo")) {
                    createToDos(words);
                } else if (keyword.equals("deadline")) {
                    createDeadlines(words);
                } else if (keyword.equals("event")) {
                    createEvents(words);
                } else if (keyword.equals("mark")) {
                    mark(words);
                } else if (keyword.equals("unmark")) {
                    unmark(words);
                } else {
                    throw new DukeInvalidException();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } 
        }
    }
    
    private void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    private void mark(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = Integer.parseInt(currInput[1]) - 1;
            System.out.println(listOfTasks.get(taskIndex).markAsDone());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    
    private void unmark(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = Integer.parseInt(currInput[1]) - 1;
            System.out.println(listOfTasks.get(taskIndex).unmarkAsNotDone());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    
    private void newTaskAdded() {
        System.out.println("Got it. I've added this task: \n" + listOfTasks.get(index -1) + "\n" + numberOfTasks());
        index++;
    }
    
    private void createToDos(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            listOfTasks.add(new ToDos(currInput[1]));
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    private void createDeadlines(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            String[] taskDetails = currInput[1].split(" /by ", 2);
            String task = taskDetails[0];
            String deadline = taskDetails[1];
            listOfTasks.add(new Deadlines(task, deadline));
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }

    private void createEvents(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            String[] taskDetails = currInput[1].split(" /at ", 2);
            String task = taskDetails[0];
            String eventTime = taskDetails[1];
            listOfTasks.add(new Events(task, eventTime));
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }

}
