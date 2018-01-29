package clases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MiLog {
	static public Logger logger = null;
	static private FileHandler fileTxt = null;
	static private SimpleFormatter formatterTxt = null;

	 static {
		 	logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		    logger.setLevel(Level.INFO);
		    try {
				fileTxt = new FileHandler("MiLog.log");
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		    formatterTxt = new SimpleFormatter();
		    fileTxt.setFormatter(formatterTxt);
		    logger.addHandler(fileTxt);
	 }
	 
}
