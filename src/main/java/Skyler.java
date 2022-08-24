import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.regex.Pattern;
import java.lang.Character;

public class Skyler {
    public static void saveTask(ArrayList<Task> task) throws java.io.IOException {
        String filepath = new File("data/skyler.txt").getAbsolutePath();
        FileWriter fw = new FileWriter(filepath);

        for (Task currTask : task) {
            String taskStr = currTask.toString();
            String taskDescription = taskStr.substring(7);
            int strLen = taskDescription.length();

            char taskType = taskStr.charAt(1);
            int taskStatus = taskStr.charAt(4) == 'X' ? 1 : 0;

            StringBuilder output = new StringBuilder();
            String str = String.format("%c | %d | ", taskType, taskStatus);
            output.append(str);

            switch (taskType) {
            case 'T':
                output.append(taskDescription).append(System.lineSeparator());
                break;
            case 'D':
                Pattern p = Pattern.compile(" [(]by: ");
                String[] descriptors = p.split(taskDescription.substring(0, strLen - 1));
                String str1 = String.format("%s | %s", descriptors[0], descriptors[1]);
                output.append(str1).append(System.lineSeparator());
                break;
            case 'E':
                Pattern p1 = Pattern.compile(" [(]at: ");
                String[] descriptors1 = p1.split(taskDescription.substring(0, strLen - 1));
                String str2 = String.format("%s | %s", descriptors1[0], descriptors1[1]);
                output.append(str2).append(System.lineSeparator());
                break;
            }
            fw.write(output.toString());
            fw.flush();
        }

        fw.close();
    }

    //method below adapted from
    //https://www.geeksforgeeks.org/java-program-to-search-for-a-file-in-a-directory/ ,
    //https://www.educba.com/java-directories/ and
    //https://www.tutorialspoint.com/determine-if-file-or-directory-exists-in-java
    public static void loadTask(ArrayList<Task> task) throws IOException {
        String name = new File("data").getAbsolutePath();
        Path path = Paths.get(name);
        // if folder does not exist yet
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        name = new File("data/").getAbsolutePath();
        File directory = new File(name);

        String[] flist = directory.list();
        int flag = 0;
        if (flist == null) {
            name = new File("data/skyler.txt").getAbsolutePath();
            directory = new File(name);
            directory.createNewFile();
        }

        for (int i = 0; i < flist.length; i++) {
            String filename = flist[i];
            if (filename.equals("skyler.txt")) {
                name = new File("data/skyler.txt").getAbsolutePath();
                directory = new File(name);
                flag = 1;
            }
        }

        if (flag == 0) {
            name = new File("data/skyler.txt").getAbsolutePath();
            directory = new File(name);
            directory.createNewFile();
        }

        Scanner sc = new Scanner(directory);
        while (sc.hasNextLine()) {
            String taskStr = sc.nextLine();
            String taskDescription = taskStr.substring(8);

            char type = taskStr.charAt(0);
            int taskStatus = Character.getNumericValue(taskStr.charAt(4));

            switch(type) {
            case 'T':
                Todo todo = new Todo(taskDescription);
                if (taskStatus == 1) {
                    todo.markAsDone();
                }
                task.add(todo);
                break;
            case 'D':
                Pattern p = Pattern.compile(" [|] ");
                String[] descriptors = p.split(taskDescription);

                Deadline deadline = new Deadline(descriptors[0], descriptors[1]);
                if (taskStatus == 1) {
                    deadline.markAsDone();
                }
                task.add(deadline);
                break;
            case 'E':
                Pattern p1 = Pattern.compile(" [|] ");
                String[] descriptors1 = p1.split(taskDescription);

                Event event = new Event(descriptors1[0], descriptors1[1]);
                if (taskStatus == 1) {
                    event.markAsDone();
                }
                task.add(event);
                break;
            }
        }
    }

