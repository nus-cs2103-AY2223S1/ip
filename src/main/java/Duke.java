import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {
    public ArrayList<Task> taskArray;
    public int index;

    public Duke(ArrayList<Task> array, int index) {
        this.taskArray = array;
        this.index = index;
    }

    public String printList() {
        String list = "Here are the tasks in your list:\n";
        int size = this.taskArray.size();
        for (int i = 0; i < size; i++) {
            if (this.taskArray.get(i) != null) {
                list += ((i + 1) + "." + this.taskArray.get(i).toString() + '\n');
            }
        }
        return list;
    }

    public void incrementIndex() {
        this.index += 1;
    }

    public void decrementIndex() {
        this.index -= 1;
    }

    public void addTask(Task task) {
        this.taskArray.add(this.index, task);
    }

    public void markList(int i) throws IOException {
        this.taskArray.get(i - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + this.taskArray.get(i - 1).toString() + '\n');
        writeToFile();

    }

    public void unMarkList(int i) throws IOException {
        this.taskArray.get(i - 1).unMarkTask();
        System.out.println("OK, I've marked this task as not done yet:\n " + this.taskArray.get(i - 1).toString() + '\n');
        writeToFile();
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask(int index) {
        return this.taskArray.get(index);
    }

    public void removeTask(int index) {
        this.taskArray.remove(index);
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
                this.taskArray.add(this.index, new Event(" " + task.substring(9, at - 1), timingWithBracket));
                if (status == 'X') {
                    this.taskArray.get(this.index).markAsDone();
                }
                this.index += 1;
            } else if (type == 'D') {
                int by = task.indexOf("(by:");
                String timingWithBracket = task.substring(by + 5, task.lastIndexOf(")"));
                String description = task.substring(9, by - 1) + " " + "/by" + timingWithBracket;
                this.taskArray.add(this.index, new Deadline(" " + task.substring(9, by - 1),
                        timingWithBracket));
                if (status == 'X') {
                    this.taskArray.get(this.index).markAsDone();
                }
                this.index += 1;
            } else {
                String description = task.substring(9);
                this.taskArray.add(this.index, new Todo(" " + description));
                if (status == 'X') {
                    this.taskArray.get(this.index).markAsDone();
                }
                this.index += 1;
            }
        }
    }

    public static void main(String[] args) {
        Duke dukeBot = new Duke(new ArrayList<>(100), 0);
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
                            dukeBot.addTask(toDoTask);
                            System.out.println("Got it. I've added this task:\n " +
                                    dukeBot.getTask(dukeBot.getIndex()).toString() + "\nNow you have "
                                    + (dukeBot.getIndex() + 1) + " tasks in the list.\n");
                            dukeBot.writeToFile();
                            dukeBot.incrementIndex();
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
                            dukeBot.addTask(deadLineTask);
                            System.out.println("Got it. I've added this task:\n " +
                                    dukeBot.getTask(dukeBot.getIndex()).toString() +
                                    "\nNow you have " + (dukeBot.getIndex() + 1) + " tasks in the list.\n");
                            dukeBot.writeToFile();
                            dukeBot.incrementIndex();
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
                            dukeBot.addTask(eventTask);
                            System.out.println("Got it. I've added this task:\n " +
                                    dukeBot.getTask(dukeBot.getIndex()).toString() + "\nNow you have "
                                    + (dukeBot.getIndex() + 1) + " tasks in the list.\n");
                            dukeBot.writeToFile();
                            dukeBot.incrementIndex();
                        }
                    } else if (input.equals("delete")) {
                        int deleteIndex = sc.nextInt();
                        Task task = dukeBot.getTask(deleteIndex - 1);
                        dukeBot.removeTask(deleteIndex - 1);
                        dukeBot.decrementIndex();
                        dukeBot.writeToFile();
                        System.out.println("Noted. I've removed this task:\n " + task.toString() + "\nNow you have "
                                + dukeBot.getIndex() + " tasks in the list.\n");
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