package de.org.cluster.controller;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.RealmCallback;

import org.jboss.as.controller.client.ModelControllerClient;

import de.org.cluster.controller.interfaces.BasicOnlineTestInterface;

/**
 * This class provide a JBoss Server Connection<\br>
 * It contains the ability to connect to a specific Server <\br>
 * <ul>
  <li>BasicOnlineTest
    <ul>
      <li>boolean isAvailable(InetAdress Server,Int port,String username,Char[] password, String SecurityRealm)</li>
      <li>boolean isAvailable(InetAdress Server,Int port,String username, String SecurityRealm)</li>	
    </ul>
  </li>

<h2>NOTE<h2>
  <table border="0">
  <tr>
  <td><h2>Note</h2></td>
    <td><p>Both functions will throw errors if they run into one.</p></td>
  <\tr>
  <\table>
 * @author danielrhein
 * @version 1.0
 */
public class BasicOnlineTest implements BasicOnlineTestInterface{
/**
 * This static method provide a connection with password to the given serveradress
 * @param host
 * @param port
 * @param username
 * @param password
 * @param securityRealmName
 * @return
 */
	static private ModelControllerClient createClient(final InetAddress host, final int port,
            final String username, final char[] password, final String securityRealmName) {

final CallbackHandler callbackHandler = new CallbackHandler() {

  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
      for (Callback current : callbacks) {
          if (current instanceof NameCallback) {
              NameCallback ncb = (NameCallback) current;
              ncb.setName(username);
          } else if (current instanceof PasswordCallback) {
              PasswordCallback pcb = (PasswordCallback) current;
              pcb.setPassword(password.toString().toCharArray());
          } else if (current instanceof RealmCallback) {
              RealmCallback rcb = (RealmCallback) current;
              rcb.setText(rcb.getDefaultText());
          } else {
              throw new UnsupportedCallbackException(current);
          }
      }
  }
};

return ModelControllerClient.Factory.create(host, port, callbackHandler);
}

public boolean isAvailable(InetAddress Server, int port, String username,
		char[] password, String SecurityRealm) throws IOError,
		ConnectException, UnknownHostException {
	// TODO Auto-generated method stub
	return false;
}

public boolean isAvailable(InetAddress Server, int port, String username,
		String SecurityRealm) throws IOError, ConnectException,
		UnknownHostException {
	// TODO Auto-generated method stub
	return false;
}

	
	
}
