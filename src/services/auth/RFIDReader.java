package services.auth;

import com.fazecast.jSerialComm.SerialPort;

public class RFIDReader {

    private SerialPort port;

    public boolean start(String portName) {

        port = SerialPort.getCommPort(portName);
        port.setBaudRate(9600);

        return port.openPort();
    }

    public String readUID() {

        byte[] buffer = new byte[1024];

        int read = port.readBytes(buffer, buffer.length);

        if (read > 0) {
            return new String(buffer, 0, read).trim();
        }

        return null;
    }

    public void close() {
        if (port != null) port.closePort();
    }
}