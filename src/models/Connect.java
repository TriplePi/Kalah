package models;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Created by TriplePi on 17.05.2017.
 */
public class Connect extends Thread {
    public Connect(String ip, boolean player){

    }

    public static void netter(String ip,int port, boolean player){
        Socket s;
        try {
            s = new Socket(ip,port);
        }
        catch (ConnectException e){
            e.printStackTrace();
            netter(ip,port,player);
            return;
        }
        catch (IOException ignored){}

    }
}
