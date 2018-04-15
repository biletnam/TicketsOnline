package clases;

public class ReleaseSeatsByTime {
	final static int MINUTES = 5;

	public static void main(String[] args) {
		do {
			
			try {
				new SQLServerConnectionJDBC().actualizar("delete from tbButacasEnProceso where fecha < dateadd(minute, -"+MINUTES+", getdate()) ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(MINUTES * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}

}
