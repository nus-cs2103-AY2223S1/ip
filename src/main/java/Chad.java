import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Chad {
    private static final String[] taskTypes = {"todo", "deadline", "event"};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userInputArray = Storage.initializeArrayList();
        String startChat = "Hello! I'm Chadbot\nWhat can I do for you?";
        String exitChat = "Bye. Hope to see you again soon!";

        System.out.println(formatText(startChat));

        while (true) {
            String userInput = sc.nextLine();
            String firstWord = userInput.split(" ")[0];
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

            } else if (userInput.startsWith("delete")) {
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                deleteTask(userInputArray, taskID);

            }else if (userInput.startsWith("date")) {
                printTaskAtDate(userInputArray,userInput);

            } else {
                try {
                    if (Arrays.asList(taskTypes).contains(firstWord)) {
                        if (userInput.startsWith("todo")) {
                            addTodoTask(userInputArray, userInput);

                        } else if (userInput.startsWith("deadline")) {
                            addDeadlineTask(userInputArray, userInput);

                        } else if (userInput.startsWith("event")) {
                            addEventTask(userInputArray, userInput);
                        }
                    } else {
                        throw new ChadException("I'm sorry, but I don't know what that means :-(");
                    }

                } catch (ChadException ce) {
                    String err = formatText(ce.getMessage());
                    System.out.println(err);
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

    public static void markTask(ArrayList<Task> tasks, int taskID) throws IOException {
        String outputText = "";
        outputText += "Nice! I've marked this task as done:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsDone();
        outputText +=  "  " + currentTask;
        Storage.toggleMarkTaskInFile(taskID);
        System.out.println(formatText(outputText));
    }

    public static void unmarkTask(ArrayList<Task> tasks, int taskID) throws IOException {
        String outputText = "";
        outputText += "OK, I've marked this task as not done yet:\n";
        Task currentTask = tasks.get(taskID);
        currentTask.markAsUndone();
        outputText +=  "  " + currentTask;
        Storage.toggleMarkTaskInFile(taskID);
        System.out.println(formatText(outputText));
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskID) throws IOException {
        String outputText = "Noted. I've removed this task:\n";
        Task currentTask = tasks.get(taskID);
        outputText += " " + currentTask.toString() + "\n";
        tasks.remove(taskID);
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        Storage.deleteTaskInFile(taskID);
        System.out.println(outputText);
    }

    public static void addTodoTask(ArrayList<Task> tasks, String userInput) throws ChadException, IOException {
        String outputText = "Got it. I've added this task:\n";
        String taskDescription = userInput.replaceFirst("todo","").strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a todo cannot be empty.");
        }
        Todo newTask = new Todo(taskDescription);
        tasks.add(newTask);

        String strIsDone = newTask.getIsDone() ? "1" : "0";
        Storage.writeToFile("T | " + strIsDone + " | " + newTask.getDescription());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        System.out.println(outputText);

    }

    public static void addDeadlineTask(ArrayList<Task> tasks, String userInput) throws ChadException, IOException {
        String outputText = "Got it. I've added this task:\n";

        String[] temp = userInput.replaceFirst("deadline","").strip().split("/by");
        String taskDescription = temp[0].strip();
        String byDate = temp[1].strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a deadline cannot be empty.");
        }

        if (byDate.isEmpty()) {
            throw new ChadException("The date of a deadline cannot be empty.");
        }

        Deadline newTask = new Deadline(taskDescription, byDate);
        tasks.add(newTask);

        String strIsDone = newTask.getIsDone() ? "1" : "0";
        Storage.writeToFile("D | " + strIsDone + " | " + newTask.getDescription()
                        + " | " + newTask.getDateTime());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        System.out.println(outputText);
    }

    public static void addEventTask(ArrayList<Task> tasks, String userInput) throws ChadException, IOException {
        String outputText = "Got it. I've added this task:\n";
        String[] temp = userInput.replaceFirst("event","").strip().split("/at");
        String taskDescription = temp[0].strip();
        String byDateTime = temp[1].strip();

        if (taskDescription.isEmpty()) {
            throw new ChadException("The description of a event cannot be empty.");
        }

        if (byDateTime.isEmpty()) {
            throw new ChadException("The datetime of a event cannot be empty.");
        }

        Event newTask = new Event(taskDescription, byDateTime);
        tasks.add(newTask);

        String strIsDone = newTask.getIsDone() ? "1" : "0";
        Storage.writeToFile("E | " + strIsDone + " | " + newTask.getDescription()
                + " | " + newTask.getDateTime());

        outputText += " " + newTask + "\n";
        outputText += "Now you have " + tasks.size() + " tasks in the list.";
        outputText= formatText(outputText);
        System.out.println(outputText);
    }

    public static void printTaskAtDate(ArrayList<Task> tasks, String date) {
        StringBuilder output = new StringBuilder();
        date = date.split(" ", 2)[1];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate theDate = LocalDate.parse(date, format);

        if(theDate == null) {
            return;
        }

        for (Task t: tasks) {
            if (t instanceof Deadline) {
                LocalDate d = ((Deadline) t).getDateTime().toLocalDate();
                if(d.compareTo(theDate) == 0) {
                    output.append(t);
                    output.append(System.getProperty("line.separator"));
                }
            } else if (t instanceof Event) {
                LocalDate d = ((Event) t).getDateTime().toLocalDate();
                if(d.compareTo(theDate) == 0) {
                    output.append(t);
                    output.append(System.getProperty("line.separator"));
                }
            }
        }
        String text = output.toString().trim();
        if(text.equals("")) {
            text = "No such record for " + theDate;
        }

        System.out.println(formatText(text));
    }

    public static String formatText(String text) {
        String line = "____________________________________________________________";
        return line + "\n" + text+ "\n" + line;
    }
}
