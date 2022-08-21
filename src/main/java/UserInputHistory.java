import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class UserInputHistory {
    private ArrayList<Task> userInputHistory = new ArrayList<>();
    private Path path;

    private void getPath() throws DukeException {
        String pathString = System.getProperty("user.dir");
                if (pathString.contains("ip")) {
                    pathString = pathString.substring(0, pathString.indexOf("ip") + 2);
                    this.path = Paths.get(pathString, "src", "main", "java", "userinputhistory.txt");
                } else {
                    throw new DukeException("Cannot run from outside of ip folder of Duke");
                }
    }

    private void createIfDoesntExist() {
        try {
            getPath();
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e);
        }
    }

    private void appendToFile(String s)  {
        createIfDoesntExist();
        try {
            Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Removes everything from file at path.
     * Rewrites to file from userInputHistory arraylist.
     * @param index index of line to delete (1-indexed)
     */
    private void deleteLine(int index) {
        try {
            createIfDoesntExist();
            String temp = "";
            List<String> history = Files.readAllLines(path);
            Files.write(path, temp.getBytes(StandardCharsets.UTF_8));
            int n = history.size(), i = 0;
            for (i = 0; i < n ; i ++) {
                if (i != index - 1) {
                    temp = history.get(i) + "\n";
                    Files.write(path, temp.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Method to add Task to history
     * @param s String description to add to userInputHistory
     */
    public void addTaskToHistory(String s) {
            Task newTask = new Task(s);
            appendToFile(newTask.toString() + "\n");
            userInputHistory.add(newTask);
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
            appendToFile(newEvent.toString() + "\n");
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
            appendToFile(newDeadline.toString() + "\n");
            userInputHistory.add(newDeadline);
            //echo request
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", description, userInputHistory.size());
            System.out.print(">>");
    }

    /**
     * Method to show history
     */
    public void showHistory() throws IOException{
        createIfDoesntExist();
        int count = 1;
        System.out.print("______\n");
        System.out.println("Tasks in your list are: ");

            List<String> history = Files.readAllLines(path);
            for (String s: history) {
                System.out.printf("%d. %s\n", count, s);
                count++;
            }
        System.out.printf("Total: %d\n", userInputHistory.size());
        System.out.print("______\n");
        System.out.print(">>");
    }

    /**
     *
     * @param n index to retrieve
     * @return task at index n - 1 in the userInputHistory arraylist
     */
    public Task getTask(int n)  {
            return userInputHistory.get(n - 1);
    }

    /**
     * Delete task at index n in userInputHistory
     * @param n index to be deleted
     */
    public void deleteTask(int n)  {
            Task taskToModify = userInputHistory.get(n - 1);
            userInputHistory.remove(n - 1);
            deleteLine(n);
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
