package src.HWSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import src.HWSystem.Devices.*;
import src.HWSystem.Protocols.*;

/**
 * Main class for managing the hardware system.
 * It handles the configuration, device management, and communication protocols.
 */
public class HWSystem {
    private ArrayList<Protocol> ports;
    private ArrayList<Device> devices;
    private String logPath;
    //to be able to store assigned ports with their devices
    private ArrayList<Device> portAssignment;
    private ArrayList<Sensor> sensors;
    private ArrayList<Display> displays;
    private ArrayList<WirelessIO> wirelesses;
    private ArrayList<MotorDriver> motorDrivers;
    private int maxDisplay, maxSensor, maxWireless, maxMotorDriver;
    //to be able to count the devices and control whether it reached max
    private int display=0,sensor=0,wireless=0,motorDriver=0;

    /**
     * Constructor to initialize the hardware system with a configuration file and log path.
     *
     * @param filename The configuration file.
     * @param logPath The directory path for logs.
     */
    public HWSystem(String filename, String logPath) {
        this.logPath=logPath;
        ports=new ArrayList<>();
        devices=new ArrayList<>();
        portAssignment=new ArrayList<>();
        sensors=new ArrayList<>();
        displays=new ArrayList<>();
        wirelesses=new ArrayList<>();
        motorDrivers=new ArrayList<>();
        readConfig(filename);
    }

     /**
     * Reads the configuration file and sets up the hardware system.
     *
     * @param filename The configuration file to read.
     */
    private void readConfig(String filename){
        try {
            File file=new File(filename);
            Scanner scan=new Scanner(file);
            while(scan.hasNextLine()){
                String line=scan.nextLine();
                if(line.startsWith("Port Configuration:")){
                    String[] portTypes=line.split(":")[1].split(",");
                    Iterator<String> iter=Arrays.asList(portTypes).iterator();
                    int index=0;
                    while(iter.hasNext()){
                        String portName=iter.next().trim();
                        Protocol protocol=createProtocol(portName);
                        protocol.setLogPath(logPath);
                        protocol.setPortID(index);
                        protocol.createLogFile();
                        ports.add(protocol);
                        portAssignment.add(null);
                        index++;
                    }
                }
                else if(line.startsWith(("# of sensors:"))){
                    maxSensor=Integer.parseInt(line.split(":")[1].trim());
                    for(int i=0;i<maxSensor;i++)
                    	sensors.add(null);
                }
                else if(line.startsWith(("# of displays:"))){
                    maxDisplay=Integer.parseInt(line.split(":")[1].trim());
                    for(int i=0;i<maxDisplay;i++)
                    	displays.add(null);
                }
                else if(line.startsWith(("# of wireless adapters:"))){
                    maxWireless=Integer.parseInt(line.split(":")[1].trim());
                    for(int i=0;i<maxWireless;i++)
                    	wirelesses.add(null);
                    
                }
                else if(line.startsWith(("# of motor drivers:"))){
                    maxMotorDriver=Integer.parseInt(line.split(":")[1].trim());
                    for(int i=0;i<maxMotorDriver;i++)
                    	motorDrivers.add(null);
                }
            }
            scan.close();
        }
        catch (Exception e) {
            System.err.println("Error while reading cınfiguration file..."+e.getMessage());
        }
    }

     /**
     * Creates a protocol object based on the given protocol name.
     *
     * @param protocol The protocol name (e.g., "SPI", "I2C").
     * @return A protocol object.
     */
    private Protocol createProtocol(String protocol){
        switch(protocol){
            case "SPI":
                return new SPI();
            case "I2C":
                return new I2C();
            case "OneWire":
                return new OneWire();
            case "UART":
                return new UART();
            default:
                throw new IllegalArgumentException("Invalid protocol name. Try Again...");
        }
    }
    
     /**
     * Checks if the device ID is already taken by another device of the same type.
     *
     * @param devType The type of the device.
     * @param devID The ID of the device.
     * @return True if the device ID is taken, false otherwise.
     */
    private boolean isDevIDTaken(String devType, int devID){
    	switch(devType){
            case "Sensor":
                if(sensors.get(devID)!=null){
                    return true;
                }
                break;
            case "Display":
                if(displays.get(devID)!=null){
                    return true;
                }
                break;
            case "MotorDriver":
                if(motorDrivers.get(devID)!=null){
                    return true;
                }
                break;
            case "WirelessIO":
                if(wirelesses.get(devID)!=null){
                    return true;
                }
                break;
            default:
                System.err.println("Invalid device type..");
        }
    	return false;
    }

