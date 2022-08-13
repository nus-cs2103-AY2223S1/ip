import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> history = new ArrayList<>();
    private Scanner commandInput;
    private boolean isClosed = false;

    private enum Commands {
        bye, list, help, mark, unmark, delete, todo, deadline, event, invalid;
    }

    //The strings that Duke uses for greetings and formatting. Constants.
    private final static String lineBreak1
            = "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private final static String lineBreak2
            = "______________________________________________________"
            + "______________________________________________________";
    private final static String logo
            = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private final static String greeting
            = "Hello! I'm Duke.\n"
            + "What can I do for you?";

    private void goodbye() {
        System.out.println("Goodbye! See you next time!");
        this.isClosed = true;
    }

    private void addToDoToHistory(String task) throws DukeException {
        String[] returnedArray = task.split(" ");
        if (returnedArray.length == 1) {
            throw new DukeException("your [todo] command is empty." +
                    "\nPlease use the [help] command to check the proper usage of [todo].");
        }
        ToDo toDo = new ToDo(task);
        history.add(toDo);
        System.out.println("Adding to Tasks:" + "\n"
                + toDo
                + "\nYou have " + history.size() + " tasks in the list.");
    }

    private void addDeadlineToHistory(String task) throws DukeException {
        String[] returnedArray = task.split(" /by ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [deadline].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/by] component, or the second half of the command." +
                    "\nPlease use the [help] command to check the proper usage of [deadline].");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Deadline deadline = new Deadline(returnedArray[0], returnedArray[1]);
        history.add(deadline);
        System.out.println("Adding to Tasks:" + "\n"
                + deadline
                + "\nYou have " + history.size() + " tasks in the list.");
    }

    private void addEventToHistory(String task) throws DukeException {
        String[] returnedArray = task.split(" /at ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [event].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/at] component, or the second half ot the command." +
                    "\nPlease use the [help] command to check the proper usage of [event].");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Event event = new Event(returnedArray[0], returnedArray[1]);
        history.add(event);
        System.out.println("Adding to Tasks:" + "\n"
                + event
                + "\nYou have " + history.size() + " tasks in the list.");
    }

    private String taskItem(int i) {
        int taskNum = i + 1;
        return taskNum + ". " + history.get(i);
    }

    private void deleteTask(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (returnedArray.length == 2){
            try {
                int taskId = Integer.parseInt(returnedArray[1]) - 1;
                if (history.size() <= taskId || taskId < 1) {
                    throw new DukeException("that task you want to delete does not exist."
                            + "\nUse the [list] command to check what tasks are available.");
                } else {
                    System.out.println("Understood. I will purge this task from your list:\n" + history.get(taskId) +
                            "\nCurrently, you have " + (history.size() - 1) + " tasks in your list.");
                    history.remove(taskId);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("your command is incorrect." +
                        "\nPlease use the [help] command to check the proper usage of [delete].");
            }
        } else {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        }
    }

    private void listOut() {
        System.out.println("Here are the current tasks in your list:");
        if (history.size() <= 0) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println(this.taskItem(i));
            }
        }
    }

    private void markDone(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [mark].");
        } else if (returnedArray.length == 2){
            try {
                int taskId = Integer.parseInt(returnedArray[1]) - 1;
                if (history.size() <= taskId || taskId < 1) {
                    throw new DukeException("that task you want to mark does not exist. "
                            + "Use the [list] command to check what tasks are available.");
                } else {
                    System.out.println("Good Job! I will mark this task as done:");
                    history.get(taskId).markDone();
                }
            } catch (NumberFormatException e) {
                throw new DukeException("your command is incorrect." +
                        "\nPlease use the [help] command to check the proper usage of [mark].");
            }
        } else {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [mark].");
        }
    }

    private void markUndone(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [unmark].");
        } else if (returnedArray.length == 2){
            try {
                int taskId = Integer.parseInt(returnedArray[1]) - 1;
                if (history.size() <= taskId || taskId < 1) {
                    throw new DukeException("that task you want to unmark does not exist."
                            + "\nUse the [list] command to check what tasks are available.");
                } else {
                    System.out.println("Alright, I will mark this task as undone:");
                    history.get(taskId).markUndone();
                }
            } catch (NumberFormatException e) {
                throw new DukeException("your command is incorrect." +
                        "\nPlease use the [help] command to check the proper usage of [unmark].");
            }
        } else {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [unmark].");
        }
    }

    private Commands checkEnums(String command) {
        for (Commands e : Commands.values()) {
            if (e.name().equals(command))
                return e;
        }
        return Commands.invalid;
    }

    private void listCommands() {
        System.out.println("These are the commands I know.");
        for (Commands e : Commands.values()) {
            switch (e) {
                case bye:
                    System.out.println("Ends my service.");
                    break;
                case list:
                    System.out.println("Lists all the tasks I have been given to track.");
                    break;
                case help:
                    System.out.println("Lists all the commands I know.");
                    break;
                case mark:
                    System.out.println("Format: mark x, where x is an integer." +
                            "\nMarks the task that is index x on the list as done.");
                    break;
                case unmark:
                    System.out.println("Format: unmark x, where x is an integer." +
                            "\nMarks the task that is index x on the list as not done.");
                    break;
                case delete:
                    System.out.println("Format: delete x, where x is an integer." +
                            "\nMarks the task that is index x on the list as done.");
                    break;
                case todo:
                    System.out.println("Format: todo <task>" +
                            "\nI will add the <task> to the list of tasks.");
                    break;
                case deadline:
                    System.out.println("Format: todo <task> /by <time/date>" +
                            "\nI will add the <task> to the list of tasks." +
                            "\nThe <task> will also display its deadline at <time/date>.");
                    break;
                case event:
                    System.out.println("Format: todo <task> /at <time/date" +
                            "\nI will add the <task> to the list of tasks." +
                            "\nThe <task> will also display the <time/date> the task should be done.");
                    break;
            }
        }
    }

    //For single-word commands
    private void inputCommand(String command) throws DukeException {
        String[] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null
                || returnedArray[0].equals("")) {
            throw new DukeException("Sorry, I am a bit hard of hearing." +
                    "\nCan you please repeat yourself for my sake?" +
                    "\nIf unsure, please use command [help] for " +
                    "the list of commands that I understand.");
        } else {
            Commands word = checkEnums(returnedArray[0]);
            switch (word) {
                case bye:
                    this.goodbye();
                    break;
                case list:
                    this.listOut();
                    break;
                case help:
                    this.listCommands();
                    break;
                case mark:
                    markDone(returnedArray);
                    break;
                case unmark:
                    markUndone(returnedArray);
                    break;
                case delete:
                    deleteTask(returnedArray);
                    break;
                case todo:
                    addToDoToHistory(command);
                    break;
                case deadline:
                    addDeadlineToHistory(command);
                    break;
                case event:
                    addEventToHistory(command);
                    break;
                case invalid: //Notice the control flow still reaches here even if [invalid] is input
                    throw new DukeException("I don't understand your command." +
                            "\nCan you please repeat yourself for my sake?" +
                            "\nIf unsure, please use command [help] for " +
                            "the list of commands that I understand.");
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
            System.out.println(lineBreak2);
            try {
                duke.inputCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(lineBreak1);
            if (duke.isClosed) {
                duke.commandInput.close();
                break;
            }
        }
    }
}
