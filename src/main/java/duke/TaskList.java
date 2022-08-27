package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    int numTasks;

    /**
     * Constructs a TaskList object given a set of tasks
     *
     * @param tasks The given ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numTasks = tasks.size();
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
    }

    /**
     * This method adds a task to the given task-list.
     *
     * @param noType The input String with type removed.
     * @param type The type of the task
     */
    public String addTask(String noType, String type, boolean done, boolean load)
            throws DukeException {
        String confirmation = "Got it. I've added this task:\n  ";
        String number = "\nNow you have " + (numTasks + 1) + " tasks in the list.";

        if(noType == ""){
            throw new DukeException("☹ OOPS!!! The description of a " + type +" cannot be empty.");
        }

        if(type.equals("deadline")) {
            String[] split = noType.split(" /by ");
            if(split.length != 2){
                throw new DukeException("☹ OOPS!!! A single deadline must be specified." +
                        "e.g. deadline finish work /by June 9th");
            }
            tasks.add(numTasks, new Deadline(split[0], split[1], done));
            numTasks ++;
            if(!load) {
                return(confirmation + tasks.get(numTasks - 1) + number);
            }
        } else if(type.equals("event")){
            String[] split = noType.split(" /at ");
            if(split.length != 2){
                throw new DukeException("☹ OOPS!!! A single timeframe must be specified." +
                        "e.g. event meeting /at Thursday 2-4pm");
            }
            tasks.add(numTasks, new Event(split[0], split[1], done));
            numTasks ++;
            if(!load) {
                return(confirmation + tasks.get(numTasks - 1) + number);
            }
        } else {
            tasks.add(numTasks, new ToDo(noType, done));
            numTasks ++;
            if(!load) {
                return(confirmation + tasks.get(numTasks - 1) + number);
            }
        }
        return "";
    }

    /**
     * Deletes a task from the task list
     *
     * @param index The index of the task being deleted.
     * @return A string that specifies information on the deletion attempt.
     * @throws DukeException
     */
    public String deleteTask(String index) throws  DukeException{
        if(!index.matches("\\d+")) {
            throw new DukeException("☹ OOPS!!! Please specify an integer index.");
        } else if(Integer.valueOf(index) > numTasks) {
            throw new DukeException("☹ OOPS!!! The index specified is out of range.");
        }
        Task task = tasks.get(Integer.valueOf(index) - 1);
        tasks.remove(Integer.valueOf(index) - 1);
        numTasks--;
        return("Noted. I've removed this task:\n  " + task + "\nNow you have " + (numTasks) +
                " tasks in the list.");

    }

    /**
     * Marks a task as done
     *
     * @param index The index of the task.
     */
    public String markTask(String index)
            throws DukeException {
        if(!index.matches("\\d+")) {
            throw new DukeException("☹ OOPS!!! Please specify an integer index.");
        } else if(Integer.valueOf(index) > numTasks) {
            throw new DukeException("☹ OOPS!!! The index specified is out of range.");
        }
        Task task = tasks.get(Integer.valueOf(index) - 1);
        task.mark();
        return("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Marks a task as not done
     *
     * @param index The index of the task.
     */
    public String unmarkTask(String index)
            throws DukeException {
        //Note potential exception: Duke.Task index exceeded
        if(!index.matches("\\d+")) {
            throw new DukeException("☹ OOPS!!! Please specify an integer index.");
        } else if(Integer.valueOf(index) > numTasks) {
            throw new DukeException("☹ OOPS!!! The index specified is out of range.");
        }
        Task task = tasks.get(Integer.valueOf(index) - 1);
        task.unmark();
        return("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * Lists out the current tasks.
     */
    public String listTasks() {
        String list = "Here are the tasks in your list:\n";
        for(int i = 0; i < numTasks; i++) {
            //Add index
            list +=  (i+1) + ".";
            //Add task
            list += tasks.get(i);
            //Add nextline
            if(i != numTasks -1) {
                list += "\n";
            }
        }
        return list;
    }

    /**
     * Retrieves the length of the task list.
     *
     * @return The length of the task list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Converts the TaskList to a string for writing into a file.
     *
     * @return The TaskList as a string.
     */
    public String getWrite() {
        String s = "";
        for(int i = 0; i < numTasks; i++) {
            //Get task
            Task t =  tasks.get(i);
            //Add general details
            s += t.getType() + " - " + t.isDone() + " - " + t.getTitle();
            //Add additional details
            if(t instanceof Deadline){
                s += " - /by " + ((Deadline) t).getTime();
            } else if (t instanceof Event){
                s += " - /at " + ((Event) t).getTime();
            }
            //Add newline
            s += "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;
            System.out.print(taskList.listTasks());
            System.out.print(this.listTasks());
            if(taskList.listTasks().equals(this.listTasks())) {
                return true;
            }
//            return true;
        }
        return  false;
    }

    /**
     * Finds tasks that match the given search input.
     *
     * @param input Search input.
     * @return The list of matching tasks.
     */
    public String find(String input) throws DukeException {
        String list = "Here are the matching tasks in your list:\n";
        int found = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitle().contains(input)){
                found += 1;
                //Add index
                list +=  (found) + ".";
                //Add task
                list += tasks.get(i);
                //Add nextline
                list += "\n";
            }
        }
        if (found > 0) {
            list = list.substring(0, list.length() - 1);
        } else {
            throw new DukeException("No matching tasks found! Please try something else.");
        }
        return list;
    }
}
