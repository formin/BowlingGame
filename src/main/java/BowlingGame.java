import java.util.Scanner;

/*
 * @class UserException
 * @brief 사용자정의에러.
 */
class UserException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String character;
	
	public UserException(String s)
	{
		this.character = s;
	}
	
	@Override
	public String getMessage()
	{
		return String.valueOf(character);
	}
}

/*
 * @class Print
 * @brief 콘솔메시지출력.
 */
class Print
{  
    public static void print(String strPin, String strScore) 
    { 
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
         
        System.out.println("-------------------------------------------");
        System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |  10 |");
        System.out.println("|"+strPin.replace("0", "-"));
        System.out.println("|"+strScore);
        System.out.println("-------------------------------------------");
    }
 
    public static void text(String text)
    {
    	System.out.println();
    	System.out.println(text);
    }
    
    public static void end() throws UserException
    {  
        System.out.println();
        System.out.println();
        System.out.println(); 
    	throw new UserException("GameOverException");
    }
}

/*
 * @class StrPin
 * @brief 콘솔출력용 넘어진핀수.
 */
class StrPins
{
	private static String values; 
	  
	public static String getValue()
	{
		return StrPins.values;
	}
	
	public static void setValue(String v)
	{
		StrPins.values = v;
	}

	public static void addValue(String v)
	{
		StrPins.values += v;
	}
}

/*
 * @class StrScore
 * @brief 콘솔출력용 점수.
 */
class StrScores
{ 
	private static String values;
 
	public static String getValue()
	{
		return StrScores.values;
	}

	public static void setValue(String v)
	{
		StrScores.values = v;
	}
	
	public static void addValue(String v)
	{
		StrScores.values += v;
	}
}

/*
 * @class Score
 * @brief 점수.
 */
class Score
{ 
	private static int values;
 
	public static int getValue()
	{
		return Score.values;
	}
	
	public static void setValue(int v)
	{
		Score.values = v;
	}
	
	public static void addValue(int v)
	{
		Score.values += v;
	}
}


/*
 * @class Strike
 * @brief Strike횟수.
 */
class Strike
{ 
	private static int values;
 
	public static int getValue()
	{
		return Strike.values;
	}
	
	public static void setValue(int v)
	{
		Strike.values = v;
	}

	public static void addValue(int v)
	{
		Strike.values += v;
	}
}

/*
 * @class Spare
 * @brief Spare횟수.
 */
class Spare
{ 
	private static int values;
 
	public static int getValue()
	{
		return Spare.values;
	}

	public static void setValue(int v)
	{
		Spare.values = v;
	}

	public static void addValue(int v)
	{
		Spare.values += v;
	}
}


/*
 * @class BowlingGame
 * @brief 볼링게임.
 */
public class BowlingGame 
{

	public static void main(String[] args) throws UserException 
	{
        int[] Pin = new int [24];	               			// 사용자 입력 값을 기록하는 숫자배열. 
        String[] strPin = new String [24];					// 사용자 입력 값을 기록하는 문자배열.
        		
        Scanner input = new Scanner(System.in);				
        BowlingGame BG = new BowlingGame();
        
        BG.calculator_Score_Display(Pin, strPin, input); 		// 사용자 입력 값을 점수로 계산하고, 콘솔에 출력한다.
	}
	
	/*
	 * 사용자 입력 값을 점수로 계산하고, 콘솔에 출력한다.
	 *
	 * @param Pin : 입력 된 핀수가 숫자로 변환되어 저장 되는 정수 배열.
	 * @param strPin : 입력 된 핀수가 그대로 저장되는 문자 배열.
	 * @param input : 사용자 입력을 위한 Scanner 개체.
	 */
	public void calculator_Score_Display(int [] Pin, String[] strPin, Scanner input) throws UserException
	{ 
		StrPins.setValue("");				  // 콘솔출력용핀수.
		StrScores.setValue("");               // 콘솔출력용문자점수.
        
		Score.setValue(0);                    // 점수계산용숫자점수.
        Strike.setValue(0);                   // strike횟수.
        Spare.setValue(0);                    // spare횟수.
        
        for(int i=0; i<24; i=i+2)
        {
        	Pin[i] = 0;
        	
        	input_text_display(i);            // 사용자 입력을 위한 안내 문구 표시.
        	
        	strPin[i] = input.next().toUpperCase();
        	Pin[i] = play_odd(strPin[i]); // 1구 핀수 확인.
        	
            score_display(input, Pin, strPin, i); // 점수표기.
        }
	}

