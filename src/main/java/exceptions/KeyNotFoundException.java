package exceptions;

public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(String keyName, String collectionName) {
        super(String.format("%s was not found in %s", keyName, collectionName));
    }

    public KeyNotFoundException() {
        super();
    }
}