     /**
     * Adds a device to the system based on the device name, port ID, and device ID.
     *
     * @param device The name of the device.
     * @param portID The ID of the port.
     * @param devID The ID of the device.
     */
    public void addDevice(String device, int portID, int devID) {
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Error: Invalid portID");
            return;
        }
        // control if the port is occupied from portAssignment
        if (portAssignment.get(portID) != null) {
            System.err.println("Error: Port " + ports.get(portID).getProtocolName() + " is already occupied");
            return;
        }
        Protocol portProt = ports.get(portID);
        String protocolName = portProt.getProtocolName();
    
        try {
            switch (device) {
                case "SparkFunMD":
                    if (!protocolName.equals("SPI")) {
                        System.err.println("SparkFunMD is not compatible with " + protocolName);
                        return;
                    }
                    if (motorDriver >= maxMotorDriver) {
                        System.err.println("MotorDriver has been reached its maximum..");
                        return;
                    }
                    if (devID < 0 || devID >= maxMotorDriver) {
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if (isDevIDTaken("MotorDriver", devID)) {
                        System.err.println("Device ID " + devID + " is taken by another MotorDriver device");
                        return;
                    }
                    SparkFunMD spark = new SparkFunMD(portProt);
                    devices.add(spark);
                    motorDrivers.set(devID, spark);
                    portAssignment.set(portID, spark);
                    motorDriver++;
                    break;
    
                case "PCA9685":
                    if (!protocolName.equals("I2C")) {
                        System.err.println("PCA9685 is not compatible with " + protocolName);
                        return;
                    }
                    if (motorDriver >= maxMotorDriver) {
                        System.err.println("MotorDriver has been reached its maximum..");
                        return;
                    }
                    if (devID < 0 || devID >= maxMotorDriver) {
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if (isDevIDTaken("MotorDriver", devID)) {
                        System.err.println("Device ID " + devID + " is taken by another MotorDriver device");
                        return;
                    }
                    PCA9685 pca = new PCA9685(portProt);
                    devices.add(pca);
                    motorDrivers.set(devID, pca);
                    portAssignment.set(portID, pca);
                    motorDriver++;
                    break;
    
                    case "OLED":
                    if(!protocolName.equals("SPI")){
                        System.err.println("OLED is not compatible with "+protocolName);
                        return;
                    }
                    if(display>=maxDisplay){
                        System.err.println("Display has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxDisplay){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("Display", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another Display device");
                        return;
                    }
                    OLED oled=new OLED(portProt);
                    devices.add(oled);
                    displays.set(devID, oled);
                    portAssignment.set(portID,oled);
                    display++;
                    break;
    
                    case "LCD":
                    if(!protocolName.equals("I2C")){
                        System.err.println("LCD is not compatible with "+protocolName);
                        return;
                    }
                    if(display>=maxDisplay){
                        System.err.println("Display has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxDisplay){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("Display", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another Display device");
                        return;
                    }
                    LCD lcd=new LCD(portProt);
                    devices.add(lcd);
                    displays.set(devID, lcd);
                    portAssignment.set(portID,lcd);
                    display++;
                    break;
    
                    case "Bluetooth":
                    if(!protocolName.equals("UART")){
                        System.err.println("Bluetooth is not compatible with "+protocolName);
                        return;
                    }
                    if(wireless>=maxWireless){
                        System.err.println("WirelessIO has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxWireless){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("WirelessIO", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another WirelessIO device");
                        return;
                    }
                    Bluetooth bluetooth=new Bluetooth(portProt);
                    devices.add(bluetooth);
                    wirelesses.set(devID, bluetooth);
                    portAssignment.set(portID,bluetooth);
                    wireless++;
                    break;
    
                    case "Wifi":
                    if(!(protocolName.equals("UART") || protocolName.equals("SPI"))){
                        System.err.println("Wifi is not compatible with "+protocolName);
                        return;
                    }
                    if(wireless>=maxWireless){
                        System.err.println("WirelessIO has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxWireless){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("WirelessIO", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another WirelessIO device");
                        return;
                    }
                    Wifi wifi=new Wifi(portProt);
                    devices.add(wifi);
                    wirelesses.set(devID, wifi);
                    portAssignment.set(portID,wifi);
                    wireless++;
                    break;
    
                    case "MPU6050":
                    if(!protocolName.equals("I2C")){
                        System.err.println("MPU6050 is not compatible with "+protocolName);
                        return;
                    }
                    if(sensor>=maxSensor){
                        System.err.println("Sensor has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxSensor){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("Sensor", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another Sensor device");
                        return;
                    }
                    MPU6050 mpu=new MPU6050(portProt);
                    devices.add(mpu);
                    sensors.set(devID, mpu);
                    portAssignment.set(portID,mpu);
                    sensor++;
                    break;
    
                    case "GY-951":
                    if(!(protocolName.equals("SPI") || protocolName.equals("UART"))){
                        System.err.println("GY-951 is not compatible with "+protocolName);
                        return;
                    }
                    if(sensor>=maxSensor){
                        System.err.println("Sensor has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxSensor){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("Sensor", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another Sensor device");
                        return;
                    }
                    GY951 gy=new GY951(portProt);
                    devices.add(gy);
                    sensors.set(devID, gy);
                    portAssignment.set(portID,gy);
                    sensor++;
                    break;
    
                    case "BME280":
                    if(!(protocolName.equals("SPI") || protocolName.equals("I2C"))){
                        System.err.println("BME280 is not compatible with "+protocolName);
                        return;
                    }
                    if(sensor>=maxSensor){
                        System.err.println("Sensor has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxSensor){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("Sensor", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another Sensor device");
                        return;
                    }
                    BME280 bme=new BME280(portProt);
                    devices.add(bme);
                    sensors.set(devID, bme);
                    portAssignment.set(portID,bme);
                    sensor++;
                    break;
    
                    case "DHT11":
                    if(!protocolName.equals("OneWire")){
                        System.err.println("DHT11 is not compatible with "+protocolName);
                        return;
                    }
                    if(sensor>=maxSensor){
                        System.err.println("Sensor has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxSensor){
                        System.err.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("Sensor", devID)){
                        System.err.println("Device ID "+ devID+" is taken by another Sensor device");
                        return;
                    }
                    DHT11 dht=new DHT11(portProt);
                    devices.add(dht);
                    sensors.set(devID, dht);
                    portAssignment.set(portID,dht);
                    sensor++;
                    break;
                default:
                    System.err.println("Unknown device: " + device);
                    return;
            }
            System.out.println("Device added.");
    
        } catch (Exception e) {
            throw new IllegalArgumentException("Device could not be added..." + e.getMessage());
        }
    }
    
    /**
     * Removes a device from the system based on the port ID.
     *
     * @param portID The ID of the port to remove the device from.
     */
    public void removeDevice(int portID) {
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID");
            return;
        }
        Device device = portAssignment.get(portID);
        if (device == null) {
            System.err.println("There is no device on the port " + portID);
            return;
        }
        if (device.getState() == Device.State.ON) {
            System.err.println("Device is turned ON cannot remove");
            return;
        }
        switch (device.getDevType()) {
            case "Sensor":
                sensors.remove(device);
                break;
            case "Display":
                displays.remove(device);
                break;
            case "WirelessIO":
                wirelesses.remove(device);
                break;
            case "MotorDriver":
                motorDrivers.remove(device);
                break;
        }
        portAssignment.set(portID, null);
        devices.remove(device);
        System.out.println("Device removed.");
    }
    
     /**
     * Lists the configuration of all ports in the system.
     */
    public void listOfPorts() {
        System.out.println("list of ports:");
        for (int i = 0; i < ports.size(); i++) {
            Protocol protocol = ports.get(i);
            Device device = portAssignment.get(i);
            if (device == null) {
                System.out.println(i + " " + protocol.getProtocolName() + " empty");
            } else {
                System.out.println(i + " " + protocol.getProtocolName() + " occupied " +
                    device.getName() + " " + device.getDevType() + " " +
                    getDevID(device) + " " + device.getState());
            }
        }
    }
    private int getDevID(Device device){
        String devType=device.getDevType();
        int devID=0;
        for (int i=0;i<portAssignment.size();i++) {
            Device dev=portAssignment.get(i);
            //if there is no device in that index skip 
            if(dev==null){
            	continue;
            }
            if(dev.getDevType().equals(devType) && !dev.getName().equals(device.getName())){
                devID++;
            }
            else{
                return devID;
            }
        }
        return -1;
    }

     /**
     * Turns a device on based on its port ID.
     *
     * @param portID The ID of the port.
     */
    public void turnDeviceON(int portID) {
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID");
            return;
        }
        Device device = portAssignment.get(portID);
        if (device == null) {
            System.err.println("There is no device on port " + portID);
            return;
        }
        device.turnON();
    }
    
