package top.clearlight.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SiteConfig {

	@Autowired
	private CookieConfig cookieConfig;
	@Value("${intro}")
	private String intro;
	
	public CookieConfig getCookieConfig() {
		return cookieConfig;
	}

	public void setCookieConfig(CookieConfig cookieConfig) {
		this.cookieConfig = cookieConfig;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
