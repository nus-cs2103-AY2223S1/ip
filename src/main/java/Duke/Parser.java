package Duke;

import javafx.application.Platform;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with making sense of the user's commands
 */
public class Parser {
    private TaskList tasklist;
    private Ui bot;
    private Storage storage;

    public Parser(TaskList tasklist, Ui bot, Storage storage) {
        this.tasklist = tasklist;
        this.bot = bot;
        this.storage = storage;
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
        }
        else if (str.equals("sort")) {
            tasklist.sort();
            stringReturned = "tasks have been sorted";

        }
        else if (arr[0].equals("delete")) {
            System.out.println(arr[1]);
            int index = Integer.parseInt(arr[1]);
            Task deleted = tasklist.removeTask(index);
            stringReturned = bot.removeTask2(tasklist.size(), deleted);
        } else if (arr[0].equals("deadline")) {
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
            dateline = arr[counter + 1];
            LocalDate d1 = LocalDate.parse(dateline);
            Task task = new Deadline(description, d1);
            tasklist.addTask(task);
            int total = tasklist.size();
            stringReturned = bot.addTask2(total,task);
        } else if (arr[0].equals("todo")) {
            String todoDes = "";
            for(int i = 1; i<arr.length; i++){
                todoDes = todoDes + arr[i] + " ";
            }
            if (todoDes.equals("")) {
                bot.displayError();
            } else {
                Task task = new ToDo(todoDes, null);
                tasklist.addTask(task);
                int total = tasklist.size();
                stringReturned = bot.addTask2(total,task);
            }
        } else if (arr[0].equals("event")) {
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
            time = arr[counter + 1];
            LocalDate d1 = LocalDate.parse(time);
            Task task = new Event(description, d1);
            tasklist.addTask(task);
            int total = tasklist.size();
            stringReturned = bot.addTask2(total,task);
        } else if (str.equals("list")) {
            stringReturned = bot.printTasks2(tasklist.getOldTasks());
        } else if (arr[0].equals("unmark")) {
            String strnum = arr[1];
            int num = Integer.valueOf(strnum);
            Task task;
                task = tasklist.getOldTask(num-1);
                task.markUndone();;
                tasklist.setOldTasks(num-1,task);
                stringReturned = bot.markTask2(false);
                stringReturned += task.toString();
            } else if (arr[0].equals("mark")) {
                String strnum = arr[1];
                int num = Integer.valueOf(strnum);
                Task task;
                task = tasklist.getOldTask(num-1);
                task.markDone();
                tasklist.setOldTasks(num-1, task);
                stringReturned = bot.markTask2(true);
                stringReturned += task;
            } else if (str.equals("bye")) {
                stringReturned = bot.goodBye2();
                Platform.exit();
            } else{
                bot.displayError();
            }
        storage.replaceTasks("data/Duke2.txt", tasklist.getOldTasks()); //make sure to replace the task after every action
        return stringReturned;
   }



}
