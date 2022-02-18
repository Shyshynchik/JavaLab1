package serialezi_handler.Impl;

import device.DeviceAbstract;
import logger.impl.LoggerImpl;
import serialezi_handler.SerializeHandler;

import java.io.*;
import java.util.ArrayList;

public class SerializeHandlerImpl implements SerializeHandler {

    private final LoggerImpl log;
    private final ArrayList<DeviceAbstract> deviceArrayList;

    public SerializeHandlerImpl(LoggerImpl log, ArrayList<DeviceAbstract> deviceArrayList) {
        this.log = log;
        this.deviceArrayList = deviceArrayList;
    }

    @Override
    public void serializeArray() {
        log.createInfoLog("Начало сериализации");
        try {
            FileOutputStream outputStream = new FileOutputStream(PATH_SAVE_SER);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(deviceArrayList);
            objectOutputStream.close();
            log.createSuccessLog("Сериализация успешно выполнена",  "Файл помещен в " + PATH_SAVE_SER);
            log.createInfoLog("Сериализация закончена");
        } catch (FileNotFoundException e) {
            log.createErrorLog("Файл не найден", e);
        } catch (IOException e) {
            log.createErrorLog("Ошибка во время записи", e);
        }
    }

    @Override
    public ArrayList<DeviceAbstract> deserializeArray() {
        ArrayList<DeviceAbstract> deviceArrayList = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(PATH_SAVE_SER);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            deviceArrayList = (ArrayList<DeviceAbstract>) objectInputStream.readObject();
            log.createSuccessLog("Десериализация успешно выполнена",  "Файл помещен в массив " + deviceArrayList);
            log.createInfoLog("Десериализация закончена");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            log.createErrorLog("Ошибка во время десереализации", e);
        }

        return deviceArrayList;
    }
}
