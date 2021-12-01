package proyectoFinal;

import java.util.Calendar;

public class DateTime extends Date{
	protected int hour, minute, second;
	
	public DateTime(int dd, int mm, int yy, int hh, int mi, int ss) {
		super(dd, mm, yy);
		setHour( hh );
		setMinute( mi );
		setSecond( ss );
	}
	
	public DateTime(int hh, int mi, int ss) {
		super();
		setHour(hh);
		setMinute(mi);
		setSecond(ss);
	}
	
	public DateTime(Date d) {
		super(d.get_day(), d.get_month(), d.get_year());
		Calendar cal = Calendar.getInstance();
		setHour(cal.get(Calendar.HOUR_OF_DAY));
		setMinute(cal.get(Calendar.MINUTE));
		setSecond(cal.get(Calendar.SECOND));
	}
	
	public void setHour( int hh ) {
		hour = (hh >= 0 && hh <= 23) ? hh : hour;
	}
	public void setMinute( int mi ) {
		minute = (mi >= 0 && mi <= 59) ? mi : minute;
	}
	public void setSecond( int ss ) {
		second = (ss >= 0 && ss <= 59) ? ss : second;
	}
	public int getHour() { return hour; }
	public int getMinute() { return minute; }
	public int getSecond() { return second; }

	
}
