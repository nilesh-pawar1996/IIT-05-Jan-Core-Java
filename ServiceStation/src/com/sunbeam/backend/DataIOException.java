package sunbeam.servicestation;

public class DataIOException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public DataIOException() {
	}
	public DataIOException(String message) {
		super(message);
	}
	public DataIOException(Throwable cause) {
		super(cause);
	}
	public DataIOException(String message, Throwable cause) {
		super(message, cause);
	}
	public DataIOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
