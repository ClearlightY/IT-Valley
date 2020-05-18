package top.clearlight.exception;

/**
 * 重复注册/修改异常
 */
public class RepeatUserException extends RuntimeException{

	public RepeatUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatUserException(String message) {
		super(message);
	}

}
