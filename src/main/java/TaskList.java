import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    protected static final UI UI = new UI();
    protected ArrayList<Task> taskList;
    protected String taskDataPath;

    TaskList(String taskDataPath) {
        this.taskList = new ArrayList<Task>();
        this.taskDataPath = taskDataPath;
        readTaskData();
    }

    public void add(Task task) {
        taskList.add(task);
        UI.printResponse("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.\n");
    }

    public void readTaskData() {
        try {
            File f = new File(taskDataPath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] commands = line.split(",");

                Task newTask;

                if (commands[0].equals("T")) {
                    newTask = new ToDo(commands[2]);
                } else if (commands[0].equals("D")) {
                    newTask = new Deadline(commands[2], commands[3], commands[4]);
                } else {
                    newTask = new Event(commands[2], commands[3], commands[4]);
                }

                if(!commands[1].equals("0")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            UI.printResponse("I cannot find the data file :(\n");
        }
    }

    public void updateTaskData() {
        try {
            FileWriter fw = new FileWriter(taskDataPath);
            for (int i = 0; i < taskList.size(); ++i) {
                fw.write(taskList.get(i).toWrite());
            }
            fw.close();
        } catch (IOException e) {
            UI.printResponse("Something went wrong: " + e.getMessage() + "\n");
        }
    }

    public void printTaskList() {
        String response = UI.taskListOpening;

        if (taskList.size() <= 0) {
            response += UI.noListFound;
        } else {
            for (int i = 1; i <= taskList.size(); ++i) {
                response += (i + ". " + taskList.get(i - 1).toString()) + "\n";
            }
        }
        UI.printResponse(response);
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot mark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot mark this task because task does not exist");
        }

        taskList.get(index).markAsDone();
        UI.printResponse("Nice! I've marked this task as done:\n" + taskList.get(index).toString() +"\n");
    }

    public void markTaskAsNotDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("I cannot unmark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot unmark this task because task does not exist");
        }

        taskList.get(index).markAsNotDone();
        UI.printResponse("OK, I've marked this task as not done yet: \n" + taskList.get(index).toString() + "\n");
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot delete this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot delete this task because task does not exist");
        }

        UI.printResponse("Noted. I've removed this task:\n"
                        + taskList.get(index).toString()
                        + "Now you have " + (taskList.size() - 1)+ " tasks in the list.\n");

        taskList.remove(index);

    }
}
