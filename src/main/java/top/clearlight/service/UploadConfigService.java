package top.clearlight.service;

import java.util.Map;


public interface UploadConfigService extends SystemConfigService {

	@Override
	Map<String, Object> getUploadConfig();
}
