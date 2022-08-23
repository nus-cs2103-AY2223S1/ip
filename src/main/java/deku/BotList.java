package deku;

import deku.task.Task;

import java.util.ArrayList;
import java.util.List;

class BotList {
    private final List<Task> internalArray;
    private final Storage storage;

    BotList(List<Task> internalArray, Storage storage) {
        this.internalArray = internalArray;
        this.storage = storage;
    }
    /**
    * Adds an element to the array stored, which acts as a to-do list
    *
    * @param task a deku.task.Task to be added to the array
    * @return String of either the element is added successfully or not
    */
    String add(Task task) {
        StringBuilder output = new StringBuilder("I've added this task:\n").append(task);
        this.internalArray.add(task);
        storage.save(internalArray);
        return output.append("\n").append(this.getNoTasks()).toString();
    }

    /**
    * Marks the task in the user's list as done
    *
    * @param taskIndex task number within the list, starting from 1
    * @return String of the task marked as done
    */
    String mark(int taskIndex) {
        this.internalArray.get(taskIndex - 1).setCompletionStatus(true);
        storage.save(internalArray);
        return "Good Job! This task is now completed:\n" + this.internalArray.get(taskIndex - 1);
    }

    /**
     * Marks the task in the user's list as undone
     *
     * @param taskIndex task number within the list, starting from 1
     * @return String of the task marked as undone
     */
    String unmark(int taskIndex) {
        this.internalArray.get(taskIndex - 1).setCompletionStatus(false);
        storage.save(internalArray);
        return "This task is now yet to be done:\n" + this.internalArray.get(taskIndex - 1);
    }

    private String getNoTasks() {
        return "Now you have " + (this.internalArray.size()) + " task(s) in total.";
    }


    /**
     * Deletes the task from the array of stored tasks.
     *
     * @param taskIndex task number within the list, starting from 1
     * @return String of the task deleted
     */
    String delete(int taskIndex) {
        Task task = this.internalArray.remove(taskIndex - 1);
        storage.save(internalArray);
        return "Noted.\n" + task.toString() + "\nhas been deleted.\n" + getNoTasks();
    }

    String find(String date) throws DekuExceptions {
        ArrayList<Task> newArray = new ArrayList<>();
        InputParser parser = new InputParser();
        for (Task task: this.internalArray) {
            parser.parseDate(date);
            if (task.getDate().equals(parser.getDate())) {
                newArray.add(task);
            }
        }
        return this.outputList("Here are the tasks with the same date: ", newArray);
    }

    private String outputList(String message, List<Task> array) {
        StringBuilder output = new StringBuilder(message);
        for (int i = 1; i < array.size() + 1; i++) {
            if (output.length() != 0) {
                output.append("\n");
            }
            output.append(i).append(") ").append(array.get(i - 1));
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return this.outputList("Here are your tasks:\n", this.internalArray);
    }
}
