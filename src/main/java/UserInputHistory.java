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
    private void createIfDoesntExist() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private void appendToFile(String s) throws IOException {
        createIfDoesntExist();
        Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    private void updateUserInputHistory(String control,String command, String description, String date, int n) throws IOException {
        switch (control) {
        case "add" :
            switch(command)  {
            case "deadline":
                userInputHistory.add(new Deadline(description, date));
                break;
            case "event":
                userInputHistory.add(new Event(description, date));
                break;
            case "task":
                userInputHistory.add(new Task(description));
                break;
        }
            break;
        case "remove":
            userInputHistory.remove(n - 1);
            break;
        }
    }

    private void  syncUserInputHistory() throws IOException {
        createIfDoesntExist();
        List<String> history = Files.readAllLines(path);;
        history.forEach(line -> {
            userInputHistory.add();
        });
    }

    /**
     * Method to add Task to history
     * @param s String description to add to userInputHistory
     */
    public void addTaskToHistory(String s) {
        try {
            createIfDoesntExist();
            Task newTask = new Task(s);
            appendToFile(newTask.toString() + "\n");
            updateUserInputHistory("add", "task",s, "", -1);
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
            createIfDoesntExist();
            Event newEvent = new Event(description, at);
            appendToFile(newEvent.toString() + "\n");
            updateUserInputHistory("add", "event",description, "", -1);
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
            createIfDoesntExist();
            Deadline newDeadline = new Deadline(description, by);
            appendToFile(newDeadline.toString() + "\n");
            updateUserInputHistory("add", "deadline",description, "", -1);
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
