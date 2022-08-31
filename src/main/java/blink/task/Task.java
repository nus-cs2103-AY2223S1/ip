package blink.task;

import blink.BlinkException;

import java.time.LocalDate;

/**
 * Task object that contains description, boolean to mark if it is
 * done and date for some Task
 */
abstract public class Task {

    protected String description;
    protected boolean isDone;

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
     * unknown input to be read
     */
    public static Task readSaveTask(String input) throws BlinkException {
        String[] info = input.split("\\|", 3);
        switch(info[0].strip()) {
            case "T":
                ToDos todo = new ToDos(info[2].strip());
                if (info[1].strip().equals("1")) {
                    todo.isDone = true;
                }
                return todo;
            case "D":
                String[] desc = info[2].split("\\|");
                Deadlines deadline = new Deadlines(desc[0].strip(), desc[1].strip());
                if (info[1].strip().equals("1")) {
                    deadline.isDone = true;
                }
                return deadline;
            case "E":
                String[] temp = info[2].split("\\|");
                Events event = new Events(temp[0].strip(), temp[1].strip());
                if (info[1].strip().equals("1")) {
                    event.isDone = true;
                }
                return event;
            default:
                throw new BlinkException("wtf happened");
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
            throw new BlinkException("This task has already been done :|");
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
            throw new BlinkException("An unfinished task cannot be unmark...");
        } else {
            this.isDone = false;
            return "Looks like there is more work to do\n" + this;
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

    /**
     * Finds Tasks with specified keyword in description or date if necessary.
     *
     * @param keyword Keyword to find Tasks
     * @return True if keyword inside Tasks and false if not
     */
    public boolean hasKeyword(String keyword){
        String[] allWords = this.toString().split(" ");
        boolean isFound = false;
        for (int x = 0; x < allWords.length; x++) {
            String word = allWords[x].toLowerCase();
            if (x != 0) {
                word = word.replaceAll("[-+.<>?/:;',()]","");
            }
            if (word.equals(keyword)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }
}