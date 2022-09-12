package duke.task;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a task with a task description and a boolean indicator for task status (done / not done)
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<String>();
    }

    public Task(String description, Boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String taskType() {
        return "";
    }

    public static Task stringToTask(String str) {
        String[] line = str.split("\\|");
        assert line.length >= 3 : "missing task attribute in input string";
        assert line[0].equals("Todo      ") || line[0].equals("Deadline  ") || line[0].equals("Event     ")
                : "invalid task type in input string";
        if (line[0].equals("Todo      ")) {
            return new ToDo(line[2].trim(), " Done   ".equals(line[1]), Task.readTags(line[3].trim()));
        } else if (line[0].equals("Deadline  ")) {
            return new Deadline(line[2].trim(), " Done   ".equals(line[1]), LocalDate.parse(line[3].trim()), Task.readTags(line[4].trim()));
        } else {
            String[] time = line[3].split("to");
            String start = time[0].trim();
            String end = time[1].trim();
            LocalDate endDate = LocalDate.parse(end);
            LocalDate startDate = LocalDate.parse(start);
            assert startDate.isBefore(endDate) || startDate.isEqual(endDate)
                    : "invalid date range (start date after end date).";
            return new Event(line[2].trim(), " Done   ".equals(line[1]), startDate, endDate, Task.readTags(line[4].trim()));
        }
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void deleteTag(String tag) {
        this.tags.remove(tag);
    }

    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

    public int noOfTags() {
        return this.tags.size();
    }

    public String printTags() {
        if (noOfTags() == 1) {
            return String.format("{%s}", tags.get(0));
        } else if (noOfTags() == 0) {
            return "{}";
        } else {
            return String.format("{%s}", String.join(",", tags));
        }
    }

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

    public String printTask() {
        return String.format("%s %s %s", this.getStatusIcon(), this.getDescription(), this.printTags());
    }

}
