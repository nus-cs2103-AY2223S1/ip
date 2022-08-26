package Rabbit.util;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Rabbit.RabbitException.FindFormatException;
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
            for (int i = 0; i < this.list.size(); i++) {
                String content = this.list.get(i).getContent();

                if (content.length() < keyword.length()) {
                    continue;
                }

                for (int j = 0; j < content.length() - keyword.length() + 1; j++) {
                    if (content.substring(j, j + keyword.length()).equals(keyword)) {
                        output += list.get(i).toString();
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
     */
    public void list() {
        if (list.size() == 0) {
            System.out.println("There is no task in the list.");
        }
        for (int i  = 0; i < list.size(); i++ ) {
            int index = i + 1;
            System.out.println(index + ". " + list.get(i));
        }
    }

    /**
     * Marks the task at index i as done
     *
     * @param input the user's input.
     * @return the index of the task changed.
     * @throws MarkUnmarkException
     */
    public int mark(String input) throws MarkUnmarkException {
        int i = Parser.parseMark(input, this);
        System.out.println("Okay...task: " + list.get(i - 1).getContent() + " is marked as done.");
        list.get(i - 1).markDone();
        return i - 1;
    }

    /**
     * Unmarks the task at index i as not done
     *
     * @param input the user's input.
     * @return the index of the task changed.
     * @throws MarkUnmarkException
     */
    public int unmark(String input) throws MarkUnmarkException {
        int i = Parser.parseUnmark(input, this);
        System.out.println("Okay...task: " + list.get(i - 1).getContent() + " is unmarked.");
        list.get(i - 1).unmark();
        return i - 1;
    }

    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a specified task from the list.
     *
     * @param input The input from the user intended to delete a task.
     * @return the index of the task deleted.
     * @throws DeleteException The task to be deleted is not in the list.
     */
    public int delete(String input) throws DeleteException {
        int i = Parser.parseDelete(input, this);
        System.out.println("Okay...task: " + list.get(i - 1).getContent() + " is deleted.");
        list.remove(i - 1);
        return i - 1;
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int i) {
        return this.list.get(i);
    }
}
