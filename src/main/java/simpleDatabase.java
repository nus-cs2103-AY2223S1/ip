import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;


public class simpleDatabase {
    private static final String FILE_NOT_FOUND = "☹ Sorry , the file cannot be found.\n";
    private static final String FILE_READ_ERROR = "☹ Sorry , there is an error when reading the file.\n";

    private final String filePath;

    public simpleDatabase(String filePath){
        this.filePath = filePath;
        File f = new File(filePath);
    }

    public TaskList getMemory() throws DukeException{
        TaskList taskList = new TaskList();
        try {
            File localFile = new File(this.filePath);
            Scanner s = new Scanner(localFile);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                if (taskString.strip().equals("")) {
                    continue;
                }
                taskList.add(makeTask(taskString));
            }
        } catch (FileNotFoundException e) {
            makeNewFile(this.filePath);
        }
        return taskList;
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
            throw new DukeException("Error in creating memory space");
        }
    }


    private Event makeEvent(String markIndex, String description, String at) {
        Event newEvent = new Event(description, at);
        if (markIndex.equals("1")) {
            newEvent.markAsDone();
            return newEvent;
        }
        return newEvent;
    }

    private Deadline makeDeadline(String markIndex, String description, String by) {
        Deadline newDeadline = new Deadline(description, LocalDate.parse(by));
        if (markIndex.strip().equals("1")) {
            newDeadline.markAsDone();
            return newDeadline;
        }
        return newDeadline;
    }

    private ToDo makeToDo(String markIndex, String description){
        ToDo newToDo = new ToDo(description);
        if (markIndex.strip().equals("1")) {
            newToDo.markAsDone();
            return newToDo;
        }
        return newToDo;
    }

    public Task makeTask(String taskString) throws DukeException {
        Task newTask;
        try {
            String[] taskSegments = taskString.split("\\|");
            switch (taskSegments[0].strip()) {
                case "E":
                    newTask = makeEvent(taskSegments[1].strip(), taskSegments[2].strip(), taskSegments[3].strip());
                    break;
                case "D":
                    newTask = makeDeadline(taskSegments[1].strip(), taskSegments[2].strip(), taskSegments[3].strip());
                    break;
                case "T":
                    newTask = makeToDo(taskSegments[1].strip(), taskSegments[2].strip());
                    break;
                default:
                    throw new DukeException(FILE_READ_ERROR);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(FILE_READ_ERROR);
        }
        return newTask;
    }

    public void save(String taskString) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(taskString + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(FILE_NOT_FOUND);
        }
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 1; i <= taskList.getCount(); i++) {
                fw.write(taskList.getTask(i).toSimpleString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(FILE_NOT_FOUND);
        }
    }
}
