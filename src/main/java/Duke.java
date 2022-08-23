import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {
    static ArrayList<Task> taskArray = new ArrayList<>(100);
    static int index = 0;

    public String printList() {
        String list = "Here are the tasks in your list:\n";
        int size = taskArray.size();
        for (int i = 0; i < size; i++) {
            if (taskArray.get(i) != null) {
                list += ((i + 1) + "." + taskArray.get(i).toString() + '\n');
            }
        }
        return list;
    }

    public void markList(int i) throws IOException {
        taskArray.get(i - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + taskArray.get(i - 1).toString() + '\n');
        writeToFile();

    }

    public void unMarkList(int i) throws IOException {
        taskArray.get(i - 1).unMarkTask();
        System.out.println("OK, I've marked this task as not done yet:\n " + taskArray.get(i - 1).toString() + '\n');
        writeToFile();
    }

    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("taskList.txt", false);
        fw.write(printList());
        fw.close();
    }

    public void loadUpData(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String header = sc.nextLine();
        while(sc.hasNextLine()) {
            String task = sc.nextLine();
            char type = task.charAt(3);
            char status = task.charAt(6);
            if (type == 'E') {
                int at = task.indexOf("(at:");
                String timingWithBracket = task.substring(at + 5, task.lastIndexOf(")"));
                String description = task.substring(9, at - 1) + " " + "/at" + timingWithBracket;
                taskArray.add(index, new Event(" " + task.substring(9, at - 1), timingWithBracket));
                if (status == 'X') {
                    taskArray.get(index).markAsDone();
                }
                index += 1;
            } else if (type == 'D') {
                int by = task.indexOf("(by:");
                String timingWithBracket = task.substring(by + 5, task.lastIndexOf(")"));
                String description = task.substring(9, by - 1) + " " + "/by" + timingWithBracket;
                taskArray.add(index, new Deadline("" + task.substring(9, by - 1), timingWithBracket));
                if (status == 'X') {
                    taskArray.get(index).markAsDone();
                }
                index += 1;
            } else {
                String description = task.substring(9);
                taskArray.add(index, new Todo(" " + description));
                if (status == 'X') {
                    taskArray.get(index).markAsDone();
                }
                index += 1;
            }
        }
    }

    public static void main(String[] args) {
        Duke dukeBot = new Duke();
        try {
            File taskList = new File("taskList.txt");
            if(!Files.exists(Path.of("taskList.txt"))) {
                taskList.createNewFile();
            }
            dukeBot.loadUpData(taskList);
            String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
            System.out.println(welcome);
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                try {
                    String input = sc.next();
                    if (input.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (input.equals("list")) {
                        System.out.println(dukeBot.printList());
                    } else if (input.equals("mark")) {
                        int number = Integer.parseInt(sc.next());
                        dukeBot.markList(number);
                    } else if (input.equals("unmark")) {
                        int number = Integer.parseInt(sc.next());
                        dukeBot.unMarkList(number);
                    } else if (input.equals("todo")) {
                        String toDo = sc.nextLine();
                        if (toDo.equals("")) {
                            throw new DukeException("☹ OOPS!!! " + "The description of a todo cannot be empty.\n");
                        } else {
                            Todo toDoTask = new Todo(toDo);
                            taskArray.add(index, toDoTask);
                            System.out.println("Got it. I've added this task:\n " + taskArray.get(index).toString() +
                                    "\nNow you have " + (index + 1) + " tasks in the list.\n");
                            dukeBot.writeToFile();
                            index += 1;
                        }
                    } else if (input.equals("deadline")) {
                        String deadlineTask = sc.nextLine();
                        if (deadlineTask.equals("")) {
                            throw new DukeException("☹ OOPS!!! " +
                                    "The description of a deadline cannot be empty.\n");
                        } else {
                            int integer = deadlineTask.indexOf("/by");
                            String description = deadlineTask.substring(0, integer - 1);
                            String by = deadlineTask.substring(integer + 4);
                            Deadline deadLineTask = new Deadline(description, by);
                            taskArray.add(index, deadLineTask);
                            System.out.println("Got it. I've added this task:\n " + taskArray.get(index).toString() +
                                    "\nNow you have " + (index + 1) + " tasks in the list.\n");
                            dukeBot.writeToFile();
                            index += 1;
                        }
                    } else if (input.equals("event")) {
                        String event = sc.nextLine();
                        if (event.equals("")) {
                            throw new DukeException("☹ OOPS!!! " +
                                    "The description of a event cannot be empty.\n");

                        } else {
                            int integer = event.indexOf("/at");
                            String description = event.substring(0, integer - 1);
                            String at = event.substring(integer + 4);
                            Event eventTask = new Event(description, at);
                            taskArray.add(index, eventTask);
                            System.out.println("Got it. I've added this task:\n " + taskArray.get(index).toString() +
                                    "\nNow you have " + (index + 1) + " tasks in the list.\n");
                            dukeBot.writeToFile();
                            index += 1;
                        }
                    } else if (input.equals("delete")) {
                        int deleteIndex = sc.nextInt();
                        Task task = taskArray.get(deleteIndex - 1);
                        taskArray.remove(deleteIndex - 1);
                        index -= 1;
                        dukeBot.writeToFile();
                        System.out.println("Noted. I've removed this task:\n " + task.toString() + "\nNow you have "
                                + index + " tasks in the list.\n");
                    } else {
                        input += sc.nextLine();
                        throw new DukeException("☹ OOPS!!! I'm sorry, " +
                                "but I don't know what that means :-(\n");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
       } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}