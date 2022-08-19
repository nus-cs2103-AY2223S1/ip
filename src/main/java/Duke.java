import java.rmi.server.ExportException;
import java.util.Scanner;

public class Duke {

    private static int index = 1;
    private final static Task[] userInputs = new Task[100];

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
            System.out.println(i + "." + userInputs[i]);
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
            int taskIndex = Integer.parseInt(currInput[1]);
            System.out.println(userInputs[taskIndex].markAsDone());
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeIndexOutOfBoundsException(userInputs.length);
        }
    }
    
    private void unmark(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = Integer.parseInt(currInput[1]);
            System.out.println(userInputs[taskIndex].unmarkAsNotDone());
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeIndexOutOfBoundsException(userInputs.length);
        }
    }
    
    private void newTaskAdded() {
        System.out.println("Got it. I've added this task: \n" + userInputs[index] + "\n" + numberOfTasks());
        index++;
    }
    
    private void createToDos(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            userInputs[index] = new ToDos(currInput[1]);
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(userInputs.length);
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
            userInputs[index] = new Deadlines(task, deadline);
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(userInputs.length);
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
            userInputs[index] = new Events(task, eventTime);
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(userInputs.length);
        }
    }

}
