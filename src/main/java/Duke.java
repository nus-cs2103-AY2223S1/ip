import java.io.*;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    static final String pathName = "data/duke.txt";

    public static void main(String[] args) {
        try {
            if (!Files.exists(Paths.get(pathName))) {
                Files.createFile(Paths.get(pathName));
            }
            ArrayList<Task> tasksToDo = new ArrayList<>();
            BufferedReader bR;
            try {
                bR = new BufferedReader(new FileReader(pathName));
                String currLine = bR.readLine();
                while (currLine != null) {
                    String[] details = currLine.split("\\|");
                    boolean done = !Objects.equals(details[1], "0");
                    String type = details[0];
                    switch (type) {
                        case "T":
                            tasksToDo.add(new Todo(details[2], done));
                            break;
                        case "D":
                            tasksToDo.add(new Deadline(details[2], done, details[3]));
                            break;
                        case "E":
                            tasksToDo.add(new Event(details[2], done, details[3]));
                            break;
                        default:
                            break;
                    }
                    currLine = bR.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Hi I'm Duke, What can I do for you?");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            while (!input.equals("bye")) {
                String[] strs = input.split(" ");
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < tasksToDo.size(); j++) {
                        System.out.println((j + 1) + ". " + tasksToDo.get(j).toString());
                    }
                } else if (strs.length == 2 && (strs[0].equals("mark") || strs[0].equals("unmark"))) {
                    int index = Integer.parseInt(strs[1]) - 1;
                    Task task = tasksToDo.get(index);
                    if (strs[0].equals("mark")) {
                        task.markTaskAsDone();
                        System.out.println("Nice! I have mark this task as done:\n" + task.toString());
                    } else if (strs[0].equals("unmark")) {
                        task.unMarkTaskAsDone();
                        System.out.println("Ok, I have mark this task as not done yet:\n" + task.toString());
                    }
                    RewriteFile(tasksToDo);
                } else if (strs.length == 2 && (strs[0].equals("delete"))) {
                    int index = Integer.parseInt(strs[1]) - 1;
                    Task task = tasksToDo.get(index);
                    tasksToDo.remove(index);
                    System.out.println("Noted. I've removed this task:\n" + task.toString() +
                            "\nNow you have " + tasksToDo.size() + " tasks in the list.");
                    RewriteFile(tasksToDo);
                } else {
                    try {
                        String[] details;
                        Task task;
                        switch (strs[0]) {
                            case "deadline":
                                details = input.split(" ", 2)[1].split(" /by ");
                                task = new Deadline(details[0], false, details[1]);
                                tasksToDo.add(task);
                                if (WriteToFile(task)) {
                                    System.out.println("Got it! I've added this task:\n"
                                            + tasksToDo.get(tasksToDo.size() - 1).toString()
                                            + "\nNow you've got " + tasksToDo.size() + " tasks in the list!");
                                }
                                break;
                            case "event":
                                details = input.split(" ", 2)[1].split(" /at ");
                                task = new Event(details[0], false, details[1]);
                                tasksToDo.add(task);
                                if (WriteToFile(task)) {
                                    System.out.println("Got it! I've added this task:\n"
                                            + tasksToDo.get(tasksToDo.size() - 1).toString()
                                            + "\nNow you've got " + tasksToDo.size() + " tasks in the list!");
                                }
                                break;
                            case "todo":
                                String detail = input.split(" ", 2)[1];
                                task = new Todo(detail, false);
                                tasksToDo.add(task);
                                if (WriteToFile(task)) {
                                    System.out.println("Got it! I've added this task:\n"
                                            + tasksToDo.get(tasksToDo.size() - 1).toString()
                                            + "\nNow you've got " + tasksToDo.size() + " tasks in the list!");
                                }
                                break;
                            default:
                                System.out.println("OOPS!!! I don't understand what that means!");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a " + strs[0] + " cannot be empty");
                        break;
                    }
                }
                input = scanner.nextLine();
            }
            System.out.println("Bye!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("The folder: data does not exist");
        }
    }

    private static boolean WriteToFile(Task task) throws IOException {
        String str = task.getType() == 'T' ?
                ('T' + "|" + task.getDone() + "|" + task.getTask())
                : (task.getType() + "|" + task.getDone() + "|" + task.getTask() + "|" + task.getDetail());
        Files.write(Paths.get(pathName), (str + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        return true;
    }

    private static void RewriteFile(ArrayList<Task> tasks) throws IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathName));
            writer.write("");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                WriteToFile(task);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
