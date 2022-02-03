package csvhandler;

public interface CSVHandler {
    String PATH = "src/files/smth.csv";
    String SPLITTER = ",";
    String PATH_SAVE_SER = "src/files/save.ser";
    String PATH_SAVE_CSV = "src/files/smth1.csv";

    void read();

    void serializeArray();

    void saveToCsv();
}
