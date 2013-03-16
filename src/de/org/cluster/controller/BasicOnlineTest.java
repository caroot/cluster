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
import org.jboss.dmr.ModelNode;

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
/**
 * Minimum for a Port-Number
 */
public static int MIN_PORT=0;
/**
 * Maximum for a Port-Number
 */
public static int MAX_PORT=9999;

public boolean isAvailable(InetAddress server, int port, String username,
		char[] password, String securityRealm) throws IOError,
		IOException {
	testServerAddress(server);
	testPort(port);
	testUsername(username);
	testPassword(password.toString());
	testSecurityRealm(securityRealm);
	ModelControllerClient client = null;
	client = createClient(server,port,username,password,securityRealm);
	ModelNode testConnection = new ModelNode();
	testConnection.get("operation").set("read-resource"); 
	client.execute(testConnection);
	client.close();
	return true;
}

public boolean isAvailable(InetAddress server, int port,
		String securityRealm) throws IOError, ConnectException,
		UnknownHostException,IOException {
	testServerAddress(server);
	testPort(port);
	testSecurityRealm(securityRealm);
	ModelControllerClient unauthenticatedClient = null;
	ModelNode testConnection = new ModelNode();
	unauthenticatedClient= ModelControllerClient.Factory.create(server,port);
	testConnection.get("operation").set("read-resource");  // Execute an
	unauthenticatedClient.execute(testConnection);
	unauthenticatedClient.close();
	return true;
}

private void testServerAddress(InetAddress server) throws IllegalArgumentException
{
	if (server==null) throw new IllegalArgumentException("Server can't be null");
}
private void testPort(int port) throws IllegalArgumentException
{
	if (port<MIN_PORT) throw new IllegalArgumentException("Serverport need to be positiv and zero at least");
	if (port>MAX_PORT) throw new IllegalArgumentException("Serverport can be 9999 at least.");
}
private void testSecurityRealm(String securityRealm) throws IllegalArgumentException
{
	if (securityRealm==null) throw new IllegalArgumentException("SecurityRealm can't be null");
}

private void testUsername(String username) throws IllegalArgumentException
{
	if (username==null) throw new IllegalArgumentException("Username can't be null");
}

private void testPassword(String password) throws IllegalArgumentException
{
	if (password==null) throw new IllegalArgumentException("Password can't be null");
}

public boolean isServerAdressValid(InetAddress server)
{
	if (server==null) return false;
	return true;
}

public boolean isPortNumberValid(int port) {
	if (port<MIN_PORT) return false;
	if (port>MAX_PORT) return false;
	return true;
}

public boolean isUsernameValid(String username) {
	if (username!=null) return true;
	return false;
}

public boolean isPasswordValid(String password) {
	if (password!=null) return true;
	return false;
}





	
	
}
