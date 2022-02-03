import csvhandler.Impl.CSVHandlerImpl;

public class Main {
    public static void main(String[] args) {
        CSVHandlerImpl reader = new CSVHandlerImpl();

        reader.read();
        reader.serializeArray();
        reader.saveToCsv();
    }
}
