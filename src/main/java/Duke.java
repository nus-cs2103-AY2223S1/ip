import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> history = new ArrayList<>();
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

    private void goodbye() {
        System.out.println(this.lineBreak2);
        System.out.println("Goodbye! See you next time!\n" + this.lineBreak1);
        this.isClosed = true;
    }
/*
    private void addTaskToHistory(String task) {
        System.out.println(this.lineBreak2);
        System.out.println("Adding to Tasks: " + task + "\n"
                + "You have " + history.size() + " tasks in the list");
        history.add(new Task(task));
        System.out.println(lineBreak1);
    }
*/
    private void addToDoToHistory(String task) throws DukeException {
        System.out.println(this.lineBreak2);
        String[] returnedArray = task.split(" ");
        if (returnedArray.length == 1) {
            throw new DukeException("your [todo] command is empty" +
                    "\nPlease use the [help] command to check the proper usage of [todo]");
        }
        ToDo toDo = new ToDo(task);
        history.add(toDo);
        System.out.println("Adding to Tasks: " + "\n"
                + toDo
                + "\nYou have " + history.size() + " tasks in the list");
        System.out.println(lineBreak1);
    }

    private void addDeadlineToHistory(String task) throws DukeException {
        System.out.println(this.lineBreak2);
        String[] returnedArray = task.split(" /by ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete" +
                    "\nPlease use the [help] command to check the proper usage of [deadline]");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/by] component, or the second half of the command." +
                    "\nPlease use the [help] command to check the proper usage of [deadline]");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Deadline deadline = new Deadline(returnedArray[0], returnedArray[1]);
        history.add(deadline);
        System.out.println("Adding to Tasks: " + "\n"
                + deadline
                + "\nYou have " + history.size() + " tasks in the list");
        System.out.println(lineBreak1);
    }

    private void addEventToHistory(String task) throws DukeException {
        System.out.println(this.lineBreak2);
        String[] returnedArray = task.split(" /at ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete" +
                    "\nPlease use the [help] command to check the proper usage of [event]");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/at] component, or the second half ot the command." +
                    "\nPlease use the [help] command to check the proper usage of [event]");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Event event = new Event(returnedArray[0], returnedArray[1]);
        history.add(event);
        System.out.println("Adding to Tasks: " + "\n"
                + event
                + "\nYou have " + history.size() + " tasks in the list");
        System.out.println(lineBreak1);
    }

    private String taskItem(int i) {
        int taskNum = i + 1;
        return taskNum + ". " + history.get(i);
    }

    private void listOut() {
        System.out.println(lineBreak2 + "\nHere are the current tasks in your list:");
        if (history.size() <= 0) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println(this.taskItem(i));
            }
        }
        System.out.println(lineBreak1);
    }

    private void markDone(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete" +
                    "\nPlease use the [help] command to check the proper usage of [mark]");
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
                throw new DukeException("your command is incorrect" +
                        "\nPlease use the [help] command to check the proper usage of [mark]");
            }
        } else {
            throw new DukeException("your command has too many arguments" +
                    "\nPlease use the [help] command to check the proper usage of [mark]");
        }
    }

    private void markUndone(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete" +
                    "\nPlease use the [help] command to check the proper usage of [unmark]");
        } else if (returnedArray.length == 2){
            try {
                int taskId = Integer.parseInt(returnedArray[1]) - 1;
                if (history.size() <= taskId || taskId < 1) {
                    System.out.println(lineBreak2);
                    System.out.println("Hmm... That task you want to unmark does not exist."
                            + "\nUse the [list] command to check what tasks are available");
                    System.out.println(lineBreak1);
                } else {
                    System.out.println(lineBreak2);
                    System.out.println("Alright, I will mark this task as undone:");
                    history.get(taskId).markUndone();
                    System.out.println(lineBreak1);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("your command is incorrect" +
                        "\nPlease use the [help] command to check the proper usage of [unmark]");
            }
        } else {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [unmark]");
        }
    }

    //For multi-word commands with 1 header word
    private void parseCommand(String command) throws DukeException {
        String [] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null
                || returnedArray[0].equals("")) {
            throw new DukeException("Sorry, I am a bit hard of hearing." +
                    "\nCan you please repeat yourself for my sake?" +
                    "\nIf unsure, please use command [help] for " +
                    "the list of commands that I understand.");
        } else if (returnedArray[0].equals("mark")) {
            markDone(returnedArray);
        } else if (returnedArray[0].equals("unmark")) {
            markUndone(returnedArray);
        } else if (returnedArray[0].equals("todo")) {
            this.addToDoToHistory(command);
        } else if (returnedArray[0].equals("deadline")) {
            this.addDeadlineToHistory(command);
        } else if (returnedArray[0].equals("event")) {
            this.addEventToHistory(command);
        } else {
            throw new DukeException("I don't understand your command." +
                    "\nCan you please repeat yourself for my sake?" +
                    "\nIf unsure, please use command [help] for " +
                        "the list of commands that I understand.");
        }
    }

    //For single-word commands
    private void inputCommand(String command) {
        if (command.equals("bye")) {
            this.goodbye();
        } else if (command.equals("list")) {
            this.listOut();
        } else if (command.equals("help")) {
            //TODO: help command
        } else {
            {
                try {
                    this.parseCommand(command);
                } catch (DukeException e) {
                    System.out.println(lineBreak2 + "\n" +
                            e.getMessage() + "\n" +
                            lineBreak1);
                }
            }
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
