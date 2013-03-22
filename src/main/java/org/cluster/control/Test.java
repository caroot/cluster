package main.java.org.cluster.control;

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

public class Test {

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
	
	public static void main(String args[])
	{
		//Server auf Server-Adresse hierlocal host setzen
		ModelControllerClient client = null;
		ModelControllerClient unauthenticatedClient = null;
		
		try
		{
			InetAddress server = InetAddress.getByName("localhost");
			//unauthenticatedClient = 
			//		ModelControllerClient.Factory.create(server,9999);
			unauthenticatedClient = Test.createClient(server, 9999, "sa", "sa".toCharArray(),"ManagementRealm");
			 ModelNode testConnection = new ModelNode();
			    testConnection.get("operation").set("read-resource");  // Execute an
			    unauthenticatedClient.execute(testConnection);
			    client = unauthenticatedClient;
			   // ModelNode op = new ModelNode();
			    testConnection.get("operation").set("read-resource-description");
			    ModelNode address = testConnection.get("address");
			    address.add("subsystem", "web");
			    address.add("connector", "http"); 
			    testConnection.get("recursive").set(true);
			    testConnection.get("operations").set(true);
			    ModelNode returnVal = client.execute(testConnection);
			    System.out.println(returnVal.get("result").toString());
			    client.close();
		}
		catch(ConnectException ex)
		{
			System.err.println("Fehler bei dem Verbindungsaufbau:"+ ex.getLocalizedMessage());
		}
		catch(UnknownHostException ex)
		{
			System.err.println("Fehler bei Host:"+ ex.getLocalizedMessage());
		}
		//catch(Exception ex){
			//ex.printStackTrace();
		//}
 catch (IOException e) {
	 		System.err.println("Fehler bei dem Verbindungsaufbau:"+ e.getLocalizedMessage());
		}
		
	}

}
