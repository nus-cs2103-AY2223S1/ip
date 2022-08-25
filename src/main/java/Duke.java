import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! I'm Duke\nWhat can I do for you?\n";

    private static ArrayList<Task> taskArr = new ArrayList<>();

    private static void echo(String str) {
        System.out.println("Got it. I've added this task:\n" + str);
    }

    private static void printArrAsNumberedList(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i+1 + ". " + arr.get(i).toString());
        }
    }

    private static void loadFileData() throws DukeException {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("##");
                Task task;
                if ("D".equals(split[0])) {
                    LocalDate localDate = LocalDate.parse(split[3]);
                    task = new Deadline(split[2], localDate);
                } else if ("E".equals(split[0])) {
                    LocalDate localDate = LocalDate.parse(split[3]);
                    task = new Event(split[2], localDate);
                } else if ("T".equals(split[0])) {
                    task = new ToDo(split[2]);
                } else {
                    throw new DukeException("Unable to parse items in file.");
                }
                if (split[1].equals("Y")) {
                    task.toggleDone();
                }
                taskArr.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("data/"));
                File file = new File("data/duke.txt");
                System.out.println("New file created to store tasks.");
            } catch (IOException ex) {
                throw new DukeException(ex.getMessage());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse dates in file.");
        }
    }

    private static void saveFileData() throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : taskArr) {
                String str = task.stringify();
                fw.write(str + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.println(GREETINGS);

        try {
            loadFileData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    printArrAsNumberedList(taskArr);
                } else if (input.startsWith("mark")) {
                    taskArr.get(Integer.parseInt(input.substring(5)) - 1).mark();
                } else if (input.startsWith("unmark")) {
                    taskArr.get(Integer.parseInt(input.substring(7)) - 1).unmark();
                } else {
                    // this is under task creation
                    if (input.startsWith("event")) {
                        String[] inputArr = input.split("/");
                        taskArr.add(new Event(inputArr[0], LocalDate.parse(inputArr[1].substring(3))));
                        echo(taskArr.get(taskArr.size() - 1).toString());
                    } else if (input.startsWith("todo")) {

                        if (input.substring(4).equals("")){
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            taskArr.add(new ToDo(input));
                            echo(taskArr.get(taskArr.size() - 1).toString());
                        }
                    } else if (input.startsWith("deadline")) {
                        String[] inputArr = input.split("/");
                        taskArr.add(new Deadline(inputArr[0], LocalDate.parse(inputArr[1].substring(3))));
                        echo(taskArr.get(taskArr.size() - 1).toString());
                    } else if (input.startsWith("delete")) {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
                                taskArr.get(index).toString(), (taskArr.size() - 1));
                        taskArr.remove(index);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
//                    echo(taskArr.get(taskArr.size() - 1).toString());

                }
                saveFileData();

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");

    }

}
