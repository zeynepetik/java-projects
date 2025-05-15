package src.HWSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import src.HWSystem.Devices.*;
import src.HWSystem.Protocols.*;

public class HWSystem {
    private ArrayList<Protocol> ports;
    private ArrayList<Device> devices;
    //to be able to store assigned ports with their devices
    private ArrayList<Device> portAssignment;
    private ArrayList<Sensor> sensors;
    private ArrayList<Display> displays;
    private ArrayList<WirelessIO> wirelesses;
    private ArrayList<MotorDriver> motorDrivers;
    private int maxDisplay, maxSensor, maxWireless, maxMotorDriver;
    //to be able to count the devices and control whether it reached max
    private int display=0,sensor=0,wireless=0,motorDriver=0;
    public HWSystem(String filename){
        ports=new ArrayList<>();
        devices=new ArrayList<>();
        portAssignment=new ArrayList<>();
        sensors=new ArrayList<>();
        displays=new ArrayList<>();
        wirelesses=new ArrayList<>();
        motorDrivers=new ArrayList<>();
        readConfig(filename);
    }

    private void readConfig(String filename){
        try {
            File file=new File(filename);
            Scanner scan=new Scanner(file);
            while(scan.hasNextLine()){
                String line=scan.nextLine();
                if(line.startsWith("Port Configuration:")){
                    String[] portTypes=line.split(":")[1].split(",");
                    for(String portType: portTypes){
                        Protocol protocol=createProtocol(portType.trim());
                        ports.add(protocol);
                        portAssignment.add(null);
                    }
                }
                else if(line.startsWith(("# of sensors:"))){
                    maxSensor=Integer.parseInt(line.split(":")[1].trim());
                }
                else if(line.startsWith(("# of displays:"))){
                    maxDisplay=Integer.parseInt(line.split(":")[1].trim());
                }
                else if(line.startsWith(("# of wireless adapters:"))){
                    maxWireless=Integer.parseInt(line.split(":")[1].trim());
                }
                else if(line.startsWith(("# of motor drivers:"))){
                    maxMotorDriver=Integer.parseInt(line.split(":")[1].trim());
                }
            }
            scan.close();
        }
        catch (Exception e) {
            System.out.println("Error while reading cınfiguration file..."+e.getMessage());
        }
    }
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
    
    private boolean isDevIDTaken(String devType, int devID){
    	for(Device d:devices){
    		if(d.getDevType().equals(devType) && getDevID(d)==devID){
    			return true;
    		}
    		if(d.getDevType().contains("Sensor") && getDevID(d)==devID){
    			return true;
    		}
    	}
    	return false;
    }
    
