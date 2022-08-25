
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String ROUTE = "./data/dukeInfo.txt";


    private static void write(){
        try {
            FileWriter toLoad = new FileWriter(ROUTE);
            for (Task t: tasks) {
                String taskInfo = t.getTaskType() + ",";

                if (t.isCompleted()) {
                    taskInfo += "1,";
                } else {
                    taskInfo += "0,";
                }
                taskInfo += t.getTaskName() + ",";

                if (t.getTaskType().equals("D") || t.getTaskType().equals("E")) {
                    taskInfo += t.getTime();
                }

                toLoad.write(taskInfo + "\n");
            }

            toLoad.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void read() {
        File toRead = new File(ROUTE);

        if (!toRead.exists()) {
            try {
                File directory = toRead.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                toRead.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            Scanner fileReader = new Scanner(toRead);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] info = line.split(",");
                String taskType = info[0];
                Task toAdd;
            switch (taskType) {
            case "T":
                toAdd = new Todo(info[2]);
                if (Integer.parseInt(info[1]) == 1) {
                    toAdd.complete();
                }
                tasks.add(toAdd);
                break;
            case "D":
                toAdd = new Deadline(info[2], info[3]);
                if (Integer.parseInt(info[1]) == 1) {
                    toAdd.complete();
                }
                tasks.add(toAdd);
                break;
            case "E":
                toAdd = new Event(info[2], info[3]);
                if (Integer.parseInt(info[1]) == 1) {
                    toAdd.complete();
                }
                tasks.add(toAdd);
                break;
            default:

            }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Oops file cannot be found!");
        }



    }
    private static boolean inputChecker(String[] arr) {
        if (arr.length  < 2) {
            return false;
        }
        if (arr[1].isBlank()) {
            return false;
        }
        return true;
    }

    private static void mark(String[] arr) {
        int i = Integer.parseInt(arr[1]);
        if (i  <= Task.getCount()) {
            tasks.get(i - 1).complete();
            System.out.println("Nice! I have marked this task as done: ");
            System.out.println(tasks.get(i - 1));
        } else {
            System.out.println("Index does not exist");
        }

    }

    private static void unmark(String[] arr) {
        int i = Integer.parseInt(arr[1]);
        if (i <= Task.getCount()) {
            tasks.get(i - 1).incomplete();
            System.out.println("OK, I have marked this task as not done yet: ");
            System.out.println(tasks.get(i - 1));
        } else {
            System.out.println("Index does not exist");
        }
    }

    private static void list() {
        if (Task.getCount() == 0) {
            System.out.println("You currently have no tasks remaining! Create a task now!");
            return;
        }
        for (int i = 0; i < Task.getCount(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            else {
                System.out.println((i+1) + ". " + tasks.get(i).toString());
            }
        }
    }

    private static void todo(String input) {
        Todo curr = new Todo(input);
        tasks.add(curr);
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    private static void deadline (String input) {
        String arr[] = input.split("/by", 2);
        Deadline curr = new Deadline(arr[0], arr[1]);
        tasks.add(curr);
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    private static void event(String input) {
        String arr[] = input.split("/at", 2);
        Event curr = new Event(arr[0], arr[1]);
        tasks.add(curr);
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    private static void delete (String input) {
        int i = Integer.parseInt(input);
        Task removed = tasks.remove(i - 1);
        Task.reduceCount();
        System.out.println("Noted. I have removed this task:");
        System.out.println(removed);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }
    public static void main(String[] args) {
        read();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            String arr[] = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                write();
                break;
            }
            else if (input.equals("list")) {
                list();
            }

            else if (arr[0].equals("mark")){
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.MarkIndexEmptyException());
                } else {
                    mark(arr);
                }

            }
            else if (arr[0].equals("unmark")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.UnmarkIndexEmptyException());
                } else {
                    unmark(arr);
                }
            }

            else if (arr[0].equals("todo")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.EmptyTaskException());
                } else {
                    todo(arr[1]);
                }

            }

            else if (arr[0].equals("deadline")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.EmptyTaskException());
                } else {
                    deadline(arr[1]);
                }

            }
            else if (arr[0].equals("event")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.EmptyTaskException());
                } else {
                    event(arr[1]);
                }
            }
            else if (arr[0].equals("delete")) {
                if (!inputChecker(arr)) {
                    System.out.println("Index not found in the list!");
                } else {
                    delete(arr[1]);
                }

            }

            else {
                System.out.println("Input not recognised! Please try again! ");
            }
        }

    }
}
