import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.valueOf;

public class Duke {
    private static final String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String exit_message = "Bye. Hope to see you again soon!";
    private static final String line = "______________________________________________________________________________";
    private static final String list_message = "Here are the tasks in your list:";
    private static final String add_message = "Got it. I've added this task:";
    private static final String delete_message = "Noted. I've removed this task:";
    private static final ArrayList<Task> ls = new ArrayList<>(100);
    private static final String filePath = "./data/duke.txt";
    private static final String dirPath = "./data";
    private static void addToList(Task task) {
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
    }
    public static void main(String[] args) throws IOException {
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);
        File f = new File(filePath);
        try {
            loadFile(filePath);
        } catch (FileNotFoundException e) {
            File newDirectory = new File(dirPath);
            newDirectory.mkdir();
            f.createNewFile();
        }
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
            String response = sc.nextLine();
            String [] split_slash = response.split("/");
            String [] cmd_descp = split_slash[0].split(" ");
            String task_description = String.join(" ", Arrays.copyOfRange(cmd_descp,1,cmd_descp.length));
            String command = cmd_descp[0];
            System.out.println(line);
            if (command.toLowerCase().equals(exit_command)) {
                flag = false;
                System.out.println(exit_message);
                saveFile();
            } else if (command.toLowerCase().equals(list_command)) {
                display(ls);
            } else if (command.toLowerCase().equals(mark_command)) {
                String ind = cmd_descp[1];
                ls.get(valueOf(ind) - 1).doing();
                saveFile();
            } else if (command.toLowerCase().equals(unmark_command)) {
                String ind = cmd_descp[1];
                ls.get(valueOf(ind) - 1).undo();
                saveFile();
            } else if (command.toLowerCase().equals(delete_command)) {
                String ind = cmd_descp[1];
                delFromList(valueOf(ind)-1);
                saveFile();
            } else if (command.toLowerCase().equals(todo_command)){
                try {
                    String test = cmd_descp[1];
                    addToList(new Todo(task_description));
                    saveFile();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of a todo cannot be empty");
                }
            } else if (command.toLowerCase().equals(deadline_command)){
                try {
                    String test = cmd_descp[1];
                    try {
                        String[] time_part = split_slash[1].split(" ");
                        String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                        try {
                            LocalDate date = LocalDate.parse(time);
                            addToList(new Deadline(task_description, date));
                            saveFile();
                        } catch (DateTimeParseException e) {
                            System.out.println("Deadline takes in a date in yyyy-mm-dd format after /by");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Deadline require a /by");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of a deadline cannot be empty");
                }
            } else if (command.toLowerCase().equals(event_command)){
                try {
                    String test = cmd_descp[1];
                    try {
                        String[] time_part = split_slash[1].split(" ");
                        String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                        try {
                            LocalDate date = LocalDate.parse(time);
                            addToList(new Event(task_description, date));
                            saveFile();
                        } catch (DateTimeParseException e) {
                            System.out.println("Event takes in a date in yyyy-mm-dd format after /at");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Event require a /at");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of a event cannot be empty");
                }
            } else {
                System.out.println("Invalid Command. Try again.");
            }
            System.out.println(line);
        }
    }
}
