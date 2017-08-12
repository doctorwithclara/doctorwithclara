import java.util.Scanner;
import java.util.StringTokenizer;
public class AlphabetHistogramApp48 {
	public static void main(String args[]){
		int count;
		int arr[] = new int[26];
		Scanner s = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		while(true){
			String str = s.nextLine();
			for(int i=0;i<26;i++){}
			if(str.length()==1&&str.charAt(0)==';')
				break;
			sb.append(str);
		}
		for(int i=0;i<sb.length();i++){
			count=0;
			for(char a='a';a<'z';a++){
				if(sb.charAt(i)==a)
					arr[count]++;
				count++;
			}
			count=0;
			for(char a='A';a<'Z';a++){
				if(sb.charAt(i)==a)
					arr[count]++;
				count++;
			}
		}
		count=0;
		for(char a='A';a<'Z';a++){
			System.out.print(a);
			for(int i=0;i<arr[count];i++)
				System.out.print("-");
			System.out.println();	
			count++;
		}
	}
}
