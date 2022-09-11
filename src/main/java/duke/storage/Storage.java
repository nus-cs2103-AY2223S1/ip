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
     *
     * @param file The file containing previously saved tasks.
     * @param tasks The TaskList object that contains the task list.
     * @throws FileNotFoundException When the file of the previously saved tasks cannot be found.
     */
    public void readFileContent(File file, TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int curr = 0;
        while (s.hasNext()) {
            String currLine = s.nextLine();
            String[] spiltCurrLine = currLine.split(",", 2);
            if (spiltCurrLine[0].equals("T")) {
                readTodoContent(spiltCurrLine[1], tasks);
            } else if (spiltCurrLine[0].equals("D")) {
                readDeadlineContent(spiltCurrLine[1], tasks);
            } else if (spiltCurrLine[0].equals("E")) {
                readEventContent(spiltCurrLine[1], tasks);
            } else {
                assert false;
                continue;
            }
        }

    }

    private void readTodoContent(String input, TaskList tasks) {
        String[] spiltCurrTodo = input.split(",", 3);
        ToDo currTodo = new ToDo(spiltCurrTodo[1]);
        checkMarkAsDone(currTodo, spiltCurrTodo[0]);
        checkPriority(currTodo, spiltCurrTodo[2]);
        tasks.addTask(currTodo);
    }

    private void readDeadlineContent(String input, TaskList tasks) {
        String[] spiltCurrDeadline = input.split(",", 5);
        LocalDate localDate = null;
        LocalTime localTime = null;
        if (spiltCurrDeadline.length == 5) {
            localDate = LocalDate.parse(spiltCurrDeadline[2]);
            localTime = LocalTime.parse(spiltCurrDeadline[3]);
        } else if (spiltCurrDeadline.length == 4){
            localDate = LocalDate.parse(spiltCurrDeadline[2]);
        } else {
            assert false;
        }
        Deadline currDeadline = new Deadline(spiltCurrDeadline[1], localDate, localTime);
        if (spiltCurrDeadline.length == 5) {
            checkPriority(currDeadline, spiltCurrDeadline[4]);
        } else if (spiltCurrDeadline.length == 4) {
            checkPriority(currDeadline, spiltCurrDeadline[3]);
        } else {
            assert false;
        }
        checkMarkAsDone(currDeadline, spiltCurrDeadline[0]);
        tasks.addTask(currDeadline);
    }

    private void readEventContent(String input, TaskList tasks) {
        String[] spiltCurrEvent = input.split(",", 4);
        Event currEvent = new Event(spiltCurrEvent[1], spiltCurrEvent[2]);
        checkMarkAsDone(currEvent, spiltCurrEvent[0]);
        checkPriority(currEvent, spiltCurrEvent[3]);
        tasks.addTask(currEvent);
    }

    private void checkMarkAsDone(Task task, String input) {
        if (input.equals("1")) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    private void checkPriority(Task task, String priority) {
        if (priority.equals("HIGH")) {
            task.setTaskPriority("high");
        } else if (priority.equals("MEDIUM")) {
            task.setTaskPriority("medium");
        } else if (priority.equals("LOW")) {
            task.setTaskPriority("low");
        } else {
            assert false;
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
     *
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
