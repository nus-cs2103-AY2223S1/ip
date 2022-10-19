package duke;

import javafx.application.Platform;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with making sense of the user's commands
 */
public class Parser {
    private TaskList tasklist;
    private Ui bot;
    private Storage storage;
    private String pathName;

    public Parser(TaskList tasklist, Ui bot, Storage storage, String pathName) {
        this.tasklist = tasklist;
        this.bot = bot;
        this.storage = storage;
        this.pathName = pathName;
    }

    /**
     * Reads user input and performs action depending on input.
     *
     * @param str User input.
     * @return String representing bot's reply to user.
     * @throws DukeException If input command is not recognised or if input is blank.
     * @throws IOException If there is an error writing to file.
     */
    public String readInput2(String str) throws DukeException, IOException {
        String stringReturned = "Default String";
        System.out.println("code comes here");
        String[] arr = str.split(" ");
        if (arr[0].equals("find")) {
            List<String> matchlist = tasklist.findMatches(str.substring(5));
            stringReturned = bot.printMatches2(matchlist);
        }  else if (str.equals("sort")) {
            tasklist.sort();
            stringReturned = "tasks have been sorted";
        } else if (arr[0].equals("delete")) {
            System.out.println(arr[1]);
            int index = Integer.parseInt(arr[1]);
            Task deleted = tasklist.removeTask(index);
            stringReturned = bot.removeTask2(tasklist.size(), deleted);
        } else if (arr[0].equals("deadline")) {
            Task task = parseDeadline(arr);
            tasklist.addTask(task);
            int total = tasklist.size();
            stringReturned = bot.addTask2(total,task);
        } else if (arr[0].equals("todo")) {
            String todoDes = parseTodo(arr);
            if (todoDes.equals("")) {
                bot.displayError();
            } else {
                Task task = new ToDo(todoDes, null);
                tasklist.addTask(task);
                int total = tasklist.size();
                stringReturned = bot.addTask2(total,task);
            }
        } else if (arr[0].equals("event")) {
            Task task = parseEvent(arr);
            tasklist.addTask(task);
            int total = tasklist.size();
            stringReturned = bot.addTask2(total,task);
        } else if (str.equals("list")) {
            stringReturned = bot.printTasks2(tasklist.getOldTasks());
        } else if (arr[0].equals("unmark")) {
            String strnum = arr[1];
            int num = Integer.valueOf(strnum);
            stringReturned = unmarkTask(num);
        } else if (arr[0].equals("mark")) {
            String strnum = arr[1];
            int num = Integer.valueOf(strnum);
                stringReturned = markTask(num);
        } else if (str.equals("bye")) {
            stringReturned = bot.goodBye2();
            Platform.exit();
        } else{
            bot.displayError();
        }
        storage.replaceTasks(pathName, tasklist.getOldTasks()); //make sure to replace the task after every action
        return stringReturned;
   }

    /**
     * Unmarks a task in the tasklist.
     *
     * @param num Index of task to unmark.
     * @return String description of task unmarked.
     */
    public String unmarkTask(int num) {
        String str;
        Task task;
        task = tasklist.getOldTask(num-1);
        task.markUndone();
        tasklist.setOldTasks(num-1,task);
        str = bot.markTask2(false);
        str += task.toString();
        return str;
    }
    /**
     * Marks a task in the tasklist.
     *
     * @param num Index of task to mark.
     * @return String description of task unmarked.
     */
    public String markTask(int num) {
        String str;
        Task task;
        task = tasklist.getOldTask(num-1);
        task.markDone();
        tasklist.setOldTasks(num-1, task);
        str = bot.markTask2(true);
        str += task;
        return str;
    }

    /**
     * Parses a to do task.
     *
     * @param arr User input.
     * @return String representation of to do task being added.
     */
    public String parseTodo(String[] arr) {
        String todoDes = "";
        for(int i = 1; i<arr.length; i++){
            todoDes = todoDes + arr[i] + " ";
        }
        return todoDes;
    }

    /**
     * Parses a deadline task.
     *
     * @param arr User input.
     * @return Deadline object based on user input.
     */
    public Task parseDeadline(String[] arr) throws DukeException {
        String description = "";
        String dateline="";
        int counter = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i].equals("/by")){
                break;
            } else {
                description = description + arr[i]+ " ";
                counter++;
            }
        }
        try{
            dateline = arr[counter + 1];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Format for deadline not proper");
        }
        LocalDate d1;
        try{
            d1 = LocalDate.parse(dateline);
        } catch(DateTimeParseException e) {
            throw new DukeException("invalid date received");
        }
        Task task = new Deadline(description, d1);
        return task;
    }

    /**
     * Parses a event task.
     * @param arr User input.
     * @return Event object based on user input.
     */
    public Task parseEvent(String[] arr) throws DukeException {
        String description = "";
        String time = "";
        int counter = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i].equals("/at")){
                break;
            } else {
                description = description + arr[i]+ " ";
                counter++;
            }
        }
        try{
            time = arr[counter + 1];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Format for event not proper");
        }
        LocalDate d1;
        try{
            d1 = LocalDate.parse(time);
        } catch(DateTimeParseException e) {
            throw new DukeException("invalid date received");
        }
        Task task = new Event(description, d1);
        return task;
    }


}
