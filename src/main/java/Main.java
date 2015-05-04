import com.charsyam.calendar.KoreanCalendar;
import com.charsyam.calendar.LunarDate;

public class Main {

    public static void main(String[] args) {
			KoreanCalendar cal = KoreanCalendar.getInstance();
			LunarDate date = cal.fromSolarDate(1900,1,31);
			System.out.println(date);
    }
}
