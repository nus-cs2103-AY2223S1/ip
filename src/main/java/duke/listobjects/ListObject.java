package duke.listobjects;

import java.io.Serializable;

/**
 * Class to store list items with task and status of completion
 */
public class ListObject implements Serializable {
    private String task;
    private int status;

    /**
     * Constructs a ListObject with given task description and status
     * @param task String representing task description
     * @param status int with value 1 if task is complete and 0 otherwise
     */
    public ListObject(String task, int status) {
        this.task = task;
        this.status = status;
    }


    /**
     * Returns task description
     * @return String representing task decription
     */
    public String getTask(){
        return this.task;
    }

    /**
     * Returns task status
     * @return int with value 1 if task is complete and 0 otherwise
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * Returns the task's completion status
     * @return String indicating status of completion of task
     */
        public String showStatusIndicator() {
            if (this.status==1) {
                return "[X] ";
            } else {
                return "[ ] ";
            }
        }


    /**
     * Switches the status of completion of task
     */
    public void switchStatus() {
        if (this.status==1) {
            this.status=0;
        } else {
            this.status=1;
        }
    }

    /**
     * Returns String representation of the ListObject
     * @return String representing the ListObject
     */
    @Override
    public String toString() {
        return this.showStatusIndicator()+ this.getTask();
    }

}