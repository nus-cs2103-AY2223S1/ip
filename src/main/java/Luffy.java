import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/** Luffy is a tasklist program. Built for CS2103T Individual Project 2022 S1.
 * @author Silas Tay A0233425M
 */
public class Luffy {
    private static final String logo = "██╗░░░░░██╗░░░██╗███████╗███████╗██╗░░░██╗\n"
            + "██║░░░░░██║░░░██║██╔════╝██╔════╝╚██╗░██╔╝\n"
            + "██║░░░░░██║░░░██║█████╗░░█████╗░░░╚████╔╝░\n"
            + "██║░░░░░██║░░░██║██╔══╝░░██╔══╝░░░░╚██╔╝░░\n"
            + "███████╗╚██████╔╝██║░░░░░██║░░░░░░░░██║░░░\n"
            + "╚══════╝░╚═════╝░╚═╝░░░░░╚═╝░░░░░░░░╚═╝░░░";

    private static final String FILE_PATH = "./data/data.txt";

    private static void updateSaveFile(ArrayList<Task> tasks, File dataFile) throws IOException {
        PrintWriter pw = new PrintWriter(FILE_PATH);
        pw.print("");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            dataFile.createNewFile();
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String dataLine = currentTask.type + " | "
                    + (currentTask.isDone ? "1" : "0") + " | "
                    + currentTask.description
                    + (currentTask.by != null ? " | " + currentTask.by : "")
                    + (currentTask.at != null ? " | " + currentTask.at : "");
            fw.write(dataLine + "\n");
            fw.close();
        }
        pw.close();
    }

    public static void main(String[] args) {

        System.out.println("------------------------------------------------------");
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------");

        //Actual Luffy Logic:

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        //Loading existing data:
        File dataFile = new File(FILE_PATH);
        try {
            if (dataFile.exists()) {
                System.out.println("Save file exists, loading saved data!");
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNext()) {
                    Task currentTask;
                    String[] taskLine = scanner.nextLine().split(Pattern.quote(" | "));
                    String taskDescription = taskLine[2];

                    switch (taskLine[0]) {
                        case "[T]":
                            currentTask = new Todo(taskDescription);
                            tasks.add(currentTask);
                            break;
                        case "[E]":
                            currentTask = new Event(taskDescription, taskLine[3]);
                            tasks.add(currentTask);
                            break;
                        case "[D]":
                            currentTask = new Deadline(taskDescription, taskLine[3]);
                            tasks.add(currentTask);
                            break;
                    }
                }
                System.out.println(tasks);
                System.out.println("------------------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }

        int counter = tasks.size();

        while (true) {
            String s = in.nextLine();

            if (s.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon :)");
                break;
            } else if (s.equals("list")) {
                System.out.println("------------------------------------------------------");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println("------------------------------------------------------");
                continue;
            } else if (s.length() >= 6 && s.substring(0, 4).equals("mark")) {
                try {
                    System.out.println("------------------------------------------------------");
                    int taskIndex = Integer.parseInt(s.substring(5, 6)) - 1;
                    if (taskIndex >= 0 && taskIndex < counter) {
                        tasks.get(taskIndex).markCompleted();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(taskIndex));
                    } else {
                        System.out.println("Task index " + (taskIndex + 1) + " is not valid!");
                    }
                    System.out.println("------------------------------------------------------");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Task index cannot be empty.");
                    System.out.println("------------------------------------------------------");
                    continue;
                }
            } else if (s.length() >= 8 && s.substring(0, 6).equals("unmark")) {
                try {
                    System.out.println("------------------------------------------------------");
                    int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                    if (taskIndex >= 0 && taskIndex < counter) {
                        tasks.get(taskIndex).markUncompleted();
                        System.out.println("Alright! I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(taskIndex));
                    } else {
                        System.out.println("Task index " + (taskIndex + 1) + " is not valid!");
                    }
                    System.out.println("------------------------------------------------------");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Task index cannot be empty.");
                    System.out.println("------------------------------------------------------");
                    continue;
                }
            } else {
                System.out.println("------------------------------------------------------");
                Task newTask;
                if (s.length() >= 4 && s.substring(0, 4).equals("todo")) {
                    try {
                        newTask = new Todo(s.substring(5));
                    } catch(StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("------------------------------------------------------");
                        continue;
                    }
                } else if (s.length() >= 8 && s.substring(0, 8).equals("deadline")){
                    try {
                        String[] splitString = s.split(" /by ");
                        newTask = new Deadline(splitString[0].substring(9), splitString[1]);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("------------------------------------------------------");
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The date of a deadline cannot be empty.");
                        System.out.println("------------------------------------------------------");
                        continue;
                    }
                } else if (s.length() >= 5 && s.substring(0, 5).equals("event")) {
                    try {
                        String[] splitString = s.split(" /at ");
                        newTask = new Event(splitString[0].substring(6), splitString[1]);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println("------------------------------------------------------");
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The period of an event cannot be empty.");
                        System.out.println("------------------------------------------------------");
                        continue;
                    }
                } else if (s.length() >= 6 && s.substring(0, 6).equals("delete")) {
                    try {
                        System.out.println("------------------------------------------------------");
                        int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                        if (taskIndex >= 0 && taskIndex < counter) {
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + tasks.get(taskIndex));
                            tasks.remove(taskIndex);
                            counter--;
                            System.out.println("Now you have " + counter + " tasks in the list.");
                        } else {
                            System.out.println("Task index " + (taskIndex + 1) + " is not valid!");
                        }
                        System.out.println("------------------------------------------------------");
                        continue;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! Task index cannot be empty.");
                        System.out.println("------------------------------------------------------");
                        continue;
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("------------------------------------------------------");
                    continue;
                }
                tasks.add(newTask);
                counter++;
                System.out.println("Got it, I've added this task:");
                System.out.println(newTask);
                if (counter > 1) {
                    System.out.println("Now you have " + counter + " tasks in the list.");
                } else {
                    System.out.println("Now you have " + counter + " task in the list.");
                }
            }

            //Updating data file:
            try {
                updateSaveFile(tasks, dataFile);
            } catch (IOException e) {
                System.out.println("Something went wrong!");
                e.printStackTrace();
            }
            System.out.println("------------------------------------------------------");
        }
    }
}
