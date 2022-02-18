package serialezi_handler;

import device.DeviceAbstract;

import java.util.ArrayList;

public interface SerializeHandler {

    String PATH_SAVE_SER = "src/files/save.ser";

    void serializeArray();
    ArrayList<DeviceAbstract> deserializeArray();
}
