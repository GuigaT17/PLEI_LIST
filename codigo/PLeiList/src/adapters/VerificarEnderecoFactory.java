package adapters;

import utils.Configuration;

public class VerificarEnderecoFactory {
	private static VerificarEnderecoFactory INSTANCE = null;
	private IVerificarEndereco verificador = new AddressVerifierAdapter();
	
	private VerificarEnderecoFactory() {
		Configuration c = Configuration.getInstance();
		verificador = c.createInstanceFromKey("adapter", verificador);
	}
	
	public static VerificarEnderecoFactory getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new VerificarEnderecoFactory();
		}
		return INSTANCE;
	}
	
	public boolean verificaEndereco(String end) {
		return this.verificador.verificaEndereco(end);
	}

}
