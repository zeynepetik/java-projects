package src.Main;

import java.util.Scanner;
import src.PlanetSystem.*;

/**
 * Entry point of the planetary system program. Accepts CLI commands for managing the system.
 */
public class Main {
    public static void main(String[] args){
        planetSystem system= null;
        Scanner scan = new Scanner(System.in);
        boolean running=true;
        String starName, planetName, satelliteName, parentName;
        double temp,pres,hum,rad;
        while(running){
            String command=scan.nextLine().trim();
            String[] parts=command.split(" ");
            System.out.println("\nCommand: "+command);
            try {
                switch(parts[0]){
                    case "create":
                        if(parts.length!=7){
                            System.err.println("Must be used as: create planetSystem <starName> <temretaure> <pressure> <humidity> <radiation>\n");
                            break;
                        }
                        starName=parts[2];
                        temp=Double.parseDouble(parts[3]); 
                        pres=Double.parseDouble(parts[4]);
                        hum=Double.parseDouble(parts[5]);
                        rad=Double.parseDouble(parts[6]);
                        system=new planetSystem(starName, temp,pres, hum, rad);
                        System.out.println("Planetary system created with star: "+starName+"\n");
                        break;
                    case "addPlanet":
                        if(parts.length!=7){
                            System.err.println("Must be used as: addPlanet <planetName> <parentName> <temperature> <pressure> <humidity> <radiation>\n");
                            break;
                        }
                        planetName=parts[1]; parentName=parts[2];
                        temp=Double.parseDouble(parts[3]);
                        pres=Double.parseDouble(parts[4]);  
                        hum=Double.parseDouble(parts[5]);
                        rad=Double.parseDouble(parts[6]);
                        system.addPlanet(planetName,parentName,temp, pres, hum,rad);
                        break;
                    case "addSatellite":
                        if(parts.length!=7){
                            System.err.println("Must be used as: addSatellite <satelliteName> <parentName> <temperature> <pressure> <humidity> <radiation>\n");
                            break;
                        }
                        satelliteName=parts[1]; parentName=parts[2];
                        temp=Double.parseDouble(parts[3]);
                        pres=Double.parseDouble(parts[4]);
                        hum=Double.parseDouble(parts[5]);
                        rad=Double.parseDouble(parts[6]);
                        system.addSatellite(satelliteName,parentName,temp, pres, hum,rad);
                        break;
                    case "findRadiationAnomalies":
                        if(parts.length!=2){
                            System.err.println("Must be used as: findRadiationAnomalies <threshold>\n");
                            break;
                        }
                        double threshold=Double.parseDouble(parts[1]);
                        system.findRadiationAnomalies(threshold);
                        break;
                    case "getPathTo":
                        if(parts.length!=2){
                            System.err.println("Must be used as: getPathTo <planetName>\n");
                            break;
                        }
                        planetName=parts[1];
                        system.getPathTo(planetName);
                        break;
                    case "printMissionReport":
                        if(parts.length>2){
                            System.err.println("Must be used as: printMissionReport OR printMissionReport <nodeName> \n");
                        }
                        else if(parts.length==2){
                            String nodeName=parts[1];
                            system.printMissionReport(nodeName);
                        }
                        else if(parts.length==1){
                            system.printMissionReport();
                        }
                        break;
                    case "exit":
                        running =false;
                        System.out.println("Exitting...\n");
                        break;
                    default:
                        System.out.println("Invalid command. Please try again.\n");
                        break;
                }
            } catch(Exception e){
                System.err.println("An error occured..."+e.getMessage()+"\n");
            } 
            
        }
        scan.close();
    }
}
