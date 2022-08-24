package utils;

public class FileIO {
    private static FileIO instance;

    // This is a singleton class
    private FileIO(){

    };

    public static FileIO getInstance() {
        if (instance == null) {
            instance = new FileIO();
        }
        return instance;
    }
}
