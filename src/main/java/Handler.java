public class Handler {

    public Function parseText(String text){
        String[] txtArr = text.split(" ");
        switch(txtArr[0]){
            case "mark":
                return new MarkFunction(text,false);
            case "unmark":
                return new UnmarkFunction(text,false);
            case "list":
                return new ListFunction(text,false);
            case "todo":
            case "deadline":
            case "event":
                return new AddFunction(text,false);
            case "delete":
                return new DeleteFunction(text,false);
            case "greet":
                return new GreetFunction(text,false);
            case "bye":
                return new ByeFunction(text,true);
            default:
                return new DefaultFunction(text,false);
        }
    }

}

