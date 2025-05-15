package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * The DHT11 class represents a temperature sensor device that extends the TempSensor class.
 * It provides functionality to interact with the sensor using a specified protocol.
 * The DHT11 sensor can be turned ON or OFF and its state can be retrieved.
 */
public class DHT11 extends TempSensor {

    /**
     * Constructs a DHT11 temperature sensor with the specified protocol.
     *
     * @param protocol The protocol used to communicate with the sensor.
     */
    public DHT11(Protocol protocol) {
        super(protocol);
    }

    /**
     * Retrieves the name of the sensor.
     *
     * @return The name of the sensor, which is "DHT11".
     */
    @Override
    public String getName() {
        return "DHT11";
    }

    /**
     * Turns the sensor ON and sends a "turnON" command using the protocol.
     * Updates the state of the sensor to ON.
     */
    @Override
    public void turnON() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the sensor OFF and sends a "turnOFF" command using the protocol.
     * Updates the state of the sensor to OFF.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF.");
        getProtocol().write("turnOFF");
    }

    /**
     * Retrieves the current state of the sensor.
     *
     * @return The current state of the sensor, which can be ON or OFF.
     */
    @Override
    public State getState() {
        return state;
    }
}
