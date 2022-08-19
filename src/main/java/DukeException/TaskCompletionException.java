package DukeException;

public class TaskCompletionException extends DukeException{
    /**
     * The error shown when user wants to mark tasks that are done/ unmark tasks that are not done yet.
     * @param msg error message.
     */
    public TaskCompletionException(String msg) {
        super(msg+ "\nThe task status alr satisfies your requirement. PLease check!");
    }
}
