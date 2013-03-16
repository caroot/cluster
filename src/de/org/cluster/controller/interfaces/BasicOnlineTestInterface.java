package de.org.cluster.controller.interfaces;

import java.io.IOError;
import java.io.IOException;
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
 * @throws IOException
 * @throws IOError
 * @throws ConnectException
 * @throws UnknownHostException
 */
    boolean isAvailable(InetAddress server,int port,String username,char[] password, String securityRealm) throws IOError,ConnectException,UnknownHostException,IllegalArgumentException,IOException;
   /**
    * This method allows you to create a connection to the Server without a password and username
    * SecurityRealm is needed to determin wich security Realm you'll need.
    * @param Server
    * @param port
    * @param SecurityRealm
    * @return
    * @throws IOError
    * @throws IOException
    * @throws ConnectException
    * @throws UnknownHostException
    */
    boolean isAvailable(InetAddress server,int port, String securityRealm) throws IOError,ConnectException,UnknownHostException,IllegalArgumentException,IOException;

    /**
     * Test if the given Server-Address is null or not and return an boolean-value
     * @param server
     * @return
     */
    boolean isServerAdressValid(InetAddress server);
    /**
     * If the given port is between MAX_PORT and MIN_PORT it is valid, wrong otherwise.
     * @param port
     * @return
     */
    boolean isPortNumberValid(int port);
    /**
     * Check if the username is valid or not.
     * @param username
     * @return
     */
    boolean isUsernameValid(String username);
    /**
     * Chekc if the Password is valid or not.
     * @param password
     * @return
     */
    boolean isPasswordValid(String password);
    
    
    //ALSO NEEDED
    //boolean isServer(port,IPAdress)
    //boolean isBackupServer(Port,IPAdress)
    //InedAdress getDomainControllerServer(Port,IPAdress); //IPAdress must be an Server or DomainController
}
