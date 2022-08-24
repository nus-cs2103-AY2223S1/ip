import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final List<Task> taskList = new ArrayList<>();

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list :)");
        System.out.println("____________________________________________________________");
    }

    public static void deleteTask(int num) {
        if (num > taskList.size() || num == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There's no such task!");
            System.out.println("____________________________________________________________");
            return;
        }
        Task removed = taskList.remove(num - 1);
        System.out.println("____________________________________________________________");
        System.out.println("Ok, I've removed this task:\n  "
                + removed.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list :)");
        System.out.println("____________________________________________________________");
    }

    /**
     * The method to list out the tasks that are present in taskList.
     */
    public static void printTaskList() {
        System.out.println("Here are your tasks");
        System.out.println("____________________________________________________________");
        int i = 1;
        for (Task t : TaskList.taskList) {
            System.out.println(i + "." + t.toString());
            i++;
        }
        System.out.println("____________________________________________________________");
    }
    public static void mark(int number) {
        if (number > TaskList.taskList.size() || number == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There's no such task!");
            System.out.println("____________________________________________________________");
            return;
        }
        taskList.get(number - 1).setDone();
        System.out.println("____________________________________________________________");
        System.out.println("Ok! I've marked it as done.\n  "
                + taskList.get(number - 1).toString());
        System.out.println("____________________________________________________________");
    }

    public static void unMark(int number) {
        if (number > taskList.size() || number == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There's no such task!");
            System.out.println("____________________________________________________________");
            return;
        }
        taskList.get(number - 1).setUnDone();
        System.out.println("____________________________________________________________");
        System.out.println("Ok! I've marked it as undone.\n  "
                + taskList.get(number - 1).toString());
        System.out.println("____________________________________________________________");
    }

    public static void textToTaskList(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
            label:
            for (String line; (line = reader.readLine()) != null; ) {
                String[] taskInfo = line.split("[*]", 4);

                switch (taskInfo[0]) {
                    case "T":
                        Task toDo = new ToDo(taskInfo[2]);
                        if (taskInfo[1].equals("1")) {
                            toDo.setDone();
                        }
                        taskList.add(toDo);
                        break;
                    case "D":
                        Task deadline = new Deadline(taskInfo[2], taskInfo[3]);
                        if (taskInfo[1].equals("1")) {
                            deadline.setDone();
                        }
                        taskList.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(taskInfo[2], taskInfo[3]);
                        if (taskInfo[1].equals("1")) {
                            event.setDone();
                        }
                        taskList.add(event);
                        break;
                    default:
                        System.out.println("Error in file" + file.getAbsolutePath());
                        break label;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String taskListToText() {
        StringBuilder lines = new StringBuilder();
        for (Task task : taskList) {
            lines.append(task.toLine()).append("\n");
        }

        return lines.toString();
    }
}
