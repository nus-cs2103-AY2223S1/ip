import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (createDukeTextFile()) {
            try {
                File dukeFile = new File(filePath);
                Scanner myReader = new Scanner(dukeFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    char taskType = data.charAt(0);
                    switch (taskType) {
                        case 'T':
                            String[] todoSplit = data.split(" // ", 3);
                            Task todo = new Todo(todoSplit[2]);
                            if (todoSplit[1].equals("X")) {
                                todo.markAsDone();
                            }
                            tasks.add(todo);
                            break;
                        case 'D':
                            String[] deadlineSplit = data.split(" // ", 4);
                            Task deadline = new Deadline(deadlineSplit[2], LocalDateTime.parse(deadlineSplit[3]));
                            if (deadlineSplit[1].equals("X")) {
                                deadline.markAsDone();
                            }
                            tasks.add(deadline);
                            break;
                        case 'E':
                            String[] eventSplit = data.split(" // ", 4);
                            Task event = new Event(eventSplit[2], LocalDateTime.parse(eventSplit[3]));
                            if (eventSplit[1].equals("X")) {
                                event.markAsDone();
                            }
                            tasks.add(event);
                            break;
                        default:
                            throw new DukeException("Something is wrong with the text file.");
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("File does not exist.");
            }
        }
        return tasks;
    }

    public void saveData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("");
            for (Task task : tasks) {
                writer.append(task.getDataFormat()).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Something is wrong with the text file.");
        }
    }

    public boolean createDukeTextFile() throws DukeException {
        boolean isAlreadyCreated = false;
        File dataFolder = new File("data");
        File dukeFile = new File(filePath);
        try {
            dataFolder.mkdirs();
            if (!dukeFile.createNewFile()) {
                isAlreadyCreated = true;
            }
        } catch (IOException e) {
            throw new DukeException("Something is wrong with the text file.");
        }
        return isAlreadyCreated;
    }
}
