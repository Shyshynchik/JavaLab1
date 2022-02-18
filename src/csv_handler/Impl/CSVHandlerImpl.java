package csv_handler.Impl;

import device.DeviceAbstract;
import device.impl.LaptopImpl;
import device.impl.TabletImpl;
import csv_handler.CSVHandler;
import logger.impl.LoggerImpl;

import java.io.*;
import java.util.ArrayList;

public class CSVHandlerImpl implements CSVHandler, Serializable {

    private final ArrayList<DeviceAbstract> deviceArrayList;
    private final LoggerImpl log;

    public CSVHandlerImpl(LoggerImpl log, ArrayList<DeviceAbstract> deviceArrayList) {
        this.deviceArrayList = deviceArrayList;
        this.log = log;
    }

    @Override
    public void read() {
        BufferedReader bufferedReader;
        String line;
        log.createInfoLog("Чтение из файла");
        try {
            bufferedReader = new BufferedReader(new FileReader(PATH));
            while ((line = bufferedReader.readLine()) != null) {
                readDevice(line);
            }
            log.createInfoLog("Чтение из файла завершено");
            bufferedReader.close();
        } catch (Exception e) {
            log.createErrorLog("Ошибка чтения из файла", e);
        }
    }

    private void readDevice(String line) {
        try {
            String[] devices = line.split(SPLITTER);
            setDevices(devices);
            log.createSuccessLog("Файл успешно записан", line);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.createErrorLog("Не хватает атрибута или нескольких атрибутов", e);
        }
    }

    private void setDevices(String[] devices) {

        if (devices[0].equals("laptop")) {
            LaptopImpl laptop = new LaptopImpl();
            laptop.setData(devices);
            deviceArrayList.add(laptop);
        } else {
            TabletImpl tablet = new TabletImpl();
            tablet.setData(devices);
            deviceArrayList.add(tablet);
        }
    }

    @Override
    public void saveToCsv(ArrayList<DeviceAbstract> deviceArrayList) {
        log.createInfoLog("Начало десериализации");
        try {
            log.createInfoLog("Начало записи в csv файл");
            StringBuilder sb = new StringBuilder();
            PrintWriter writer = new PrintWriter(PATH_SAVE_CSV);
            for (DeviceAbstract device : deviceArrayList) {
                sb.append(device.toString()).append("\n");
            }
            writer.write(sb.toString());
            log.createSuccessLog("Запись в csv файл успешно выполнена", "Файл помещен в " + PATH_SAVE_CSV);
            writer.close();
            log.createInfoLog("Запись в csv файл закончена");
        }catch (FileNotFoundException e) {
            log.createErrorLog("Файл не найден", e);
        } catch (Exception e) {
            log.createErrorLog("Ошибка во время записи файла", e);
        }
        log.logClose();
    }
}
