package src.HWSystem.Protocols;

public class UART implements Protocol {
    public UART(){

    }
    @Override
    public String getProtocolName(){
        return "UART";
    }
    @Override
    public String read(){
        return "<"+getProtocolName()+">"+":Reading.";
    }
    @Override
    public void write(String data){
        System.out.printf("<%s>: Writing \"%s\".%n", getProtocolName(), data);
    }
}
