package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Class representing the SparkFun Motor Driver.
 * Inherits from MotorDriver and provides functionality for controlling motor speed.
 */
public class SparkFunMD extends MotorDriver{

     /**
     * Constructor to initialize the SparkFun Motor Driver with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public SparkFunMD(Protocol protocol){
        super(protocol);
    }

     /**
     * Sets the motor speed.
     * If the motor is OFF, it prints an error.
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
     * @return The name of the motor driver ("SparkFunMD").
     */
    @Override
    public String getName(){
        return "SparkFunMD";
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
