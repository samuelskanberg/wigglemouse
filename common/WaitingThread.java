package common;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/*
 * Waiting thread will sleep the time specified in the monitor by calling
 * getHours(), getMinutes(), getSeconds()
 * If the sleep was successful (i.e. no interrupts) then it will wiggle the mouse
 * using the class java.awt.Robot
 * */

public class WaitingThread extends Thread {
	private Monitor monitor;
	private Robot robot;

	public WaitingThread(Monitor monitor) {
		this.monitor = monitor;
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			System.err.println("Robot couldn't be created");
			System.exit(1);
		}
	}

	public void run() {
		while (true) {
			int secondsToSleep = (monitor.getHours()*60+monitor.getMinutes())*60+monitor.getSeconds();


			try {
				for (int i = 0; i < secondsToSleep; i++) {
					Point point = MouseInfo.getPointerInfo().getLocation();
					int oldX = (int)point.getX();
					int oldY = (int)point.getY();

					Thread.sleep(1000);

					point = MouseInfo.getPointerInfo().getLocation();
					int newX = (int)point.getX();
					int newY = (int)point.getY();

					if (newX != oldX || newY != oldY) {
						throw new Exception();
					}

				}
				wiggle();
			} catch (InterruptedException e) {
				/* Nothing wrong, the user has pressed the button 
				 * which interrupts this thread
				 */
			} catch (Exception e) {
				/* Nothing wrong, the user moved the mouse.
				 * */
			}
		}
	}

	private void wiggle() {
		Point point = MouseInfo.getPointerInfo().getLocation();
		int x = (int)point.getX();
		int y = (int)point.getY();


		try {
			robot.mouseMove(x, y+5);
			Thread.sleep(50);
			robot.mouseMove(x, y-5);
			Thread.sleep(50);
			robot.mouseMove(x, y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
