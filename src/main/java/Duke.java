import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot that helps to keep track of tasks.
 */
public class Duke {

    private static List<Task> listOfTasks = new ArrayList<>();
    private static boolean isRunning = true;
    private static final String FILE_PATH = "./data/duke.txt";
    private static File dataFile = new File(FILE_PATH);


    public static void main(String[] args) {

        if(dataFile.exists()) {
            try {
                retrieveTasks();
            } catch (IOException e) {
                System.out.println("I have trouble finding the file ☹");
            }
        } else {
            startRun();
        }

    }

    private static void retrieveTasks() throws IOException {
        printRetrievingTaskMsg();

        File file = new File("data/duke.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    private static void printRetrievingTaskMsg() {
        System.out.println("Here are the tasks! \n");
    }

    private static void startRun() {
        printWelcomeMsg();
        Scanner scanner = new Scanner(System.in);

        while (isRunning && scanner.hasNextLine()) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                try {
                    save();
                } catch (IOException e) {
                    System.out.println("I can't seem to save your changes. ☹");
                }
                printEndingMsg();
                isRunning = false;

            } else if (userInput.equals("list")) {
                displayTasks(listOfTasks);

            } else if (containsOperationWord(userInput)) {
                try {
                    performOperations(userInput);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printWithFormat("☹ OOPS!!! The description of a "
                            + userInput
                            + " cannot be empty.");
                }

            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    printWithFormat("☹ OOPS!!! I'm sorry, " +
                            "but I don't know what that means");
                }
            }
        }
    }

    private static boolean containsOperationWord(String userInput) {
        return userInput.toLowerCase().contains("mark")
                || userInput.toLowerCase().contains("unmark")
                || userInput.toLowerCase().contains("todo")
                || userInput.toLowerCase().contains("deadline")
                || userInput.toLowerCase().contains("event")
                || userInput.toLowerCase().contains("delete");
    }

    private static void performOperations(String userInput) {
        String[] tokens = userInput.split("\\s+", 2);
        String firstWord = tokens[0];

        switch (firstWord) {
        case "mark":
        case "unmark":
            int taskNumber = Integer.parseInt(tokens[1]);
            markingOfTask(firstWord, taskNumber); //and print
            break;

        case "delete":
            int taskNo = Integer.parseInt(tokens[1]);
            deleteTask(taskNo);
            break;

        case "todo":
            String taskContent = tokens[1];
            ToDos toDos = new ToDos(taskContent);
            addTaskToList(toDos); //and print
            break;

        case "deadline":
            String[] deadlineToken = splitDeadlineInput(userInput);
            String deadlineContent = deadlineToken[0];
            String date = deadlineToken[1];

            Deadline deadline = new Deadline(deadlineContent, date);
            addTaskToList(deadline);
            break;

        case "event":
            String[] eventToken = splitEventInput(userInput);
            String eventContent = eventToken[0];
            String eventDate = eventToken[1];

            Event event = new Event(eventContent, eventDate);
            addTaskToList(event);
            break;

        }
    }

    private static String[] splitEventInput(String userInput) {
        String[] result = new String[2];

        String[] tokens2 = userInput.split(" /at ", 2);
        String date = tokens2[1];
        String content = tokens2[0];
        String[] tokens3 = content.split("\\s+", 2);
        String taskContent = tokens3[1];

        result[0] = taskContent;
        result[1] = date;
        return result;
    }

    private static String[] splitDeadlineInput(String userInput) {
        String[] result = new String[2];

        String[] tokens2 = userInput.split(" /by ", 2);
        String date = tokens2[1];
        String content = tokens2[0];
        String[] tokens3 = content.split("\\s+", 2);
        String taskContent = tokens3[1];

        result[0] = taskContent;
        result[1] = date;
        return result;
    }

    private static void deleteTask(int number) {
        Task task = listOfTasks.get(number - 1);
        listOfTasks.remove(task);
        printWithFormat("Noted. I've removed this task:\n     "
                + task
                +"\n     Now you have "
                + listOfTasks.size()
                + " tasks in the list.");
    }

    private static void addTaskToList(Task task) {
        listOfTasks.add(task);
        printWithFormat("Got it, I've added this task:\n       "
                + task.toString()
                + "\n     Now you have "
                + listOfTasks.size()
                + " task in the list.");
    }

    private static void printWelcomeMsg() {
        String welcomeMsg = "Hello! I'm Duke\n     What can I do for you?";
        printWithFormat(welcomeMsg);
    }

    private static void printEndingMsg() {
        String endingMsg = "Bye. Hope to see you again soon!";
        printWithFormat(endingMsg);
    }

    private static void markingOfTask(String firstWord, int taskNumber) {
        Task task = listOfTasks.get(taskNumber - 1);
        if (firstWord.equals("mark")) {
            task.setDone(true);
            printWithFormat("Nice! I've marked this task as done:\n     "
                    + task);
        } else {
            task.setDone(false);
            printWithFormat("OK, I've marked this task as not done yet:\n     "
                    + task);
        }
    }

    private static void printWithFormat(String text) {

        String START_HORIZONTAL_LINE = "     _______________________________________________________\n";
        String END_HORIZONTAL_LINE = "\n     _______________________________________________________\n";

        System.out.println(START_HORIZONTAL_LINE
                + "     "
                + text
                + END_HORIZONTAL_LINE);
    }

    private static void displayTasks(List<Task> list) {

        String text = "Here are the tasks in your list:\n     ";

        for (int i = 0; i < list.size(); i++) {

            if(i == list.size() - 1) {
                text += i + 1 + ". " + list.get(i);
            } else {
                text += i + 1 + ". " + list.get(i) +"\n     ";
            }
        }
        printWithFormat(text);
    }

    private static void save() throws IOException {
        FileWriter filewriter = new FileWriter(dataFile.getPath());

        for (Task task : listOfTasks) {
            filewriter.write(task + System.lineSeparator());
        }
        filewriter.close();
    }
}
