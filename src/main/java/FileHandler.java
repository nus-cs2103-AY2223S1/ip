import java.util.Scanner;

/**
 * Read and write data files
 * it also parses the files and directly output the parsed information
 */
public class FileHandler {
    private String fileName="./stored/default.dat";
    private Scanner file;

    public FileHandler(){
        this.file=new Scanner(this.fileName);
    }

    public FileHandler(String fileName){
        this.fileName=fileName;
        this.file=new Scanner(fileName);
    }

    public int syncFromFile(Calendar c){

        return 501;
    }

    public int syncToFile(Calendar c){
        // TODO
        return 501;
    }
}
