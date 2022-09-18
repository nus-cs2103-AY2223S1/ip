package duke.storage;

import duke.DukeException;
import duke.Message;
import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {
    private final String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
    }

    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            File localFile = new File(this.filePath);
            Scanner s = new Scanner(localFile);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                if (taskString.strip().equals("")) {
                    continue;
                }
                tasks.add(makeTask(taskString));
            }
        } catch (FileNotFoundException e) {
            makeNewFile(this.filePath);
        }
        return tasks;
    }

    private void makeNewFile(String filePath) throws DukeException {
        String[] pathArray = filePath.split("/");
        String fileName = pathArray[pathArray.length - 1];
        String[] directories = Arrays.copyOfRange(pathArray, 0, pathArray.length - 1);
        String directoryPath = String.join("/", directories);
        File directory = new File(directoryPath);
        File newFile = new File(fileName);
        try {
            directory.mkdirs();
            newFile.createNewFile();
        } catch (Exception e) {
            throw new DukeException(Message.FILE_CREATE_ERROR);
        }
    }

    private Event makeEvent(String markStatus, String description, String at) {
        Event newEvent = new Event(description, at);
        newEvent.setMarkBasedOnSimpleStatus(markStatus);
        return newEvent;
    }

    private Deadline makeDeadline(String markStatus, String description, String by) throws DukeException {
        try {
            Deadline newDeadline = new Deadline(description, LocalDate.parse(by));
            newDeadline.setMarkBasedOnSimpleStatus(markStatus);
            return newDeadline;
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Message.FILE_READ_ERROR);
        }
    }

    private ToDo makeToDo(String markStatus, String description) {
        ToDo newToDo = new ToDo(description);
        newToDo.setMarkBasedOnSimpleStatus(markStatus);
        return newToDo;
    }

    public Task makeTask(String taskString) throws DukeException {
        Task newTask;
        try {
            String[] taskSegments = taskString.split("\\|");
            String taskIdentifier = taskSegments[0].strip();
            String taskMarkStatus = taskSegments[1].strip();
            String taskDescription = taskSegments[2].strip();
            switch (taskIdentifier) {
            case "E":
                newTask = makeEvent(taskMarkStatus, taskDescription, taskSegments[3].strip());
                break;
            case "D":
                newTask = makeDeadline(taskMarkStatus, taskDescription, taskSegments[3].strip());
                break;
            case "T":
                newTask = makeToDo(taskMarkStatus, taskDescription);
                break;
            default:
                throw new DukeException(Message.FILE_READ_ERROR);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.FILE_READ_ERROR);
        }
        return newTask;
    }

    public void save(String taskString) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(taskString + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(Message.FILE_NOT_FOUND);
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(tasks.toSimpleStrings());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(Message.FILE_NOT_FOUND);
        }
    }

}
