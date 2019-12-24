package db;

public class DbIntegrityException  extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
  // Referential Integrity Custom Solution	
	public DbIntegrityException(String message) {
		super(message);
	}

}
