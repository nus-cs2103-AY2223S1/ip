import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void level7() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");

        ArrayList<Task> lst = readFileIfExists();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.equals("bye")) {
                scanner.close();
                save(lst);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------");
                break;
            }
            else if (line.equals("list")) {
                if (!lst.isEmpty()) {
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println("\t"+ (i+1) + "." + lst.get(i));
                    }
                    System.out.println("\t-----------------------------------------------");
                } else { // lst is not initalized
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tList is empty!");
                    System.out.println("\t-----------------------------------------------");
                }
            }
            else if (line.startsWith("unmark")) {
                if (lst.isEmpty()) {
                    throw new DukeException("OOPS!!! Cannot unmark when list is empty");
                }
                if (line.length() <= 7) {
                    throw new DukeException("OOPS!!! Please enter a number after unmark");
                }
                int index = Integer.parseInt(line.replaceAll("[^0-9]", "")); // regex gotten from https://stackoverflow.com/a/14974126
                lst.get(index - 1).markAsUndone();
                System.out.println("\tNice! I've marked this task as not done yet:");
                System.out.println("\t" + lst.get(index - 1));
            }
            else if (line.startsWith("mark")) {
                if (lst.isEmpty()) {
                    throw new DukeException("OOPS!!! Cannot mark when list is empty");
                }
                if (line.length() <= 5) {
                    throw new DukeException("OOPS!!! Please enter a number after mark");
                }
                int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                lst.get(index - 1).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + lst.get(index - 1));
            }
            else if (line.startsWith("todo")) {
                if (line.length() <= 5) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Todo todo = new Todo(line.replace("todo ", ""));
                lst.add(todo);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + todo);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else if (line.startsWith("deadline")) {
                if (line.length() <= 9) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                int idxOfBy = line.indexOf("/by");
                if (idxOfBy == -1) {
                    throw new DukeException("OOPS!!! The description of a deadline must include /by");
                }
                Deadline deadline = new Deadline(line.substring(9, idxOfBy), line.substring(idxOfBy + 4));
                lst.add(deadline);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + deadline);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else if (line.startsWith("event")) {
                if (line.length() <= 6) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                int idxOfAt = line.indexOf("/at");
                if (idxOfAt == -1) {
                    throw new DukeException("OOPS!!! The description of a event must include /at");
                }
                Event event = new Event(line.substring(6, idxOfAt), line.substring(idxOfAt + 4));
                lst.add(event);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + event);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else if (line.startsWith("delete")) {
                if (line.length() <= 7) {
                    throw new DukeException("OOPS!!! Please enter a number after delete");
                }
                int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                if (index > lst.size() || index < 0) {
                    throw new DukeException("OOPS!!! Invalid number to delete");
                }
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t\t" + lst.get(index - 1));
                lst.remove(index - 1);
                System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------------");
            }
            else if (line.startsWith("save")) {
                System.out.println("Saving progress...");
                save(lst);
                System.out.println("Successfully saved!");
            }
            else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know that that means :(");
            }
        }
    }

    private static ArrayList<Task> readFileIfExists() {
        File folder = new File("./data");
        File saveFile = new File("./data/saveFile.txt");
        ArrayList<Task> arrayList = new ArrayList<>();
        if (!folder.exists()) { // folder does not exist, hence save also does not exist
            System.out.println("No data folder detected. Making data directory and save file...");
            try {
                folder.mkdirs();
                saveFile.createNewFile();
                System.out.println("Success!");
                return arrayList;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // folder exists, check if save exists
            if (saveFile.exists()) {
                System.out.println("Retrieving save file...");
                System.out.println("Success!");
                return convertSaveToArr(saveFile);

            } else {
                try {
                    System.out.println("No save file detected, creating save file...");
                    saveFile.createNewFile();
                    System.out.println("Success!");
                    return arrayList;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    private static ArrayList<Task> convertSaveToArr(File file) {
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.startsWith("[T]")) { // TodoClass
                    result.add(Todo.stringToTodo(line));
                } else if (line.startsWith("[D]")) { // Deadline Class
                    result.add(Deadline.stringToDeadline(line));
                } else if (line.startsWith("[E]")) { // Evemt Class
                    result.add(Event.stringToEvent(line));
                } else { // unknown text
                    result.add(new Task(line));
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
        return result;
    }

    // at this point readFileIfExists() should have ran hence the folder and saveFile should exist.
    private static void save(ArrayList<Task> lst) {
        try {
            FileWriter fw = new FileWriter("./data/saveFile.txt");
            for (Task task: lst) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _\n"
//            + "|  _ \\ _   _| | _____\n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        try {
            level7();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
