import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;
    private static String lineBreak
            = "____________________________________________________________";

    private static final String FILEPATH = "data/nuke.txt";

    public static int getCount() {
        return count;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void printBot(String s) {
        System.out.println(lineBreak);
        System.out.println(s);
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void addTask(Task t) {
        tasks.add(t);
        ++count;
        try {
            addTaskToFile(FILEPATH, t);
        } catch (IOException e) {
            System.out.println("Unable to write to file.");
        }

        System.out.println(lineBreak);
        System.out.println("Got it. I've added this task:\n"
                           + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(lineBreak);
        System.out.println();
    }

    private static void addTaskInternal(Task t, boolean b) {
        t.setMarked(b);
        tasks.add(t);
        ++count;
    }

    private static void addTaskToFile(String filePath, Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String type = t.getType();
        String marked = t.getMarked() ? "X" : " ";
        String description = t.getTask();
        String date = t.getDate();

        fw.write("[" + type + "][" + marked + "][" + description + "]["
                    + date + "]" + System.lineSeparator());
        fw.close();
    }

    private static void rewriteFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        for (int i = 0; i < tasks.size(); ++i) {
            try {
                addTaskToFile(filePath, tasks.get(i));
            } catch (IOException e) {
                System.out.println("Unable to rewrite file.");
            }
        }
    }

    public static void deleteTask(int index) {
        Task t = tasks.remove(index);
        --count;

        try {
            rewriteFile(FILEPATH);
        } catch (IOException E) {
            System.out.println("Unable to rewrite file.");
        }

        System.out.println(lineBreak);
        System.out.println("Noted. I've removed this task:\n"
                           + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
            System.out.println(lineBreak);
        System.out.println();
    }

    public static void listTasks() {
        System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void markTask(Task t, boolean b) {
        t.setMarked(b);
        try {
            rewriteFile(FILEPATH);
        } catch (IOException E) {
            System.out.println("Unable to rewrite file.");
        }

        if (b) {
            printBot("Nice! I've marked this task as done: \n"
                     + "  " + t);
        } else {
            printBot("OK, I've marked this task as not done: \n"
                    + "  " + t);
        }
    }

    private static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch(IOException e) {
            return;
        }

        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String nextTask = s.nextLine();
            String[] temp = nextTask.split("\\[");
            Character taskType = temp[1].charAt(0);
            Boolean marked = temp[2].charAt(0) == 'X';
            String deadline = temp[temp.length - 1];
            deadline = deadline.substring(0, deadline.length() - 1);
            String task = "";
            for (int i = 3; i < temp.length - 1; ++i) {
                if (i < temp.length - 2) {
                    task += temp[i];
                    task += "[";
                } else {
                    task += temp[i].substring(0, temp[i].length() - 1);
                }
            }

            switch(taskType) {
                case 'T':
                    addTaskInternal(new ToDo(task), marked);
                    break;

                case 'D':
                    addTaskInternal(new Deadline(task, deadline), marked);
                    break;

                case 'E':
                    addTaskInternal(new Event(task, deadline), marked);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            readFileContents(FILEPATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println("Hello! I'm Nuke");
        System.out.println("What can I do for you?");
        String s;
        while(true) {
            s = scanner.nextLine();
            String[] words = s.split(" ", 2);
            try {
                Command command = Command.valueOf(words[0].toUpperCase());
                command.run(words.length == 2 ? words[1] : "");
            } catch (IllegalArgumentException e) {
                if (s.equals("bye")) {
                    printBot("Bye. Hope to see you again soon!");
                    return;
                } else {
                    printBot("----Error----\nPlease enter a valid command:\n\n"
                            + "mark\n" + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event");
                }
            }
        }
    }
}
