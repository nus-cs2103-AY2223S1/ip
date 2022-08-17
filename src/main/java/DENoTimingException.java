public class DENoTimingException extends TumuException {
    public DENoTimingException(String command) {
        super(String.format("\tRemember to add a timing for the " +
                "deadline/event using /%s! (╥_╥)", command));
    }
}
