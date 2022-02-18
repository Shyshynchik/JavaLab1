package csv_handler;

import device.DeviceAbstract;

import java.util.ArrayList;

public interface CSVHandler {
    String PATH = "src/files/smth.csv";
    String SPLITTER = ",";
    String PATH_SAVE_CSV = "src/files/smth1.csv";

    void read();

    void saveToCsv(ArrayList<DeviceAbstract> deviceArrayList);
}
