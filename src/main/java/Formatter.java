import java.io.IOException;

/**
 * Formats all output to and print it to screen.
 */
public class Formatter {
    /**
     * Prints after task is created.
     * @param description of task created.
     * @param numberOfElementsInList in TaskList object.
     */
    public static void uiTaskDescription(String description, int numberOfElementsInList) {
        String printToScreen = String.format("Noted down: %s\n There are %d items on your list now. \n>>",
                description, numberOfElementsInList);
        System.out.print(printToScreen);
    }

    /**
     * Prints when showHistory() called.
     * @param listElements StringBuffer storing information about all elements in the list.
     * @param numberOfElementsInList in TaskList object.
     * @throws IOException
     */
    public static void uiHistory(StringBuffer listElements, int numberOfElementsInList) throws IOException {
        System.out.print("______\nTasks in your list are: \n");
        System.out.println(listElements);
        System.out.println(String.format("Total: %d", numberOfElementsInList));
        System.out.println("______>>");
    }

    /**
     * Prints after task is deleted.
     * @param taskToModify deleted task.
     * @param numberOfElementsInList in TaskList object.
     */
    public static void uiDeleteTask(Task taskToModify, int numberOfElementsInList) {
        System.out.printf("Task removed: \n%s\n", taskToModify);
        System.out.printf("Total: %d\n", numberOfElementsInList);
        System.out.print("______\n>>");
    }

    /**
     * Prints when Task is marked/unmarked.
     * @param marked is eithe "Marked" or "Unmarked".
     * @param n index of task deleted.
     * @param taskToModify Task marked.
     */
    public static void uiMarkingTask(String marked, int n, Task taskToModify) {
        System.out.printf("%s task %d \n%s\n",marked, n, taskToModify);
        System.out.print(">>");
    }

    /**
     * Prints after required operation is called.
     * @param printToScreen message to print to screen.
     */
    public static void specialOperations(String printToScreen) {
        System.out.println(printToScreen);
    }

}