import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class Guess {
public static void main(String[] args) {

	while(game()!=0){
		game();
	}
	System.out.println("欢迎下次再玩");
	
	
}

public static int game(){
Map<String,String> map = new LinkedHashMap<String,String>();
	
	//产生四位随机数
	String  random  =random(4);
	String input;
	int times=1;
	System.out.println(random);
	//将4位随机数保存至数组
	int[] number = new int[4];
	int[] guess = new int[4];
	//谜底转换成数组
	number = stringToArray(random);
	System.out.println("请输入一组不重复的数字：");
	Scanner sb = new Scanner(System.in);
	input = sb.nextLine().toString();
    while(input.length()!=4){
    	System.out.println("请输入一组不重复的数字：");
    	 sb = new Scanner(System.in);
    	 input = sb.nextLine().toString();
    }
    //猜的转换成数组
    guess = stringToArray(input);
	while(!input.equals(random)){
		int right = right(number,guess);
		int wrong = include(random,guess)-right(number,guess);
		map.put(String.valueOf(times++), "输入："+input+"  正确:"+right+"\t位置错误:"+wrong+"");
		for(Map.Entry<String, String> x:map.entrySet()){
			System.out.println("猜数历史：第"+x.getKey()+"  次"+x.getValue());
		}
		System.out.println("请输入一组不重复的数字：\n");
		Scanner sn = new Scanner(System.in);
	    input = sn.nextLine().toString();
	    while(input.length()!=4){
	    	System.out.println("请输入一组不重复的数字：");
	    	sb = new Scanner(System.in);
	    	input = sb.nextLine().toString();
	    }
		guess = stringToArray(input);
	};
	
	System.out.println("恭喜答对 正确结果是："+random);
	System.out.println("再玩输入1，退出输入0：");
	sb = new Scanner(System.in);
	input = sb.nextLine().toString();
	if(input.equals("1")){
		return 1;
	}else{
		return 0;
	}
}

public static int right(int[] random,int[] guess) {
	int sum =0;
	for(int i=0;i<random.length;i++){
		if(random[i]==guess[i]){
			//System.out.println(random[i]+""+guess[i]);
			sum++;
		}
	}
	return sum;
	
}
public static int include(String random,int[] guess) {
	int sum =0;
	for(int i=0;i<guess.length;i++){
		if(random.contains(String.valueOf(guess[i]))){
			sum++;
		}
	}
	return sum;
	
}

public static int[] stringToArray(String  str) {
	int[] number = new int[4];
	for (int i=0;i<str.length();i++){
		number[i]=Integer.parseInt(String.valueOf(str.charAt(i)));
	}
	return number;
}
public static String random(int n) {
    if (n < 1 || n > 10) {
        throw new IllegalArgumentException("cannot random " + n + " bit number");
    }
    Random ran = new Random();
    if (n == 1) {
        return String.valueOf(ran.nextInt(10));
    }
    int bitField = 0;
    char[] chs = new char[n];
    for (int i = 0; i < n; i++) {
        while(true) {
            int k = ran.nextInt(10);
            if( (bitField & (1 << k)) == 0) {
                bitField |= 1 << k;
                chs[i] = (char)(k + '0');
                break;
            }
        }
    }
    return new String(chs);
}
}
