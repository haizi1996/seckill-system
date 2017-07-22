package seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * @author haizi
 *
 */
public class RepeatKillException extends SeckillException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8870743355064389788L;

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}

	
}
