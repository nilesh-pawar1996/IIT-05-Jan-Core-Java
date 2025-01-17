package sunbeam.servicestation;

public class BillingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BillingException() {
	}
	public BillingException(String message) {
		super(message);
	}
	public BillingException(Throwable cause) {
		super(cause);
	}
	public BillingException(String message, Throwable cause) {
		super(message, cause);
	}
	public BillingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
