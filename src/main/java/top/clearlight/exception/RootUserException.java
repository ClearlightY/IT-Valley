package top.clearlight.exception;

/**
 * 注册用户系统异常
 * @author Clearlight
 * TODO
 */
public class RootUserException extends RuntimeException{

	public RootUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootUserException(String message) {
		super(message);
	}

}
