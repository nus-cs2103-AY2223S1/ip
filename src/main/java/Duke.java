import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> db = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void bye() {
        System.out.println("Sayonara, Adios!");
    }

    public static void list() {
        System.out.println("Here are your list of tasks!");
        db.forEach(task -> System.out.println((db.indexOf(task) + 1)
                + ". "  + task.toString()));
        System.out.println("You have " + db.size() + " tasks in the list.");
    }

    public static void markTask(String str) throws DukeException, IOException {
        int index = Integer.parseInt(str.substring(5));
        if (index <= db.size() && index > 0) {
            Task task = db.get(index - 1);
            if (!task.isDone()) {
                task.toggleDoneness();
                Duke.saveLocalData();
                System.out.println("Good job for doing this task!");
                System.out.println(task);
            } else {
                System.out.println("This task has already been marked done.");
                System.out.println(task);
            }
        } else {
            throw new DukeException("Index invalid, no such task exists.");
        }
    }

    public static void unmarkTask(String str) throws DukeException, IOException {
        int index = Integer.parseInt(str.substring(7));
        if (index <= db.size() && index > 0) {
            Task task = db.get(index - 1);
            if (task.isDone()) {
                task.toggleDoneness();
                Duke.saveLocalData();
                System.out.println("Task shall be marked as undone.");
                System.out.println(task);
            } else {
                System.out.println("This task has already been marked undone.");
                System.out.println(task);
            }
        } else {
            throw new DukeException("Index invalid, no such task exists.");
        }
    }

    public static void addTodo(String str) throws DukeException, IOException {
        String sub = str.substring(5).trim();
        if (!sub.isEmpty()) {
            db.add(new Todo(str.substring(5)));
            Duke.saveLocalData();
            System.out.println("Got it, I've added this task:");
            System.out.println(db.get(db.size() - 1));
            System.out.println("Now you have " + db.size() + " tasks in the list.");
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void addDeadline(String str) throws DukeException, IOException {
        String sub = str.substring(9).trim();
        if (str.contains("/by")) {
            String[] split = sub.split("/by");
            if (split.length < 2) {
                throw new DukeException("Please specify the deadline.");
            } else {
                db.add(new Deadline(split[0], split[1]));
                Duke.saveLocalData();
                System.out.println("Got it, I've added this task:");
                System.out.println(db.get(db.size() - 1));
                System.out.println("Now you have " + db.size() + " tasks in the list.");
            }
        } else {
            throw new DukeException("Please specify the deadline by using \"/by\".");
        }
    }

    public static void addEvent(String str) throws DukeException, IOException {
        String sub = str.substring(6).trim();
        if (str.contains("/at")) {
            String[] split = sub.split("/at");
            db.add(new Event(split[0], split[1]));
            Duke.saveLocalData();
            System.out.println("Got it, I've added this task:");
            System.out.println(db.get(db.size() - 1));
            System.out.println("Now you have " + db.size() + " tasks in the list.");
        } else {
            throw new DukeException("Please specify the event date by using \"/at\"");
        }
    }

    public static void deleteTask(String str) throws DukeException, IOException {
        int index = Integer.parseInt(str.substring(7));
        if (index <= db.size() && index > 0) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(db.remove(index - 1));
            Duke.saveLocalData();
            System.out.println("Now you have " + db.size() + " tasks in the list.");
        } else {
            throw new DukeException("Index invalid, no such task exists.");
        }
    }

    private static void loadLocalData() throws DukeException, IOException {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("##");
                Task task;
                if (split[0].equals("D")) {
                    task = new Deadline(split[2], split[3]);
                } else if (split[0].equals("E")) {
                    task = new Event(split[2], split[3]);
                } else if (split[0].equals("T")) {
                    task = new Todo(split[2]);
                } else {
                    throw new DukeException("Unable to read file.");
                }
                if (split[1].equals("Y")) {
                    task.toggleDoneness();
                }
                db.add(task);
            }
        } catch (FileNotFoundException e) {
            Files.createDirectories(Paths.get("data/"));
            File file = new File("data/duke.txt");
            file.createNewFile();
            System.out.print("New file created to store tasks.");
        }
    }

    private static void saveLocalData() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task task: db) {
            String str = task.stringify();
            fw.write(str + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        Duke.welcome();
        try {
            loadLocalData();
        } catch (DukeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.equals("bye")) {
                    Duke.bye();
                    break;
                }
                if (str.equals("list")) {
                    Duke.list();
                } else if (str.startsWith("mark ")) {
                    Duke.markTask(str);
                } else if (str.startsWith("unmark ")) {
                    Duke.unmarkTask(str);
                } else if (str.startsWith("todo ")) {
                    Duke.addTodo(str);
                } else if (str.equals("todo")) { // to avoid strings such as "todotodo for earlier block"
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (str.startsWith("deadline ")) {
                    Duke.addDeadline(str);
                } else if (str.startsWith("event ")) {
                    Duke.addEvent(str);
                } else if (str.startsWith("delete ")) {
                    Duke.deleteTask(str);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } catch (NumberFormatException e) {
                System.out.println("Invalid index input, please try again.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sc.close();
    }
}