	/*
	 * 사용자 입력을 위한 안내 문구 표시.
	 *
	 * @param i : 프레임번호.
	 */
	public void input_text_display(int i)
	{
        if(i < 20)
        { 
        	Print.text((i/2+1)+"프레임 1구 ('X','-','0~9') 의 값 중 하나를 입력 후 엔터를 눌러 주세요 : "); 
        }

        if(i >= 20)
        { 
        	Print.text("10프레임 보너스 구:");  
        }
	}

	/*
	 * 1구에 처리 된 문자 핀수를 정수로 리턴한다.
	 *
	 * @param 
	 * pin : 1구처리된문자핀수.
	 * 
	 * @return 
	 * pin : 1구처리된정수핀수. 
	 */
	public int play_odd(String pin)
	{ 
        if(pin.toUpperCase().equalsIgnoreCase("/")) // Spare 는 2구에서만 입력가능
        { 
          Print.text("GameException");   
    	  throw new RuntimeException(); 
        }
        
        if(pin.toUpperCase().equalsIgnoreCase("X")) // Strike
        {
        	pin = "10";
        }
          
        if(pin.toUpperCase().equalsIgnoreCase("F")) // Foul
        {
        	pin = "0";  
        }

        if(pin.toUpperCase().equalsIgnoreCase("G")) // Gutter
        {
        	pin = "0"; // 또랑에 빠진경우. 
        }
        
        return Integer.parseInt(pin); 
	}

	/*
	 * 콘솔에 게임점수표시.
	 *
	 * @param input : 사용자입력을위한Scanner개체.
	 * @param Pin : 숫자핀수기록배열.
	 * @param strPin : 문자핀수기록배열.
	 * @param i : 프레임번호. 
	 */
	public void score_display(Scanner input, int [] Pin, String[] strPin, int i) throws UserException
	{  
        if(Pin[i] == 10)  //Strike
        { 
        	strike(Pin, i); 
        }
        
        if(Pin[i] < 10)   // Strike 이외.
        {
        	strike_other_one(Pin, strPin, i); // 프레임1구.
        	strike_other_two(input, Pin, strPin, i); // 프레임2구.
        }

        if(i >= 22)        // 10프레임게임종료 퍼펙트게임종료.
        { 
        	Print.end();
        }
	}

	/*
	 * strike 스코어 계산.
	 *
	 * @param Pin : 핀수.
	 * @param i : 프레임번호. 
	 */
	public void strike(int [] Pin, int i) throws UserException
	{
		StrPins.addValue("X|");
         
        if(i < 18)               //10프레임 1칸 줄이기.
        { 
        	StrPins.addValue(" |");
        }
         
        Pin[i+1] = 0;
          
        if(Strike.getValue() < 3)
        	Strike.addValue(1);
          
        if(Strike.getValue() == 3)
        {
        	Score.addValue(30); 
            StrScores.addValue(String.format("%3d|",Score.getValue())); 
            Print.print(StrPins.getValue(), StrScores.getValue());  
        }
        
        if(i>=20 && Strike.getValue() != 3) // 10프레임마지막.strike
        {
        	Score.addValue(10 + Pin[i]); 
        	StrScores.addValue(String.format("%5d|",Score.getValue()));  
        }
        
        Print.print(StrPins.getValue(), StrScores.getValue());

        if(i>=20 && Strike.getValue() != 3) // 10프레임마지막.strike
        { 
        	Print.end();
        } 
	}

