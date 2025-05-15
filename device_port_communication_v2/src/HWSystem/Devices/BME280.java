package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;
/**
 * The BME280 class represents a temperature sensor device that extends the TempSensor class.
 * It provides functionality to interact with the sensor using a specified protocol.
 * The class overrides methods to turn the sensor on and off, retrieve its name, and get its state.
 */
public class BME280 extends TempSensor {

    /**
     * Constructs a BME280 temperature sensor with the specified protocol.
     *
     * @param protocol The communication protocol used by the sensor.
     */
    public BME280(Protocol protocol) {
        super(protocol);
    }

    /**
     * Retrieves the name of the sensor.
     *
     * @return The name of the sensor, which is "BME280".
     */
    @Override
    public String getName() {
        return "BME280";
    }

    /**
     * Turns the sensor on and sets its state to ON.
     * Sends a "turnON" command to the sensor using the protocol.
     */
    @Override
    public void turnON() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the sensor off and sets its state to OFF.
     * Sends a "turnOFF" command to the sensor using the protocol.
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
     * @return The current state of the sensor, either ON or OFF.
     */
    @Override
    public State getState() {
        return state;
    }
}



