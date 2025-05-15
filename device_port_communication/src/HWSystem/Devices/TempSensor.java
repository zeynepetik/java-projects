package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class TempSensor extends Sensor{
    public TempSensor(Protocol protocol){
        super(protocol);
    }
    @Override
    public String getSensType(){
        return "TempSensor";
    }
    @Override
    public String data2String(){
        return String.format("Temperature: %.2fC", getTemp());
    }
    public float getTemp(){
        String readData=getProtocol().read();
        System.out.println(readData);
        float temp= (float)(Math.random()*100);
        return temp;
        
    }
    
}
