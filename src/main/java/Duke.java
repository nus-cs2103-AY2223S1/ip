import java.io.File;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ListResourceBundle;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Duke {

    private static String indent = "        ";
    private static String divider = " ___________________________________________________________________";
    private enum TaskTypes {TODO, DEADLINE, EVENT, TASK}
    private static final String DUKE_FILEPATH = "data/duke.txt";
    private static Scanner sFile = null;
    private static File dukeFile = null;

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void addTask(String taskToAdd) throws DukeException.EmptyTaskException {
        if (taskToAdd.isBlank()) {
            throw new DukeException.EmptyTaskException();
        }
        TaskTypes taskType = TaskTypes.TASK;
        if (taskToAdd.contains("todo")) {
            taskType = TaskTypes.TODO;
        } else if (taskToAdd.contains("deadline")) {
            taskType = TaskTypes.DEADLINE;
        } else if (taskToAdd.contains("event")) {
            taskType = TaskTypes.EVENT;
        }
        switch (taskType) {
            case TODO:
                String task = taskToAdd.substring(5);
                if (task.isBlank()) {
                    throw new DukeException.EmptyTaskException();
                } else {
                    tasks.add(new Todo(task));
                }
                break;

            case DEADLINE:
                int deadlineChar = taskToAdd.indexOf("/");
                String taskDesc = taskToAdd.substring(9, deadlineChar);
                String deadlineInput = taskToAdd.substring(deadlineChar + 4);
                if (taskDesc.isBlank() || deadlineInput.isBlank()) {
                    throw new DukeException.EmptyTaskException();
                } else {
                    try {
                        LocalDate deadline = LocalDate.parse(deadlineInput);
                        tasks.add(new Deadline(taskDesc, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                    } catch (DateTimeParseException e) {
                        throw new DateTimeParseException("", "", 0);
                    }
                }
                break;

            case EVENT:
                int eventChar = taskToAdd.indexOf("/");
                String eventDesc = taskToAdd.substring(6, eventChar);
                String eventTimeInput = taskToAdd.substring(eventChar + 4);
                if (eventDesc.isBlank() || eventTimeInput.isBlank()) {
                    throw new DukeException.EmptyTaskException();
                } else {
                    try {
                        LocalDate eventTime = LocalDate.parse(eventTimeInput);
                        tasks.add(new Event(eventDesc, eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                    } catch (DateTimeParseException e) {
                        throw new DateTimeParseException("", "", 0);
                    }
                }
                break;
        }
    }

    private static void writeToFile(String file, String toWrite) throws IOException {
        try {
            FileWriter dukeWriter = new FileWriter(file, true);
            dukeWriter.append(toWrite + "\n");
            dukeWriter.close();
        } catch (java.io.IOException e) {
            throw new java.io.IOException();
        }
    }

    private static void removeFromFile(String file, int taskNoToRemove) throws IOException {
        Scanner s = new Scanner(dukeFile);
        String toReplace = "";
        int taskCounter = 0;
        while (s.hasNextLine()) {
            String nextTask = s.nextLine();
            taskCounter++;
            if (taskCounter != taskNoToRemove) {
                toReplace = toReplace + nextTask + "\n";
            }
        }
        try {
            FileWriter dukeWriter = new FileWriter(file);
            dukeWriter.write(toReplace);
            dukeWriter.close();
        } catch (java.io.IOException e) {
            throw new java.io.IOException();
        }
        s.close();
    }

    public static void main(String[] args) {
        System.out.println("\n Hello there!\n"
                + "\n My name is Zelk, nice to meet you :)\n");

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            dukeFile = new File(DUKE_FILEPATH);
            if (dukeFile.createNewFile()) {
                System.out.println(" A new Task file is created! What can I do for you?\n" + divider);
            } else {
                System.out.println(" Your Task file already exists! Welcome back :D\n"
                        + " What can I do for you today?\n" + divider);
            }
            sFile = new Scanner(dukeFile);
            while (sFile.hasNextLine()) {
                String nextTask = sFile.nextLine();
                try {
                    addTask(nextTask);
                } catch (DukeException.EmptyTaskException e) {
                    //Do nothing, ignore empty lines
                    continue;
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        Scanner s = new Scanner(System.in);
        if (sFile == null) {
            System.out.println(" ohno, I can't seem to find your Task list :(\n" + divider);
            return;
        }

        String input = "";

        while ((input = s.nextLine()) != null) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(indent + "These are the tasks in your list so far!\n"
                        + indent + "You currently have " + tasks.size() + " tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(indent + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println(divider);
            } else if (input.contains("unmark")) {
                Integer taskNo = Integer.valueOf(input.substring(7));
                tasks.get(taskNo - 1).markAsUndone();
                System.out.println(indent + "Okay, I'll mark this task as undone: \n"
                        + indent + " " + tasks.get(taskNo - 1));
                System.out.println(divider);
            } else if (input.contains("mark")) {
                Integer taskNo = Integer.valueOf(input.substring(5));
                tasks.get(taskNo - 1).markAsDone();
                System.out.println(indent + "alright! I've marked this task as done :) \n"
                        + indent + " " + tasks.get(taskNo - 1));
                System.out.println(divider);
            } else if (input.contains("delete")) {
                Integer taskNo = Integer.valueOf(input.substring(7));
                Task taskToRemove = tasks.get(taskNo - 1);
                System.out.println(indent + "got it, removing this task from your list... \n"
                        + indent + "   " + taskToRemove);
                tasks.remove(taskToRemove);
                try {
                    removeFromFile(DUKE_FILEPATH, taskNo);
                } catch (java.io.IOException e) {
                    System.out.println(indent + "Sorry, I can't seem to remove this task from your Task list :(\n" + divider);
                    continue;
                }
                System.out.println(indent + "You now have " + tasks.size() + " total tasks in your list \n"
                        + divider);
            } else {
                if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                    try {
                        addTask(input);
                        writeToFile(DUKE_FILEPATH, input);
                    } catch (DukeException.EmptyTaskException | IndexOutOfBoundsException e) {
                        System.out.println(indent + "oops, the description of your task seems to be incomplete!\n"
                                + divider);
                        continue;
                    } catch (java.io.IOException e) {
                        System.out.println(" oops, your task is added, but I couldn't save your task to your " +
                                "Task file :(\n" + divider);
                    } catch (DateTimeParseException e) {
                        System.out.println(indent + "Sorry :( You have given me an invalid date, please check"
                                + " your date format again!\n" + divider);
                        continue;
                    }
                } else {
                    System.out.println(indent + "I'm sorry, I'm not sure I understand what that means :(\n" + divider);
                    continue;
                }

                System.out.println(indent + "new task added: " + tasks.get(tasks.size() - 1)
                        + "\n" + indent + "You now have " + tasks.size() + " tasks in your list"
                        + "\n" + indent + "Task is saved in memory :)"
                        + "\n" + divider);
            }
        }

        System.out.println(indent + "Bye! Hope to see you again soon! Thank you for chatting with me :)");
        sFile.close();
    }
}
