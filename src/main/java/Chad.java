import java.util.ArrayList;
import java.util.Scanner;

public class Chad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userInputArray = new ArrayList<>();

        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?";
        String exitChat = "Bye. Hope to see you again soon!";

        System.out.println(formatText(startChat));

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                byeChat(exitChat);
                break;

            }else if (userInput.equals("list")){
                listTask(userInputArray);

            }else if (userInput.startsWith("mark")){
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                markTask(userInputArray, taskID);

            }else if (userInput.startsWith("unmark")) {
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                unmarkTask(userInputArray, taskID);

            } else {
                if (userInput.startsWith("todo")) {
                    addTodoTask(userInputArray, userInput);

                } else if (userInput.startsWith("deadline")) {
                    addDeadlineTask(userInputArray, userInput);

                } else if (userInput.startsWith("event")) {
                    addEventTask(userInputArray, userInput);

                }
            }
        }
    }

    public static void byeChat(String text) {
        System.out.println(formatText(text));
    }

    public static void listTask(ArrayList<Task> tasks) {
        String outputText = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputText += i + 1 + "."
                    + task.toString() +"\n";
        }
        outputText = formatText(outputText.trim());
        System.out.println(outputText);
    }

    public static void markTask(ArrayList<Task> tasks, int taskID) {
        String outputText = "";
        outputText += "Nice! I've marked this task as done:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsDone();
        outputText +=  "  " + currentTask.toString();
        System.out.println(formatText(outputText));
    }

    public static void unmarkTask(ArrayList<Task> tasks, int taskID) {
        String outputText = "";
        outputText += "OK, I've marked this task as not done yet:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsUndone();
        outputText +=  "  " + currentTask.toString();
        System.out.println(formatText(outputText));
    }

    public static void addTodoTask(ArrayList<Task> tasks, String userInput) {
        String outputText = "Got it. I've added this task:\n";
        String taskDescription = userInput.replaceFirst("todo","").strip();
        Task newTask = new Todo(taskDescription);
        tasks.add(newTask);
        outputText += " " + newTask.toString() + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        System.out.println(outputText);

    }

    public static void addDeadlineTask(ArrayList<Task> tasks, String userInput) {
        String outputText = "Got it. I've added this task:\n";
        String[] temp = userInput.replaceFirst("deadline","").strip().split("/by");
        String taskDescription = temp[0].strip();
        String byDate = temp[1].strip();
        Task newTask = new Deadline(taskDescription, byDate);
        tasks.add(newTask);
        outputText += " " + newTask.toString() + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        System.out.println(outputText);
    }

    public static void addEventTask(ArrayList<Task> tasks, String userInput) {
        String outputText = "Got it. I've added this task:\n";
        String[] temp = userInput.replaceFirst("event","").strip().split("/at");
        String taskDescription = temp[0].strip();
        String byDateTime = temp[1].strip();
        Task newTask = new Event(taskDescription, byDateTime);
        tasks.add(newTask);
        outputText += " " + newTask.toString() + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        System.out.println(outputText);
    }

    public static String formatText(String text) {
        String line = "____________________________________________________________";
        return line + "\n" + text+ "\n" + line;
    }
}
