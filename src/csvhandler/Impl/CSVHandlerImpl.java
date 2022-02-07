package csvhandler.Impl;

import device.impl.DeviceImpl;
import device.impl.LaptopImpl;
import device.impl.TabletImpl;
import csvhandler.CSVHandler;
import logger.impl.LoggerImpl;

import java.io.*;
import java.util.ArrayList;

public class CSVHandlerImpl implements CSVHandler, Serializable {

    private final ArrayList<DeviceImpl> deviceArrayList = new ArrayList<>();
    private final LoggerImpl log = new LoggerImpl();

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
    public void saveToCsv() {
        log.createInfoLog("Начало десериализации");
        try {
            ArrayList<DeviceImpl> deviceArrayList = deserializeArray();
            log.createInfoLog("Начало записи в csv файл");
            StringBuilder sb = new StringBuilder();
            PrintWriter writer = new PrintWriter(PATH_SAVE_CSV);
            for (DeviceImpl device : deviceArrayList) {
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

    private ArrayList<DeviceImpl> deserializeArray() {
        ArrayList<DeviceImpl> deviceArrayList = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(PATH_SAVE_SER);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            deviceArrayList = (ArrayList<DeviceImpl>) objectInputStream.readObject();
            log.createSuccessLog("Десериализация успешно выполнена",  "Файл помещен в массив " + deviceArrayList);
            log.createInfoLog("Десериализация закончена");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            log.createErrorLog("Ошибка во время десереализации", e);
        }

        return deviceArrayList;
    }
}
