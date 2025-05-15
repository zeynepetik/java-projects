package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class Sensor extends Device{
    public Sensor(Protocol protocol){
        super(protocol);
    }
    @Override
    public String getDevType(){
        return getSensType()+ "Sensor";
    }
    public abstract String getSensType();
    public abstract String data2String();
}
