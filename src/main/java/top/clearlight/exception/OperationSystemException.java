package top.clearlight.exception;

/**
 * 系统异常
 */
public class OperationSystemException extends RuntimeException{

	public OperationSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationSystemException(String message) {
		super(message);
	}

}
