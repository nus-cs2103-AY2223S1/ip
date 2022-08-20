/**
 * This is an exception class when user input has missing details for deadline and event.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class MissingDetailException extends CaCaException {
    public MissingDetailException(String message) {
        super(message);
    }
}