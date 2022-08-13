import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> history = new ArrayList<Task>();
    private Scanner commandInput;
    private boolean isClosed = false;

    //The strings that Duke uses for greetings and formatting. Constants.
    private final static String lineBreak1
            = "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private final static String lineBreak2
            = "______________________________________________________"
            + "______________________________________________________";
    private final static String logo
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private final static String greeting
            = "Hello! I'm Duke\n"
            + "What can I do for you?";

    private class Task {
        private String task;
        private boolean isDone;

        private Task(String task) {
            this.task = task;
            this.isDone = false;
        }

        private void markDone() {
            isDone = true;
            System.out.println(this);
        }

        private void markUndone() {
            isDone = false;
            System.out.println(this);
        }

        public String toString() {
            String box = isDone ?  "[X] " : "[ ] ";
            return box + task;
        }
    }

    private void goodbye() {
        System.out.println(this.lineBreak2);
        System.out.println("Goodbye! See you next time!\n" + this.lineBreak1);
        this.isClosed = true;
    }

    private void addToHistory(String task) {
        System.out.println(this.lineBreak2);
        System.out.println("Adding to Tasks: " + task);
        history.add(new Task(task));
        System.out.println(lineBreak1);
    }

    private String taskItem(int i) {
        int taskNum = i + 1;
        return String.valueOf(taskNum) + ". " + history.get(i);
    }

    private void listOut() {
        System.out.println(lineBreak2);
        if (history.size() <= 0) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println(this.taskItem(i));
            }
        }
        System.out.println(lineBreak1);
    }

    private void markDone(String[] returnedArray) {
        if (returnedArray.length == 1) {
            System.out.println("Error to be handled properly in level 5."
                    + "Error: Nothing to mark");
        } else if (returnedArray.length == 2){
            try {
                int taskId = Integer.parseInt(returnedArray[1]) - 1;
                if (history.size() <= taskId || taskId < 1) {
                    System.out.println(lineBreak2);
                    System.out.println("Hmm... That task you want to mark does not exist. "
                            + "Use the [list] command to check what tasks are available");
                    System.out.println(lineBreak1);
                } else {
                    System.out.println(lineBreak2);
                    System.out.println("Good Job! I will mark this task as done:");
                    history.get(taskId).markDone();
                    System.out.println(lineBreak1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error to be handled properly in level 5. " +
                        "Error: Incorrect command");
            }
        } else {
            System.out.println("Error to be handled properly in level 5. Error: " +
                    "Too many arguments for mark command");
        }
    }

    private void markUndone(String[] returnedArray) {
        if (returnedArray.length == 1) {
            System.out.println("Error to be handled properly in level 5. " +
                    "Error: Nothing to mark");
        } else if (returnedArray.length == 2){
            try {
                int taskId = Integer.parseInt(returnedArray[1]) - 1;
                if (history.size() <= taskId || taskId < 1) {
                    System.out.println(lineBreak2);
                    System.out.println("Hmm... That task you want to unmark does not exist. "
                            + "Use the [list] command to check what tasks are available");
                    System.out.println(lineBreak1);
                } else {
                    System.out.println(lineBreak2);
                    System.out.println("Alright, I will mark this task as undone:");
                    history.get(taskId).markUndone();
                    System.out.println(lineBreak1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error to be handled properly in level 5. " +
                        "Error: Incorrect command");
            }
        } else {
            System.out.println("Error to be handled properly in level 5. " +
                    "Error: Too many arguments for unmark command");
        }
    }

    private void parseCommand(String command) {
        String [] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null
                || returnedArray[0] == "") {
            System.out.println("Error to be handled properly in level 5. " +
                    "Error: No command");
        } else if (returnedArray[0].equals("mark")) {
            markDone(returnedArray);
        } else if (returnedArray[0].equals("unmark")) {
            markUndone(returnedArray);
        } else {
            this.addToHistory(command);
        }
    }

    private void inputCommand(String command) {
        if (command.equals("bye")) {
            this.goodbye();
        } else if (command.equals("list")) {
            this.listOut();
        } else {
            this.parseCommand(command);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.logo + "\n"
                + duke.greeting
                + "\n"
                + duke.lineBreak1);
        duke.commandInput = new Scanner(System.in);
        while (duke.commandInput.hasNextLine()) {
            String command = duke.commandInput.nextLine();
            duke.inputCommand(command);
            if (duke.isClosed) {
                duke.commandInput.close();
                break;
            }
        }
    }
}
