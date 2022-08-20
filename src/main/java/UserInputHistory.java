import java.util.ArrayList;

public class UserInputHistory {
    private ArrayList<Task> userInputHistory = new ArrayList<>();

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
    private  void addEventToHistory(String description, String at) {
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
    private void addDeadlineToHistory(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        userInputHistory.add(newDeadline);
        //echo request
        System.out.printf("Noted down: %s\n There are %d items on your list now. \n", description, userInputHistory.size());
        System.out.print(">>");
    }

    /**
     * Method to show history
     */
    private void showHistory() {
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

}
