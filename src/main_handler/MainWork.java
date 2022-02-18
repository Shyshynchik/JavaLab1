package main_handler;

import csv_handler.Impl.CSVHandlerImpl;
import device.DeviceAbstract;
import logger.impl.LoggerImpl;
import serialezi_handler.Impl.SerializeHandlerImpl;

import java.util.ArrayList;

public class MainWork {

    private final ArrayList<DeviceAbstract> deviceArrayList = new ArrayList<>();
    private final LoggerImpl log = new LoggerImpl();

    public void run() {
        CSVHandlerImpl csvHandler = new CSVHandlerImpl(log, deviceArrayList);
        SerializeHandlerImpl serializeHandler = new SerializeHandlerImpl(log, deviceArrayList);
        csvHandler.read();
        serializeHandler.serializeArray();
        csvHandler.saveToCsv(serializeHandler.deserializeArray());
    }
}
