package top.clearlight.exception;

/**
 * 发布话题失败异常
 * @author Clearlight
 * TODO
 */
public class NoTopicException extends RuntimeException{

	public NoTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTopicException(String message) {
		super(message);
	}

}
