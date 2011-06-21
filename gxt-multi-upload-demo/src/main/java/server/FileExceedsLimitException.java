package server;


public class FileExceedsLimitException extends Exception {
	
	public FileExceedsLimitException() {
		super();
	}
	
	public FileExceedsLimitException(String message) {
		super(message);
	}
}
