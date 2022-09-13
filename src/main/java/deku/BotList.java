package deku;

import java.util.ArrayList;
import java.util.List;

import deku.task.Task;

class BotList {
    private List<Task> userInstructions;
    private List<Task> storedList;
    private boolean hasUndo = false;
    private final Storage storage;

    BotList(List<Task> userInstructions, Storage storage) {
        this.userInstructions = userInstructions;
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
        userInstructions.add(task);
        storage.save(userInstructions);
        return output.toString();
    }

    /**
    * Marks the task in the user's list as done
    *
    * @param taskIndex task number within the list, starting from 1
    * @return String of the task marked as done
    */
    String mark(int taskIndex) {
        assert(taskIndex > 0);
        userInstructions
                .get(taskIndex - 1)
                .setCompletionStatus(true);
        storage.save(userInstructions);
        return "Good Job! This task is now completed:\n"
               + userInstructions.get(taskIndex - 1);
    }

    /**
     * Marks the task in the user's list as undone
     *
     * @param taskIndex task number within the list, starting from 1
     * @return String of the task marked as undone
     */
    String unmark(int taskIndex) {
        assert(taskIndex > 0);
        userInstructions.get(taskIndex - 1)
                .setCompletionStatus(false);
        storage.save(userInstructions);
        return "This task is now yet to be done:\n"
               + userInstructions.get(taskIndex - 1);
    }

    /**
     * Deletes the task from the array of stored tasks.
     *
     * @param taskIndex task number within the list, starting from 1
     * @return String of the task deleted
     */
    String delete(int taskIndex) throws DekuExceptions {
        assert(taskIndex > 0);
        try {
            Task task = userInstructions.remove(taskIndex - 1);
            storage.save(userInstructions);
            return "Noted.\n"
                    + task.toString()
                    + "\nhas been deleted.\n";
        } catch (IndexOutOfBoundsException e) {
            throw new DekuExceptions("There is no task: " + taskIndex);
        }

    }

    String find_date(String date) throws DekuExceptions {
        List<Task> containsDate = new ArrayList<>();
        InputParser parser = new InputParser();
        for (Task task: userInstructions) {
            parser.parseDate(date);
            boolean isEmptyDate = (task.getDate() != null);
            boolean isEqualDate = (task.getDate().equals(parser.getDate()));
            if (isEmptyDate && isEqualDate) {
                containsDate.add(task);
            } else {
                containsDate.add(null);
            }
        }
        return outputList("Here are the tasks with the same date:", containsDate);
    }

    String find_word(String word) throws DekuExceptions {
        List<Task> containsWord = new ArrayList<>();

        for (Task task: userInstructions) {
            if (task.find_word(word)) {
                containsWord.add(task);
            } else {
                containsWord.add(null);
            }
        }
        return outputList("Here are the tasks containing the word: "
                + word, containsWord);
    }

    String undo() {
        if (hasUndo) {
            userInstructions = new ArrayList<>(storedList);
            storage.save(userInstructions);
            hasUndo = false;
            return "I have undo-ed your last command";
        }
        return "I cannot undo that!";
    }

    void overwriteUndo() {
        storedList = new ArrayList<>(userInstructions);
        hasUndo = true;
    }

    private String outputList(String message, List<Task> array) {
        StringBuilder output = new StringBuilder(message + "\n");
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == null) {
                continue;
            }

            if (output.length() != 0) {
                output.append("\n");
            }
            output.append(i + 1).append(") ").append(array.get(i));
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return this.outputList("Here are your tasks:", userInstructions);
    }
}
