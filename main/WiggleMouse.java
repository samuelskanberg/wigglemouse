package main;

import common.Monitor;
import common.TextDialog;
import common.WaitingThread;

public class WiggleMouse {

	public static void main(String[] args) {

		Monitor monitor = new Monitor();

		try {
			int hours = 0;
			int minutes = 10;
			int seconds = 0;

			/* Parse arguments. The two possible arguments are:
			 * -nogui
			 * -interval hh:mm:ss
			 * */
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-interval")) {
					i++;
					String times[] = args[i].split(":");
					hours = Integer.parseInt(times[0]);
					minutes = Integer.parseInt(times[1]);
					seconds = Integer.parseInt(times[2]);

					if (minutes > 60 || seconds > 60) {
						throw new Exception();
					}
				}
			}
			
			monitor.setTime(hours, minutes, seconds);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Usage: java -jar wigglemouse [-interval hh:mm:ss]");
			System.exit(1);
		} 


		/* We'll create a thread that will sleep for some seconds and
		 * after those seconds it will wiggle the mouse 
		 * */
		WaitingThread waitingThread = new WaitingThread(monitor);
		TextDialog textDialog = new TextDialog(monitor, waitingThread);


		waitingThread.start();
	}
}
