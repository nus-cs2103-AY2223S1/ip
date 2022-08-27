package exceptions;

/**
 * KeyNotFoundException is thrown if the key is not found in a mapping
 */
public class KeyNotFoundException extends Exception {

    /**
     * Creates a new KeyNotFoundException
     * @param keyName the given key name
     * @param collectionName the collection to find the key in
     */
    public KeyNotFoundException(String keyName, String collectionName) {
        super(String.format("%s was not found in %s", keyName, collectionName));
    }

    public KeyNotFoundException() {
        super();
    }
}
