package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public TaskList load() throws DukeException {
        Scanner sc = null;
        TaskList taskList = new TaskList();
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();

                if (currLine.equals("")) {
                    break;
                }

                String[] taskSplit = currLine.split("\\|", 4);
                Task newTask = null;
                String taskType = taskSplit[0].trim();
                boolean isDone = taskSplit[1].trim().equals("1");
                String taskDesc = taskSplit[2].trim();
                String taskDate = taskSplit.length == 4 ? taskSplit[3].trim() : "";
                switch (taskType) {
                case "T":
                    newTask = new ToDo(taskDesc, isDone);
                    break;
                case "D":
                    newTask = new Deadline(taskDesc, LocalDate.parse(taskDate), isDone);
                    break;
                case "E":
                    newTask = new Event(taskDesc, LocalDate.parse(taskDate), isDone);
                    break;
                }
                taskList.addTask(newTask);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            this.createFile();
            return new TaskList();
        } catch (DateTimeParseException e) {
            throw new DukeException("Saved file is corrupted.");
        } finally {
            if (sc != null) sc.close();
        }
    }

    public void createFile() throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("An error occurred when creating new file.");
        } catch (SecurityException e) {
            throw new DukeException("Duke does not have access to write to file.");
        }
    }

    public void overwriteFile(String textToReplace) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToReplace);
            fw.close();
        } catch (IOException e) {
            // handle exception
            System.out.println("An error occurred when saving the changes to file.");
        }
    }
}
