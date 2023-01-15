package blink.task;

import java.time.LocalDate;
import java.util.ArrayList;

import blink.BlinkException;

/**
 * Task object that contains description, boolean to mark if it is
 * done and date for some Task
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    private ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructor of Task to set description and set not marked
     * when first created.
     *
     * @param description Description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Reads the lines in storage and interprets Task type and its
     * associated information.
     *
     * @param input Line from Storage save file
     * @return Task object and its information
     * @throws BlinkException Thrown when error in save file causes
     *     unknown input to be read
     */
    public static Task readSaveTask(String input) throws BlinkException {
        String[] info = input.split("\\|", 3);
        boolean isMarked = info[1].strip().equals("1");
        String[] addInfo = info[2].split("\\|");
        switch(info[0].strip()) {
        case "T":
            Task todo = new ToDos(addInfo[0].strip());
            todo.isDone = isMarked;
            todo.addSavedTags(addInfo[1].strip());
            return todo;
        case "D":
            Task deadline = new Deadlines(addInfo[0].strip(), addInfo[1].strip());
            deadline.isDone = isMarked;
            deadline.addSavedTags(addInfo[2].strip());
            return deadline;
        case "E":
            Task event = new Events(addInfo[0].strip(), addInfo[1].strip());
            event.isDone = isMarked;
            event.addSavedTags(addInfo[2].strip());
            return event;
        default:
            throw new BlinkException("Error in reading save file...");
        }
    }

    /**
     * Returns string to represent if Task is marked.
     *
     * @return X if Task is marked and blank if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Check if Task is marked, and mark it if not.
     *
     * @return String to show that the task is marked successfully
     * @throws BlinkException Thrown if marked task is marked again
     */
    public String mark() throws BlinkException {
        if (this.isDone) {
            throw new BlinkException("You do already still wanna do again? :|");
        } else {
            this.isDone = true;
            return "Mission complete! Nice ah\n" + this;
        }
    }

    /**
     * Check if Task is unmark, and unamrk if it is not.
     *
     * @return String to show that the Task is unmarked successfully
     * @throws BlinkException Thrown if unmarked task is unmarked again
     */
    public String unMark() throws BlinkException {
        if (!this.isDone) {
            throw new BlinkException("How to unmark? You haven't even do yet...");
        } else {
            this.isDone = false;
            return "Looks like got more work to do\n" + this;
        }
    }

    /**
     * String representation of Task.
     *
     * @return String showing the information of Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Translate task and its information into String that can be
     * saved and read by the Blink program.
     *
     * @return String to represent Task and its information
     */
    public abstract String saveString();

    /**
     * Check equality of Task date with anoDate passed in.
     *
     * @param anoDate date passed in to check equality
     * @return True if dates are equal and false if not
     */
    public abstract boolean checkDate(LocalDate anoDate);

    private String getDescription() {
        return this.description;
    }

    /**
     * Finds Tasks with specified keyword in description or date if necessary.
     *
     * @param keyword Keyword to find Tasks
     * @return True if keyword inside Tasks and false if not
     */
    public boolean hasKeyword(String keyword) {
        String[] allDesc = this.getDescription().split(" ");
        boolean isFound = false;
        for (int x = 0; x < allDesc.length; x++) {
            String word = allDesc[x].toLowerCase();
            if (word.equals(keyword)) {
                isFound = true;
                break;
            }
        }
        for (int y = 0; y < this.tags.size(); y++) {
            String tag = tags.get(y);
            if (tag.equals(keyword)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    /**
     * Add tag to Task.
     *
     * @param tag Tag String to add to a task
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Make string format for all the tags of a task.
     *
     * @return String for all tags of task
     */
    public String tagToString() {
        String str = "";
        for (int x = 0; x < this.tags.size(); x++) {
            String tag = " #" + this.tags.get(x);
            str += tag;
        }
        return str;
    }

    /**
     * String format for all tags of a task to be recorded down into save file.
     *
     * @return String format for all tags of a task into save file
     */
    public String saveTagString() {
        String tagString = " | ";
        for (int x = 0; x < this.tags.size(); x++) {
            String tag = "#" + this.tags.get(x) + " ";
            tagString += tag;
        }
        return tagString;
    }

    private void addSavedTags(String input) {
        if (input.isBlank()) {
            return;
        }
        String[] tags = input.split("#");
        for (int x = 1; x < tags.length; x++) {
            String tag = tags[x].strip().replace("#", "");
            this.tags.add(tag);
        }
    }
}
