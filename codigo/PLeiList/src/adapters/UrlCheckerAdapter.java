package adapters;

import com.chavetasfechadas.UrlChecker;

public class UrlCheckerAdapter implements IVerificarEndereco{
	private UrlChecker uc = new UrlChecker();
	public boolean verificaEndereco(String url) {
		uc.setUrl(url);
		return uc.validate();
	}
}
