package clevernotbot;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.*;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String fileLocation = "/data/cleverNotBot.txt";
    private File file;

    /**
     * Constructor for Storage.
     */
    public Storage() {
        this.file = new File(this.fileLocation);
    }

    /**
     * Gets Task from the saved file.
     *
     * @return An ArrayList full of tasks.
     */
    public ArrayList<Task> getTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            while (true) {
                String line = reader.readLine();
                boolean hasNextLine = (line != null);
                if (!hasNextLine){
                    break;
                }
                Task task = convertLineToTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File does not exist! Creating a new file at location!");
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException errorCreate) {
                System.out.println("An error has occurred when trying to create new file");
            }
        } catch (IOException errorObtain) {
            System.out.println("An error has occurred when trying to obtain data from the save file.");
        }
        return tasks;
    }

    /**
     * Writes tasks to file.
     *
     * @param tasks The tasks that needs to put in.
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            // if the file above doesn't exist, create it.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            FileWriter fw = new FileWriter(file);
            StringBuilder op = new StringBuilder();
            int counter = 0;
            final String TODO_PREFIX = "T";
            final String DEADLINE_PREFIX = "D";
            final String EVENT_PREFIX = "E";
            for (Task task : tasks) {
                String taskToStorage = "";
                String prefix = task.getTaskType();
                switch(prefix.trim()){
                case TODO_PREFIX:
                    taskToStorage = String.format("%s | %d | %s",
                            task.getTaskType(), task.isCompleted() ? 1 : 0, task.getName());
                    break;
                case DEADLINE_PREFIX:
                case EVENT_PREFIX:
                    taskToStorage = String.format("%s | %d | %s | %s",
                            task.getTaskType(), task.isCompleted() ? 1 : 0, task.getName(), task.getTime());
                    break;
                default:
                    System.out.println("Warning! Illegal entries has been detected!");
                    System.out.println(prefix);
                }
                op.append(taskToStorage);
                counter++;
                if (counter < tasks.size()) {
                    op.append("\n");
                }
            }
            String result = op.toString();
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occurred during saving of file!");
        }
    }

    /**
     * Converts line to Tasks.
     * E.g. "D | 0 | return book | 02-12-2022 18:00" -> new Dateline("return book",false,02-12-2022 18:00")
     *
     * @param line Line that stored in the file.
     * @return Task.
     */
    private Task convertLineToTask(String line) {
        // This is to make it in a form of [prefix,marked,name,dateTime]
        // In order to get the individual data.
        String[] content = line.split(" \\| ");
        /* Debug line
        System.out.println(file.getAbsolutePath());
        System.out.println(String.join(",",content));
         */
        String prefix = content[0];
        String markInStringFormat = content[1];
        String name = content[2];
        // content[3] is dateTime
        switch (prefix) {
        case "T":
            return new ToDo(name, isMarked(markInStringFormat));
        case "D":
            return new Deadline(name, isMarked(markInStringFormat), content[3]);
        default:
            // new event
            return new Event(name, isMarked(markInStringFormat), content[3]);
        }
    }

    /**
     * Returns that it is marked if the marked col is "1"
     *
     * @param number Marked col store in the txt.
     * @return Marked or not.
     */
    private boolean isMarked(String number) {
        return "1".equals(number);
    }

}
