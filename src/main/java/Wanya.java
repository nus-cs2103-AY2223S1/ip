import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.time.DateTimeException;

public class Wanya {
    private static List<Task> taskList = new ArrayList<>();
    private static int count = 0;
    private static final File FILE = new File("tasks.txt");

    public void addToDo(String taskName)  {
        Task newTask = new ToDo(taskName);
        addTask(newTask);
    }

    public void addDeadline(String commandInput) throws WanyaException, DateTimeException {
        String[] inputs = commandInput.split("/by");
        //no deadline provided
        if (inputs.length == 1) {
            throw new WanyaException("Deadline must have a due date\n" +
                    "Include '/by' and date with format " +
                    "\"yyyy-mm-dd\" at the back");
        }
        String taskName = inputs[0];
        String dueDate = inputs[1].trim();
        Task newTask = new Deadline(taskName, dueDate);
        addTask(newTask);
    }

    public void addEvent(String commandInput) throws WanyaException, DateTimeException {
        String[] inputs = commandInput.split("/at");
        //no date provided
        if (inputs.length == 1) {
            throw new WanyaException("Event must have a date\n" +
                    "Include '/at' and date with format " +
                    "\"yyyy-mm-dd\" at the back");
        }
        String taskName = inputs[0];
        String date = inputs[1].trim();
        Task newTask = new Event(taskName, date);
        addTask(newTask);
    }

    public void addTask(Task task) {
        count++;
        taskList.add(task);
        System.out.println("You have added: \n" + task);
        System.out.println("Now you have " + count + " tasks in your list. \n");
        writeToFile();
    }

    public void deleteTask(int index) {
        Task task = taskList.get(index - 1);
        count--;
        taskList.remove(index - 1);
        System.out.println("WWaku WWaku!!! Wanya has used her magic powers to remove this task:\n" + task);
        System.out.println("Now you have " + count + " tasks in your list. \n");
        writeToFile();
    }

    public void checkTask(String[] inputs, String command) throws WanyaException {
        //handle the error where no task name
        if (inputs.length == 1) {
            throw new WanyaException("The description of a " + command + " cannot be empty");
        }
    }

    public int checkTaskNumber(String[] inputs) throws WanyaException {
        //handle error without task number
        if (inputs.length == 1) {
            throw new WanyaException("You didn't put the task number at the back :(.\n" +
                    "Wanya isn't Anya. I can't read your mind!");
        }
        try {
            int index = Integer.parseInt(inputs[1]);
            if (index > count || index < 1) {
                throw new WanyaException("Invalid task number!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WanyaException("The task number got to be an integer!");
        }
    }

    public void showTasks() {
        if (count == 0) {
            System.out.println("List is empty! Wheee slack time!");
        }
        for (int i = 1; i <= count; i++) {
            Task task = taskList.get(i - 1);
            System.out.println(i + "." + task);
        }
        System.out.println("");
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < count; i++) {
                Task task = taskList.get(i);
                fw.write(task.toStorageString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! Problem encountered! Wanya cannot save the task to hard disk.");
        }
    }

    public static void loadFromFile() {
        try {
            Scanner sc = new Scanner(FILE);
            taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                String[] inputs = currLine.split("\\|");
                String taskType = inputs[0];
                boolean completed = inputs[1].equals("1");
                String description = inputs[2];
                switch(taskType) {
                case "T":
                    ToDo toDo = new ToDo(description, completed);
                    taskList.add(toDo);
                    break;
                case "D":
                    String dueDate = inputs[3];
                    Deadline deadline = new Deadline(description, completed, dueDate);
                    taskList.add(deadline);
                    break;
                case "E":
                    String date = inputs[3];
                    Event event = new Event(description, completed, date);
                    taskList.add(event);
                    break;
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops! File not found hehe maybe a spy stole the file awayyy.\n" +
                    "Wanya shall create a new list for you\n");
        }
    }

    public static void exit() {
        String closeMsg = "Yayyy Wanya gets to slack and watch shows now. Bye bye! :)";
        System.out.println(closeMsg);
    }

    public static void main(String[] args) {
        Wanya wanya = new Wanya();
        loadFromFile();
        String startMsg = "Hello!!! My name is Wanya! \nWWaku WWaku! \nHow can I help you? \n";

        Scanner sc = new Scanner(System.in);
        System.out.println(startMsg);

        while (sc.hasNext()) {
            try {
                String commandInput = sc.nextLine();
                String[] inputs = commandInput.split(" ", 2);
                String command = inputs[0];

                if (commandInput.equals("bye")) {
                    exit();
                    break;
                } else if (commandInput.equals("list")) {
                    wanya.showTasks();
                } else if (command.equals("mark")) {
                    int indexToMark = wanya.checkTaskNumber(inputs);
                    wanya.taskList.get(indexToMark - 1).completedTask();
                    writeToFile();
                } else if (command.equals("unmark")) {
                    int indexToUnmark = wanya.checkTaskNumber(inputs);
                    wanya.taskList.get(indexToUnmark - 1).uncompletedTask();
                    writeToFile();
                } else if (command.equals("todo")) {
                    wanya.checkTask(inputs, command);
                    wanya.addToDo(inputs[1]);
                } else if (command.equals("deadline")) {
                    wanya.checkTask(inputs, command);
                    try {
                        wanya.addDeadline(inputs[1]);
                    } catch (DateTimeException e) {
                        System.out.println("Please enter a valid date behind /by with the format " +
                                "\"yyyy-mm-dd HH:mm\" where time is optional.\n " +
                                "If time is provided, leave it in 24 hours format.\n");
                    }
                } else if (command.equals("event")) {
                    wanya.checkTask(inputs, command);
                    try {
                        wanya.addEvent(inputs[1]);
                    } catch (DateTimeException e) {
                        System.out.println("Please enter a valid date behind /at with the format " +
                                "\"yyyy-mm-dd HH:mm\" where time is optional.\n " +
                                "If time is provided, leave it in 24 hours format.\n");
                    }
                } else if (command.equals("delete")) {
                    int index = wanya.checkTaskNumber(inputs);
                    wanya.deleteTask(index);
                } else {
                    throw new WanyaException("I am sorry. Wanya doesn't like to study " +
                            "so Wanya don't know what that means.");
                }
            } catch (WanyaException e) {
                System.out.println(e.getMessage() + '\n');
            }
        }
    }
}

