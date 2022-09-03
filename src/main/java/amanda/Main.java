package main.java.amanda;

public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
        Amanda amanda = new Amanda("./src/main/java/data/amanda.txt");
        amanda.run();
    }
}
