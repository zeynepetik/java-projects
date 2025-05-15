package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class IMUSensor extends Sensor{
    public IMUSensor(Protocol protocol){
        super(protocol);
    }
    @Override
    public String getSensType(){
        return "IMUSensor";
    }
    @Override
    public String data2String(){
        return String.format("Accel: %.2f, Rot: %.2f",getAccel(),getRot());
    }
    public float getAccel(){
        String readData=getProtocol().read();
        System.out.println(readData);
        float accel=(float)(Math.random()*10);
        return accel;
    }
    public float getRot(){
        String readData=getProtocol().read();
        System.out.println(readData);
        float rot=(float)(Math.random()*10);
        return rot;
    }
}
