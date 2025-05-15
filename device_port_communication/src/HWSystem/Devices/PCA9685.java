package src.HWSystem.Devices;

import src.HWSystem.Protocols.I2C;

public class PCA9685 extends MotorDriver {
    public PCA9685(){
        super(new I2C());
    }
    @Override
    public void setMotorSpeed(int speed){
        if(state==State.OFF){
            System.out.println(getName()+" is Turned OFF. Cannot set the speed");
        }else{
            getProtocol().write("setSpeed"+speed);
        }
    }
    @Override
    public String getName(){
        return "PCA9685";
    }
    @Override
    public void turnON(){
        state=State.ON;
        System.out.println(getName()+" is Turning On");
        getProtocol().write("Turn ON");
    }
    @Override
    public void turnOFF(){
        state=State.OFF;
        System.out.println(getName()+" is Turning Off");
        getProtocol().write("Turn OFF");
    }
    @Override
    public State getState(){
        return state;
    }
}
