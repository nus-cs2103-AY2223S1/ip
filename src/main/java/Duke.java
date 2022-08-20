import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private enum Commands {
        bye, list, help, mark, unmark, delete, todo, deadline, event, invalid;
    }
    //The strings that Duke uses for greetings and formatting.
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
    private final static String greeting1
            = "Hello! I'm Duke.";
    private final static String greeting2
            = "What can I do for you?";
    private static String saveFilePath;

    private ArrayList<Task> history = new ArrayList<>();
    private Scanner commandInput;
    private boolean isClosed = false;

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
        return (i + 1) + ". " + history.get(i);
    }

    private void deleteTask(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (returnedArray.length > 2) {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (isNumber(returnedArray[1])) {
            int taskId = Integer.parseInt(returnedArray[1]) - 1;
            if (history.size() <= taskId || taskId < 0) {
                throw new DukeException("that task you want to delete does not exist." +
                        "\nUse the [list] command to check what tasks are available.");
            } else {
                System.out.println("Understood. I will purge this task from your list:\n" +
                        history.get(taskId) +
                        "\nCurrently, you have " + (history.size() - 1) + " tasks in your list.");
                history.remove(taskId);
            }
        } else {
            throw new DukeException("your command is incorrect." +
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

    private boolean isNumber(String string) {
        char[] numberArray = string.toCharArray();
        for (char c : numberArray) {
            if (c < 48 || c > 57)
                return false;
        }
        return true;
    }

    private void markDone(String[] returnedArray) throws DukeException {
        if (returnedArray.length <= 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [mark].");
        } else if (returnedArray.length > 2) {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [mark].");
        } else if (isNumber(returnedArray[1])) {
            int taskId = Integer.parseInt(returnedArray[1]) - 1;
            if (history.size() <= taskId || taskId < 0) {
                throw new DukeException("that task you want to mark does not exist. "
                        + "Use the [list] command to check what tasks are available.");
            } else {
                System.out.println("Good Job! I will mark this task as done:");
                history.get(taskId).markDone();
                System.out.println(history.get(taskId));
            }
        } else {
            throw new DukeException("your command is incorrect." +
                "\nPlease use the [help] command to check the proper usage of [mark].");
        }
    }

    private void markUndone(String[] returnedArray) throws DukeException {
        if (returnedArray.length == 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [unmark].");
        } else if (returnedArray.length > 2) {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [unmark].");
        } else if (isNumber(returnedArray[1])) {
            int taskId = Integer.parseInt(returnedArray[1]) - 1;
            if (history.size() <= taskId || taskId < 0) {
                throw new DukeException("that task you want to unmark does not exist."
                        + "\nUse the [list] command to check what tasks are available.");
            } else {
                System.out.println("Alright, I will mark this task as undone:");
                history.get(taskId).markUndone();
                System.out.println(history.get(taskId));
            }
        } else {
            throw new DukeException("your command is incorrect." +
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

    //Extra method not in project specs
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

    private void saveDuke() throws IOException {
        File save = new File(saveFilePath);
        FileWriter saveWriter = new FileWriter(save);
        String saveString = "";

        for (int i = 0; i < history.size(); i++) {
            saveString += history.get(i) + "\n";
        }
        saveWriter.write(saveString);
        saveWriter.flush();
        saveWriter.close();
    }

    private void loadToDo(boolean taskWasDone, String taskDesc) {
        ToDo todo = new ToDo("todo " + taskDesc);
        if (taskWasDone) {
            todo.markDone();
        } else {
            todo.markUndone();
        }
        history.add(todo);
    }

    private void loadDeadline(boolean taskWasDone, String taskDesc) {
        String[] deadlineSpecifics = taskDesc.split(" \\(by: ");
        Deadline deadline = new Deadline(deadlineSpecifics[0], deadlineSpecifics[1].substring(0, deadlineSpecifics[1].length() - 1));
        if (taskWasDone) {
            deadline.markDone();
        } else {
            deadline.markUndone();
        }
        history.add(deadline);
    }

    private void loadEvent(boolean taskWasDone, String taskDesc) {
        String[] eventSpecifics = taskDesc.split(" \\(at: ");
        Event event = new Event(eventSpecifics[0], eventSpecifics[1].substring(0, eventSpecifics[1].length() - 1));
        if (taskWasDone) {
            event.markDone();
        } else {
            event.markUndone();
        }
        history.add(event);
    }

    private void loadTaskDifferentiator(String data) throws DukeException {
        String taskType = data.substring(0, 3);
        boolean taskWasDone = data.startsWith("[X]", 4);
        String taskDesc = data.substring(8);
        switch (taskType) {
        case "[T]":
            loadToDo(taskWasDone, taskDesc);
            break;
        case "[D]":
            loadDeadline(taskWasDone, taskDesc);
            break;
        case "[E]":
            loadEvent(taskWasDone, taskDesc);
            break;
        default:
            throw new DukeException("I seem to forgotten what tasks you have asked me to remember." +
                    "Do you know where I left my notes?");
        }
        return ;
    }

    private void loadDuke() throws IOException {
        File dataDir = new File(System.getProperty("user.dir") + "\\data");
        File savesDir = new File(System.getProperty("user.dir") + "\\data\\saves");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        if (!savesDir.exists()) {
            savesDir.mkdir();
        }

        File save = new File(saveFilePath);
        if (save.exists()) {
            Scanner sc = new Scanner(save);
            while (sc.hasNextLine()) {
                try {
                    String command = sc.nextLine();
                    loadTaskDifferentiator(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            listOut();
            sc.close();
        } else {
            System.out.println("It appears this is our first meeting.");
        }
    }

    //For single-word commands
    private void inputCommand(String command) throws DukeException {
        String[] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null || returnedArray[0].equals("")) {
            throw new DukeException("Sorry, I am a bit hard of hearing.\nCould you please repeat yourself?" +
                    "\nIf unsure, please use command [help] for the list of commands that I understand.");
        } else {
            Commands word = checkEnums(returnedArray[0]);
            switch (word) {
            case bye:
                goodbye();
                break;
            case list:
                listOut();
                break;
            case help:
                listCommands();
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
                throw new DukeException("I don't understand your command.\nCould you please repeat yourself?" +
                        "\nIf unsure, please use command [help] for the list of commands that I understand.");
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        saveFilePath = System.getProperty("user.dir") + "\\data\\saves\\tasks.txt";
        System.out.println(duke.logo + "\n" + greeting1);
        try {
            duke.loadDuke();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(greeting2 + "\n" + duke.lineBreak1);
        duke.commandInput = new Scanner(System.in);
        while (duke.commandInput.hasNextLine()) {
            String command = duke.commandInput.nextLine();
            System.out.println(lineBreak2);
            try {
                duke.inputCommand(command);
                duke.saveDuke(); //consider a bool check for if the save should happen?
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
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
