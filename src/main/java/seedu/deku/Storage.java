package seedu.deku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Storage for Deku.
 */
public class Storage {

    private String filePath;
    private ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new ArrayList<>();
    }


    /**
     * Returns list of tasks in an ArrayList based on lines in data/deku.txt
     *
     * @return list of tasks.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        this.getTasks(taskList);
        return taskList;
    }

    /**
     * Returns LocalDate object of task.
     *
     * @param date given by user input
     * @return LocalDate object.
     */
    public static LocalDate getLocalDate(String date) {
        String[] dateDetails = date.split("-");
        String day = dateDetails[0];
        String month = dateDetails[1];
        String year = dateDetails[2];
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        List<String> list = Arrays.asList(year, month, day);

        String dateToParse = String.join("-", list);
        return LocalDate.parse(dateToParse);
    }

    /**
     * Adds ToDo object to tasklist in TaskList.
     *
     * @param taskList this.tasklist of TaskList object.
     * @param isMarked true if the task being read is marked.
     * @param description Task description.
     */
    public void addToDo(ArrayList<Task> taskList, String isMarked, String description) {
        ToDo todo = new ToDo(description);
        if (isMarked.equals("1")) {
            todo.mark();
        }
        taskList.add(todo);
    }

    /**
     * Adds Deadline or Event object to tasklist in TaskList.
     *
     * @param taskList this.tasklist of TaskList object.
     * @param s String differentiates between a Deadline or Event object.
     * @param isMarked true if the task being read is marked.
     * @param description Task description.
     * @param datetime String datetime based on user input.
     */
    public void addDeadlineOrEvent(ArrayList<Task> taskList, String s, String isMarked, String description,
                                   String datetime) {
        ArrayList assertionList = new ArrayList();
        assertionList.add("D");
        assertionList.add("E");
        assert assertionList.contains(s);
        if (s.equals("D")) {

            String[] dateAndTime = datetime.split(" ");
            if (dateAndTime.length == 2) {
                String dueDate = dateAndTime[0];
                String dueTime = dateAndTime[1];
                LocalDate localDate = getLocalDate(dueDate);
                String dueDateTime = dueDate + " " + dueTime;
                Task deadline = new Deadline(description, localDate, dueTime, dueDateTime);
                if (isMarked.equals("1")) {
                    deadline.mark();
                }
                taskList.add(deadline);
            } else {
                String dueDate = dateAndTime[0];
                LocalDate localDate = getLocalDate(dueDate);
                Task deadline = new Deadline(description, localDate, dueDate);
                if (isMarked.equals("1")) {
                    deadline.mark();
                }
                taskList.add(deadline);
            }
        } else {
            Event event = new Event(description, datetime);
            if (isMarked.equals("1")) {
                event.mark();
            }
            taskList.add(event);
        }
    }

    /**
     * Reads lines from data/deku.txt and updates Deku.tasks with tasks stored in deku.txt.
     *
     * @param taskList tasklist of Storage object.
     * @throws FileNotFoundException
     */
    public void getTasks(ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File("data/deku.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskInList = s.nextLine().split(" \\| ");
            if (taskInList[0].equals("D") || taskInList[0].equals("E")) {
                addDeadlineOrEvent(taskList, taskInList[0], taskInList[1], taskInList[2], taskInList[3]);
            } else {
                addToDo(taskList, taskInList[1], taskInList[2]);
            }
        }
    }

    /**
     * Returns the string representation of the Event object.
     *
     * @param event
     * @return string representation of the Event object.
     */
    public static String getEventDueDate(Event event) {
        return event.getDueTime();
    }

    /**
     * Returns the string representation of the Deadline object.
     *
     * @param deadline
     * @return string representation of the Deadline object.
     */
    public static String getDeadlineDueDate(Deadline deadline) {
        return deadline.dueDateToString();
    }

    /**
     * Updates data/deku.txt whenever there is a change to the list of tasks.
     *
     * @param tl
     * @throws IOException
     */
    public static void rewriteTasks(TaskList tl) throws IOException {
        ArrayList<Task> currTaskList = tl.getTaskList();
        File theDir = new File("/data/deku.txt");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        String path = "data/deku.txt";
        FileWriter fw = new FileWriter(path);
        ArrayList<String> taskListArray = new ArrayList<>();
        for (int i = 0; i < currTaskList.size(); i++) {
            if (currTaskList.get(i) instanceof ToDo) {
                String taskString = String.format("T | %s | %s", currTaskList.get(i).getIsDone() ? 1 : 0,
                        currTaskList.get(i).getDescription());
                taskListArray.add(taskString);
            } else if (currTaskList.get(i) instanceof Deadline) {
                String taskString = String.format("D | %s | %s | %s", currTaskList.get(i).getIsDone() ? 1 : 0,
                        currTaskList.get(i).getDescription(), ((Deadline) currTaskList.get(i)).dueDateString);
                taskListArray.add(taskString);
            } else {
                String taskString = String.format("E | %s | %s | %s", currTaskList.get(i).getIsDone() ? 1 : 0,
                        currTaskList.get(i).getDescription(), getEventDueDate((Event) currTaskList.get(i)));
                taskListArray.add(taskString);
            }
        }
        String taskListString = "";
        for (int j = 0; j < currTaskList.size(); j++) {
            taskListString += taskListArray.get(j);
            if (j != currTaskList.size() - 1) {
                taskListString += "\n";
            }
        }
        fw.write(taskListString);
        fw.close();
    }
}
