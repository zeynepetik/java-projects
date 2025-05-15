package src.HWSystem.Protocols;

/**
 * Interface representing a protocol for communication.
 * Defines methods for reading, writing, and logging data as well as setting ports.
 */
public interface Protocol {

    /**
     * Returns the name of the protocol.
     *
     * @return The protocol name.
     */
    public String getProtocolName();

     /**
     * Reads data from the protocol.
     *
     * @return The read data.
     */
    public String read();

    /**
     * Writes data to the protocol.
     *
     * @param data The data to be written.
     */
    public void write(String data);

    /**
     * Creates a log file for communication events.
     */
    public void createLogFile();

    /**
     * Writes the communication log to a file.
     */
    public void writeLog();

    /**
     * Sets the port ID for communication.
     *
     * @param portID The port ID to be set.
     */
    public void setPortID(int portID);

    /**
     * Sets the path for logging.
     *
     * @param logPath The log file path.
     */
    public void setLogPath(String logPath);
}