	/*
	 * 1구 스코어 계산.
	 * 
	 * @param Pin : 숫자핀수기록배열.
	 * @param strPin : 문자핀수기록배열.
	 * @param i : 프레임번호. 
	 */
	public void strike_other_one(int [] Pin, String[] strPin, int i) throws UserException
	{
    	if(Spare.getValue() == 1)	//Spare
        {
    		Score.addValue(10 + Pin[i]); 
    		StrScores.addValue(String.format("%3d|",Score.getValue())); 
        }
          
    	if(Strike.getValue() == 3 || Strike.getValue() == 2)
        {
    		Score.addValue(20 + Pin[i]); 
        	StrScores.addValue(String.format("%3d|",Score.getValue())); 
            Print.print(StrPins.getValue(), StrScores.getValue()); 
        }
        
    	StrPins.addValue(strPin[i]+"|");
        Print.print(StrPins.getValue(), StrScores.getValue());
         
        Spare.setValue(0);
 
        if(i >= 20)
        { 
            Print.text("10프레임 보너스 구:");    
        }
        
        if(i < 20)
        { 
            Print.text((i/2+1)+"프레임 2구 ('/','-','0~9') 의 값 중 하나를 입력 후 엔터를 눌러 주세요 : ");  
        } 
	}

	/*
	 * 2구 스코어 계산.
	 * 
	 * @param input : 사용자입력을위한Scanner개체.
	 * @param Pin : 숫자핀수기록배열.
	 * @param strPin : 문자핀수기록배열.
	 * @param i : 프레임번호. 
	 */
	public void strike_other_two(Scanner input, int [] Pin, String[] strPin, int i) throws UserException
	{ 
        strPin[i+1] = input.next().toUpperCase();
        Pin[i+1] = play_even(strPin[i+1],Pin[i]); //2구
         
        if(Strike.getValue() == 3 || Strike.getValue() == 2 || Strike.getValue() == 1)
        {
        	Score.addValue(10 + Pin[i] + Pin[i+1]); 
        	StrScores.addValue(String.format("%3d|",Score.getValue())); 
            Print.print(StrPins.getValue(), StrScores.getValue()); 
        }
         
        Strike.setValue(0);
         
        if((Pin[i] + Pin[i+1]) == 10) //Spare 경우
        {
        	StrPins.addValue("/|");
        	Print.print(StrPins.getValue(), StrScores.getValue()); 
            Spare.setValue(1);
        }
        
        if((Pin[i] + Pin[i+1]) != 10) // Spare 아닌경우
        {
        	StrPins.addValue(strPin[i+1]+"|");
        	Print.print(StrPins.getValue(), StrScores.getValue());
        	  
        	Score.addValue(Pin[i] + Pin[i+1]); 
            StrScores.addValue(String.format("%3d|",Score.getValue())); 
            Print.print(StrPins.getValue(), StrScores.getValue()); 
        }

        if(((Pin[i] + Pin[i+1]) != 10) && (i == 18)) // Spare 아닌경우 10프레임게임종료 조건.
        { 
        	Print.end();
        }
        
        if(i == 20)        // Spare 경우 10프레임게임종료 조건.
        { 
        	Print.end();
        }
	}
	
	/*
	 * 2구에 처리 된 문자 핀수를 정수로 리턴한다.
	 *
	 * @param
	 * in_str_next : 2구처리된핀수.
	 * in_str : 1구처리된핀수.
	 * 
	 * @return 
	 * pin : 2구처리된핀수.
	 */
	public int play_even(String in_str_next, int in_str)
	{ 
		int pin=0; // 2구에 처리 된 핀수
  
        if(in_str_next.equalsIgnoreCase("/")) // Spare
        {
        	pin = 10 - in_str; // 총 10개의 핀 중 1구에서 넘어지지 않은 핀 갯수가 2구의 Spare 핀수
        }

        if(in_str_next.equalsIgnoreCase("-")) // Open
        {
        	in_str_next = "0"; // 하나도 넘어지지 않은 경우 
        }

        if(in_str_next.toUpperCase().equalsIgnoreCase("F")) // Foul
        {
        	in_str_next = "0";  
        }

        if(in_str_next.toUpperCase().equalsIgnoreCase("G")) // Gutter
        {
        	in_str_next = "0"; // 또랑에 빠진경우. 
        }
        
        if(pin == 0) //  문자 이외의 입력 값 처리
        {
        	pin = Integer.parseInt(in_str_next);
        } 
        
        return pin;  //2구처리핀수 
	}
   
}
