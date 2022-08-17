public class MarkableList {
    private static final int LIST_SIZE = 100;
    private Task[] items = new Task[LIST_SIZE];
    private int numOfElements = 0;

    public String insertItem(String newItem) 
            throws ArrayIndexOutOfBoundsException {
        if (numOfElements == LIST_SIZE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if (newItem.split(" ", 2).length < 2) {
            return "Please specify the task!";
        }

        String taskType = newItem.split(" ", 2)[0];
        String taskDetails = newItem.split(" ", 2)[1];

        if (taskType.equalsIgnoreCase("deadline")) {
            if (taskDetails.split("/by").length < 2) {
                return "Please specify the deadline!";
            }
            items[numOfElements] = new DeadlineTask(
                taskDetails.split("/by")[0].trim(), 
                taskDetails.split("/by")[1].trim());
        } else if (taskType.equalsIgnoreCase("event")) {
            if (taskDetails.split("/at").length < 2) {
                return "Please specify the period!";
            }
            items[numOfElements] = new EventTask(
                taskDetails.split("/at")[0].trim(), 
                taskDetails.split("/at")[1].trim());
        } else if (taskType.equalsIgnoreCase("todo")) {
            items[numOfElements] = new ToDoTask(taskDetails.trim());
        }

        numOfElements += 1;
        return String.format(
            "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
            items[numOfElements - 1].toString(),
            numOfElements);
    }

    public String markItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >= numOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index].markTask();
    }

    public String unmarkItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >= numOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index].unmarkTask();
    }

    /**
     * Print the output in customised format.
     * @param list The list to print
     */ 
    @Override
    public String toString() {
        String res = ("Here are the tasks in your list:");
        for (int i = 0; i < numOfElements; i++) {
            if (items[i] == null) {
                break;
            }
            res += String.format("\n %d.%s", i + 1, items[i].toString());
        }
        return res;
    }
}
