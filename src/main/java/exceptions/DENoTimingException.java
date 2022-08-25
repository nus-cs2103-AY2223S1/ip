package exceptions;

public class DENoTimingException extends TumuException {
    public DENoTimingException(String command) {
        super(String.format("Remember to add a timing for the " +
                "deadline/event using /%s! (╥_╥)", command));
    }
}
