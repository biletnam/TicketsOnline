package clases;

public class ReleaseSeatsByTime {

	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				new SQLServerConnectionJDBC().actualizar("delete from tbButacasEnProceso where fecha < dateadd(minute, -"+args[0]+", getdate()) ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
