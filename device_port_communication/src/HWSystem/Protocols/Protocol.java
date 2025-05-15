package src.HWSystem.Protocols;

public interface Protocol {
    public String getProtocolName();
    public String read();
    public void write(String data);
}
