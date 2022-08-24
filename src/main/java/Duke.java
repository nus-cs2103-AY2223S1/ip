import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private String input;
    private Scanner sc = new Scanner(System.in);
    private List<Task> tasks = new ArrayList<>();


    private void dukeReply(String message) {
        String lineBreak = "-------------------------------------------------";
        String reply = String.format("%s\n%s\n%s", lineBreak, message, lineBreak);
        System.out.println(reply);
    }

    private void greet() {
        dukeReply("Hello! I'm Ee Suan!\nWhat can I do for you?");
    }

    private void loadSaveData() throws DukeException, IOException {

        File savedFile = new File("src/main/duke.txt");
        if (!savedFile.exists()) {
            savedFile.createNewFile();
        } else {
            Scanner scan = new Scanner(savedFile);
            while (scan.hasNextLine()) {
                String taskString = scan.nextLine();

                // split and organise task data
                String[] taskSplit = taskString.split(" \\| ");
                String taskType = taskSplit[0];
                boolean isTaskDone = taskSplit[1].equals("1");
                String taskDescription = taskSplit[2];

                Task task;
                switch (taskType) {
                case "T": {
                    task = new ToDo(taskDescription);
                    break;
                }
                case "D": {
                    String taskDate = taskSplit[3];
                    task = new Deadline(taskDescription, taskDate);
                    break;
                }
                case "E": {
                    String taskDate = taskSplit[3];
                    task = new Event(taskDescription, taskDate);
                    break;
                }
                default:
                    throw new DukeException("Task type is not supported!");
            }
                if (isTaskDone) {
                    task.markAsDone();
                }
                this.tasks.add(task);
            }
        }
    }

    private void start() {
        this.greet();
        input = sc.next();
        while (!input.equals("bye")) {
            try {
                switch (input) {
                    case "list":
                        printList();
                        break;
                    case "mark": {
                        int index = sc.nextInt();
                        mark(index);
                        save();
                        break;
                    }
                    case "unmark": {
                        int index = sc.nextInt();
                        unmark(index);
                        save();
                        break;
                    }
                    case "todo": {
                        String next = sc.nextLine();
                            input += next;
                            Task todo = createToDo();
                            addTask(todo);
                            echoTask(todo);
                            save();
                            break;
                    }
                    case "deadline": {
                        input += sc.nextLine();
                        Task deadline = createDeadline();
                        addTask(deadline);
                        echoTask(deadline);
                        save();
                        break;
                    }
                    case "event": {
                        input += sc.nextLine();
                        Task event = createEvent();
                        addTask(event);
                        echoTask(event);
                        save();
                        break;
                    }
                    case "delete": {
                        int index = sc.nextInt();
                        delete(index);
                        save();
                        break;
                    }
                    default:
                        input += sc.nextLine();
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                dukeReply(e.getMessage());
            }
            input = sc.next();
        }
}

    private void save() {
        try {
            File file = new File("src/main/duke.txt");
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.saveTaskAsString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void exit() {
        dukeReply("Bye. Hope to see you again soon!");
    }

    private void echoTask(Task task) {
        int size = tasks.size();
        dukeReply("Got it. I've added this task: \n  " + task + "\nNow you have " + size + " task(s) in the list.");
    }

    private void printList() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task curTask = tasks.get(i);
            message +="\n" + (i + 1) + ". " + curTask;
        }
        dukeReply(message);
    }

    private void mark(int i) throws DukeException {
        int size = tasks.size();
        if (size == 0) {
            throw new DukeException("☹ OOPS!!! You do not have any task right now.");
        }
        if (i <= 0 || i > size) {
            throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
        } else {
            Task curTask = tasks.get(i - 1);
            curTask.markAsDone();
            dukeReply("Nice! I've marked this task as done: \n  " + curTask);
        }
    }

    private void unmark(int i) throws DukeException {
        int size = tasks.size();
        if (size == 0) {
            throw new DukeException("☹ OOPS!!! You do not have any task right now.");
        }
        if (i <= 0 || i > size) {
            throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
        } else {
            Task curTask = tasks.get(i - 1);
            curTask.markAsUndone();
            dukeReply("OK, I've marked this task as not done yet: \n  " + curTask);
        }
    }

    private Task createToDo() throws DukeException {
        try {
            String description = input.substring(5);
            return new ToDo(description);
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private Task createDeadline() throws DukeException {
        int end = input.indexOf('/');
        String description = input.substring(9, end - 1);
        String by = input.substring(end + 4);
        return new Deadline(description, by);
    }

    private Task createEvent() throws DukeException {
        int end = input.indexOf('/');
        String description = input.substring(6, end - 1);
        String at = input.substring(end + 4);
        return new Event(description, at);
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    private void delete(int i) throws DukeException {
        int size = tasks.size();
        if (size == 0) {
            throw new DukeException("☹ OOPS!!! You do not have any task right now.");
        }
        if (i <= 0 || i > size) {
            throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
        } else {
            Task taskToDelete = tasks.remove(i - 1);
            dukeReply("Noted. I've removed this task:\n  " + taskToDelete +"\nNow you have " + tasks.size() + " task(s) in the list." );
        }
    }

    public static void main(String[] args) {

        try {
            Duke duke = new Duke();
            duke.loadSaveData();
            duke.start();
            duke.exit();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
