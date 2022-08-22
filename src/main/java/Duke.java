import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Duke {
    private static final String saveFilePath = "..\\duke.txt";
    private static final String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String listMessage = "Here are the tasks in your list: \n";
    private static final String markText = "Nice! I've marked this task as done: \n";
    private static final String unmarkText = "OK, I've marked this task as not done yet: \n";
    private static final String addTask = "Got it. I've added this task: \n";
    private static final String deleteTask = "Noted. I've removed this task: \n";

    private static ArrayList<Task> userTasks = new ArrayList<>();

    private static void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    private static void taskWrapper() {
        int length = userTasks.size();
        String content = userTasks.get(length - 1).returnDescription();
        content = addTask + content;
        content += "\nNow you have " + length + " tasks in the list.";
        wrapText(content);
    }

    private static void wrapDelete(String content) {
        content = deleteTask + content;
        content += "\nNow you have " + userTasks.size() + " tasks in the list.";
        wrapText(content);
    }

    private static void appendToDo(String inputString) {
        userTasks.add(new ToDo(inputString));
    }

    private static void appendDeadline(String taskDescription, String date) {
        date = date.replace("by ","");
        userTasks.add(new Deadline(taskDescription, date));
    }
    private static void appendEvent(String taskDescription, String dateTime) {
        dateTime = dateTime.replace("at ","");
        userTasks.add(new Event(taskDescription, dateTime));
    }

    private static String removeTask(String number) {
        int index = Integer.parseInt(number) - 1;
        Task deletedTask = userTasks.remove(index);
        String taskMessage = deletedTask.returnDescription();
        return taskMessage;
    }
    private static String generateList() {
        String listInString = listMessage;
        int index = userTasks.size();
        for (int i = 0; i < index; i++) {
            listInString += String.valueOf(i + 1) + ". " + userTasks.get(i).returnDescription();
            if(i != index - 1) {
                listInString += "\n";
            }
        }
        return listInString;
    }

    private static String generateMark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        String markInText = markText;
        Task currentTask = userTasks.get(index);
        currentTask.markTask();
        markInText += currentTask.returnDescription();
        return markInText;
    }

    private static String generateUnmark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        String unmarkInText = unmarkText;
        Task currentTask = userTasks.get(index);
        currentTask.unmarkTask();
        unmarkInText += currentTask.returnDescription();
        return unmarkInText;
    }

    private static String generateTextToSave() {
        String textToSave = "";
        for (Task task: userTasks) {
            textToSave += task.toWriteFile() + System.lineSeparator();
        }
        return textToSave;
    }
    private static void writeToFile(String textToSave) throws IOException {
        FileWriter fw = new FileWriter(saveFilePath);
        fw.write(textToSave);
        fw.close();
    }

    private static void checkExistsOrCreateNewFile() throws IOException, FileNotFoundException {
        File f = new File(saveFilePath);
        if (f.exists()) {
            readFile(f);
        } else {
            f.createNewFile();
        }
    }

    private static void readFile(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        String tempLine = "";
        while (s.hasNext()) {
            tempLine = s.nextLine();
            String[] tempWords = tempLine.split(" , ");
            boolean isCompleted = tempWords[1].contains("true");
//            System.out.println(Arrays.toString(tempWords));
//            System.out.println(tempLine);
            if (tempWords[0].equals("T")) {
                userTasks.add(new ToDo(tempWords[2], isCompleted));
            } else if (tempWords[0].equals("E")) {
                userTasks.add(new Event(tempWords[2], tempWords[3], isCompleted));
            } else if (tempWords[0].equals("D")) {
                userTasks.add(new Deadline(tempWords[2], tempWords[3], isCompleted));
            }
        }
    }

    private static void handleUserInputs(Scanner scanner) {
        try {
            checkExistsOrCreateNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while(scanner.hasNext()) {
            String inputString = scanner.nextLine();
            String[] inputs = inputString.split(" ");
            String input = inputs[0];

            try {
                if (input.equals("bye")) {
                    wrapText(byeMessage);
                    break;
                } else if (input.equals("list")) {
                    wrapText(generateList());
                } else if (input.equals("mark")) {
                    wrapText(generateMark(inputs[1]));
                    writeToFile(generateTextToSave());
                } else if (input.equals("unmark")) {
                    wrapText(generateUnmark(inputs[1]));
                    writeToFile(generateTextToSave());
                } else if (input.equals("todo")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    appendToDo(inputString);
                    taskWrapper();
                    writeToFile(generateTextToSave());
                } else if (input.equals("deadline")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineDescription = inputString.split("/");
                    appendDeadline(deadlineDescription[0], deadlineDescription[1]);
                    taskWrapper();
                    writeToFile(generateTextToSave());
                } else if (input.equals("event")) {
                    if (inputs.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] eventDescription = inputString.split("/");
                    appendEvent(eventDescription[0], eventDescription[1]);
                    taskWrapper();
                    writeToFile(generateTextToSave());
                } else if (input.equals("delete")) {
                    String taskMessage = removeTask(inputs[1]);
                    wrapDelete(taskMessage);
                    writeToFile(generateTextToSave());
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                wrapText(e.getMessage());
            } catch (IOException e) {
                wrapText(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        wrapText(greetMessage);
        Scanner scanner = new Scanner(System.in);
        handleUserInputs(scanner);
    }
}
