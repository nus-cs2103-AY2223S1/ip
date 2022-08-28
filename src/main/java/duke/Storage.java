package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    public void update(TaskList list) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(list.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file");
        }
    }

    public ArrayList<String> load() throws DukeException{
        try {
            Scanner s = new Scanner(file).useDelimiter("\\r?\\n|\\r");
            ArrayList<String> taskList = new ArrayList<String>();
            while (s.hasNext()){
                taskList.add(s.next());
            }
            s.close();
            return taskList;
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                int directoryEndIndex = filePath.lastIndexOf("/");
                if (directoryEndIndex != -1) {
                    String directory = filePath.substring(0, directoryEndIndex);
                    new File(directory).mkdir();
                }
                file.createNewFile();
                return new ArrayList<>();
            } catch (IOException ioException) {
                // System.out.println(ioException.getMessage());
                throw new DukeException("Unable to create file. Changes made will not be saved.");
            }
        }
    }

}