    public static void printTask(Task task, int num) {
        System.out.println("I've added the following task:");
        String str = String.format("  %s", task);
        System.out.println(str);
        String summary = String.format("Total number of tasks: %d", num);
        System.out.println(summary);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            loadTask(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Good day, mate! I'm Skyler\nHow can I help you?\n");
            while (sc.hasNext()) {
                String description = sc.nextLine();

                if (description.equals("bye")) {
                    System.out.println("Bye! See you again soon!");
                    break;
                } else if (description.equals("list")) {
                    System.out.println("Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        String str = String.format("%d.%s", i + 1, tasks.get(i));
                        System.out.println(str);
                    }
                } else if (description.startsWith("mark")) {
                    // task list changes
                    int item = Integer.parseInt(description.substring(description.length() - 1));
                    Task currTask = tasks.get(item - 1);
                    currTask.markAsDone();
                    System.out.println("You have completed this task:");
                    String show = String.format("  %s", currTask);
                    System.out.println(show);

                    try {
                        saveTask(tasks);
                    } catch (IOException ie) {
                        System.out.println(ie.getMessage());
                    }
                } else if (description.startsWith("unmark")) {
                    // task list changes
                    int item = Integer.parseInt(description.substring(description.length() - 1));
                    Task currTask = tasks.get(item - 1);
                    currTask.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    String show = String.format("  %s", currTask);
                    System.out.println(show);

                    try {
                        saveTask(tasks);
                    } catch (IOException ie) {
                        System.out.println(ie.getMessage());
                    }
                } else if (description.startsWith("todo")) {
                    String[] arr = description.split(" ", 2);
                    if (description.trim().equals("todo")) {
                        throw new EmptyDescriptionException();
                    }
                    Todo newTodo = new Todo(arr[1]);
                    tasks.add(newTodo);

                    // task list changes
                    printTask(newTodo, tasks.size());
                    try {
                        saveTask(tasks);
                    } catch (IOException ie) {
                        System.out.println(ie.getMessage());
                    }
                } else if (description.startsWith("deadline")) {
                    String[] arr = description.split(" ", 2);
                    if (description.trim().equals("deadline")) {
                        throw new EmptyDescriptionException();
                    }
                    String item = arr[1];
                    String[] arr1 = item.split(" /by ", 2);

                    Deadline newDeadline = new Deadline(arr1[0], arr1[1]);
                    tasks.add(newDeadline);

                    // task list changes
                    printTask(newDeadline, tasks.size());
                    try {
                        saveTask(tasks);
                    } catch (IOException ie) {
                        System.out.println(ie.getMessage());
                    }
                } else if (description.startsWith("event")) {
                    String[] arr = description.split(" ", 2);
                    if (description.trim().equals("event")) {
                        throw new EmptyDescriptionException();
                    }
                    String item = arr[1];
                    String[] arr1 = item.split(" /at ", 2);

                    Event newEvent = new Event(arr1[0], arr1[1]);
                    tasks.add(newEvent);

                    // task list changes
                    printTask(newEvent, tasks.size());
                    try {
                        saveTask(tasks);
                    } catch (IOException ie) {
                        System.out.println(ie.getMessage());
                    }
                } else if (description.startsWith("delete")) {
                    // task list changes
                    int item = Integer.parseInt(description.substring(description.length() - 1));
                    Task currTask = tasks.get(item - 1);
                    System.out.println("The following task will be removed:");
                    String show = String.format("  %s", currTask);
                    System.out.println(show);
                    tasks.remove(item - 1);
                    String str = String.format("Total number of tasks: %d", tasks.size());
                    System.out.println(str);

                    try {
                        saveTask(tasks);
                    } catch (IOException ie) {
                        System.out.println(ie.getMessage());
                    }
                } else {
                    throw new TaskNotRecognisedException();
                }
            }
        } catch (EmptyDescriptionException e) {
            System.out.println("Oh no! Cannot insert task without description.");
        } catch (TaskNotRecognisedException e) {
            System.out.println("Oops! I'm sorry, but I don't know what that means.");
        }
    }
}
