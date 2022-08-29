import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    private static void loadFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        tasks = new ArrayList<Task>();
        while (sc.hasNextLine()) {

            String taskType = sc.nextLine();
            if (taskType.isBlank()) {
                continue;
            }

            String taskName = sc.nextLine();
            String done = sc.nextLine();
            boolean isDone = Integer.parseInt(done) == 1;

            Task task;
            if (taskType.equals("T")) {
                task = new Todo(taskName);

            } else if (taskType.equals("D")) {
                String deadline = sc.nextLine();
                task = new Deadline(taskName, deadline);
            } else if (taskType.equals("E")){
                String eventTime = sc.nextLine();
                task = new Event(taskName, eventTime);
            } else {
                throw new RuntimeException("Duke cannot understand the input file.");
            }
            if (isDone) {
                task.mark();
            }
            tasks.add(task);
        }
    }

    private static void saveFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            fw.write(task.saveStringToFile());
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {

        Files.createDirectories(Paths.get("data"));
        File file = new File("data/tasks.txt");

        file.createNewFile();
        loadFile(file);


        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String dummyString;
        Task dummyTask;
        int counter;
        int start;
        int end;
        String input = myObj.nextLine();  // Read user input

        while (!input.equals("bye")) {
            try {
                if (input.startsWith("mark")) {
                    dummyString = input.substring(5); //get number of task
                    counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
                    tasks.get(counter).mark();
                    System.out.println("Nice! I've marked this task as done:\n" +
                            "[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getDescription());
                    saveFile(file);

                } else if (input.startsWith("unmark")) {
                    dummyString = input.substring(7); //get number of task
                    counter = Integer.parseInt(dummyString) - 1; //convert to index of task (int)
                    tasks.get(counter).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            "[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getDescription());
                    saveFile(file);

                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) { //iterate through all tasks
                        System.out.println( (i+1) + "." + tasks.get(i).toString());
                    }

                } else if (input.startsWith("delete")) {
                    if (input.equals("delete")) {
                        throw new DukeException("Please specify which item is to be deleted.");
                    }
                    dummyString = input.substring(7); //get item number to be deleted
                    counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
                    dummyTask = tasks.get(counter);
                    tasks.remove(counter);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + dummyTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile(file);

                } else if (input.startsWith("todo")) {
                    if (input.equals("todo")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    start = 5;
                    tasks.add(new Todo(input.substring(start)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile(file);

                } else if (input.startsWith("deadline")) {
                    if (input.equals("deadline")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    counter = input.indexOf("/");
                    start = 9;
                    end = counter - 1;
                    tasks.add(new Deadline(input.substring(start, end), input.substring(counter + 4)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile(file);

                } else if (input.startsWith("event")) {
                    if (input.equals("event")) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    counter = input.indexOf("/");
                    start = 6;
                    end = counter - 1;
                    tasks.add(new Event(input.substring(start, end), input.substring(counter + 4)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile(file);

                } else { //random input
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e){
                    System.out.println(e.toString().substring(15));
            }
            input = myObj.nextLine(); // Read next user input
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
