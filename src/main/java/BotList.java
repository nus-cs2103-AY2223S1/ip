public class BotList {
    private Task[] internalArray;
    private int pointer;

    BotList() {
        this.internalArray = new Task[100];
        this.pointer = 0;
    }

    /*
    * Adds an element to the array stored, which acts as a to-do list
    *
    * @param element a String to be added to the array
    * @return String of either the element is added successfully or not
    */
    String add(Task task) {
        StringBuilder output = new StringBuilder("I've added this task:\n").append(task);
        this.internalArray[pointer] = task;
        this.pointer++;
        return output.append("\n").append(this.getNoTasks()).toString();
    }

    /*
    * Marks the task in the user's list as done
    *
    * @param taskIndex task number within the list, starting from 1
    * @return String of the task marked as done
    */
    String mark(int taskIndex) {
        this.internalArray[taskIndex - 1].setCompletionStatus(true);
        return "Good Job! This task is now completed:\n" + this.internalArray[taskIndex - 1];
    }

    /*
     * Marks the task in the user's list as undone
     *
     * @param taskIndex task number within the list, starting from 1
     * @return String of the task marked as undone
     */
    String unmark(int taskIndex) {
        this.internalArray[taskIndex - 1].setCompletionStatus(false);
        return "This task is now yet to be done:\n" + this.internalArray[taskIndex - 1];
    }

    private String getNoTasks() {
        return "Now you have " + (this.pointer) + " task(s) in total.";
    }


    @Override
    public String toString() {
        String niceMessage = "Here are your tasks:\n";
        StringBuilder output = new StringBuilder(niceMessage);
        for (int i = 1; i < pointer + 1; i++) {
            if (output.length() != 0) {
                output.append("\n");
            }
            output.append(i).append(") ").append(this.internalArray[i - 1]);
        }
        return output.toString();
    }
}
