import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {

    public static final String folderPath = "./data";
    public static final String filePath = "./data/duke.txt";

    public void dukeRun() throws DukeException, IOException {
        String line = "_______________________________\n";
        System.out.println(line +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line
        );
        String input = "";
        ArrayList<Task> arr = loadFileData();
        int index = arr.size();
        while (!input.equals("bye")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("bye")) {
                for (int i = 0; i < arr.size(); i++) {
                    Task task = arr.get(i);
                    String toSave = task.saveString();
                    if (i == 0) {
                        writeToFile(filePath, toSave);
                    } else {
                        appendToFile(filePath, toSave);
                    }
                }
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
            } else if (input.startsWith("mark")){
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);
                String text = br.readLine();
                if (input.length() > 5) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    arr.get(taskNum - 1).makeDone();
                    System.out.println(line +
                            "Nice! I've marked this task as done: \n" +
                            arr.get(taskNum - 1).getStatusIcon() +
                            arr.get(taskNum - 1).description + "\n" + line);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as completed!");
                }
            } else if (input.startsWith("unmark")){
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    arr.get(taskNum - 1).makeUndone();
                    System.out.println(line +
                            "OK, I've marked this task as not done yet: \n" +
                            arr.get(taskNum - 1).getStatusIcon() +
                            arr.get(taskNum - 1).description + "\n" + line);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as incomplete!");
                }
            } else if (input.equals("list")) {
                String list = line + "\n" + "Here are the tasks in your list: \n";
                for (int i = 0; i < index; i++) {
                    list += i + 1;
                    list += ". ";
                    list += arr.get(i).toString();
                    list += "\n";
                }
                System.out.println(list + line);
            } else if (input.startsWith("todo")){
                if (input.length() > 5) {
                    Todo todo = new Todo(input.substring(5));
                    arr.add(todo);
                    index++;
                    System.out.println(todo.addString(index));
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (input.startsWith("deadline")){
                if (input.length() > 9) {
                    String[] dead = input.split(" /by ");
                    Deadlines deadlines = new Deadlines(dead[0].substring(9), LocalDate.parse(dead[1]));
                    arr.add(deadlines);
                    index++;
                    System.out.println(deadlines.addString(index));
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (input.startsWith("event")){
                if (input.length() > 6) {
                    String[] time = input.split(" /at ");
                    Event event = new Event(time[0].substring(6), LocalDate.parse(time[1]));
                    arr.add(event);
                    index ++;
                    System.out.println(event.addString(index));
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if (input.startsWith("delete")) {
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    Task toDelete = arr.get(taskNum - 1);
                    arr.remove(taskNum - 1);
                    index --;
                    System.out.println(line + "Noted. I've removed this task: \n" +
                            toDelete.toString() + "\n" +
                            "Now you have " + index + " tasks in the list. \n" + line);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to delete!");
                }
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private ArrayList<Task> loadFileData() {
        ArrayList<Task> arr = new ArrayList<>();
        try {
            File folder = new File(folderPath);
            folder.mkdir();
            File file = new File(filePath);
            if (!file.createNewFile()) {
                Scanner scan = new Scanner(file);
                String input = "";
                while (scan.hasNext()){
                    input = scan.nextLine();
                    if (input.startsWith("T")) {
                        Todo todo = new Todo(input.substring(3));
                        if (input.substring(1).startsWith("X")) {
                            todo.makeDone();
                        }
                        arr.add(todo);
                    } else if (input.startsWith("E")) {
                        String[] time = input.split("/at");
                        Event event = new Event(time[0].substring(3), time[1]);
                        if (input.substring(1).startsWith("X")) {
                            event.makeDone();
                        }
                        arr.add(event);
                    } else if (input.startsWith("D")) {
                        String[] dead = input.split("/by");
                        Deadlines deadlines = new Deadlines(dead[0].substring(3), dead[1]);
                        if (input.substring(1).startsWith("X")) {
                            deadlines.makeDone();
                        }
                        arr.add(deadlines);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static void main(String[] args) {
       Duke duke = new Duke();
       try {
           duke.dukeRun();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
}