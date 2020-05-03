package top.clearlight.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CookieConfig {
	
	@Value("${cookie.name}")
	private String name;

	@Value("${cookie.maxAge}")
	private int maxAge;
	
	@Value("${cookie.path}")
	private String path;
	
	@Value("${cookie.httpOnly}")
	private boolean httpOnly;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isHttpOnly() {
		return httpOnly;
	}

	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}
	
}
