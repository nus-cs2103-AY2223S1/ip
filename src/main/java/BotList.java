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
    String add(String element) {
        Task newTask = new Task(element);
        this.internalArray[pointer] = newTask;
        this.pointer++;
        return "Added: " + element.toString();
    }

    void mark(int taskIndex) {
        this.internalArray[taskIndex - 1].setCompletionStatus(true);
    }

    void unmark(int taskIndex) {
        this.internalArray[taskIndex - 1].setCompletionStatus(false);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < pointer + 1; i++) {
            if (output.length() != 0) {
                output.append("\n");
            }
            output.append(i).append(". ").append(this.internalArray[i - 1]);
        }
        return output.toString();
    }
}
