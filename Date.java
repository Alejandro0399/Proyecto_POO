package proyectoFinal;
import java.util.Calendar;

public class Date implements Comparable<Date> {
	private int day = 1;
	private int month = 1; 
	private int year = 1;
	public static final int MIN_YEAR;
	public static final int MAX_YEAR;
	private String mName;
	private final static String mon_name[] = {"January", "February", "March",
			"April", "May", "June", "July", "August", "September",
			"October", "November", "December"};
	
	static {
		MIN_YEAR = 0;
		MAX_YEAR = 3000;
	}
	
	public Date() {
		Calendar calendario = Calendar.getInstance();
		set_day(calendario.get(Calendar.DAY_OF_MONTH));
		set_month(calendario.get(Calendar.MONTH) + 1); //Inicia en 0. Agregar 1
		set_year(calendario.get(Calendar.YEAR));
	}
	public Date(int day, int month, int year) {
		set_year(year);
		set_month(month);
		set_day(day);
	}
	
	public void set_day(int day) {
		if(isValidDate(day, month, year))
			this.day = day;
	}
	
	public void set_month(int month) {
		if(isValidDate(day, month, year)) {
			this.month = month;
			mName = mon_name[month];
		}
	}
	
	public void set_year(int year) {
		if (year >= MIN_YEAR && year <= MAX_YEAR) {
			this.year = year;
		}
		else {
			System.out.println("Año no valido, asignando valor default 1");
		}
	}
	
	public int get_day() {
		return day;
	}
	
	public int get_year() {
		return year;
	}
	
	public int get_month() {
		return month;
	}
	
	public String get_mName() {
		return mName;
	}
	public static boolean isLeap(int year) {
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		}
		else {
			return false;
		}
	}
		
	public boolean isValidDate(int day, int month, int year) {
		if (month > 0 && month <= 12) {
			switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if(day > 0 && day <= 31) {
					return true;
				}
				else {
					return false;
				}
			case 4:
			case 6:
			case 9:
			case 11:
				if(day > 0 && day <= 30) {
					return true;
				}
				else {
					return false;
				}
			case 2:
				if(isLeap(year)) {
					if(day > 0 && day <= 29) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if(day > 0 && day <= 28) {
						return true;
					}
					else {
						return false;
					}
				}
			default:
				break;
			}
		}
		return false;
	}
	
	
	public String toString() {
		return "" + this.day + '/' + this.month + "/" + this.year;
	}
	
	public int compareTo(Date d) {
		if(this.year < d.year) {
			return -1;
		}
		if(this.year > d.year) {
			return 1;
		}
		
		//Mismo año
		if(this.month < d.month) {
			return -1;
		}
		if(this.month > d.month) {
			return 1;
		}
		
		//Mismo mes
		if(this.day < d.day) {
			return -1;
		}
		if(this.day > d.day) {
			return 1;
		}
		
		//Same day
		return 0;
	}
	
}
