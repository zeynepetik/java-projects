package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Abstract class representing a Wireless Input/Output device.
 * Extends the Device class and provides methods for sending and receiving data.
 */
public abstract class WirelessIO extends Device {

    /**
     * Constructor to initialize the WirelessIO device with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public WirelessIO(Protocol protocol){
        super(protocol);
    }

    /**
     * Returns the device type of the WirelessIO.
     *
     * @return The device type ("WirelessIO").
     */
    @Override
    public String getDevType(){
        return "WirelessIO";
    }

    /**
     * Abstract method for sending data over the wireless connection.
     *
     * @param data The data to be sent.
     */
    public abstract void sendData(String data);

    /**
     * Abstract method for receiving data over the wireless connection.
     *
     * @return The received data.
     */
    public abstract String recvData();    
}
