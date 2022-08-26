package Rabbit.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Rabbit.Task.Task;
import Rabbit.Task.Event;
import Rabbit.Task.Deadline;
import Rabbit.Task.Todo;
import Rabbit.RabbitException.AddToListException;
import Rabbit.RabbitException.MarkUnmarkException;
import Rabbit.RabbitException.DeleteException;


public class TaskList {
    private ArrayList<Task> list;
    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     *  Adds the input lines the user types into
     *  a list with a size no more than 100.
     *
     * @param task the type of the task that is to be added.
     * @param input the content (and the time) of the task the user inputs.
     * @return the content of the task.
     */
    public String addToList(TaskType task, String input) throws AddToListException {
        if (this.list.size() == 100) {
            // throws an exception when there are already 100 lines in
            // the list when the user is trying to input a new line.
            throw new AddToListException(AddToListException.Type.FULL);
        }

        try {
            // initialise the task to be added
            Task added = new Todo("");
            String content = "";
            LocalDateTime time = LocalDateTime.now();
            switch (task) {
            case TODO:
                content = input.substring(5);
                added = new Todo(content);
                list.add(added);
                break;
            case DEADLINE:
                // the content and time of the task
                String deadline = input.substring(9);
                // the index of the character in the string before which is the content
                int i = Parser.parseContent(deadline);
                content = deadline.substring(0, i - 1);
                time = Parser.parseTime(deadline.substring(i + 1));
                added = new Deadline(content, time);
                list.add(added);
                break;
            case EVENT:
                // the content and time of the task
                String event = input.substring(6);
                // the index of the character in the string before which is the content
                int j = Parser.parseContent(event);
                content = event.substring(0, j - 1);
                time = Parser.parseTime(event.substring(j + 1));
                added = new Event(event.substring(0, j - 1), time);
                this.list.add(added);
                break;
            }
            return added.getContent();
        } catch (StringIndexOutOfBoundsException e) {
            // if the format is wrong, there will be a
            // StringIndexOutOfBoundsException, catch it
            // and throw an AddToListException
            throw new AddToListException(AddToListException.Type.FORMAT);
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

        if (list.size() == 0) {
            output = "There is no task in the list.";
        }

        for (int i  = 0; i < list.size(); i++ ) {
            int index = i + 1;
            output += index + ". " + list.get(i) + "\n";
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

        list.get(i - 1).markDone();
        return list.get(i - 1).getContent();
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

        list.get(i - 1).unmark();
        return list.get(i - 1).getContent();
    }

    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a specified task from the list.
     *
     * @param input The input from the user intended to delete a task.
     * @return the content of the task changed.
     * @throws DeleteException The task to be deleted is not in the list.
     */
    public String delete(String input) throws DeleteException {
        int i = Parser.parseDelete(input, this);

        list.remove(i - 1);
        return list.get(i - 1).getContent();
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int i) {
        return this.list.get(i);
    }
}
