package br.com.twsoftware.alfred;

@SuppressWarnings("serial")
public class AlfredException extends RuntimeException {

	public AlfredException() {
	}

	public AlfredException(String message) {
		super(message);
	}

	public AlfredException(Throwable cause) {
		super(cause);
	}

	public AlfredException(String message, Throwable cause) {
		super(message, cause);
	}

}
