import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> LIST_OF_TASKS = new ArrayList<>();

    private static void updateFromFile() {
        try {
            String line = FileHandler.readLine(1);
            int lineNum = 0;
            while (!line.equals("")) {
                lineNum++;
                line = FileHandler.readLine(lineNum);
                if (line.charAt(1) == 'D') {
                    if (line.charAt('4') == 'Y') {
                        int index = line.indexOf('|');
                        String taskName = line.substring(8, index - 1);
                        String dateTime = line.substring(index + 2);
                        Task task = new Task.DeadlineTask(taskName, dateTime);
                    }
                }
            }
            //TODO
        } catch (FileNotFoundException e) {
            System.out.println("No file found to read from");
        } catch (IOException e) {
            System.out.println("Unknown IO Exception");
        }
    }

    private static void updateToFile() {
        StringBuilder outputFile = new StringBuilder();
        for (Task task : LIST_OF_TASKS) {
            outputFile.append(task);
            outputFile.append("\n");
        }
        try {
            FileHandler.writeToFile(outputFile.toString());
        } catch (FileNotFoundException e) {
            System.out.println("No file found to write to");
        } catch (IOException e) {
            System.out.println("Unknown IO error");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hi, I'm Duke. What can I do for you?");
        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
        while (true) {
            if (message.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            } else if (message.toLowerCase().equals("list")) {
                StringBuilder output_message = new StringBuilder();
                for (int i = 0; i < LIST_OF_TASKS.size(); i++) {
                    output_message.append(String.format("%d. %s", i + 1, LIST_OF_TASKS.get(i)));
                    output_message.append("\n");
                }
                System.out.println(output_message);
            } else if (message.toLowerCase().startsWith("mark")) {
                try {
                    int task_index = Integer.parseInt(message.substring(5)) - 1;
                    Task task = LIST_OF_TASKS.get(task_index);
                    task.markComplete();
                    System.out.printf("Marked task %d as complete. %n%s",task_index + 1, task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task you would like to mark");
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("You only have %d tasks!%n%n", LIST_OF_TASKS.size());
                }
            } else if (message.toLowerCase().startsWith("unmark")) {
                try {
                    int task_index = Integer.parseInt(message.substring(7)) - 1;
                    Task task = LIST_OF_TASKS.get(task_index);
                    task.markIncomplete();
                    System.out.printf("Marked task %d as incomplete. %n%s%n",task_index + 1, task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task you would like to unmark");
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("You only have %d tasks!%n", LIST_OF_TASKS.size());
                }
            }else if (message.toLowerCase().startsWith("deadline")) {
                int slash_char_pos = message.indexOf("/by");
                if (slash_char_pos < 13) {
                    System.out.println("Deadline tasks require a task name and a deadline specified by /by");
                } else {
                    String task_name = message.substring(9, slash_char_pos);
                    String deadline = message.substring(slash_char_pos + 4);
                    Task this_task = new Task.DeadlineTask(task_name, deadline);
                    LIST_OF_TASKS.add(this_task);
                    System.out.printf("I've added this task:%n%s%n", this_task);
                }
            } else if (message.toLowerCase().startsWith(("event"))) {
                int slash_char_pos = message.indexOf("/at");
                if (slash_char_pos < 10) {
                    System.out.println("Event tasks require a task name and a datetime specified by /at");
                } else {
                    String task_name = message.substring(6, slash_char_pos);
                    String date = message.substring(slash_char_pos + 4);
                    Task this_task = new Task.EventTask(task_name, date);
                    LIST_OF_TASKS.add(this_task);
                    System.out.printf("I've added this task:%n%s%n", this_task);
                }
            } else if (message.toLowerCase().startsWith(("todo"))) {
                String task_name = message.substring(5);
                Task this_task = new Task.TodoTask(task_name);
                LIST_OF_TASKS.add(this_task);
                System.out.printf("I've added this task:%n%s%n", this_task);
            } else if (message.toLowerCase().startsWith("delete")) {
                try {
                    int task_index = Integer.parseInt(message.substring(7)) - 1;
                    Task removed = LIST_OF_TASKS.remove(task_index);
                    System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d tasks in the list%n",
                            removed, LIST_OF_TASKS.size());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task you would like to delete");
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("You only have %d tasks!%n", LIST_OF_TASKS.size());
                }
            } else {
                System.out.println("Sorry, I don't know what that means :(");
            }
            updateToFile();
            message = keyboard.nextLine();
        }
    }
}