    public void addDevice(String device, int portID, int devID){
        if(portID<0 || portID>=ports.size()){
            System.out.println("Error: Invalid portID");
            return;
        }
        //control if the port is occupied from portAssignment 
        if(portAssignment.get(portID)!=null){
            System.out.println("Error: Port "+ports.get(portID).getProtocolName()+" is already occupied");
            return;
        }
        Protocol portProt=ports.get(portID);
        String protocolName=portProt.getProtocolName();

        try {
            switch(device){
                case "SparkFunMD":
                    if(!protocolName.equals("SPI")){
                        System.out.println("SparkFunMD is not compatible with "+protocolName);
                        return;
                    }
                    if(motorDriver>=maxMotorDriver){
                        System.out.println("MotorDriver has been reached its maximum..");
                        return;
                    }
                    if(devID<0 || devID>=maxMotorDriver){
                        System.out.println("Invalid device ID..");
                        return;
                    }
                    if(isDevIDTaken("MotorDriver", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another MotorDriver device");
                	return;
                }
                    for(Device d: devices){
                        if(d.getName().equals("SparkFunMD")){
                            System.out.println("This device is already used.");
                            return;
                        }
                    }
                    SparkFunMD spark=new SparkFunMD();
                    devices.add(spark);
                    motorDrivers.add(spark);
                    portAssignment.set(portID,spark);
                    motorDriver++;
                    break;

                case "PCA9685":
                if(!protocolName.equals("I2C")){
                    System.out.println("PCA9685 is not compatible with "+protocolName);
                    return;
                }
                if(motorDriver>=maxMotorDriver){
                    System.out.println("MotorDriver has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxMotorDriver){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("MotorDriver", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another MotorDriver device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("PCA9685")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                PCA9685 pca=new PCA9685();
                devices.add(pca);
                motorDrivers.add(pca);
                portAssignment.set(portID,pca);
                motorDriver++;
                break;

                case "OLED":
                if(!protocolName.equals("SPI")){
                    System.out.println("OLED is not compatible with "+protocolName);
                    return;
                }
                if(display>=maxDisplay){
                    System.out.println("Display has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxDisplay){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("Display", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another Display device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("OLED")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                OLED oled=new OLED();
                devices.add(oled);
                displays.add(oled);
                portAssignment.set(portID,oled);
                display++;
                break;

                case "LCD":
                if(!protocolName.equals("I2C")){
                    System.out.println("LCD is not compatible with "+protocolName);
                    return;
                }
                if(display>=maxDisplay){
                    System.out.println("Display has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxDisplay){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("Display", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another Display device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("LCD")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                LCD lcd=new LCD();
                devices.add(lcd);
                displays.add(lcd);
                portAssignment.set(portID,lcd);
                display++;
                break;

                case "Bluetooth":
                if(!protocolName.equals("UART")){
                    System.out.println("Bluetooth is not compatible with "+protocolName);
                    return;
                }
                if(wireless>=maxWireless){
                    System.out.println("WirelessIO has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxWireless){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("WirelessIO", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another WirelessIO device");
                	return;
                }
                
                for(Device d: devices){
                    if(d.getName().equals("Bluetooth")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                Bluetooth bluetooth=new Bluetooth();
                devices.add(bluetooth);
                wirelesses.add(bluetooth);
                portAssignment.set(portID,bluetooth);
                wireless++;
                break;

                case "Wifi":
                if(!(protocolName.equals("UART") || protocolName.equals("SPI"))){
                    System.out.println("Wifi is not compatible with "+protocolName);
                    return;
                }
                if(wireless>=maxWireless){
                    System.out.println("WirelessIO has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxWireless){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("WirelessIO", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another WirelessIO device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("Wifi")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                Wifi wifi=new Wifi(protocolName);
                devices.add(wifi);
                wirelesses.add(wifi);
                portAssignment.set(portID,wifi);
                wireless++;
                break;

                case "MPU6050":
                if(!protocolName.equals("I2C")){
                    System.out.println("MPU6050 is not compatible with "+protocolName);
                    return;
                }
                if(sensor>=maxSensor){
                    System.out.println("Sensor has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxSensor){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("Sensor", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another Sensor device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("MPU6050")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                MPU6050 mpu=new MPU6050();
                devices.add(mpu);
                sensors.add(mpu);
                portAssignment.set(portID,mpu);
                sensor++;
                break;

                case "GY-951":
                if(!(protocolName.equals("SPI") || protocolName.equals("UART"))){
                    System.out.println("GY-951 is not compatible with "+protocolName);
                    return;
                }
                if(sensor>=maxSensor){
                    System.out.println("Sensor has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxSensor){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("Sensor", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another Sensor device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("GY-951")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                GY951 gy=new GY951(protocolName);
                devices.add(gy);
                sensors.add(gy);
                portAssignment.set(portID,gy);
                sensor++;
                break;

                case "BME280":
                if(!(protocolName.equals("SPI") || protocolName.equals("I2C"))){
                    System.out.println("BME280 is not compatible with "+protocolName);
                    return;
                }
                if(sensor>=maxSensor){
                    System.out.println("Sensor has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxSensor){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("Sensor", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another Sensor device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("BME280")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                BME280 bme=new BME280(protocolName);
                devices.add(bme);
                sensors.add(bme);
                portAssignment.set(portID,bme);
                sensor++;
                break;

                case "DHT11":
                if(!protocolName.equals("OneWire")){
                    System.out.println("DHT11 is not compatible with "+protocolName);
                    return;
                }
                if(sensor>=maxSensor){
                    System.out.println("Sensor has been reached its maximum..");
                    return;
                }
                if(devID<0 || devID>=maxSensor){
                    System.out.println("Invalid device ID..");
                    return;
                }
                if(isDevIDTaken("Sensor", devID)){
                	System.out.println("Device ID "+ devID+" is taken by another Sensor device");
                	return;
                }
                for(Device d: devices){
                    if(d.getName().equals("DHT11")){
                        System.out.println("This device is already used.");
                        return;
                    }
                }
                DHT11 dht=new DHT11();
                devices.add(dht);
                sensors.add(dht);
                portAssignment.set(portID,dht);
                sensor++;
                break;

                default:
                    System.out.println("Unknown device: "+device);
                    return;
            }
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Device could not be added..."+e.getMessage());
        }
    }
    public void removeDevice(int portID){
        if(portID<0 || portID>=ports.size()){
            System.out.println("Invalid port ID");
            return;
        }
        Device device=portAssignment.get(portID);
        if(device==null){
            System.out.println("There is no device on the port "+portID);
            return;
        }
        if(device.getState()==Device.State.ON){
            System.out.println("Device is turned ON cannot remove");
            return;
        }
        switch(device.getDevType()){
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
        portAssignment.set(portID,null);
        devices.remove(device);
    }

    public void listOfPorts(){
        System.out.println("List Of Ports");
        for(int i=0;i<ports.size();i++){
            Protocol protocol=ports.get(i);
            Device device=portAssignment.get(i);
            if(device==null){
                System.out.println(i+" "+protocol.getProtocolName()+" empty");
            }
            else{
                System.out.println(i+" "+protocol.getProtocolName()+" occupied "+
                    device.getName()+" "+ device.getDevType()+ " "+
                    getDevID(device)+" "+device.getState());
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

    public void turnDeviceON(int portID){
        if(portID<0 || portID>=ports.size()){
            System.out.println("Invalid port ID");
            return;
        }
        Device device =portAssignment.get(portID);
        if(device==null){
            System.out.println("There is no device on port "+portID);
            return;
        }
        device.turnON();
    }
    public void turnDeviceOff(int portID){
        if(portID<0 || portID>=ports.size()){
            System.out.println("Invalid port ID");
            return;
        }
        Device device =portAssignment.get(portID);
        if(device==null){
            System.out.println("There is no device on port "+portID);
            return;
        }
        device.turnOFF();
    }
    public void listSpecificTypes(String devType){
        System.out.println("List of "+ devType+"s");
        for (int i=0; i<ports.size();i++) {
            Protocol prot=ports.get(i);
            Device dev=portAssignment.get(i);
            if(dev==null){
            	continue;
            }
          
            if(dev.getDevType().equals(devType)){
                System.out.println(dev.getName()+" "+getDevID(dev)+
                " "+i+" "+prot.getProtocolName());
            }
        }
    }
    public void readSensor(int devID){
        if(devID<0 || devID>maxSensor){
            System.out.println("Invalid device ID");
            return;
        }
        Sensor sens=sensors.get(devID);
        if(sens.getState()==Device.State.OFF){
            System.out.println("Device has been turned OFF. Cannot read the data");
            return;
        }
        System.out.println(sens.getName()+" "+sens.getDevType()+" "+sens.data2String());
    }
    //to be able to call 
    public void printDisplay(int devID,String data){
        if(devID<0 || devID>maxDisplay){
            System.out.println("Invalid device ID");
        }
        Display dis=displays.get(devID);
        if(dis.getState()==Device.State.OFF){
            System.out.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(dis.getName()+" "+dis.getDevType());
        dis.printData(data);
    }
    public void readWireless(int devID){
        if(devID<0 || devID>maxWireless){
            System.out.println("Invalid device ID");
        }
        WirelessIO wir=wirelesses.get(devID);
        if(wir.getState()==Device.State.OFF){
            System.out.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(wir.getName()+" "+wir.getDevType());
        System.out.println(wir.recvData());
    }
    public void writeWireless(int devID, String data){
        if(devID<0 || devID>maxWireless){
            System.out.println("Invalid device ID");
        }
        WirelessIO wir=wirelesses.get(devID);
        if(wir.getState()==Device.State.OFF){
            System.out.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(wir.getName()+" "+wir.getDevType());
        wir.sendData(data);
    }
    public void setMotorSpeed(int devID,int speed){
        if(devID<0 || devID>maxMotorDriver){
            System.out.println("Invalid device ID");
        }
        MotorDriver motor=motorDrivers.get(devID);
        if(motor.getState()==Device.State.OFF){
            System.out.println("Device has been turned OFF. Cannot print the data");
            return;
        }
        System.out.println(motor.getName()+" "+motor.getDevType());
        motor.setMotorSpeed(speed);
    }    
}
