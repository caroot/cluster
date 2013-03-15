package de.org.cluster.controller.interfaces;

import java.io.IOError;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class determine which methods the BasicOnlineTest should implement 
 * @author danielrhein
 *
 */
public interface BasicOnlineTestInterface {


/**
 * If you'll need a Password you'll should use this method.<br>
 * SecurityRealm is needed to determin wich security Realm you'll need.
 * @param Server
 * @param port
 * @param username
 * @param password
 * @param SecurityRealm
 * @return
 * @throws IOError
 * @throws ConnectException
 * @throws UnknownHostException
 */
    boolean isAvailable(InetAddress Server,int port,String username,char[] password, String SecurityRealm) throws IOError,ConnectException,UnknownHostException;
   /**
    * This method allows you to create a connection to the Server without a password
    * SecurityRealm is needed to determin wich security Realm you'll need.
    * @param Server
    * @param port
    * @param username
    * @param SecurityRealm
    * @return
    * @throws IOError
    * @throws ConnectException
    * @throws UnknownHostException
    */
    boolean isAvailable(InetAddress Server,int port,String username, String SecurityRealm) throws IOError,ConnectException,UnknownHostException;

}
