package eccezioni;

public class DuplicateUsernameException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateUsernameException() {
		super();
	}

	public DuplicateUsernameException(String message) {
		super(message);
	}
	

}
