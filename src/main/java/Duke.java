import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Duke {

    private final String horizontalLine = "-------------------------";
    private ArrayList<Task> taskList;
    private Scanner sc;
    private static File saveFile = new File("duke.txt");
    
    public Duke() {
        this.taskList = new ArrayList<>();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greeting();
        duke.run();
        duke.goodBye();
    }
    //Solution below adapted from https://github.com/24Donovan24/ip
    
    private void run() {
        if (saveFile.exists()) {
            load();
        } else {
            initialise();
        }
    }

    public void load() {
        try {
            sc = new Scanner(this.saveFile);
            while (sc.hasNextLine()) {
                String taskInFile = sc.nextLine();
                String[] taskInArray = taskInFile.split(" \\| "); //The \\ is really equivalent to a single \ (the first \ is required as a Java escape sequence in string literals).
                // It is then a special character in regular expressions which means "use the next character literally, don't interpret its special meaning"
                String taskType = taskInArray[0];
                switch (taskType) {
                    case "T": {
                        Task task = new ToDo(taskInArray[2]);
                        taskList.add(task);
                        if (taskInArray[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    }

                    case "D": {
                        Task task = new Deadline(taskInArray[2], taskInArray[3]);
                        taskList.add(task);
                        if (taskInArray[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    }

                    case "E": {
                        Task task = new Event(taskInArray[2], taskInArray[3]);
                        taskList.add(task);
                        if (taskInArray[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    }
                    
                }
            }
            initialise();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }
    }
    
    
    private void initialise() {
        sc = new Scanner(System.in);
        String command = sc.next();
        
        while (!command.equals("bye")) {
            try {

                switch (command) {

                    case "list": {
                        showList();
                        break;
                    }

                    case "mark": {
                        int index = sc.nextInt() - 1;
                        markTask(index);
                        save(taskList);
                        break;
                    }

                    case "unmark": {
                        int index = sc.nextInt() - 1;
                        unMarkTask(index);
                        save(taskList);
                        break;
                    }

                    case "todo": {
                        String description = sc.nextLine();
                        setToDo(description);
                        save(taskList);
                        break;
                    }

                    case "deadline": {
                        String input = sc.nextLine();
                        String description = input.substring(0, input.indexOf("/") - 1);
                        String by = input.substring(input.indexOf("/") + 4);
                        setDeadLine(description, by);
                        save(taskList);
                        break;
                    }

                    case "event": {
                        String input = sc.nextLine();
                        String description = input.substring(0, input.indexOf("/") - 1);
                        String at = input.substring(input.indexOf("/") + 4);
                        setEvent(description, at);
                        save(taskList);
                        break;
                    }
                    
                    case "delete": {
                        int index = sc.nextInt() - 1;
                        deleteTask(index);
                        save(taskList);
                        break;
                    }

                    default:
                        sc.nextLine();
                        throw new DukeException("I'm sorry, but I don't know what that means.");
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            }
            command = sc.next();
        }

    }
    
    private void greeting() {
        System.out.println("To all Subjects of Ymir. My name is Eren Yeager.\n" + "How can I help you?" + "\n" + horizontalLine);
    }
    
    private void goodBye() {
        System.out.println(horizontalLine + "\n" + "Keep moving forward until you finish all your tasks. Goodbye." + "\n" + horizontalLine);
    }
    
    private void showList() {
        System.out.println(horizontalLine + "\n" + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println(horizontalLine);
    }
    
    private void markTask(int index) {
        Task completedTask = taskList.get(index);
        completedTask.markAsDone();
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:" + "\n" + completedTask);
        System.out.println(horizontalLine);
    }
    
    private void unMarkTask(int index) {
        Task unfinishedTask = taskList.get(index);
        unfinishedTask.markAsNotDone();
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:" + "\n" + unfinishedTask);
        System.out.println(horizontalLine);
    }
    
    private void setToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of todo cannot be empty");
        }
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:" + "\n" + toDo + "\n" + "Now you have " + taskList.size()
                + " tasks in your list.");
        System.out.println(horizontalLine);
    }
    
    private void setDeadLine(String description, String by) {
        Task deadLine = new Deadline(description, by);
        taskList.add(deadLine);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:" + "\n" + deadLine + "\n" + "Now you have " + taskList.size()
                + " tasks in your list.");
        System.out.println(horizontalLine);
    }
    
    private void setEvent(String description, String at) {
        Task event = new Event(description, at);
        taskList.add(event);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:" + "\n" + event + "\n" + "Now you have " + taskList.size()
                + " tasks in your list.");
        System.out.println(horizontalLine);
    }
    
    private void deleteTask(int index) {
        Task toBeDeleted = taskList.get(index);
        taskList.remove(index);
        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:" + "\n" + toBeDeleted + "\n" + "Now you have " + taskList.size()
                + " tasks in your list.");
        System.out.println(horizontalLine);
    }
    
    public void save(List<Task> list) {
        try {
            FileWriter writer = new FileWriter(saveFile.getPath());
            for (Task task : list) {
                writer.write(task.saveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("I cannot save your tasks in the file.");
        }
    }
    
}
