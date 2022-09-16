package ui;

public class UI {

    /**
     * Creates a new UI.
     */
    public UI () {

    }

    public String showGotTask() {
        String out = ("----------------------\nOooo looks like you have tasks already planned\n" +
                "----------------------\n");
        return out;
    }

    public String showNoTask() {
        String out = ("----------------------\nA fresh start for you :)\n----------------------\n");
        return out;
    }

    public String welcomeMessage() {
        String out = ("----------------------\n" +
                "Hello! I'm Dookie\nWhat can I do for you?\n----------------------\n");
        return out;
    }

    public String showInaccurateInput() {
        String out = "----------------------\nI am sorry pls input again\n" +
                "----------------------\n";
        return out;
    }

    public String wrongDateTimeFormat() {
        String out = "----------------------\nHi please put in the proper date and time format: DD/MM/YYYY.\n" +
        "----------------------\n";
        return out;
    }

    public String indexOutOfBounds(int i) {
        String out = "----------------------\nThe number" + i + " is out of bounds please choose a smaller index.\n" +
                "----------------------\n";
        return out;
    }

    public String notNumber() {
        String out = "----------------------\nThe index u input is not a number, please input a number.\n" +
                "----------------------\n";
        return out;
    }

    public String noDateOrTime() {
        String out = "----------------------\nI see you may have forgotten to add the date or time to the deadline :)\n" +
                "----------------------\n";
        return out;
    }

    public String goodByeMessage() {
        String out = "GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOD Bye ";
        return out;
    }

    public String threadException() {
        String out = "Thread exception has occured :(";
        return out;
    }



}
