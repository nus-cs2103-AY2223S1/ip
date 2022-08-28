package duke;

import duke.command.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
* Storage class to store tasks into the TaskList and load tasks from external files.
*
* @author Sheryl Kong (A0240686Y)
*/

public class Storage {
    private final String filePath;
    private static boolean isLoadingFile = false;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIsLoadingFile() {
        return isLoadingFile;
    }

    public String read() throws IOException {
        return Files.readString(Path.of(this.filePath));
    }

    public TaskList load() throws DukeException {
        isLoadingFile = true;
        TaskList taskList = new TaskList();
        try {
            String fileContent = this.read();
            String[] lines = fileContent.split(System.lineSeparator());
            for (String line : lines) {
                Command command = Parser.parseFileLine(line);
                command.execute(taskList, this);
            }
        } catch (FileNotFoundException f) {
            throw new DukeException("File not found");
        } catch (IOException i) {
            throw new DukeException("IO exception");
        }
        isLoadingFile = false;
        return taskList;
    }


    public void saveData(TaskList taskList) throws DukeException {
        try {
            File f = new File(this.filePath);
            if (!f.createNewFile()) f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(this.filePath, true);
            for (Task t : taskList.getList()) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException i) {
            throw new DukeException("OOPS!! data/duke.txt file does not exist");
        }
    }


}

