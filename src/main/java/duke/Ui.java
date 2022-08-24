package duke;

public class Ui {

    /**
     *  This method prints out a horizontal line 40 dashes long.
     */
    private void printLine() {
        for(int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * This method prints out the string s, wrapped within 2 horizontal lines.
     * @param s The given string to be printed.
     */
    public void say(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    public void showLoadingError(){
        say("Failed to load! :(");
    }
}
