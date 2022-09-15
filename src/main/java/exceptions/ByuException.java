package exceptions;

/**
 * An exception indicating the occurrence of an error while running Byu.
 */
public abstract class ByuException extends Exception {

    @Override
    public abstract String getMessage();

}
