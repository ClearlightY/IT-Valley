package top.clearlight.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


public class SimpleHashUtil {

	/**
	 * 加密
	 * @param hashAlgorithmName: 加密类型
	 * @param credentials: 加密对象
	 * @param salt: 盐值
	 * @param hashIterations: 加密次数
	 * @return
	 */
	public static SimpleHash simpleHash(String hashAlgorithmName,String credentials,Object salt,int hashIterations) {
		return new SimpleHash(hashAlgorithmName, credentials, ByteSource.Util.bytes(salt), hashIterations);
	}
}
