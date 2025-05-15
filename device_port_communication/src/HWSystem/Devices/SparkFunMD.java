package src.HWSystem.Devices;

import src.HWSystem.Protocols.SPI;

public class SparkFunMD extends MotorDriver{
    public SparkFunMD(){
        super(new SPI());
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
        return "SparkFunMD";
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
