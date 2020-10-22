import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
    FileImportOutput.readFile();
    FileImportOutput.splitFile();
    FileImportOutput.readMeetingFile();
    FileImportOutput.splitMeetingFile();
    FileImportOutput.readUserFile();
    FileImportOutput.splitUserFile();
    Login.loginMenu();

    }
}
