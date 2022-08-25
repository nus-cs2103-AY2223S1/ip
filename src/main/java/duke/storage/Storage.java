package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Part of the chatbot that deals with reading and writing of task information in files.
 */
public class Storage {

    /**
     * Reads the file containing previously saved tasks and add them to the current task list of the chatbot.
     * @param file The file containing previously saved tasks.
     * @param tasks The TaskList object that contains the task list.
     * @throws FileNotFoundException When the file of the previously saved tasks cannot be found.
     */
    public void ReadFileContent(File file, TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int curr = 0;
        while(s.hasNext()) {
            String currLine = s.nextLine();
            String[] spiltCurrLine = currLine.split(",", 2);
            if (spiltCurrLine[0].equals("T")) {
                String[] spiltCurrTodo = spiltCurrLine[1].split(",", 2);
                ToDo currTodo = new ToDo(spiltCurrTodo[1]);
                if (spiltCurrTodo[0].equals("1")) {
                    currTodo.markAsDone();
                } else {
                    currTodo.markAsNotDone();
                }
                tasks.addTask(currTodo);
            } else if (spiltCurrLine[0].equals("D")) {
                String[] spiltCurrDeadline = spiltCurrLine[1].split(",", 4);
                LocalDate localDate = null;
                LocalTime localTime = null;
                if (spiltCurrDeadline.length == 4) {
                    localDate = LocalDate.parse(spiltCurrDeadline[2]);
                    localTime = LocalTime.parse(spiltCurrDeadline[3]);
                } else {
                    localDate = LocalDate.parse(spiltCurrDeadline[2]);
                }
                Deadline currDeadline = new Deadline(spiltCurrDeadline[1], localDate, localTime);
                if (spiltCurrDeadline[0].equals("1")) {
                    currDeadline.markAsDone();
                } else {
                    currDeadline.markAsNotDone();
                }
                tasks.addTask(currDeadline);
            } else if (spiltCurrLine[0].equals("E")) {
                String[] spiltCurrEvent = spiltCurrLine[1].split(",", 3);
                Event currEvent =  new Event(spiltCurrEvent[1], spiltCurrEvent[2]);
                if (spiltCurrEvent[0] .equals("1")) {
                    currEvent.markAsDone();
                } else {
                    currEvent.markAsNotDone();
                }
                tasks.addTask(currEvent);
            } else {
                System.out.println("error");
                continue;
            }
        }

    }


    private void writeFileContent(String filePath, String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }

    private void appendFileContent(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Saves the current task list to a text file that can be used in the future for reference.
     * @param taskList The list containing the tasks that is to be saved.
     */
    public void saveTaskToFile(ArrayList<Task> taskList) {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                if (i == 0) {
                    writeFileContent("duke.txt", taskList.get(i).taskSaveInfo());
                } else {
                    appendFileContent("duke.txt", "\n" + taskList.get(i).taskSaveInfo());
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot save task to hard disk" + e.getMessage());
        }
    }

}
