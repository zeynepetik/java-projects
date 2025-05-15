package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Class representing the PCA9685 motor driver.
 * Inherits from the MotorDriver class and provides functionality for controlling motor speed.
 */
public class PCA9685 extends MotorDriver {

    /**
     * Constructor to initialize the PCA9685 motor driver with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public PCA9685(Protocol protocol){
        super(protocol);
    }

    /**
     * Sets the speed of the motor.
     * If the motor is OFF, it will print an error.
     *
     * @param speed The desired motor speed.
     */
    @Override
    public void setMotorSpeed(int speed){
        if(state==State.OFF){
            System.err.println(getName()+" is Turned OFF. Cannot set the speed");
        }else{
            getProtocol().write(Integer.toString(speed));
        }
    }

    /**
     * Returns the name of the motor driver.
     *
     * @return The name of the motor driver ("PCA9685").
     */
    @Override
    public String getName(){
        return "PCA9685";
    }

    /**
     * Turns the motor driver ON.
     */
    @Override
    public void turnON(){
        state=State.ON;
        System.out.println(getName()+": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the motor driver OFF.
     */
    @Override
    public void turnOFF(){
        state=State.OFF;
        System.out.println(getName()+": Turning OFF.");
        getProtocol().write("turnOFF");
    }

     /**
     * Returns the current state of the motor driver.
     *
     * @return The current state of the motor driver.
     */
    @Override
    public State getState(){
        return state;
    }
}
