package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class MotorDriver extends Device{
    public MotorDriver(Protocol protocol){
        super(protocol);
    }
    @Override
    public String getDevType(){
        return "MotorDriver";
    }
    public abstract void setMotorSpeed(int speed);
}