    /**
     * Turns a device off based on its port ID.
     *
     * @param portID The ID of the port.
     */
    public void turnDeviceOff(int portID) {
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID");
            return;
        }
        Device device = portAssignment.get(portID);
        if (device == null) {
            System.err.println("There is no device on port " + portID);
            return;
        }
        device.turnOFF();
    }

     /**
     * Lists all devices of a specific type.
     *
     * @param devType The type of the device (e.g., "Sensor").
     */
    public void listSpecificTypes(String devType) {
        System.out.println("list of " + devType + "s:");
        for (int i = 0; i < ports.size(); i++) {
            Protocol prot = ports.get(i);
            Device dev = portAssignment.get(i);
            if (dev == null) {
                continue;
            }
    
            if (dev.getDevType().equals(devType)) {
                System.out.println(dev.getName() + " " + getDevID(dev) +
                    " " + i + " " + prot.getProtocolName());
            }
            // to be able to get sensor since getDevType returns with its sensor type
            else if (dev.getDevType().contains(devType)) {
                System.out.println(dev.getName() + " " + getDevID(dev) +
                    " " + i + " " + prot.getProtocolName());
            }
        }
    }
    
     /**
     * Reads data from a sensor based on its device ID.
     *
     * @param devID The ID of the sensor device.
     */
    public void readSensor(int devID) {
        if (devID < 0 || devID > maxSensor) {
            System.err.println("Invalid device ID");
            return;
        }
        Sensor sens = sensors.get(devID);
        if (sens.getState() == Device.State.OFF) {
            System.err.println("Device has been turned OFF. Cannot read the data");
            return;
        }
        System.out.println(sens.getName() + " " + sens.getDevType() + " " + sens.data2String()+".");
    }
    
     /**
     * Prints data to a display device based on its device ID.
     *
     * @param devID The ID of the display device.
     * @param data The data to be printed.
     */
    public void printDisplay(int devID, String data) {
        if (devID < 0 || devID > maxDisplay) {
            System.err.println("Invalid device ID");
            return;
        }
        Display dis = displays.get(devID);
        if (dis.getState() == Device.State.OFF) {
            System.err.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(dis.getName() + " " + dis.getDevType());
        dis.printData(data);
    }
    
     /**
     * Reads data from a wireless I/O device based on its device ID.
     *
     * @param devID The ID of the wireless I/O device.
     */
    public void readWireless(int devID) {
        if (devID < 0 || devID > maxWireless) {
            System.err.println("Invalid device ID");
            return;
        }
        WirelessIO wir = wirelesses.get(devID);
        if (wir.getState() == Device.State.OFF) {
            System.err.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(wir.getName() + " " + wir.getDevType());
        System.out.println(wir.recvData());
    }
    
     /**
     * Writes data to a wireless I/O device based on its device ID.
     *
     * @param devID The ID of the wireless I/O device.
     * @param data The data to be sent.
     */
    public void writeWireless(int devID, String data) {
        if (devID < 0 || devID > maxWireless) {
            System.err.println("Invalid device ID");
            return;
        }
        WirelessIO wir = wirelesses.get(devID);
        if (wir.getState() == Device.State.OFF) {
            System.err.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(wir.getName() + " " + wir.getDevType());
        wir.sendData(data);
    }
    
    /**
     * Sets the motor speed for a motor driver based on its device ID.
     *
     * @param devID The ID of the motor driver device.
     * @param speed The speed to be set.
     */
    public void setMotorSpeed(int devID, int speed) {
        if (devID < 0 || devID > maxMotorDriver) {
            System.err.println("Invalid device ID");
            return;
        }
        MotorDriver motor = motorDrivers.get(devID);
        if (motor.getState() == Device.State.OFF) {
            System.err.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(motor.getName() + " " + motor.getDevType());
        motor.setMotorSpeed(speed);
    }

     /**
     * Writes the log files for all protocols.
     */
    public void writeLogFiles(){
        Iterator<Protocol> iter=ports.iterator();
        while(iter.hasNext()){
            Protocol protocol=iter.next();
            protocol.writeLog();
        }
    }
}
