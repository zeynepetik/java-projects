package src.HWSystem.Devices;

import src.HWSystem.Protocols.I2C;

public class MPU6050 extends IMUSensor{
    public MPU6050(){
        super(new I2C());
    }
    @Override
    public String getName(){
        return "MPU6050";
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
