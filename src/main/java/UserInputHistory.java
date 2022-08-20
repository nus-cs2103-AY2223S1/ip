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
    Path path = Paths.get(System.getProperty("user.dir"),"src", "main", "java", "userinputhistory.txt");

    private void createIfDoesntExist() {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

    }
    private void appendToFile(String s)  {
        try {
            createIfDoesntExist();
            Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    private void  syncUserInputHistory() throws IOException {
        createIfDoesntExist();
        userInputHistory.removeAll(userInputHistory);
        List<String> history = Files.readAllLines(path);
        int historyLen = history.size();
        String line, taskType, description, date;
        Task t = new Task("");
        for (int i = 0; i < historyLen; i++ ) {
            line = history.get(i);
            taskType = line.substring(1,2);
            switch (taskType) {
            case "T":
                description = line.substring(6);
                t = new Task(description);
                break;
            case "E":
                description = line.substring(6, line.indexOf("("));
                date = line.substring(line.indexOf(")"));
                t = new Event(description, date);
                break;
            case "D":
                description = line.substring(6, line.indexOf("("));
                date = line.substring(line.indexOf("(by: ") + 5, line.indexOf(")"));
                t = new Deadline(description, date);
                break;
            }
            userInputHistory.add(t);
        }
    }

    /**
     * Method to add Task to history
     * @param s String description to add to userInputHistory
     */
    public void addTaskToHistory(String s) {
        try {
            syncUserInputHistory();
            Task newTask = new Task(s);
            appendToFile(newTask.toString() + "\n");
            userInputHistory.add(newTask);
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", s, userInputHistory.size());
            System.out.print(">>");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Method to add Event to userInputHistory
     * @param description
     * @param at
     */
    public void addEventToHistory(String description, String at) {
        try {
            syncUserInputHistory();
            Event newEvent = new Event(description, at);
            appendToFile(newEvent.toString() + "\n");
            userInputHistory.add(newEvent);
            //echo request
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", description, userInputHistory.size());
            System.out.print(">>");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Method to add Event to userInputHistory
     * @param description
     * @param by
     */
    public void addDeadlineToHistory(String description, String by) {
        try {
            syncUserInputHistory();
            Deadline newDeadline = new Deadline(description, by);
            appendToFile(newDeadline.toString() + "\n");
           userInputHistory.add(newDeadline);
            //echo request
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", description, userInputHistory.size());
            System.out.print(">>");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Method to show history
     */
    public void showHistory() {
        int count = 1;
        System.out.print("______\n");
        System.out.println("Tasks in your list are: ");
        try {
            syncUserInputHistory();
            List<String> history = Files.readAllLines(path);;
            for (String s: history) {
                System.out.printf("%d. %s\n", count, s);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
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
    public Task getTask(int n) throws IOException {
            syncUserInputHistory();
            return userInputHistory.get(n - 1);
    }

    /**
     * Delete task at index n in userInputHistory
     * @param n index to be deleted
     */
    public void deleteTask(int n)  {
        try {
            syncUserInputHistory();
            Task taskToModify = userInputHistory.get(n - 1);
            userInputHistory.remove(n - 1);
            System.out.printf("Task removed: \n%s\n", taskToModify);
            System.out.printf("Total: %d\n", userInputHistory.size());
            System.out.print("______\n");
            System.out.print(">>");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Extract task number from string input
     * @param s extracts task number from user input
     * @return index of the task in the list plus one
     */
    public int getTaskNumber(String s) throws DukeException, IOException{
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        syncUserInputHistory();
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
