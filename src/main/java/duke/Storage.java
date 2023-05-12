package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Class that deals with storing & loading of task in file
 * @author Ashiqur Rahman A0230107Y
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for Storage class
     * @param filePath Location of file with Tasks
     */
    public Storage (String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        File folder = new File("data");
        if(!folder.exists()) {
            folder.mkdir();
        } if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Method that loads data from the filePath
     * @return ArrayList<Task> which was read from file
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        BufferedReader reader;
        ArrayList<Task> tasklist = new ArrayList<>();
        try{
            reader = new BufferedReader(new FileReader(this.filePath));
            String line = reader.readLine();
            while ( line != null){
                String modifiedLine = line.replaceFirst("(\\d+\\.)", "" );
                if (modifiedLine.contains("[T]")) {
                    extractTodoTask(tasklist, modifiedLine);
                } else if (modifiedLine.contains("[E]")) {
                    extractEventTask(tasklist, modifiedLine);
                } else if (modifiedLine.contains("[D]")) {
                    extractDeadlineTask(tasklist, modifiedLine);
                }
                // read next line
                line = reader.readLine();
            }
            assert(line == null);
            reader.close();
        } catch(FileNotFoundException e){
            File file = new File(this.filePath);
            Parser.echo("File not found so let's create one!");
        } catch(IOException e){
            File file = new File(this.filePath);
            Parser.echo("File not found so let's create one!");
        }
        return tasklist;
    }

    private void extractDeadlineTask(ArrayList<Task> tasklist, String modifiedLine) {
        if (modifiedLine.contains("[ ]")) {
            String deadlineDsc = modifiedLine.replace("[D][ ] ", ""); // replace target with '' string
            // get 2 substring for description and deadlineTime
            String description = deadlineDsc.substring(0, deadlineDsc.indexOf("(") - 1);
            String dlTime = deadlineDsc.substring(deadlineDsc.indexOf("(")+1,deadlineDsc.indexOf(")"));
            String modDeadlineTime = dlTime.replaceFirst("by: ", "");
            // to convert string to date
            DateTimeFormatter convertFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate deadlineDate = LocalDate.parse(modDeadlineTime, convertFormatter);
            // to put in the same format as Deadline class parameter
            DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String modifiedString = deadlineDate.format(stringFormatter);
            Deadline newTask = new Deadline(description, modifiedString);
            tasklist.add(newTask);
        } else {
            // replace the formats with empty string
            String deadlineDsc = modifiedLine.replace("[D][X] ", "");
            // get 2 substring for description and deadlineTime
            String description = deadlineDsc.substring(0, deadlineDsc.indexOf("(") - 1);
            String dlTime = deadlineDsc.substring(deadlineDsc.indexOf("(")+1,deadlineDsc.indexOf(")"));
            String modDeadlineTime = dlTime.replaceFirst("by: ", "");
            // to convert string to date
            DateTimeFormatter convertFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate deadlineDate = LocalDate.parse(modDeadlineTime, convertFormatter);
            // to put in the same format as Deadline class parameter
            DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String modifiedString = deadlineDate.format(stringFormatter);
            Deadline newTask = new Deadline(description, modifiedString);
            newTask.markAsDone();
            tasklist.add(newTask);
        }
    }

    private void extractEventTask(ArrayList<Task> tasklist, String modifiedLine) {
        if (modifiedLine.contains("[ ]")) {
            String evDescrip = modifiedLine.replace("[E][ ] ", "");
            String description = evDescrip.substring(0, evDescrip.indexOf("(") - 1);
            String eventTime = evDescrip.substring(evDescrip.indexOf("(")+1,evDescrip.indexOf(")"));
            // data in textfile contains : after at, to replace it with whitespace
            String modEventTime = eventTime.replaceFirst("at: ", " ");
            Event newTask = new Event(description, modEventTime);
            tasklist.add(newTask);
        } else {
            // replace the formats with empty string
            String evDescrip = modifiedLine.replace("[E][X] ", "");
            // get 2 substring for description and eventTime
            String description = evDescrip.substring(0, evDescrip.indexOf("(") - 1);
            String eventTime = evDescrip.substring(evDescrip.indexOf("(")+1,evDescrip.indexOf(")"));
            // data in textfile contains : after at, to replace it with whitespace
            String modEventTime = eventTime.replaceFirst("at: ", " ");
            Event newTask = new Event(description, modEventTime);
            newTask.markAsDone();
            tasklist.add(newTask);
        }
    }

    private void extractTodoTask(ArrayList<Task> tasklist, String modifiedLine) {
        if (modifiedLine.contains("[ ]")) {
            String todoDescription = modifiedLine.replace("[T][ ] ", "");
            Todo newTask = new Todo(todoDescription);
            tasklist.add(newTask);
        } else {
            String todoDescription = modifiedLine.replace("[T][X] ", "");
            Todo newTask = new Todo(todoDescription);
            newTask.markAsDone();
            tasklist.add(newTask);
        }
    }
}
