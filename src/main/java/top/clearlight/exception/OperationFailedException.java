package top.clearlight.exception;

/**
 * 操作失败异常
 */
public class OperationFailedException extends RuntimeException{

	public OperationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationFailedException(String message) {
		super(message);
	}

}
