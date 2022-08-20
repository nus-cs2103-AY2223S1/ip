/**
 * This is an exception class when user has entered an invalid task index for marking or unmarking task.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class InvalidTaskIndex extends CaCaException {
    public InvalidTaskIndex(String message) {
        super(message);
    }
}
