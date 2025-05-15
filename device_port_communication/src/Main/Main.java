package src.Main;

import java.util.Scanner;
import src.HWSystem.*;

public class Main {
    public static void main(String[] args) {
        String configFile="config.txt";
        HWSystem system=new HWSystem(configFile);
        Scanner scan=new Scanner(System.in);
        boolean running=true;
        while(running){
            System.out.print("Command: ");
            //for user input
            String command=scan.nextLine().trim();
            String[] parts=command.split(" "); 
            int portID,devID;
            String devName,data;
            try {
                switch (parts[0]) {
                    case "turnON": 
                        if(parts.length!=2){
                            System.out.println("Must be used as: turnON <portID>");
                            break;
                        }
                        portID=Integer.parseInt(parts[1]);
                        system.turnDeviceON(portID);
                        break;
                    case "turnOFF":
                        if(parts.length!=2){
                            System.out.println("Must be used as: turnOFF <portID>");
                            break;
                        }
                        portID=Integer.parseInt(parts[1]);
                        system.turnDeviceOff(portID);
                        break;
                    case "addDev":
                        if(parts.length!=4){
                            System.out.println("Must be used as: addDev <DevName> <portID> <DevID>");
                            break;
                        }
                        devName=parts[1];
                        portID=Integer.parseInt(parts[2]);
                        devID=Integer.parseInt(parts[3]);
                        system.addDevice(devName, portID, devID);
                        break;
                    case "rmDev":
                        if(parts.length!=2){
                            System.out.println("Must be used as: rmDev <portID>");
                            break;
                        }
                        portID=Integer.parseInt(parts[1]);
                        system.removeDevice(portID);
                        break;
                    case "list":
                        if(parts.length!=2){
                            System.out.println("Must be used as: list ports OR list <devType>");
                            break;
                        }
                        if(parts[1].equals("ports")){
                            system.listOfPorts();
                        }else{
                            system.listSpecificTypes(parts[1]);
                        }
                        break;
                    case "readSensor":
                        if(parts.length!=2){
                            System.out.println("Must be used as: readSensor <devID>");
                            break;
                        }
                        devID=Integer.parseInt(parts[1]);
                        system.readSensor(devID);
                        break;
                    case "printDisplay":
                        if(parts.length!=3){
                            System.out.println("Must be used as: printDisplay <devID> <String>");
                            break;
                        }
                        devID=Integer.parseInt(parts[1]);
                        data=parts[2];
                        system.printDisplay(devID, data);
                        break;
                    case "readWireless":
                        if(parts.length!=2){
                            System.out.println("Must be used as: readWireless <devID>");
                            break;
                        }
                        devID=Integer.parseInt(parts[1]);
                        system.readWireless(devID);
                        break;
                    case "writeWireless":
                        if(parts.length!=3){
                            System.out.println("Must be used as: writeWireless <devID> <String>");
                            break;
                        }
                        devID=Integer.parseInt(parts[1]);
                        data=parts[2];
                        system.writeWireless(devID, data);
                        break;
                    case "setMotorSpeed":
                        if(parts.length!=3){
                            System.out.println("Must be used as: setMotorSpeed <devID> <Integer>");
                            break;
                        }
                        devID=Integer.parseInt(parts[1]);
                        int d=Integer.parseInt(parts[2]);
                        system.setMotorSpeed(devID,d);
                        break;
                    case "exit":
                        running=false;
                        break;
                    default:
                        System.out.println("Invalid command "+parts[0]);
                        break;
                }
            } catch(IllegalArgumentException e) {
                System.out.println("Error: "+e.getMessage());
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}

