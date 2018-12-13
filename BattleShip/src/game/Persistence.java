package game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import interfaces.PersistenceInterface;

public class Persistence implements PersistenceInterface{

	@Override
	public void writeSaveFile(String filename) throws FileNotFoundException {
		try {
			OutputStream os = new FileOutputStream(filename);
		} catch (FileNotFoundException x) {
			throw new FileNotFoundException();
		}
		
		
		
	}

	@Override
	public void readSaveFile(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
