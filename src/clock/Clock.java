package clock;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock {
	
	private int hour;
	private int min;
	private int sec;
	
	public Clock() {
		
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		this.hour=calendar.get(GregorianCalendar.HOUR_OF_DAY); //on recupere l'heure actuelle au format 12h
		this.min=calendar.get(GregorianCalendar.MINUTE); //on recupere les minutes actuelles
		this.sec=calendar.get(GregorianCalendar.SECOND); //on recupère les secondes actuelles
		
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public void setMinutes(int minutes) {
		this.min = minutes;
	}
	
	public void setSeconds(int seconds) {
		this.sec = seconds;
	}
	
	public int getHour() {
		return this.hour;
	}
	
	public int getMinutes() {
		return this.min;
	}
	
	public int getSeconds() {
		return this.sec;
	}
	
	
	public void increment() {
		if((getSeconds()+1) % 60 == 0) { //Si on va atteindre 60 secondes a la prochaine incrémentation
			setSeconds(0);
			if((getMinutes()+1) % 60 == 0) {
				setMinutes(0);
				
				if((getHour()+1) % 13 == 0) {
					setHour(1);
				}else {
					setHour(getHour()+1);
				}
			}else {
				setMinutes(getMinutes()+1);
			}
		}else {
			setSeconds(getSeconds()+1);
		}
	}
}
