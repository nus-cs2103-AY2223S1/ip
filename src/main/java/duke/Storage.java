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
    public Storage (String filePath){
        this.filePath = filePath;
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
                } else if (modifiedLine.contains("[E]")) {
                    if (modifiedLine.contains("[ ]")) {
                        String evDescrip = modifiedLine.replace("[E][ ] ", "");
                        String description = evDescrip.substring(0, evDescrip.indexOf("(") - 1);
                        String eventTime = evDescrip.substring(evDescrip.indexOf("(")+1,evDescrip.indexOf(")"));
                        // data in textfile contains : after at, to replace it with whitespace
                        String modEventTime = eventTime.replaceFirst(": ", " ");
                        Event newTask = new Event(description, modEventTime);
                        tasklist.add(newTask);
                    } else {
                        // replace the formats with empty string
                        String evDescrip = modifiedLine.replace("[E][X] ", "");
                        // get 2 substring for description and eventTime
                        String description = evDescrip.substring(0, evDescrip.indexOf("(") - 1);
                        String eventTime = evDescrip.substring(evDescrip.indexOf("(")+1,evDescrip.indexOf(")"));
                        // data in textfile contains : after at, to replace it with whitespace
                        String modEventTime = eventTime.replaceFirst(": ", " ");
                        Event newTask = new Event(description, modEventTime);
                        newTask.markAsDone();
                        tasklist.add(newTask);
                    }
                } else if (modifiedLine.contains("[D]")) {
                    if (modifiedLine.contains("[ ]")) {
                        // replace the formats with empty string
                        String deadlineDsc = modifiedLine.replace("[D][ ] ", "");
                        // get 2 substring for description and deadlineTime
                        String description = deadlineDsc.substring(0, deadlineDsc.indexOf("("));
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
                        String description = deadlineDsc.substring(0, deadlineDsc.indexOf("("));
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
                // read next line
                line = reader.readLine();
            }
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
}
