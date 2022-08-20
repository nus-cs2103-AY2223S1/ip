import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserInputHistory {
    private ArrayList<Task> userInputHistory = new ArrayList<>();

    /**
     * Appends string to file given.
     * @param filePath path of file to add text to.
     * @param textToAdd text to append to file.
     * @throws IOException e.g. when file not found.
     */
    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Method to add Task to history
     * @param s String description to add to userInputHistory
     */
    public void addTaskToHistory(String s) {
        Task newTask = new Task(s);
        userInputHistory.add(newTask);
        //echo request
        System.out.printf("Noted down: %s\n There are %d items on your list now. \n", s, userInputHistory.size());
        System.out.print(">>");
    }

    /**
     * Method to add Event to userInputHistory
     * @param description
     * @param at
     */
    public void addEventToHistory(String description, String at) {
        Event newEvent = new Event(description, at);
        userInputHistory.add(newEvent);
        //echo request
        System.out.printf("Noted down: %s\n There are %d items on your list now. \n", description, userInputHistory.size());
        System.out.print(">>");
    }

    /**
     * Method to add Event to userInputHistory
     * @param description
     * @param by
     */
    public void addDeadlineToHistory(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        userInputHistory.add(newDeadline);
        //echo request
        System.out.printf("Noted down: %s\n There are %d items on your list now. \n", description, userInputHistory.size());
        System.out.print(">>");
    }

    /**
     * Method to show history
     */
    public void showHistory() {
        int count = 1;
        System.out.print("______\n");
        System.out.println("Tasks in your list are: ");
        for (Task t: userInputHistory) {
            System.out.printf("%d. %s\n",count, t);
            count ++;
        }
        userInputHistory.forEach(input -> {

        });
        System.out.printf("Total: %d\n", userInputHistory.size());
        System.out.print("______\n");
        System.out.print(">>");
    }

    /**
     *
     * @param n index to retrieve
     * @return task at index n - 1 in the userInputHistory arraylist
     */
    public Task getTask(int n) {
        return userInputHistory.get(n - 1);
    }

    /**
     * Delete task at index n in userInputHistory
     * @param n index to be deleted
     */
    public void deleteTask(int n)   {
        Task taskToModify = userInputHistory.get(n - 1);
        userInputHistory.remove(n - 1);
        System.out.printf("Task removed: \n%s\n", taskToModify);
        System.out.printf("Total: %d\n", userInputHistory.size());
        System.out.print("______\n");
        System.out.print(">>");
    }

    /**
     * Extract task number from string input
     * @param s extracts task number from user input
     * @return index of the task in the list plus one
     */
    public int getTaskNumber(String s) throws DukeException{
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly= s.replaceAll("[^0-9]", "");
        int n;
        if (numberOnly.length() <= 0) {
            throw new DukeException("no number given\n>>");
        } else {
            n = Integer.parseInt(numberOnly);
            if (n > userInputHistory.size()) {
                throw new DukeException("task does not exist is list\n>>");
            } else {
                return n;
            }
        }
    }
}
