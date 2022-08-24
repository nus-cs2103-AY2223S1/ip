public class Task {
    private String name;
    private Boolean status = false;

    Task(String name) {
        this.name = name;
    }

    Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    // Sets status of task at index to be true
    public void mark() {
        String message;
        if (status == true) {
            message = "\t This task is already marked:";
        } else {
            status = true;
            message = "\tGood Job on completing the task! I've marked this task as done:";
        }
        System.out.println(message);
        System.out.println("\t   " + toString());
    }

    // Sets status of task at index to be false
    public void unmark() {
        String message;
        if (status == false) {
            message = "\t This task is already unmarked:";
        } else {
            status = false;
            message = "\tOK, I've marked this task as not done yet:";
        }
        System.out.println(message);
        System.out.println("\t   " + toString());
    }

    /**
     * Returns name of task and its status to be used when list is called
     * 
     * @return Returns String of name of task formatted with status showing
     */
    @Override
    public String toString() {
        String mark = status ? "X" : " ";
        return String.format("[%s] %s", mark, name);
    }

    //Returns status of task
    public boolean getStatus() {
        return status;
    }

    //Returns name of task
    public String getName() {
        return name;
    }
}
