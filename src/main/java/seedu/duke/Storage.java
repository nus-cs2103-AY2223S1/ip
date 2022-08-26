package seedu.duke;

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
 * Represents the Storage for Duke.
 */
public class Storage {

    public String filePath;
    public ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new ArrayList<>();
    }


    /**
     * Returns list of tasks in an ArrayList based on lines in data/duke.txt
     * @return list of tasks.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> taskList = new ArrayList<>();
        this.getTasks(taskList);
        return taskList;
    }

    /**
     * Returns LocalDate object of task.
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
        System.out.println(dateToParse);
        return LocalDate.parse(dateToParse);
    }

    /**
     * Adds ToDo object to tasklist in TaskList.
     * @param taskList this.tasklist of TaskList object.
     * @param isMarked true if the task being read is marked.
     * @param description Task description.
     */
    public void addToDo(ArrayList<Task> taskList, String isMarked, String description) {
//        System.out.println("added T");
        ToDo todo = new ToDo(description);
        if (isMarked.equals("1")) {
            todo.mark();
        }
        taskList.add(todo);
    }

    /**
     * Adds Deadline or Event object to tasklist in TaskList.
     * @param taskList this.tasklist of TaskList object.
     * @param s String differentiates between a Deadline or Event object.
     * @param isMarked true if the task being read is marked.
     * @param description Task description.
     * @param datetime String datetime based on user input.
     */
    public void addDeadlineOrEvent(ArrayList<Task> taskList, String s, String isMarked, String description, String datetime){
//        System.out.println("type:" + s);
        if (s.equals("D")){
//            System.out.println("added D");
            String[] dateAndTime = datetime.split(" ");
            System.out.println(dateAndTime.length);
            if (dateAndTime.length == 2) {
                String dueDate = dateAndTime[0];
                String dueTime = dateAndTime[1];
                LocalDate localDate = getLocalDate(dueDate);
                Task deadline = new Deadline(description, localDate, dueTime);
                if (isMarked.equals("1")) {
                    deadline.mark();
                }
                taskList.add(deadline);
            } else {
                String dueDate = dateAndTime[0];
                LocalDate localDate = getLocalDate(dueDate);
                Task deadline = new Deadline(description, localDate);
                if (isMarked.equals("1")) {
                    deadline.mark();
                }
                taskList.add(deadline);
            }
        } else {
//            System.out.println("added E");
            Event event = new Event(description, datetime);
            if (isMarked.equals("1")) {
                event.mark();
            }
            taskList.add(event);
        }
    }

    /**
     * Reads lines from data/duke.txt and updates Duke.tasks with tasks stored in duke.txt.
     * @param taskList tasklist of Storage object.
     * @throws FileNotFoundException
     */
    // settled reading task from text file
    public void getTasks(ArrayList<Task> taskList) throws FileNotFoundException{
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] taskInList = s.nextLine().split(" \\| ");
//            System.out.println(taskInList.length);
            if (taskInList.length == 4) {
                addDeadlineOrEvent(taskList, taskInList[0], taskInList[1],taskInList[2],taskInList[3]);
            } else {
                addToDo(taskList, taskInList[1],taskInList[2]);
            }
        }
    }

    /**
     * Returns the string representation of the Event object.
     * @param event
     * @return string representation of the Event object.
     */
    public static String getEventDueDate(Event event) {
        return event.getDueTime();
    }

    /**
     * Returns the string representation of the Deadline object.
     * @param deadline
     * @return string representation of the Deadline object.
     */
    public static String getDeadlineDueDate(Deadline deadline) {
        return deadline.dueDateToString();
    }

    // delete file, add lines to write, put string together and write in file writer
    // NEVER DELETE WRITE THE FILE IN THE SAME METHOD

    /**
     * Updates data/duke.txt whenever there is a change to the list of tasks.
     * @param tl
     * @throws IOException
     */
    public static void rewriteTasks(TaskList tl) throws IOException{
        ArrayList<Task> currTaskList = tl.taskList;
        String path = "data/duke.txt";
        FileWriter fw = new FileWriter(path);
        ArrayList<String> taskListArray = new ArrayList<>();
        for (int i=0; i < currTaskList.size(); i++){
            if (currTaskList.get(i) instanceof ToDo) {
                System.out.println("task is a todo");
                String taskString = String.format("T | %s | %s", currTaskList.get(i).getIsDone() ? 1 : 0, currTaskList.get(i).getDescription());
                taskListArray.add(taskString);
            } else if (currTaskList.get(i) instanceof Deadline) {
                System.out.println("task is a deadline");
                String taskString = String.format("T | %s | %s | %s", currTaskList.get(i).getIsDone() ? 1 : 0, currTaskList.get(i).getDescription(), getDeadlineDueDate((Deadline) currTaskList.get(i)));
                taskListArray.add(taskString);
            } else {
                System.out.println("task is an event");
                String taskString = String.format("T | %s | %s | %s", currTaskList.get(i).getIsDone() ? 1 : 0, currTaskList.get(i).getDescription(), getEventDueDate((Event) currTaskList.get(i)));
                taskListArray.add(taskString);
            }
        }
        String taskListString = "";
        for (int j=0; j < currTaskList.size(); j++) {
            taskListString += taskListArray.get(j);
            if (j != currTaskList.size() -1) {
                taskListString += "\n";
            }
        }
        fw.write(taskListString);
        fw.close();
    }
}
