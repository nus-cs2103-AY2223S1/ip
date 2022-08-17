import java.util.Arrays;

public class DeadlineRequest extends Request {
    private String[] inputArray;
    private TasksList tasksList;
    private static final String DELIMITER = " /by ";
    private static final String DEADLINE_MSG = "Got it. I've added this task: \n";

    public DeadlineRequest(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() throws DukeException{
        if (this.inputArray.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty!");
        }

        //split again to get date/time
        String[] splitArray = this.inputArray[1].split(DeadlineRequest.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Please enter a due date for this task!");
        }

        //Make a new deadline object
        Deadline deadline = new Deadline(splitArray[0], splitArray[1]);
        this.tasksList.addToList(deadline);
        StringBuilder sb = new StringBuilder();
        sb.append(DeadlineRequest.DEADLINE_MSG + deadline + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        super.printResponse(sb.toString());
    }

}


