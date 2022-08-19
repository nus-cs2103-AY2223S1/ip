import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> list = new ArrayList<>();
    static boolean isRunning = true;

    public static void main(String[] args) {

        printWelcomeMsg();

        //interaction with user
        while(isRunning) {
            try {
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();

                if (userInput.equals("bye")) {

                    printEndingMsg();
                    isRunning = false;

                } else if (userInput.equals("list")) {

                    displayTasks(list);

                } else if (userInput.contains("mark")
                        || userInput.contains("unmark")
                        || userInput.contains("todo")
                        || userInput.contains("deadline")
                        || userInput.contains("event")
                        || userInput.contains("delete")) {

                    operations(userInput);
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                printWithFormat("☹ OOPS!!! I'm sorry, but I don't know what that means");
            }
        }
    }

    private static void operations(String userInput) {

        try {
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

                case "todo": {

                    String taskContent = tokens[1];
                    ToDos toDos = new ToDos(taskContent);
                    addTaskToList(toDos); //and print


                    break;
                }
                case "deadline": {

                    String[] token = splitDeadlineInput(userInput);
                    String taskContent = token[0];
                    String date = token[1];

                    Deadline deadline = new Deadline(taskContent, date);
                    addTaskToList(deadline);

                    break;
                }
                case "event": {
                    String[] token = splitEventInput(userInput);
                    String taskContent = token[0];
                    String date = token[1];

                    Event event = new Event(taskContent, date);
                    addTaskToList(event);
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithFormat(" ☹ OOPS!!! The description of a " + userInput + " cannot be empty.");
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
        Task task = list.get(number - 1);
        list.remove(task);
        printWithFormat("Noted. I've removed this task:\n"+ task+"\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void addTaskToList(Task task) {
        list.add(task);
        printWithFormat("Got it, I've added this task:\n       "
                + task.toString()
                + "\n     Now you have "
                + list.size()
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
        Task task = list.get(taskNumber - 1);
        if(firstWord.equals("mark")) {
            task.setDone(true);
            printWithFormat("Nice! I've marked this task as done:\n     " + task);
        } else {
            task.setDone(false);
            printWithFormat("OK, I've marked this task as not done yet:\n     " + task);
        }
    }

    private static void printWithFormat(String text) {

        String startHorizontalLines = "     ________________________________________\n";
        String endHorizontalLines = "\n     ________________________________________\n";

        System.out.println(startHorizontalLines + "     " + text + endHorizontalLines);
    }

    private static void displayTasks(List<Task> list) {

        String text = "Here are the tasks in your list:\n     ";

        for(int i = 0; i < list.size(); i++) {

            if(i == list.size() - 1) {
                text += i + 1 + ". " + list.get(i);
            } else {
                text += i + 1 + ". " + list.get(i) +"\n     ";
            }
        }

        printWithFormat(text);
    }
}
