import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class BotList {
    private final ArrayList<Task> internalArray;

    BotList() {
        this.internalArray = new ArrayList<Task>();
    }

    /*
    * Adds an element to the array stored, which acts as a to-do list
    *
    * @param element a String to be added to the array
    * @return String of either the element is added successfully or not
    */
    String add(Task task) {
        StringBuilder output = new StringBuilder("I've added this task:\n").append(task);
        this.internalArray.add(task);
        return output.append("\n").append(this.getNoTasks()).toString();
    }

    /*
    * Marks the task in the user's list as done
    *
    * @param taskIndex task number within the list, starting from 1
    * @return String of the task marked as done
    */
    String mark(int taskIndex) {
        this.internalArray.get(taskIndex - 1).setCompletionStatus(true);
        return "Good Job! This task is now completed:\n" + this.internalArray.get(taskIndex - 1);
    }

    /*
     * Marks the task in the user's list as undone
     *
     * @param taskIndex task number within the list, starting from 1
     * @return String of the task marked as undone
     */
    String unmark(int taskIndex) {
        this.internalArray.get(taskIndex - 1).setCompletionStatus(false);
        return "This task is now yet to be done:\n" + this.internalArray.get(taskIndex - 1);
    }

    private String getNoTasks() {
        return "Now you have " + (this.internalArray.size()) + " task(s) in total.";
    }

    String delete(int taskIndex) {
        Task task = this.internalArray.remove(taskIndex - 1);
        return "Noted.\n" + task.toString() + "\nhas been deleted.\n" + getNoTasks();
    }

    String find(String date) throws DekuExceptions {
        ArrayList<Task> newArray = new ArrayList<>();
        for (Task task: this.internalArray) {
            if (task.getDate().equals(parseDate(date))) {
                newArray.add(task);
            }
        }
        return this.outputList("Here are the tasks with the same date: ",newArray);
    }

    private LocalDate parseDate(String dateString) throws DekuExceptions {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy][yyyy-MM-dd]");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeException e) {
            throw new DekuExceptions("Please input a valid date format!\n" +
                    "Currently supports: dd/MM/yyyy | dd-MM-yyyy | yyyy-MM-dd \n" +
                    "Example: 23/08/2022");
        }
    }

    private String outputList(String message, ArrayList<Task> array) {
        String niceMessage = message;
        StringBuilder output = new StringBuilder(niceMessage);
        for (int i = 1; i < array.size() + 1; i++) {
            if (output.length() != 0) {
                output.append("\n");
            }
            output.append(i).append(") ").append(array.get(i-1));
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return this.outputList("Here are your tasks:\n", this.internalArray);
    }
}
