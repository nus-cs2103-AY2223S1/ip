package main.java.amanda;

/**
 * Main class runs the program
 */
public class Main {
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Amanda amanda = new Amanda("./src/main/java/data/amanda.txt");
        amanda.run();
    }
}
