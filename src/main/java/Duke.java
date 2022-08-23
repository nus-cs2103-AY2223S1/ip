import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String SAVE_FILE_PATH = "./data/duke.txt";

    private static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    private static void printTaskAdded(Task task, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    private static void saveTasks(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(SAVE_FILE_PATH);
            String tasks = "";

            for (int i = 0; i < taskList.size(); ++i) {
                tasks += taskList.get(i).toSaveFileString() + "\n";
            }

            writer.write(tasks);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("There is a problem with saving the tasks");
        }
    }

    private static ArrayList<Task> getSavedTasks() throws DukeException {
        try {
            File saveFile = new File(SAVE_FILE_PATH);
            Scanner reader = new Scanner(saveFile);
            ArrayList<Task> savedTasks = new ArrayList<Task>();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] splitted = data.split("\\s@\\s");
                String taskType = splitted[0];
                String task, date;
                boolean isDone;

                switch (taskType) {
                case "[T]":
                    task = splitted[2];
                    isDone = splitted[1].equals("[X]");
                    savedTasks.add(new ToDo(task, isDone));
                    break;

                case "[E]":
                    task = splitted[2];
                    isDone = splitted[1].equals("[X]");
                    date = splitted[3];
                    savedTasks.add(new Event(task, date, isDone));
                    break;

                case "[D]":
                    task = splitted[2];
                    isDone = splitted[1].equals("[X]");
                    date = splitted[3];
                    savedTasks.add(new Deadline(task, date, isDone));
                    break;

                default:
                    throw new DukeException("There is a problem loading your safe file");
                }
            }

            return savedTasks;
        } catch (IOException e) {
            throw new DukeException("Failed saving data due to unknown error");
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList;
        try {
            taskList = getSavedTasks();
        } catch (DukeException e) {
            taskList = new ArrayList<>();
            System.out.println(e.getMessage());
        }
        String logo = "Botto";
        System.out.println("Hello from " + logo + "\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while (shouldContinue) {
            try {
                String input = scanner.nextLine();
                //split the input by whitespace
                String[] splitted = input.split("\\s", 2);
                //command is first word of the input
                String command = splitted[0];
                int index;
                Task task;

                switch (command) {
                case "todo":
                    //No Description Given
                    if (splitted.length < 2) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    String taskString = splitted[1];
                    task = new ToDo(taskString);
                    taskList.add(task);
                    saveTasks(taskList);
                    printTaskAdded(task, taskList.size());
                    break;

                case "deadline":
                    //No Description Given
                    if (splitted.length < 2) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    //some regex to parse the strings correctly
                    //0th index: task, 1st index: deadline
                    String[] splittedDeadline = splitted[1].split("\\s/by\\s", 2);
                    String deadlineTask = splittedDeadline[0];
                    //No Description Given
                    if (deadlineTask.equals("") || deadlineTask.startsWith("/by")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                    //No Deadline Given
                    if (splittedDeadline.length == 1) {
                        throw new DukeException("please specify a deadline");
                    }

                    String deadlineDate = splittedDeadline[1];

                    task = new Deadline(deadlineTask, deadlineDate);
                    taskList.add(task);
                    saveTasks(taskList);
                    printTaskAdded(task, taskList.size());
                    break;

                case "event":
                    //No Description Given
                    if (splitted.length < 2) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    //some regex to parse the strings correctly
                    //0th index: event, 1st index: date
                    String[] splittedEvent = input.split("\\s", 2)
                            [1].split("\\s/at\\s", 2);
                    String eventString = splittedEvent[0];

                    //No Description Given
                    if (eventString.equals("") || eventString.startsWith("/at")) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }

                    //No Deadline Given
                    if (splittedEvent.length == 1) {
                        throw new DukeException("please specify a date");
                    }

                    String eventDate = splittedEvent[1];

                    task = new Event(eventString, eventDate);
                    taskList.add(task);
                    saveTasks(taskList);
                    printTaskAdded(task, taskList.size());
                    break;

                case "mark":
                    // No index given
                    if (splitted.length < 2) {
                        throw new DukeException("No Index Given");
                    }
                    //the index should be the "2nd word"
                    index = Integer.parseInt(splitted[1]);
                    //Index out of bounds
                    if (index > taskList.size() || index < 1) {
                        throw new DukeException("Index Is Not Valid");
                    }
                    //get the selected task
                    task = taskList.get(index - 1);
                    task.markAsDone();
                    saveTasks(taskList);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;

                case "unmark":
                    //No Index Given
                    if (splitted.length < 2) {
                        throw new DukeException("No Index Given");
                    }
                    //the index should be the "2nd word"
                    index = Integer.parseInt(splitted[1]);
                    //Index out of bounds
                    if (index > taskList.size() || index < 1) {
                        throw new DukeException("Index Is Not Valid");
                    }
                    //get the selected task
                    task = taskList.get(index - 1);
                    task.markAsUndone();
                    saveTasks(taskList);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    break;

                case "delete":
                    //No Index Given
                    if (splitted.length < 2) {
                        throw new DukeException("No Index Given");
                    }
                    //the index should be the "2nd word"
                    index = Integer.parseInt(splitted[1]);
                    //Index out of bounds
                    if (index > taskList.size() || index < 1) {
                        throw new DukeException("Index Is Not Valid");
                    }
                    //get the selected task
                    task = taskList.get(index - 1);
                    //remove the task
                    taskList.remove(task);
                    //print the response to the user
                    saveTasks(taskList);
                    System.out.println("Noted. I have removed this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    shouldContinue = false;
                    break;

                case "list":
                    printTaskList(taskList);
                    break;


                default:
                    throw new DukeException("Command Not Found!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
