package sunbeam.servicestation;

public class CustomerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CustomerException() {
	}
	public CustomerException(String message) {
		super(message);
	}
	public CustomerException(Throwable cause) {
		super(cause);
	}
	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}
	public CustomerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
