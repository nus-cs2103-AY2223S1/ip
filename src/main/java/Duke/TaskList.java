package Duke;

import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> tasks;
    final static private String DIRECTORY_PATH = "./data";
    final static private String FILE_PATH = "./data/duke.txt";
    final static private String TEMP_FILE_PATH = "./data/temp.txt";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public String list() {
        int len = tasks.size();
        StringBuilder stringBuilder = new StringBuilder("\tHere are the tasks in your list :D");
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            String task = "\n\t" + index + ". " + tasks.get(i);
            stringBuilder.append(task);
        }
        return stringBuilder.toString();
    }

    /**
     * Adds a Todo object into the list
     * @param message The task's description
     * @throws DukeException If the input is invalid
     */
    public void addTodo(String message) throws DukeException {
        if (message.length() == 0) {
            throw new DukeException("Please add using the following format: todo <description>");
        }
        ToDo todo = new ToDo(message);
        this.tasks.add(todo);
        String header = "\tGot it! I have added this task:\n\t\t" + todo;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");

        FileWriter fileWriter = null;
        File file = new File(TaskList.FILE_PATH);
        try {
            fileWriter = new FileWriter(file, true);
            String textToSave = String.format("T,false,%s,%s", message, System.getProperty("line.separator"));
            fileWriter.write(textToSave);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(header + numOfTasks);
    }

    /**
     * Adds a Deadline object into the list
     * @param message The task's description and date
     * @throws DukeException If the input is invalid
     */
    public void addDeadline(String message) throws DukeException {
        String[] info = message.split(" /by ");
        if (info.length < 2) {
            throw new DukeException("Please add using the following format: " +
                    "deadline <description> /by <date>");
        }
        String description = info[0];
        String by = info[1];
        Deadline deadline = new Deadline(description, by);
        this.tasks.add(deadline);
        String header = "\tGot it! I have added this task:\n\t\t" + deadline;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");

        FileWriter fileWriter = null;
        File file = new File(TaskList.FILE_PATH);
        try {
            fileWriter = new FileWriter(file, true);
            String textToSave = String.format("D,false,%s,%s,%s", description,
                    by, System.getProperty("line.separator"));
            fileWriter.write(textToSave);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(header + numOfTasks);
    }

    /**
     * Adds an Event object into the list
     * @param message The task's description and date
     * @throws DukeException If the input is invalid
     */
    public void addEvent(String message) throws DukeException {
        String[] info = message.split(" /at ");
        if (info.length < 2) {
            throw new DukeException("Please add using the following format: " +
                    "event <description> /at <date>");
        }
        String description = info[0];
        String at = info[1];
        Event event = new Event(description, at);
        this.tasks.add(event);
        String header = "\tGot it! I have added this task:\n\t\t" + event;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");

        FileWriter fileWriter = null;
        File file = new File(TaskList.FILE_PATH);
        try {
            fileWriter = new FileWriter(file, true);
            String textToSave = String.format("D,false,%s,%s,%s", description,
                    at, System.getProperty("line.separator"));
            fileWriter.write(textToSave);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(header + numOfTasks);
    }

    public void delete(String message) throws DukeException {
        if (message.length() == 0) {
            throw new DukeException("I don't know which task to delete :(, " +
                    "please delete using the following format: delete <task-number>");
        }
        int taskNumber = Integer.parseInt(message);
        if (taskNumber > this.tasks.size()) {
            throw new DukeException("The task number exceeds the number of tasks in the list!");
        }

        Task task = tasks.get(taskNumber - 1);
        String msg = String.format("\tNoted, I have removed this task:\n\t\t%s", task);
        tasks.remove(taskNumber - 1);
        this.edit();
        System.out.println(msg);
    }

    /**
     * Sets a task's completion status
     * @param id The task number
     * @param mark Whether to mark a task as completed
     */
    public void changeStatus(String id, boolean mark) {
        int index = Integer.parseInt(id) - 1;
        if (mark) {
            System.out.println(this.tasks.get(index).markAsDone());
        } else {
            System.out.println(this.tasks.get(index).markAsNotDone());
        }

        this.edit();
    }

    /**
     * Creates the required directory and file
     */
    private void create() {
        try {
            File directory = new File(TaskList.DIRECTORY_PATH);
            directory.mkdir();

            File file = new File(TaskList.FILE_PATH);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves the information saved in the text file
     */
    public void read() {
        this.create();
        File source = new File(TaskList.FILE_PATH);
        Scanner reader = null;
        try {
            reader = new Scanner(source);
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                switch (info[0]) {
                    case "T":
                        ToDo todo = new ToDo(info[2]);
                        todo.setIsDone(info[1].equals("true"));
                        this.tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(info[2], info[3]);
                        deadline.setIsDone(info[1].equals("true"));
                        this.tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(info[2], info[3]);
                        event.setIsDone(info[1].equals("true"));
                        this.tasks.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Edits the information saved in the text file
     */
    private void edit() {
        File tempFile = new File(TaskList.TEMP_FILE_PATH);
        File file = new File(TaskList.FILE_PATH);
        FileWriter fileWriter = null;
        try {
            tempFile.createNewFile();
            fileWriter = new FileWriter(tempFile, true);
            for (Task task : tasks) {
                String taskType = task.getTaskType();
                boolean isDone = task.getisDone();
                String description = task.getDescription();
                String date = task.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                String dataToCopy;
                if (taskType.equals("T")) {
                    dataToCopy = String.format("T,%s,%s,%s", isDone, description,
                            System.getProperty("line.separator"));
                } else {
                    dataToCopy = String.format("%s,%s,%s,%s,%s", taskType, isDone, description,
                            date, System.getProperty("line.separator"));
                }

                fileWriter.write(dataToCopy);
            }

            file.delete();
            tempFile.renameTo(new File(TaskList.FILE_PATH));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String getTasks(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringBuilder stringBuilder = new StringBuilder("\tYour tasks for today include:");
        int count = 1;
        for (Task task : this.tasks) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDate().equals(parsedDate)) {
                    String formatted = String.format("\n\t%d. %s", count, task);
                    stringBuilder.append(formatted);
                    count++;
                }
            }
        }

        if (count == 1) {
             return String.format("\tNo tasks on %s", parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        } else {
            return stringBuilder.toString();
        }
    }

    public void addToList(Task task) {
        this.tasks.add(task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public Task deleteTask(int i) throws DukeException {
        if (i > this.tasks.size()) {
            throw new DukeException("No such task exist!");
        }
        Task task = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        return task;
    }
}
