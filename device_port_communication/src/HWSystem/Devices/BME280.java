package src.HWSystem.Devices;

import src.HWSystem.Protocols.I2C;
import src.HWSystem.Protocols.Protocol;
import src.HWSystem.Protocols.SPI;

public class BME280 extends TempSensor{
    public BME280(String protocol){
        super(selectProtocol(protocol));
    }
    private static Protocol selectProtocol(String protocol){
        if(protocol.equals("SPI")){
            return new SPI();
        }
        else if(protocol.equals("I2C")){
            return new I2C();
        }
        else{
            throw new IllegalArgumentException("Invalid Protocol for BME280. Try SPI or I2C");
        }
    }
    @Override
    public String getName(){
        return "BME280";
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
