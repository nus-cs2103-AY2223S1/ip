import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Qoobee {

    private static final List<Task> list = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final File taskList = new File("TaskList.txt");

    public void save(List<Task> lst) {
        try {
            FileWriter fw = new FileWriter(taskList.getPath());
            for (Task task : lst) {
                if (task instanceof ToDo) {
                    fw.write("T | ");
                    if (task.getStatusIcon() == "X") {
                        fw.write("1 | ");
                    } else {
                        fw.write("0 | ");
                    }
                    fw.write(task.getDescription());
                } else if (task instanceof Deadline) {
                    fw.write("D | ");
                    if (task.getStatusIcon() == "X") {
                        fw.write("1 | ");
                    } else {
                        fw.write("0 | ");
                    }
                    fw.write(task.getDescription() + " | " + ((Deadline) task).getDateTime());
                } else if (task instanceof Event) {
                    fw.write("D | ");
                    if (task.getStatusIcon() == "X") {
                        fw.write("1 | ");
                    } else {
                        fw.write("0 | ");
                    }
                    fw.write(task.getDescription() + " | " + ((Event) task).getAt());
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong!!");
        };
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n" + task + "\n" +
                "Now you have " + list.size() + " tasks in the list.");
        save(list);
    }

    public void removeTask(int index) throws QoobeeException {
        try {
            Task task = list.remove(index);
            System.out.println("Noted. I've removed this task:\n" + task + "\n" +
                    "Now you have " + list.size() + " tasks in the list.");
            save(list);
        } catch (IndexOutOfBoundsException e){
            throw new QoobeeException("Please enter a right number!");
        }
    }

    public void unmark(Task task) {
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        save(list);
    }

    public void mark(Task task) {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        save(list);
    }

    public void printTasks() {
        int length = list.size();
        if (length == 0) {
            System.out.println("You have no tasks dummy!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < length; i++) {
                Task currentTask = list.get(i);
                System.out.println((i + 1) + "." + currentTask);
            }
        }
    }

    public void startBot() {
        if (taskList.exists()) {
            loadFile();
        } else {
            run();
        }
    }

    public void loadFile() {
        try {
            Scanner myReader = new Scanner(taskList);
            while (myReader.hasNextLine()) {
                String curr = myReader.nextLine();
                String[] stringArray = curr.split(" \\| ");
                if (stringArray[0].equals("T")) {
                    Task todo = new ToDo(stringArray[2]);
                    if (stringArray[1].equals("1")) {
                        mark(todo);
                    }
                    list.add(todo);
                    save(list);
                } else if (stringArray[0].equals("D")) {
                    String[] dateTime = stringArray[3].split(" ", 4);
                    String[] time = dateTime[3].split(":", 2);
                    Task deadline = new Deadline(stringArray[2],
                            LocalDateTime.of(Integer.parseInt(dateTime[2]), Integer.parseInt(dateTime[1])
                            , Integer.parseInt(dateTime[0]), Integer.parseInt(time[0]),
                                    Integer.parseInt(time[1])));
                    if (stringArray[1].equals("1")) {
                        mark(deadline);
                    }
                    list.add(deadline);
                    save(list);
                } else if (stringArray[0].equals("E")) {
                    Task event = new Event(stringArray[2], stringArray[3]);
                    if (stringArray[1].equals("1")) {
                        mark(event);
                    }
                    list.add(event);
                    save(list);
                }
            }
            run();
        } catch (FileNotFoundException e) {
            System.out.println("I cant find the file!");
        }
    }

    public void run() {
        System.out.println("Hello I'm best bot Qoobee!\n" + "What can I do for you? ^.^");
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Don't miss me too much!");
                    break;
                } else if (input.equals("list")) {
                    printTasks();
                } else {
                    String[] command = input.split(" ", 2);
                    if (input.startsWith("mark")) {
                        try {
                            Task task = list.get(Integer.parseInt(command[1]) - 1);
                            mark(task);
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            throw new QoobeeException("Please enter a right number!");
                        }
                    } else if (input.startsWith("unmark")) {
                        try {
                            Task task = list.get(Integer.parseInt(command[1]) - 1);
                            unmark(task);
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            throw new QoobeeException("Please enter a right number!");
                        }
                    } else if (input.startsWith("todo")) {
                        if (command[1].isBlank()) {
                            throw new QoobeeException("The description of a todo cannot be empty :^(");
                        }
                        Task todo = new ToDo(command[1]);
                        addTask(todo);
                    } else if (input.startsWith("deadline")) {
                        if (command[1].isBlank()) {
                            throw new QoobeeException("The description of a deadline cannot be empty :^(");
                        }
                        if (!command[1].contains("/by")) {
                            throw new QoobeeException("Please use /by to specify a deadline :]");
                        }
                        String[] curr = command[1].split("/by ", 2);
                        Task deadline = new Deadline(curr[0], curr[1]);
                        addTask(deadline);
                    } else if (input.startsWith("event")) {
                        if (command[1].isBlank()) {
                            throw new QoobeeException("The description of a event cannot be empty :^(");
                        }
                        if (!command[1].contains("/at")) {
                            throw new QoobeeException("Please use /at to specify a deadline :]");
                        }
                        String[] curr = command[1].split("/at", 2);
                        Task event = new Event(curr[0], curr[1]);
                        addTask(event);
                    } else if (input.startsWith("delete")) {
                        try {
                            int index = Integer.parseInt(command[1]) - 1;
                            removeTask(index);
                        } catch (NumberFormatException e) {
                            throw new QoobeeException("Please enter a right number!");
                        }
                    } else {
                        throw new QoobeeException("I'm sorry, but I don't know what that means :^(");
                    }
                }
            } catch (QoobeeException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Qoobee qoobee = new Qoobee();
        qoobee.startBot();
    }

}
