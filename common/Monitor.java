package common;

public class Monitor {
	private int hours;
	private int minutes;
	private int seconds;
	
	public Monitor() {
		this.hours = 0;
		this.minutes = 10;
		this.seconds = 0;
	}

	public synchronized void setTime(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public synchronized int getHours() {
		return hours;
	}

	public synchronized int getMinutes() {
		return minutes;
	}

	public synchronized int getSeconds() {
		return seconds;
	}
}
