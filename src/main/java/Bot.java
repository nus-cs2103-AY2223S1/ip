class Bot {

private final String name;
private static final String SPACE = "    ";
private static final String BORDER = "    ____________________________________________________________";

    Bot() {
        this.name = "Bocil";
    }

    void introduce() {
        String line1 = String.format("%sHello! I'm %s", SPACE, this.name);
        String line2 = String.format("%sWhat can I do for you?", SPACE);
        System.out.println(String.format("%s\n%s\n%s\n%s\n", BORDER, line1, line2, BORDER));
    }
    void answer(String input) {
        String response = SPACE;
        if (input.equals("bye")) {
            response = response.concat("Bye. Hope to see you again soon!");
        } else {
            response = response.concat(input);
        }
        System.out.println(String.format("%s\n%s\n%s\n", BORDER, response, BORDER));
        }
    }
