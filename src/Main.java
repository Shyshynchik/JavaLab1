import csvhandler.Impl.CSVHandlerImpl;

public class Main {
    public static void main(String[] args) {
        CSVHandlerImpl csvHandler = new CSVHandlerImpl();

        csvHandler.read();
        csvHandler.serializeArray();
        csvHandler.saveToCsv();
    }
}
