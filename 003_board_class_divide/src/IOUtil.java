import java.util.Calendar;

public class IOUtil {
	
	
 //모든 데이터를 외부에서 제공 (class method)
	public static void print(String msg){
		
		System.out.print(msg);
		
	}
	
	public static String  date()
	{
		String calendar;		
		//등록일
		Calendar cal=Calendar.getInstance();
		calendar=cal.get(cal.YEAR)+"년"+(cal.get(cal.MONTH)+1)+"월"+cal.get(cal.DATE)+"일"+cal.get(cal.HOUR)+":" +cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
		return calendar;
	}
}
