import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.*;

public class Duke {
    static Scanner input = new Scanner(System.in);
    static String workingDir = System.getProperty("user.dir");
    static Path pathToDuke = Paths.get(workingDir, "data", "duke.txt");

    public static void fileRead() {
        boolean directoryExists = Files.exists(pathToDuke);
        if (directoryExists) {
            try {
                FileReader fr = new FileReader(String.valueOf(pathToDuke));
                BufferedReader br = new BufferedReader(fr);

                String str;
                if ((str = br.readLine()) == null) {
                    System.out.println("     You have no saved tasks yet! Add one now!");
                } else {
                    System.out.println("     " + str);
                    while ((str = br.readLine()) != null) {
                        System.out.println("     " + str);
                    }
                }
                br.close();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {
                Path path = Paths.get(workingDir, "data");
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            try {
                File duke = new File(String.valueOf(pathToDuke));
                if (duke.createNewFile()) {
                    //System.out.println("New file duke created");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("     You have no saved tasks yet! Add one now!");
        }
    }

    public static void saveTask(String taskToString) {
        try {
            FileWriter fw = new FileWriter(String.valueOf(pathToDuke), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(taskToString);
            pw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void responseRepeater() {
        String response = input.nextLine();

        //FileWriter fw = new FileWriter(String.valueOf(path));

        ArrayList<Task> taskStore = new ArrayList<>();

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println("     The following are your saved tasks: ");
                for (int i = 0; i < taskStore.size(); i++) {
                    Task t = taskStore.get(i);
                    System.out.println("       "
                                      + (i + 1)
                                      + "."
                                      + t.toString());
                }
            } else if (response.length() > 4 && response.substring(0, 4).equals("mark")) {
                int taskNumber = Integer.parseInt(response.substring(5, 6)) - 1;
                Task t = taskStore.get(taskNumber);
                t.markAsDone();
            } else if (response.length() > 6 && response.substring(0, 6).equals("unmark")) {
                int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
                Task t = taskStore.get(taskNumber);
                t.markAsUnDone();
            } else if (response.length() > 3 && response.substring(0, 4).equals("todo")) {
                if (response.length() <= 5) {
                    System.out.println("     Please add a task after 'todo'!");
                } else {
                    Task newTodo = new Todo(response.substring(5));
                    taskStore.add(newTodo);
                    saveTask(newTodo.toString());
                    //counter += 1;
                    System.out.println("     Ok! I have added this Todo task:\n"
                            + "       " + newTodo.toString() + "\n"
                            + "     You now have a total of " + taskStore.size() + " tasks!");
                }
            } else if (response.length() > 4 && response.substring(0, 5).equals("event")) {
                if (response.length() <= 6) {
                    System.out.println("     Please add a task after 'event'!");
                } else {
                    int separatorPosition = response.indexOf("/");
                    Task newEvent = new Event(
                            response.substring(6, separatorPosition - 1),
                            response.substring(separatorPosition + 4));
                    taskStore.add(newEvent);
                    saveTask(newEvent.toString());
                    //counter += 1;
                    System.out.println("     Ok! I have added this Event task:\n"
                            + "       " + newEvent.toString() + "\n"
                            + "     You now have a total of " + taskStore.size() + " tasks!");
                }
            } else if (response.length() > 7 && response.substring(0, 8).equals("deadline")) {
                if (response.length() <= 9) {
                    System.out.println("     Please add a task after 'deadline'!");
                } else {
                    int separatorPosition = response.indexOf("/");
                    Task newDeadline = new Deadline(
                            response.substring(9, separatorPosition - 1),
                            response.substring(separatorPosition + 4));
                    taskStore.add(newDeadline);
                    saveTask(newDeadline.toString());
                    //counter += 1;
                    System.out.println("     Ok! I have added this Deadline task:\n"
                            + "       " + newDeadline.toString() + "\n"
                            + "     You now have a total of " + taskStore.size() + " tasks!");
                }
            } else if (response.length() > 5 && response.substring(0, 6).equals("delete")) {
                int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
                Task t = taskStore.get(taskNumber);
                taskStore.remove(taskNumber);
                System.out.println("     Ok! I have removed the following task!: \n"
                                  + "       " + t.toString() + "\n"
                                  + "     You now have a total of " + taskStore.size() + " tasks!");
            } else {
                System.out.println("     Please specify one of the 3 commands before your task to add a task:\n"
                                  + "       todo\n"
                                  + "       event\n"
                                  + "       deadline\n");
            }
            response = input.nextLine();
        }
        System.out.println("     Sad to see you go! Visit me again soon!");
    }

    public static void main(String[] args) {
        String greetings = "Good day to you! I'm Jake!\n"
                    + "I will help you to keep track of your tasks!\n"
                    + "The following are your saved tasks:";
        /*String home = System.getProperty("user.dir");
        System.out.println(home);*/
        //fileRead();
        System.out.println(greetings);
        fileRead();
        responseRepeater();
    }
}
