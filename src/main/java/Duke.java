import java.util.Scanner;
import java.util.Arrays;
import java.time.LocalDate;

import static java.lang.Integer.valueOf;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private static final String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String exit_message = "Bye. Hope to see you again soon!";
    private static final String line = "______________________________________________________________________________";
    private static final String filePath = "./data/duke.txt";
    /*private static void addToList(Task task) {
        ls.add(task);
        System.out.println(add_message);
        System.out.println(task.toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }
    private static void delFromList(int ind) {
        Task deleting_task = ls.get(ind);
        ls.remove(ind);
        System.out.println(delete_message);
        System.out.println(deleting_task.toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }
    private static void display(ArrayList<Task> ls) {
        System.out.println(list_message);
        for (int i = 1; i <= ls.size(); i++) {
            System.out.println(i + ". " + ls.get(i - 1).toString());
        }
    }
    private static Task stringToTask(String input) {
        String [] taskDetails = input.split(",");
        String taskDescription = taskDetails[2];
        boolean completed;
        if (taskDetails[1].equals("1")) {
            completed = true;
        } else {
            completed = false;
        }
        if (taskDetails.length == 4) {
            String time = taskDetails[3];
            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (taskDetails[0].equals("D")) {
                return new Deadline(taskDescription, completed, date);
            } else {
                return new Event(taskDescription, completed, date);
            }
        }
        return new Todo(taskDescription, completed);
    }
    private static String taskToString(Task task) {
        String taskDescription = task.description();
        String completed = (task.status()) ? "1" : "0";
        String type = task.toString().substring(1,2);
        String [] splitTime = task.toString().split(":");
        if (splitTime.length == 2) {
            String time = splitTime[1];
            return String.join(",", type, completed, taskDescription, time.substring(1,time.length()-1));
        }
        return String.join(",", type, completed, taskDescription);
    }
    private static void writeToFile(String filePath, String data) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }
    private static void loadFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            ls.add(stringToTask(input));
        }
    }
    private static void saveFile() throws IOException {
        String data = "";
        for (int i = 0; i < ls.size(); i++) {
            data = data + taskToString(ls.get(i));
            if (i !=  ls.size()-1) {
                data = data + System.lineSeparator();
            }
        }
        writeToFile(filePath, data);
    }*/
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.createFile();
            } catch (DukeException ex) {
                System.err.println(ex);
            }
        }
    }
    public void run() {
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String exit_command = "bye";
        String list_command = "list";
        String mark_command = "mark";
        String unmark_command = "unmark";
        String todo_command = "todo";
        String deadline_command = "deadline";
        String event_command = "event";
        String delete_command = "delete";
        boolean flag = true;
        while (flag) {
            try {
                String response = sc.nextLine();
                String[] split_slash = response.split("/");
                String[] cmd_descp = split_slash[0].split(" ");
                String task_description = String.join(" ", Arrays.copyOfRange(cmd_descp, 1, cmd_descp.length));
                String command = cmd_descp[0];
                System.out.println(line);
                if (command.toLowerCase().equals(exit_command)) {
                    flag = false;
                    System.out.println(exit_message);
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(list_command)) {
                    tasks.display();
                } else if (command.toLowerCase().equals(mark_command)) {
                    String ind = cmd_descp[1];
                    tasks.get(valueOf(ind) - 1).doing();
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(unmark_command)) {
                    String ind = cmd_descp[1];
                    tasks.get(valueOf(ind) - 1).undo();
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(delete_command)) {
                    String ind = cmd_descp[1];
                    tasks.del(valueOf(ind) - 1);//add print lines
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(todo_command)) {
                    tasks.add(new Todo(task_description));
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(deadline_command)) {
                    String[] time_part = split_slash[1].split(" ");
                    String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                    LocalDate date = LocalDate.parse(time);
                    tasks.add(new Deadline(task_description, date));
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(event_command)) {
                    String[] time_part = split_slash[1].split(" ");
                    String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                    LocalDate date = LocalDate.parse(time);
                    tasks.add(new Event(task_description, date));
                    storage.save(tasks.toString());
                } else {
                    System.out.println("Invalid Command. Try again.");
                }
                System.out.println(line);
            } catch (DukeException e) {
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
