import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userInputArray = new ArrayList<>();

        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?";
        String exitChat = "Bye. Hope to see you again soon!";

        System.out.println(formatText(startChat));

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;

            }else if (userInput.equals("list")){
                String outputElement = "";
                for (int i = 0; i < userInputArray.size(); i++) {
                    Task task = userInputArray.get(i);
                    outputElement += i + 1 + "."
                            + task.toString() +"\n";
                }
                outputElement = formatText(outputElement.trim());
                System.out.println(outputElement);

            }else if (userInput.startsWith("mark")){
                String outputText = "";
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                outputText += "Nice! I've marked this task as done:\n";
                Task currentTask = userInputArray.get(taskID);
                currentTask.markAsDone();
                outputText +=  "  " + currentTask.toString();
                System.out.println(formatText(outputText));

            }else if (userInput.startsWith("unmark")) {
                String outputText = "";
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                outputText += "OK, I've marked this task as not done yet:\n";
                Task currentTask = userInputArray.get(taskID);
                currentTask.markAsUndone();
                outputText +=  "  " + currentTask.toString();
                System.out.println(formatText(outputText));

            } else {
                String outputElement = "Got it. I've added this task:\n";

                if (userInput.startsWith("todo")) {
                    String taskDescription = userInput.replaceFirst("todo","").strip();
                    Task newTask = new Todo(taskDescription);
                    userInputArray.add(newTask);
                    outputElement += " " + newTask.toString() + "\n";

                } else if (userInput.startsWith("deadline")) {
                    String[] temp = userInput.replaceFirst("deadline","").strip().split("/by");
                    String taskDescription = temp[0].strip();
                    String byDate = temp[1].strip();
                    Task newTask = new Deadline(taskDescription, byDate);
                    userInputArray.add(newTask);
                    outputElement += " " + newTask.toString() + "\n";

                } else if (userInput.startsWith("event")) {
                    String[] temp = userInput.replaceFirst("event","").strip().split("/at");
                    String taskDescription = temp[0].strip();
                    String byDateTime = temp[1].strip();
                    Task newTask = new Event(taskDescription, byDateTime);
                    userInputArray.add(newTask);
                    outputElement += " " + newTask.toString() + "\n";

                } else {
                    Task newTask = new Task(userInput);
                    userInputArray.add(newTask);
                    outputElement += " " + newTask.toString() + "\n";

                }
                outputElement += "Now you have " + userInputArray.size() + " tasks in the list.";
                outputElement = formatText(outputElement);
                System.out.println(outputElement);
            }
        }
        System.out.println(formatText(exitChat));
    }
    public static String formatText(String text) {
        String line = "____________________________________________________________";
        return line + "\n" + text+ "\n" + line;
    }
}
