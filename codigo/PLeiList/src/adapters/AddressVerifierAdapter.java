package adapters;

import java.net.MalformedURLException;
import java.net.URL;
import net.padroesfactory.AddressVerifier;

public class AddressVerifierAdapter implements IVerificarEndereco {
	
	private AddressVerifier av = new AddressVerifier();
	
	public boolean verificaEndereco(String url){
		try {
			URL u = new URL(url);
			return av.verifyAddress(u);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
