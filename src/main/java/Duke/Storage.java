package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {

        ArrayList<Task> listOfTasks = new ArrayList<>();
        Scanner fsc = new Scanner(this.file);

        while (fsc.hasNextLine()) {
            String currLine = fsc.nextLine();
            String[] currLineArr = currLine.split(" \\| ");
            if (currLineArr[0].equals("T")) {
                Todo td = new Todo(currLineArr[2]);
                if (currLineArr[1].equals("1")) {
                    td.markAsDone();
                }
                listOfTasks.add(td);
            }
            if (currLineArr[0].equals("D")) {
                LocalDate date = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
                String formattedDate = date.toString();
                Deadline d = new Deadline(currLineArr[2], formattedDate);
                if (currLineArr[1].equals("1")) {
                    d.markAsDone();
                }
                listOfTasks.add(d);
            }
            if (currLineArr[0].equals("E")) {
                LocalDate date = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
                String formattedDate = date.toString();
                Event e = new Event(currLineArr[2], formattedDate);
                if (currLineArr[1].equals("1")) {
                    e.markAsDone();
                }
                listOfTasks.add(e);
            }
        }
        return listOfTasks;
    }

    public void writeToTextFile(TaskList taskList) throws IOException {

        PrintWriter toWrite = new PrintWriter(this.file);
        String resultToWrite = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            String taskString = taskList.getTask(i).toString();
            String[] taskArr = taskString.split("]");
            String taskToString = "";
            if (taskArr[0].equals("[T")) {
                taskToString += "T | ";
                if (taskArr[1].equals("[✓")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                taskToString += taskArr[2];
            }

            if (taskArr[0].equals("[D")) {
                taskToString += "D | ";
                if (taskArr[1].equals("[✓")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                String[] subTaskArr = taskArr[2].split("\\(by:");
                taskToString += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length() - 1);
            }

            if (taskArr[0].equals("[E")) {
                taskToString += "E | ";
                if (taskArr[1].equals("[✓")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                String[] subTaskArr = taskArr[2].split("\\(at:");
                taskToString += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length() - 1);
            }

            taskToString = taskToString + "\n";
            resultToWrite = resultToWrite + taskToString;
        }
        toWrite.write(resultToWrite);
        toWrite.close();
    }
}
