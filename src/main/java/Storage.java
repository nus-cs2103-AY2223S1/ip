package main.java;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private File file;


    public Storage(String filePath) {
        String path = System.getProperty("user.dir") + "/data/duke.txt";
        file = new File(path);
    }

    public static ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        String directory = System.getProperty("user.dir") + "/data/";
        String path = System.getProperty("user.dir") + "/data/duke.txt";
        File directoryFolder = new File(directory);
        File file = new File(path);

        if (!directoryFolder.exists()) {
            directoryFolder.mkdir();
            Ui.showDirectoryCreation();
        }

        if (!file.exists()) {
            file.createNewFile();
            Ui.showFileCreation();
        }

        try {
            FileReader filereader = new FileReader(path);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String line;
            Ui.showLoadSuccessful();
            while ((line = bufferedreader.readLine()) != null) {
                if (line.startsWith("[T]")) {
                    tasks.add(new ToDo(line.substring(0, 5) + line.substring(7)));
                    if (line.startsWith("[T][X]")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                } else if (line.startsWith("[D]")) {
                    String tempDeadline = "deadline"
                            + line.substring(line.lastIndexOf("]") + 1, line.lastIndexOf("("))
                            + "/by " + Parser.parseLocalDate(line.substring(line.lastIndexOf(":") + 2, line.lastIndexOf(")")));
                    tasks.add(new Deadline(tempDeadline));
                    if (line.startsWith("[D][X]")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                } else if (line.startsWith("[E]")) {
                    String tempEvent = "event"
                            + line.substring(line.lastIndexOf("]") + 1, line.lastIndexOf("("))
                            + "/at " + Parser.parseLocalDate(line.substring(line.lastIndexOf(":") + 2, line.lastIndexOf(")")));
                    tasks.add(new Event(tempEvent));
                    if (line.startsWith("[E][X]")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                }
            }
            bufferedreader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void saveFile(TaskList taskList) {
        String directory = System.getProperty("user.dir") + "/data/";
        String path = System.getProperty("user.dir") + "/data/duke.txt";
        File directoryFolder = new File(directory);

        if (!directoryFolder.exists()) {
            Ui.showDirectoryCreation();
            directoryFolder.mkdir();
        }

        try {
            FileWriter filewriter = new FileWriter(path);
            for (Task task : taskList.getArray()) {
                filewriter.write(task.toString() + "\n");
            }
            filewriter.flush();
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ui.showSaveSuccessful();
    }
}
