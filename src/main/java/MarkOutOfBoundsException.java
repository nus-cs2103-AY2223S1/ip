public class MarkOutOfBoundsException extends TumuException {
    private static final String OUT_OF_BOUNDS_EXCEPTION =
            "\tSpecified index is out of bounds, please key a value from 1 to ";

    public MarkOutOfBoundsException(int listSize) {
        super(OUT_OF_BOUNDS_EXCEPTION + listSize);
    }
}
