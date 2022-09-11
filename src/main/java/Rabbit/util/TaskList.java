package Rabbit.util;

import java.util.ArrayList;

import Rabbit.RabbitException.FindFormatException;
import Rabbit.Task.Task;
import Rabbit.RabbitException.AddToListException;
import Rabbit.RabbitException.MarkUnmarkException;
import Rabbit.RabbitException.DeleteException;


public class TaskList {
    private ArrayList<Task> taskList;
    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns tasks in the list that match
     * the searching keyword.
     *
     * @param input the searching keyword.
     * @return the tasks matching the searching keyword.
     * @throws FindFormatException
     */
    public String find(String input) throws FindFormatException {
        try {
            String keyword = Parser.parseFind(input, this);
            if (keyword.equals(" ")) {
                throw new FindFormatException();
            }
            String output = "";
            for (int i = 0; i < this.taskList.size(); i++) {
                String content = this.taskList.get(i).getContent();

                if (content.length() < keyword.length()) {
                    continue;
                }

                for (int j = 0; j < content.length() - keyword.length() + 1; j++) {
                    if (content.substring(j, j + keyword.length()).equals(keyword)) {
                        output += taskList.get(i).toString();
                    }
                }
            }
            return output.length() == 0 ? "I can't find a matching task." : output;
        } catch (FindFormatException e) {
            throw e;
        }
    }
    
    /**
     *  Adds the input lines the user types into
     *  a list with a size no more than 100.
     *
     * @param taskType the type of the task that is to be added.
     * @param input the content (and the time) of the task the user inputs.
     * @return the content of the task.
     */
    public String addToList(TaskType taskType, String input) throws AddToListException {
        if (this.taskList.size() == 100) {
            // throws an exception when there are already 100 lines in
            // the list when the user is trying to input a new line.
            throw new AddToListException(AddToListException.Type.FULL);
        }

        try {
            Task task = Parser.parseTask(taskType, input);
            taskList.add(task);
            return task.getContent();
        } catch (AddToListException e) {
            throw e;
        }
    }

    /**
     * Prints the list of current tasks
     * when the user inputs "list".
     *
     * @return the string of the list;
     */
    public String list() {
        String output = "";

        if (taskList.size() == 0) {
            output = "There is no task in the list.";
        }

        for (int i = 0; i < taskList.size(); i++ ) {
            int index = i + 1;
            output += index + ". " + taskList.get(i) + "\n";
        }
        return output;
    }

    /**
     * Marks the task at index i as done
     *
     * @param input the user's input.
     * @return the content of the task changed.
     * @throws MarkUnmarkException
     */
    public String mark(String input) throws MarkUnmarkException {
        int i = Parser.parseMark(input, this);
        assert taskList.get(i - 1) != null;
        taskList.get(i - 1).markDone();
        return taskList.get(i - 1).getContent();
    }

    /**
     * Unmarks the task at index i as not done
     *
     * @param input the user's input.
     * @return the content of the task changed.
     * @throws MarkUnmarkException
     */
    public String unmark(String input) throws MarkUnmarkException {
        int i = Parser.parseUnmark(input, this);
        assert taskList.get(i - 1) != null;
        taskList.get(i - 1).unmark();
        return taskList.get(i - 1).getContent();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a specified task from the list.
     *
     * @param input The input from the user intended to delete a task.
     * @return the content of the task changed.
     * @throws DeleteException The task to be deleted is not in the list.
     */
    public String delete(String input) throws DeleteException {
        assert this.taskList.size() > 0 : "The size of the list should be larger than 0.";
        int i = Parser.parseDelete(input, this);
        String content = taskList.get(i -1).getContent();
        taskList.remove(i - 1);
        return content;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the task with the respective index.
     *
     * @param i the index.
     * @return the task.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }
}
