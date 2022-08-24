import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String file) {
        filePath = file;
    }

    private File checkFile() throws IOException {
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            System.out.println("File already exists.");
        } else {
            file.getParentFile().mkdir();
            file.createNewFile();
            System.out.println("File created: " + file.getName());
        }
        return file;
    };

    //tasktype | mark | description | dateline(optional)
    //T | 1 | read book
    //D | 0 | return book | June 6th
    //E | 0 | project meeting | Aug 6th 2-4pm
    //T | 1 | join sports club
    // reads existing file and stores into an array or creates a new file
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = checkFile();
            Scanner fileScanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] separatedLines = line.split("|");
                switch (separatedLines[0]) {
                // task
                case ("T"):
                    Task todo = new ToDos(separatedLines[2]);
                    if (separatedLines[1] == "1") {
                        todo.finished();
                    }
                    tasks.add(todo);
                    break;
                case ("D"):
                    Task deadline = new Deadline(separatedLines[2], separatedLines[3]);
                    if (separatedLines[1] == "1") {
                        deadline.finished();
                    }
                    tasks.add(deadline);
                    break;
                case ("E"):
                    Task event = new Events(separatedLines[2], separatedLines[3]);
                    if (separatedLines[1] == "1") {
                        event.finished();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new DukeException("error in file, check loading");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("IOException in loading" + e.getMessage());
        }
    };

    // saves the whole array and rewrites the entire file (for extra oop add in mtd to append
    // when adding files and use this for deletion)
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i + 1);
                fw.write(task.textFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IOException in saving" + e.getMessage());
        }
    }
}
