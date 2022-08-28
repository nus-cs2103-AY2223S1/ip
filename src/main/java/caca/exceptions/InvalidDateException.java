package caca.exceptions;

/**
 * This is an exception class when user has entered an invalid date and time for deadline or event.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class InvalidDateException extends CaCaException {
    public InvalidDateException(String message) {
        super(message);
    }
}