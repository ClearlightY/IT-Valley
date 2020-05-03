package top.clearlight.exception;

/**
 * 注册失败异常
 * @author Clearlight
 * TODO
 */
public class NoUserException extends RuntimeException{

	public NoUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoUserException(String message) {
		super(message);
	}

}
