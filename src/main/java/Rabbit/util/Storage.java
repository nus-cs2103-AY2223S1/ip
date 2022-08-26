package Rabbit.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import Rabbit.RabbitException.ImportDataException;
import Rabbit.RabbitException.RabbitException;
import Rabbit.RabbitException.ExportDataException;
import Rabbit.Task.Event;
import Rabbit.Task.Deadline;
import Rabbit.Task.Todo;


public class Storage {
    /** path of data.txt */
    private Path path;
    private File file;

    public Storage() {
        final String dir = System.getProperty("user.dir");
        this.path = Paths.get(dir, "data", "data.txt");
        this.initiate();
        this.file = new File(path.toString());
    }

    /**
     * Checks if ./data and ./data/data.txt exists.
     * If not, create one.
     */
    public void initiate() {
        // if directory ./date does not exist, create it
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                // directory already exists
                return;
            }

        // if ./data/data.txt does not exist, create it
        try {
            Files.createFile(path);
        } catch (IOException e) {
            // file already exists
        }

    }

    /**
     * Imports a task from data.txt into the list.
     *
     * @throws ImportDataException
     */
    public void importData(TaskList list) throws ImportDataException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String input = s.nextLine();
                list.add(Parser.parseImport(input));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File data.txt is not found.");
        } catch (ImportDataException e) {
            throw e;
        }
    }

    /**
     * Exports tasks from the list into data.txt
     *
     * @param list the list of tasks.
     * @throws RabbitException
     */
    public void exportData(TaskList list) throws RabbitException {
        try {
            FileWriter fw = new FileWriter(path.toString());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Todo) {
                    Todo todo = (Todo) list.get(i);
                    fw.write("T") ;
                    fw.write("|" + (todo.isDone() ? "X" : " ")
                            + "|"  + todo.getContent() + "\n");
                } else if (list.get(i) instanceof Event) {
                    Event event = (Event) list.get(i);
                    fw.write("E") ;
                    fw.write("|" + (event.isDone() ? "X" : " ")
                            + "|"  + event.getContent()
                            + "|" + event.getTime() + "\n");
                } else if (list.get(i) instanceof Deadline) {
                    Deadline deadline = (Deadline) list.get(i);
                    fw.write("D") ;
                    fw.write("|" + (deadline.isDone() ? "X" : " ")
                            + "|"  + deadline.getContent()
                            + "|" + deadline.getTime() + "\n");
                } else {
                    throw new ExportDataException();
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error when writing into data.txt.");
        }
    }
}
