import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Seaward {

    private final static String welcome = "Hello! I'm Seaward,\n" +
            "your friendly neighbourhood chatbot.\n" +
            "Type something and I will reply!";

    private static TaskList taskList;

    private static String FILE_PATH;

    public Seaward(String filePath) {
        taskList = new TaskList();
        FILE_PATH = filePath;
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.write(System.lineSeparator());
        fw.close();
    }

    private static void clearFile() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        PrintWriter writer = new PrintWriter(f);
        writer.print("");
        writer.close();
    }

    public String getWelcome() {
        return welcome;
    }

    public String readInputString(String s) throws InvalidCommandException,
            InvalidDescriptionException, IOException {
        String[] splitCommand = s.split(" ", 2);
        String command = splitCommand[0];
        switch (command) {
        case "bye":
            Seaward.clearFile();
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                char taskType = taskList.readTask(i).charAt(1);
                String status = taskList.readStatus(i);
                switch (taskType) {
                case 'T': {
                    String taskStatus = status.equals("X") ? "1" : "0";
                    String taskToAppend = "T | " + taskStatus + " | " + taskList.getDescription(i);
                    Seaward.appendToFile(taskToAppend);
                    break;
                }
                default:
                    break;
                }
            }
            return "Seaward out!";
        case "list": {
            int numOfTasks = taskList.getNumOfTasks();
            String list = "";
            for (int i = 0; i < numOfTasks; i++) {
                int index = i + 1;
                String taskDescription = taskList.readTask(i);
                if (index == numOfTasks) {
                    list = list + index + "." + taskDescription;
                } else {
                    list = list + index + "." + taskDescription + "\n";
                }
            }
            return list;
        }
        case "mark": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            taskList.setCompleted(index);
            return "I have marked this task as done:\n" +
                    taskList.readTask(index);
        }
        case "unmark": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            taskList.setNotCompleted(index);
            return "I have marked this task as undone:\n" +
                    taskList.readTask(index);
        }
        case "delete": {
            int index = Integer.parseInt(splitCommand[1]) - 1;
            int numOfTasks = taskList.getNumOfTasks();
            int newNumOfTasks = numOfTasks - 1;
            if (index + 1 > numOfTasks) {
                throw new InvalidCommandException("Task does not exist.");
            }
            String result = "Noted. I have removed this task:\n" + taskList.readTask(newNumOfTasks)
                    + "\n" + "Now you have "
                    + newNumOfTasks + " task(s) in your list.";
            taskList.deleteTask(index);
            return result;
        }
        case "todo": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description.");
            }
            taskList.addTodo(splitCommand[1]);

            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        }
        case "deadline": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and deadline.");
            }
            taskList.addDeadline(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        }
        case "event": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and time/date.");
            }
            taskList.addEvent(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return "Noted. I have added:\n" + taskList.readTask(numOfTasks - 1)
                    + "\n" + "Now you have "
                    + numOfTasks + " task(s) in your list.";
        }
        default:
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void readTasks(String s) {
        String[] splitCommand = s.split("|", 9);
        String taskType = splitCommand[0];
        boolean isMarked = splitCommand[4].equals("1");
        switch (taskType) {
        case "T": {
            taskList.addTodo(splitCommand[8]);
            int numOfTasks = taskList.getNumOfTasks();
            if (isMarked) {
                taskList.setCompleted(numOfTasks - 1);
            } else {
                taskList.setNotCompleted(numOfTasks - 1);
            }
            break;
        }
        case "D": {
            String[] splitDescription = s.split(" | ", 2);
            String description = splitDescription[0];
            taskList.addDeadline(description + "/by" + splitDescription[1]);
            int numOfTasks = taskList.getNumOfTasks();
            if (isMarked) {
                taskList.setCompleted(numOfTasks - 1);
            } else {
                taskList.setNotCompleted(numOfTasks - 1);
            }
            break;
        }
        case "E": {
            String[] splitDescription = s.split(" | ", 2);
            String description = splitDescription[0];
            taskList.addDeadline(description + "/at" + splitDescription[1]);
            int numOfTasks = taskList.getNumOfTasks();
            if (isMarked) {
                taskList.setCompleted(numOfTasks - 1);
            } else {
                taskList.setNotCompleted(numOfTasks - 1);
            }
            break;
        }
        default:
            break;
        }
    }
}
