package src.HWSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Class representing the SPI protocol for communication.
 * Implements the Protocol interface and provides methods for reading, writing, and logging data.
 */
public class SPI implements Protocol{
    private String logPath;
    private int portID;
    private Stack<String> entries=new Stack<>();
    public SPI(){
        
    }

    /**
     * Returns the name of the protocol.
     *
     * @return The protocol name ("SPI").
     */
    @Override
    public String getProtocolName(){
        return "SPI";
    }

    /**
     * Reads data from the SPI protocol.
     *
     * @return The read data.
     */
    @Override
    public String read(){
        entries.push("Reading.");
        return "<"+getProtocolName()+">"+":Reading.";
    }

    /**
     * Writes data to the SPI protocol.
     *
     * @param data The data to be written.
     */
    @Override
    public void write(String data){
        String message="Writing \""+data+"\".";
        entries.push(message);
    }

    /**
     * Sets the port ID for communication.
     *
     * @param portID The port ID to be set.
     */
    @Override
    public void setPortID(int portID){
        this.portID=portID;
    }

    /**
     * Sets the path for logging.
     *
     * @param logPath The log file path.
     */
    @Override
    public void setLogPath(String logPath){
        this.logPath=logPath;
    }

     /**
     * Creates a log file for communication events.
     */
    @Override
    public void createLogFile(){
        entries.push("Port Opened.");
    }

    /**
     * Writes the communication log to a file.
     */
    @Override
    public void writeLog(){
        if(logPath==null){
            System.err.println("Log file could not be created.");
            return;
        }
        String fileName=getProtocolName()+"_"+portID+".log";
        try(FileWriter writer=new FileWriter(logPath+File.separator+fileName)){
            while(!entries.isEmpty()){
                String entry=entries.pop();
                writer.write(entry+"\n");
            }
        }catch(Exception e){
            System.err.println("Error while creating logFile: "+e.getMessage());
        }
    }
}