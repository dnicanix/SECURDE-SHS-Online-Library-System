package edu.securde.security;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Log {
	public Logger logger;
	FileHandler fh;
	
	public Log(String filename) throws SecurityException, IOException{
		File f = new File(filename);
		
		if(!f.exists()){
			System.out.println("HELLO DAPAT MAGCECREATE :((");
			f.createNewFile();
		}
		
		fh = new FileHandler(filename, true);
		logger = logger.getLogger("-----SECURDE SHS OLS LOG-----");
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
  
}
