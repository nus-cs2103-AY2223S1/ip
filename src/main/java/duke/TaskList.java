package duke;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public void load(Task task){
        this.taskList.add(task);
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    /**
     * Lists out all the tasks currently
     * in the task list
     *
     * @return String list of tasks
     */
    public String handleList(){
        String log = "Tasks that you have:";
        for (int i = 0; i < taskList.size(); i++) {
            log += String.format("\n %d. %s", i + 1, this.taskList.get(i));
        }
        return log;
    }

    /**
     * Marks the task specified as done
     *
     * @param cmd User input
     * @return String Mark message
     * @throws IncompleteParamException incomplete cmd
     * @throws OutOfListException Task number is not within list
     */

    public String handleMark(String cmd) throws DukeException {
        int min_length = "mark ".length();
        if (cmd.length() <= min_length) {
            throw new IncompleteParamException(cmd);
        } else {
            int taskNumber = Integer.valueOf(cmd.substring("mark ".length()));
            if (taskNumber < 1 || taskNumber > this.taskList.size()) {
                throw new OutOfListException(cmd);
            } else {
                Task task = this.taskList.get(taskNumber - 1);
                task.markAsDone();
                return String.format("Nice! I've marked this task as done: \n %s", task);
            }
        }
    }

    /**
     * Marks the task specified as undone
     *
     * @param cmd User input
     * @return String Unmark message
     * @throws IncompleteParamException incomplete cmd
     * @throws OutOfListException Task number is not within list
     */
    public String handleUnmark(String cmd) throws DukeException {
        int min_length = "unmark ".length();
        if (cmd.length() <= min_length) {
            throw new IncompleteParamException(cmd);
        } else {
            int taskNumber = Integer.valueOf(cmd.substring("unmark ".length()));
            if (taskNumber < 1 || taskNumber > this.taskList.size()) {
                throw new OutOfListException(cmd);
            } else {
                Task task = this.taskList.get(taskNumber - 1);
                task.unmarkAsDone();
                return String.format("Ok! I have marked this task as not done yet: \n %s", task);
            }

        }
    }

    /**
     * Creates new ToDo task to be added into task list.
     *
     * @param cmd User input
     * @return String Add message
     * @throws IncompleteParamException incomplete cmd
     */
    public String handleToDo(String cmd) throws DukeException{
        int min_length = "todo ".length();
        if (cmd.length() <= min_length) {
            throw new IncompleteParamException(cmd);
        } else {
            String taskdes = cmd.substring("todo ".length());
            Task task = new ToDo(taskdes);
            this.taskList.add(task);
            return String.format("Got it. I've added this task: \n %s \n  Now you have %d tasks in the list.", task ,this.taskList.size());
        }
    }

    /**
     * Creates new Deadline task to be added into task list.
     *
     * @param cmd User input
     * @return String Add message
     * @throws IncompleteParamException incomplete cmd
     */
    public String handleDeadline(String cmd) throws DukeException {
        int endPointer = cmd.indexOf('/');
        if (cmd.length() <= endPointer + 3 || endPointer == -1) {
            throw new IncompleteParamException(cmd);
        } else {
            String taskdes = cmd.substring("deadline ".length(), endPointer);
            String by = cmd.substring(endPointer + 4);
            Task task = new Deadline(taskdes, by);
            this.taskList.add(task);
            return String.format("Got it. I've added this task: \n %s \n  Now you have %d tasks in the list.", task, this.taskList.size());
        }
    }

    /**
     * Creates new Event task to be added into task list.
     *
     * @param cmd User input
     * @return String Add message
     * @throws IncompleteParamException incomplete cmd
     */
    public String handleEvent(String cmd) throws DukeException {
        int endPointer = cmd.indexOf('/');
        if (cmd.length() <= endPointer + 3 || endPointer == -1) {
            throw new IncompleteParamException(cmd);
        } else {
            String taskdes = cmd.substring("event ".length(), endPointer);
            String at = cmd.substring(endPointer + 4);
            Task task = new Event(taskdes, at);
            this.taskList.add(task);
            return String.format("Got it. I've added this task: \n %s \n  Now you have %d tasks in the list.", task, this.taskList.size());

        }
    }

    /**
     * Deletes task from list
     *
     * @param cmd User input
     * @return String Delete message
     * @throws IncompleteParamException incomplete cmd
     * @throws OutOfListException Task number is not within list
     */
    public String handleDelete(String cmd) throws DukeException{
        int min_length = "delete ".length();
        if (cmd.length() <= min_length) {
            throw new IncompleteParamException(cmd);
        } else {
            int taskNumber = Integer.valueOf(cmd.substring("delete ".length()));
            if (taskNumber < 1 || taskNumber > this.taskList.size()) {
                throw new OutOfListException(cmd);
            } else {
                Task task = this.taskList.get(taskNumber - 1);
                this.taskList.remove(taskNumber - 1);
                return String.format("Got it. I've removed this task: \n %s \n  Now you have %d tasks in the list.", task ,this.taskList.size());
            }
        }
    }
}
