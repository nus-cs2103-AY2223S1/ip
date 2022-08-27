import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Bob {

    ArrayList<Task> tasks = new ArrayList<>(100);
    int taskCount = 0;

    public void save() {
        String actualFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "lists" + System.getProperty("file.separator") + "tasklist.txt";
        File actualFileLocation = new File(actualFilePath);
        try {
            FileWriter fileWriter = new FileWriter(actualFileLocation);
            String list = "";
            for (Task task : tasks) {
                list = list + task.toSave() + "\n";
            }
            fileWriter.write(list);
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("error! not able to save file :(");
        }
    }

    public void read() {
        String directoryPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "lists";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
            File taskList = new File(directoryPath, "tasklist.txt");
            try {
                FileWriter fileWriter = new FileWriter(taskList);
                fileWriter.close();
            } catch (java.io.IOException e) {
                System.out.println("error! not able to create file!");
            }
        } else {
            String actualFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "lists" + System.getProperty("file.separator") + "tasklist.txt";
            File actualFileLocation = new File(actualFilePath);
            if (!actualFileLocation.exists()) {
                File taskList = new File(directoryPath, "tasklist.txt");
                try {
                    FileWriter fileWriter = new FileWriter(taskList);
                    fileWriter.close();
                } catch (java.io.IOException e) {
                    System.out.println("error! not able to create file!");
                }
            } else {
                try {
                    Scanner reader = new Scanner(actualFileLocation);
                    while (reader.hasNextLine()) {
                        String task = reader.nextLine();
                        String[] temp = task.split("\\|");
                        String taskLabel = temp[0].substring(0,1);
                        if (taskLabel.equals("T")) {
                            String todoTaskName = temp[2].substring(1);
                            ToDo todo = new ToDo(todoTaskName);
                            if (temp[1].substring(1, 2).equals("1")) {
                                todo.toMark(true);
                            }
                            tasks.add(todo);
                            taskCount = taskCount + 1;
                        } else if (taskLabel.equals("D")) {
                            String deadlineTaskName = temp[2].substring(1);
                            String[] deadlineDetails = temp[3].substring(1).split(" ");
                            String deadlineDate = deadlineDetails[0];
                            Deadline deadline = new Deadline(deadlineTaskName, LocalDate.parse(deadlineDate));
                            if (temp[1].substring(1,2).equals("1")) {
                                deadline.toMark(true);
                            }
                            tasks.add(deadline);
                            taskCount = taskCount + 1;
                        } else if (taskLabel.equals("E")){
                            String eventTaskName = temp[2].substring(1);
                            String[] eventDetails = temp[3].substring(1).split(" ");
                            String eventDate = eventDetails[0];
                            Event event = new Event(eventTaskName, LocalDate.parse(eventDate));
                            if (temp[1].substring(1,2).equals("1")) {
                                event.toMark(true);
                            }
                            tasks.add(event);
                            taskCount = taskCount + 1;
                        }
                    }
                } catch (java.io.FileNotFoundException e) {
                    System.out.println("error! can't find file!");
                }
            }
        }
    }

    public void dateFilter(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        String list = "";
        for (Task task : tasks) {
            if (task instanceof Event && ((Event) task).at.equals(date)) {
                list = list + task.toString() + "\n";
            } else if (task instanceof Deadline && ((Deadline) task).by.equals(date)) {
                list = list + task.toString() + "\n";
            }
        }
        System.out.print("here are your tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "\n" + list);
    }

    public void activate() {
        read();
        Scanner scanner = new Scanner(System.in);
        System.out.println("hey, i'm bob!\ndo you need help?");
        System.out.println("here's what you can do!\n" +
                "    ❤️    ADD A TODO TASK: todo <task>\n" +
                "    🌸    ADD A DEADLINE: deadline <task> /by <deadline>\n" +
                "    ✨    ADD AN EVENT: event <task /at <date and time>\n" +
                "    💕    VIEW LIST OF EVENTS: list\n" +
                "    🌼    MARK AS DONE: mark <task number in list>\n" +
                "    ❣️    UNMARK TASK: unmark <task number in list>\n" +
                "    🌟    REMOVE TASK: remove <task number in list>\n" +
                "    💞    TO END THE PROGRAM: bye\n" +
                "hope this helps!");
        String reply = scanner.nextLine();

        while (!reply.equalsIgnoreCase("bye")) {
            if (reply.equalsIgnoreCase("list")) {
                int index = 1;
                String list = "";
                for (Task task : tasks) {
                    list = list + "\n" + (index++) + ". " + task.toString();
                }
                System.out.println("here are all your tasks!" + list);
            } else if (reply.toLowerCase().matches("mark(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.toMark(true);
                    save();
                    System.out.println("yay! you've completed a task!\n" + task.toString());
                } catch (NumberFormatException e) {
                    System.out.println("which task to mark?");
                }

            } else if (reply.toLowerCase().matches("unmark(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.toMark(false);
                    save();
                    System.out.println("aw...i guess there's another task.\n" + task.toString());
                } catch (NumberFormatException e) {
                    System.out.println("which task to unmark?");
                }
            } else if (reply.toLowerCase().matches("remove(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    ArrayList<Task> temp = new ArrayList<>(taskCount);
                    Task removedTask = tasks.get(index - 1);
                    for (int i = 0; i < taskCount; i++) {
                        if (i == (index - 1)) {
                            continue;
                        } else {
                            temp.add(tasks.get(i));
                        }
                    }
                    tasks = temp;
                    taskCount = taskCount - 1;
                    save();
                    System.out.println("that's one less task for you! removed:" + "\n  " + removedTask.toString() + "\njust " + taskCount + " tasks left!");
                } catch (NumberFormatException e) {
                    System.out.println("which task do you want to delete?");
                }
            } else if (reply.toLowerCase().matches("filter(.*)")) {
                try {
                    String[] temp = reply.split(" ");
                    dateFilter(temp[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("what date do you want to filter?");
                }
            } else {
                if (reply.toLowerCase().matches("todo(.*)")) {
                    try {
                        String taskName = reply.substring(5);
                        ToDo newTask = new ToDo(taskName);
                        tasks.add(newTask);
                        taskCount = taskCount + 1;
                        save();
                        System.out.println("okay! new task:" + "\n  " + newTask.toString() + "\njust " + taskCount + " tasks left!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops! What do you want to do?");
                    }
                } else if (reply.toLowerCase().matches("deadline(.*)")) {
                    try {
                        String[] temp = reply.split("/");
                        String taskName = temp[0].substring(9);
                        String[] deadlineDetails = temp[1].split(" ");
                        String date = deadlineDetails[1];
                        Deadline newTask = new Deadline(taskName, LocalDate.parse(date));
                        tasks.add(newTask);
                        taskCount = taskCount + 1;
                        save();
                        System.out.println("okay! new task:" + "\n  " + newTask.toString() + "\njust " + taskCount + " tasks left!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops! What's the deadline again?");
                    }
                } else if (reply.toLowerCase().matches("event(.*)")) {
                    try {
                        String[] temp = reply.split("/");
                        String taskName = temp[0].substring(6);
                        String[] eventDetails = temp[1].split(" ");
                        String date = eventDetails[1];
                        Event newTask = new Event(taskName, LocalDate.parse(date));
                        tasks.add(newTask);
                        taskCount = taskCount + 1;
                        save();
                        System.out.println("okay! new task:" + "\n  " + newTask.toString() + "\njust " + taskCount + " tasks left!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops! When's your event again?");
                    }
                } else {
                    System.out.println("Oops! Maybe try something else? :(");
                }
            }
            reply = scanner.nextLine();
        }

        System.out.println("bye\nsee you again!");
    }

    public static void main(String[] args) {
        new Bob().activate();
    }
}

