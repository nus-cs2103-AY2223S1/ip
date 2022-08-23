import duke.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CmdHandler {
    ArrayList<Task> tasks = new ArrayList<>();
    boolean done = false;


    private void handleList() {
        String out = "";
        for (int i = 1; i <= tasks.size(); i++) {
            out += i + "." + tasks.get(i - 1) + "\n";
        }
        System.out.println(out.trim());
    }


    private void handleBye() {
        System.out.println("Bye");
        done = true;
    }

    private void addTask(String desc) throws DukeException {
        if (desc.startsWith("deadline")) {
            String[] parsed = desc.substring(9).split("/by ");
            tasks.add(new Deadline(parsed[0], parsed[1]));
            System.out.println("task added" + tasks.get(tasks.size() - 1));
        } else if (desc.startsWith("event")) {
            String[] parsed = desc.substring(6).split("/at ");
            tasks.add(new Event(parsed[0], parsed[1]));
            System.out.println("task added" + tasks.get(tasks.size() - 1));
        } else if (desc.startsWith("todo")) {
            if (desc.substring(5).length() == 0) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(desc.substring(5)));
            System.out.println("task added" + tasks.get(tasks.size() - 1));
        } else {
            throw new DukeException("No such command");
        }
    }

    /**
     * @param i 0 based index of task in ArrayList tasks
     */
    private void handleMark(int i) {
        tasks.get(i).mark();
        System.out.println("marked as done: " + tasks.get(i));
    }

    private void handleUnMark(int i) {
        tasks.get(i).unMark();
        System.out.println("marked as undone: " + tasks.get(i));
    }

    private void handleDelete(int i) {
        System.out.println("task deleted: " + tasks.remove(i));
    }

//    private void loadTasks() throws IOException {
//        try {
//            tasks = new ArrayList<>();
//            Scanner in = new Scanner(new FileReader("data//tasks.txt"));
//            while (in.hasNextLine()) {
//                String line = in.nextLine();
//                String[] data = line.split(" \\| ");
//                duke.Task task = switch (data[0]) {
//                    case "T" -> new duke.Todo(data[2]);
//                    case "D" -> new duke.Deadline(data[2], data[3]);
//                    case "E" -> new duke.Event(data[2], data[3]);
//                    default -> throw new IllegalStateException("Unexpected value: " + data[0]);
//                };
//                tasks.add(task);
//            }
//        } catch (FileNotFoundException e) {
//            (new File("data//tasks.txt")).createNewFile();
//        }
//    }

    private void saveTasks(){
        try {
            File file = new File("data//tasks.txt");

            PrintWriter writer = new PrintWriter(file);
            String toAdd = tasks.stream().map(Task::toFileString)
                    .collect(Collectors.joining("\n"));
            writer.write(toAdd);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void handle() throws IOException {
        Scanner sc = new Scanner(System.in);
//        loadTasks();
        while (!done) {
            try {
                String inputStr = sc.nextLine();
                String[] inputArr = inputStr.split(" ");
                if (inputArr[0].equals("list")) {
                    handleList();
                } else if (inputArr[0].equals("bye")) {
                    handleBye();
                } else if (inputArr[0].equals("unmark")) {
                    handleUnMark(Integer.parseInt(inputArr[inputArr.length - 1]) - 1);
                } else if (inputArr[0].equals("mark")) {
                    handleMark(Integer.parseInt(inputArr[inputArr.length - 1]) - 1);
                } else if (inputStr.startsWith("delete")) {
                    handleDelete(Integer.parseInt(inputArr[1]) - 1);
                } else {
                    addTask(inputStr);
                }
                System.out.println();
                saveTasks();
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
    }
}
