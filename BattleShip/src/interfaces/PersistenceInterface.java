package interfaces;

import java.io.FileNotFoundException;

public interface PersistenceInterface {
	public void writeSaveFile(String filename) throws FileNotFoundException;
	
	public void readSaveFile(String filename) throws FileNotFoundException;
	
	
	
}
