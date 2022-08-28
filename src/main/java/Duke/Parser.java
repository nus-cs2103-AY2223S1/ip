package Duke;

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
     * Reads user input and performs action depending on input
     * @throws DukeException If input command is not recognised or if input is blank.
     * @throws IOException If there is an error writing to file.
     */
    public void readInput() throws DukeException, IOException {
        Scanner scanner = new Scanner(System.in);
        String str;
        int initialSize = tasklist.oldTasksSize();
        do {
            str = scanner.next();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("find")) {
                String match = scanner.nextLine();
                List<String> matchlist = tasklist.findMatches(match);
                bot.printMatches(matchlist);
            }
            else if (str.equals("delete")) {
                int index = scanner.nextInt();
                scanner.nextLine();
                Task deleted = tasklist.removeTask(index);
                bot.removeTask(tasklist.size(), deleted);
            }else if (str.equals("deadline")) {
                String description = "";
                String dateline="";
                while (scanner.hasNext()) {
                    String temp = scanner.next();
                    if (temp.equals("/by")) {
                        break;
                    }
                    description = description + temp +" ";
                }
                dateline = scanner.nextLine();
                LocalDate d1 = LocalDate.parse(dateline.substring(1));
                Task task = new Deadline(description, d1);
                tasklist.addTask(task);
                int total = tasklist.size();
                bot.addTask(total,task);
            } else if (str.equals("todo")) {
                String todoDes = scanner.nextLine();
                if (todoDes.equals("")) {
                    bot.displayError();
                }
                Task task = new ToDo(todoDes.substring(1));
                tasklist.addTask(task);
                int total = tasklist.size();
                bot.addTask(total,task);
            } else if (str.equals("event")) {
                String description = "";
                String time="";
                while (scanner.hasNext()) {
                    String temp = scanner.next();
                    if (temp.equals("/at")) {
                        break;
                    }
                    description = description + temp +" ";
                }
                time = scanner.nextLine();
                LocalDate d1 = LocalDate.parse(time.substring(1));
                Task task = new Event(description, d1);
                tasklist.addTask(task);
                int total = tasklist.size();
                bot.addTask(total,task);
            }
            else if (str.equals("list")) {
                bot.printTasks(tasklist.getOldTasks(),tasklist.getNewTasks());
                scanner.nextLine();
            }
            else if (str.equals("unmark")) {
                String strnum = scanner.next();
                int num = Integer.valueOf(strnum);
                Task task;
                if (num > tasklist.oldTasksSize()) {;
                    task = tasklist.getNewTasks(num-tasklist.oldTasksSize()-1);
                    task.markUndone();
                    tasklist.setNewTasks(num-tasklist.oldTasksSize()-1,task);
                } else {
                    task = tasklist.getOldTasks(num-1);
                    task.markUndone();;
                    tasklist.setOldTasks(num-1,task);
                }
                bot.markTask(false);
                System.out.println(task);
                scanner.nextLine();
            } else if (str.equals("mark")) {
                String strnum = scanner.next();
                int num = Integer.valueOf(strnum);
                Task task;
                if (num > tasklist.oldTasksSize()) {
                    task = tasklist.getNewTasks(num-tasklist.oldTasksSize()-1);
                    task.markDone();
                    tasklist.setNewTasks(num-tasklist.oldTasksSize()-1, task);
                } else {
                    task = tasklist.getOldTasks(num-1);
                    task.markDone();
                    tasklist.setOldTasks(num-1, task);
                }
                bot.markTask(true);
                System.out.println(task);
                scanner.nextLine();
            }
            else{
                if (tasklist.oldTasksSize() < initialSize) {
                    storage.replaceTasks("data/Duke2.txt", tasklist.getOldTasks(), tasklist.getNewTasks());
                } else {
                    storage.replaceTasks("data/Duke2.txt", tasklist.getOldTasks(), tasklist.getNewTasks());
                    //storage.addTasks("data/Duke2.txt",tasklist.newTasks);
                }
                bot.displayError();
            }

        }  while (!str.equals("bye"));
        if (str.equals("bye")) {
            if(tasklist.oldTasksSize() < initialSize){
                storage.replaceTasks("data/Duke2.txt", tasklist.getOldTasks(), tasklist.getNewTasks());
            } else{
                storage.replaceTasks("data/Duke2.txt", tasklist.getOldTasks(), tasklist.getNewTasks());
                //storage.addTasks("data/Duke2.txt",tasklist.newTasks);
            }
            bot.goodBye();
        }
   }

}
