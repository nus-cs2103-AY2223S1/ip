package duke.task;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a task that user can add/ delete /mark /unmark / tag/ untag in TaskDive chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;
    private static String TODO_TASK = "Todo      ";
    private static String DEADLINE_TASK = "Deadline  ";
    private static String EVENT_TASK = "Event     ";
    private static String DONE = " Done   ";

    /**
     * This method is a Task constructor.
     * A Task object contains a task description and a boolean attribute isDone that represents the task status
     * (true: done / false: not done).
     * @param description task description string.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<String>();
    }

    /**
     * This method is an overloaded Task constructor.
     * A Task object contains a task description and a boolean attribute isDone that represents the task status
     * (true: done / false: not done).
     * @param description task description string.
     * @param isDone a boolean value of the task status.
     * @param tags a list of tags.
     */
    public Task(String description, Boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Returns task status icon.In the status icon, [X] represents task status as done,
     * [ ] represents task status as not done.
     * @return String representation of task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns task status attribute isDone.
     * @return boolean value of task status.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns task description attribute description.
     * @return task description String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task status as done, isDone attribute of task is changed to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task status as undone, isDone attribute of task is changed to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the task type. This method is overrided the method from its subclass ToD0, Deadline and Event.
     * @return empty string.
     */
    public String taskType() {
        return "";
    }

    /**
     * Returns a ToDo/ Deadline /Event task parsed from a string representation of task.
     * @param str string representation of task stored in the storage.
     * @return a ToDo/ Deadline /Event task.
     */
    public static Task stringToTask(String str) {
        String[] line = str.split("\\|"); //split string by "|"
        assert line.length >= 3 : "missing task attribute in input string";
        assert line[0].equals(TODO_TASK) || line[0].equals(EVENT_TASK) || line[0].equals(DEADLINE_TASK)
                : "invalid task type in input string";
        if (line[0].equals(TODO_TASK)) {
            return new ToDo(line[2].trim(), DONE.equals(line[1]), Task.readTags(line[3].trim()));
        } else if (line[0].equals(DEADLINE_TASK)) { //Deadline task
            return new Deadline(line[2].trim(), DONE.equals(line[1]), LocalDate.parse(line[3].trim())
                    , Task.readTags(line[4].trim()));
        } else {
            String[] time = line[3].split("to");
            String start = time[0].trim();
            String end = time[1].trim();
            LocalDate endDate = LocalDate.parse(end);
            LocalDate startDate = LocalDate.parse(start);
            assert startDate.isBefore(endDate) || startDate.isEqual(endDate)
                    : "invalid date range (start date after end date).";
            return new Event(line[2].trim(), DONE.equals(line[1]), startDate, endDate, Task.readTags(line[4].trim()));
        }
    }

    /**
     * Adds a new tag to task.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Deletes a new tag to task.
     */
    public void deleteTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * Checks if the task has a tag, returns true if the task has tag, false otherwise.
     * @param tag a tag string.
     * @return boolean value.
     */
    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

    /**
     * Returns the number of tags that the task owns.
     * @return number of tags.
     */
    public int noOfTags() {
        return this.tags.size();
    }

    /**
     * Returns the string representation of tags with the format {tag1,tag2,tag3}.
     * @return string representation of tags.
     */
    public String printTags() {
        if (noOfTags() == 1) { //special case: task only has 1 tag
            return String.format("{%s}", tags.get(0));
        } else if (noOfTags() == 0) { //special case: task has no tag
            return "{}";
        } else {
            return String.format("{%s}", String.join(",", tags));
        }
    }

    /**
     * Returns a tag list parsed from the string representation of tags {tag1,tag2,tag3}.
     * @return a list of tags.
     */
    public static List<String> readTags(String tagString) {
        String newTagString = tagString.substring(1, tagString.length() - 1);
        if (tagString.equals("{}")) {
            return new ArrayList<String>();
        } else if (!newTagString.contains(",")) {
            List<String> newTags =  new ArrayList<String>();
            newTags.add(newTagString);
            return newTags;
        } else {
            return new ArrayList<String>(Arrays.asList(newTagString.split(",")));
        }
    }

    /**
     * Returns a string representation of a task. This method is used to display tasks in UI response messages.
     * @return a  string representation of a task.
     */
    public String printTask() {
        return String.format("%s %s %s", this.getStatusIcon(), this.getDescription(), this.printTags());
    }

}
