package deku.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import deku.DekuExceptions;
import deku.InputParser;

/**
 * Main parent Task class
 */
public class Task {
    private static final String SAVE_SEPARATOR = "|";
    private static final char SPECIAL_CHAR = '/';
    private final List<String> taskArray;
    private final String task;
    private final String icon;
    private Boolean isCompleted;
    private String completionIcon;
    private final InputParser parser = new InputParser();

    Task(List<String> task, String taskName, String icon) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions("The description of a " + taskName + " cannot be empty.");
        }

        this.task = parser.parseTask(task);
        this.taskArray = task;
        this.icon = icon;
        this.isCompleted = false;
        this.completionIcon = "[ ]";
    }

    private String getTask() {
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            String current = taskArray.get(i);
            if (current.charAt(0) == SPECIAL_CHAR) {
                break;
            }
            output += current + " ";
        }
        return (output.equals("")) ? output : output.substring(0, output.length() - 1);
    }

    public LocalDate getDate() {
        return parser.getDate();
    }

    public LocalTime getTime() {
        return parser.getTime();
    }


    /**
     * Searches for a particular word within the information user inputs
     *
     * @param word Word to be searched for
     * @return boolean if the word is present
     */
    public boolean find_word(String word) {
        for (String storedWord: taskArray) {
            System.out.println(storedWord);
            boolean isEmpty = (storedWord.length() <= 1);
            boolean isSpecialChar = (storedWord.charAt(0) == SPECIAL_CHAR);
            if (storedWord.equals(word)) {
                return true;
            } else if (!isSpecialChar || isEmpty) {
                continue;
            }
            String specialChar = storedWord.substring(1);
            if (specialChar.equals(word)) {
                return true;
            }
        }
        return false;

    }

    private String getSpecial() {
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            String current = taskArray.get(i);
            if (current.charAt(0) == SPECIAL_CHAR) {
                output = current;
                break;
            }
        }
        return output;
    }

    /**
     * Alternates Task's status of whether it is completed
     *
     * @param set 1 = Task completed, 0 = Task incomplete
     */
    public void setCompletionStatus(boolean set) {
        isCompleted = set;
        if (isCompleted) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    /**
     * Parses the current task to custom data structure to write to file for storage
     *
     * @return custom data structure for file
     */
    public String saveFormat() {
        String completionParse = (completionIcon.equals("[X]")) ? "1" : "0";
        return icon
                + SAVE_SEPARATOR
                + completionParse
                + SAVE_SEPARATOR
                + getTask()
                + SAVE_SEPARATOR
                + getSpecial()
                + SAVE_SEPARATOR
                + getDate()
                + SAVE_SEPARATOR
                + getTime();
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + completionIcon + " - " + task;
    }
}
