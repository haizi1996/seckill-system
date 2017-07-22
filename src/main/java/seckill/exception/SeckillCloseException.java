package seckill.exception;

/**
 * 秒杀关闭异常
 * @author haizi
 *
 */
public class SeckillCloseException extends SeckillException {

	private static final long serialVersionUID = 1L;

	public SeckillCloseException(String message) {
		super(message);
	}

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
}
