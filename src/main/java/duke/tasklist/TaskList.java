package duke.tasklist;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.dukeexception.DukeException;
import duke.storage.Storage;

/**
 * A class containing taskList and its operation.
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();
    /**
     * Count the task in the taskList.
     * @return The number of task in the taskList.
     */
    public int countTask() {
        int res = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) != null) {
                res += 1;
            }
        }
        return res;
    }
    /**
     *  Delete the indexed task and print out reply and update corresponding file.
     * @param i The index of the deleted task.
     * @param storage The storage containing the this taskList.
     */
    public String delete(int i, Storage storage) throws DukeException{
        try {
            int index = i - 1;
            Task deletedTask = this.taskList.remove(i - 1);
            String res = (" Noted. I've removed this task:\n" + deletedTask.printTask() +
                    "\n" + "Now you have " + this.countTask() + " tasks in the list.");
            storage.updateFile(this.taskList);
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, the command is not in right format.");
        }
    }

    /**
     * Add a task to the taskList and print response.
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return ("Got it. I've added this task:\n"+task.printTask()+
                "\nNow you have "+this.countTask()+" task in the list.");
    }
    /**
     * The method that list all the tasks in a formatted string.
     * @return A string of lines of tasks in the print format.
     */
    public String listAllTask() {
        String res = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i)!=null) {
                res += (i+1)+". " + this.taskList.get(i).printTask() + "\n";
            }
        }
        return  "Here are the tasks in your list:\n" + res;
    }
    //mark a certain task as done and print reply
    public String markAsDone(int i, Storage storage) throws DukeException {
        this.taskList.get(i).taskDone();
        String res = ("Nice! I've marked this task as done:\n"
                +this.taskList.get(i).printTask());
        storage.updateFile(this.taskList);
        return res;
    }
    //mark a certain task as not done and print reply
    public String markUndone(int i, Storage storage) throws DukeException {
        this.taskList.get(i).taskUndone();
        String res = ("OK, I've marked this task as not done yet:\n"
                +this.taskList.get(i).printTask());
        storage.updateFile(this.taskList);
        return res;
    }

    public List<Task> getTaskList(){
        return this.taskList;
    }
    public String getASpecificDay(String s) {
        try {
            String day = s.split(" ")[1];
            LocalDate d = LocalDate.parse(day);
            String res="";
            for (int i = 0; i < this.taskList.size(); i++) {
                if (this.taskList.get(i).getDay() != null) {
                    if (this.taskList.get(i).getDay().equals(d)) {
                        res += this.taskList.get(i).printTask() + "\n";
                    }
                }
            }
            if (res.equals("")) {
                return "You don't have tasks on this day.";
            } else {
                return res;
            }
        } catch (Exception e) {
            return "the input format is not correct " + e.getMessage();
        }
    }
    public String find(String fullCommand) {
        String target = fullCommand.split(" ",2)[1];
        String res = "Here is the matching tasks in your list:\n";
        int count = 1;
        for (int i = 0; i < countTask(); i++) {
            String[] temp = this.taskList.get(i).printTask().split(" ");
            for (int j = 0; j < temp.length; j++){
                if (temp[j].equals(target)) {
                    res += count + "." + this.taskList.get(i).printTask() + "\n";
                    count ++;
                    break;
                }
            }
        }
        if (count == 1){
            res += "There is no match for your search.";
        }
        return res;
    }
}
