package src.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import src.HWSystem.*;

/**
 * Main class to run the hardware system from the command line interface.
 * Handles user input commands and interacts with the HWSystem class.
 */
public class Main {
    public static void main(String[] args) {
        if(args.length<2){
            System.err.println("Usage: java Main configfile logpath");
            return;
        }
        List<String> commandList=new ArrayList<>();
        String configFile=args[0];
        String logPath=args[1];
        File logDirectory=new File(logPath);
        if(!logDirectory.exists()){
            try {
                logDirectory.mkdirs();
            } catch (Exception e) {
                System.err.println("Error creating log directory: " + e.getMessage());
                return;
            }
        }

        Scanner scan=new Scanner(System.in);
        HWSystem system=new HWSystem(configFile,logPath);

        boolean running=true;
        while(running){
            String command=scan.nextLine().trim();
            commandList.add(command);
            if(command.equals("exit")){
                running=false;
            }
        }
        scan.close();

        Iterator<String> iter=commandList.iterator();
        while(iter.hasNext()){
            String command=iter.next();
            String [] parts=command.split(" ");
            int portID, devID;
            String devName,data;
             try {
                switch (parts[0]) {
                    case "turnON":
                        if (parts.length != 2) {
                            System.err.println("Must be used as: turnON <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.turnDeviceON(portID);
                        break;
                    case "turnOFF":
                        if (parts.length != 2) {
                            System.err.println("Must be used as: turnOFF <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.turnDeviceOff(portID);
                        break;
                    case "addDev":
                        if (parts.length != 4) {
                            System.err.println("Must be used as: addDev <DevName> <portID> <DevID>");
                            break;
                        }
                        devName = parts[1];
                        portID = Integer.parseInt(parts[2]);
                        devID = Integer.parseInt(parts[3]);
                        system.addDevice(devName, portID, devID);
                        break;
                    case "rmDev":
                        if (parts.length != 2) {
                            System.err.println("Must be used as: rmDev <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.removeDevice(portID);
                        break;
                    case "list":
                        if (parts.length != 2) {
                            System.err.println("Must be used as: list ports OR list <devType>");
                            break;
                        }
                        if (parts[1].equals("ports")) {
                            system.listOfPorts();
                        } else {
                            system.listSpecificTypes(parts[1]);
                        }
                        break;
                    case "readSensor":
                        if (parts.length != 2) {
                            System.err.println("Must be used as: readSensor <devID>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        system.readSensor(devID);
                        break;
                    case "printDisplay":
                        if (parts.length != 3) {
                            System.err.println("Must be used as: printDisplay <devID> <String>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        data = parts[2];
                        system.printDisplay(devID, data);
                        break;
                    case "readWireless":
                        if (parts.length != 2) {
                            System.err.println("Must be used as: readWireless <devID>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        system.readWireless(devID);
                        break;
                    case "writeWireless":
                        if (parts.length != 3) {
                            System.err.println("Must be used as: writeWireless <devID> <String>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        data = parts[2];
                        system.writeWireless(devID, data);
                        break;
                    case "setMotorSpeed":
                        if (parts.length != 3) {
                            System.err.println("Must be used as: setMotorSpeed <devID> <Integer>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        int d = Integer.parseInt(parts[2]);
                        system.setMotorSpeed(devID, d);
                        break;
                    case "exit":
                        System.out.println("Exitting...");
                        break;
                    default:
                        System.err.println("Invalid command " + parts[0]);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

        }
        system.writeLogFiles();
    }
}