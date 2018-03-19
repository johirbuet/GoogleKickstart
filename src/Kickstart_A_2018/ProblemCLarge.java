package Kickstart_A_2018;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProblemCLarge {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new File("C-large-practice.in"));
		PrintStream ps = new PrintStream(new File("output.txt"));
		System.setOut(ps);
		int C = Integer.parseInt(sc.nextLine());
		for(int cs = 1; cs<= C; cs++) {
			int L = Integer.parseInt(sc.nextLine());
			List<List<Integer>> map = new ArrayList<>();
			String words = sc.nextLine();
			StringTokenizer st = new StringTokenizer(words);
			while(st.hasMoreTokens()) {
				String w = st.nextToken();
				List<Integer> list = new ArrayList<>();
				for(char wc : w.toCharArray()) {
					list.add(wc -'a');
				}
				map.add(list);
			}
			 String[] params = sc.nextLine().split("\\s");
	         int n = Integer.valueOf(params[2]);

			int [] S = new int[n];
            S[0] = params[0].charAt(0);
            S[1] = params[1].charAt(0);
            long a = Long.valueOf(params[3]);
            long b = Long.valueOf(params[4]);
            long c = Long.valueOf(params[5]);
            long d = Long.valueOf(params[6]);
            for (int i = 2; i < n; i++) {
                S[i] = (int) ((a * S[i-1] + b * S[i-2] + c) % d);
            }
            S[0] -= 97;
            S[1] -= 97;
            for (int i = 2; i < n; i++) {
                S[i] %= 26;
            }
            long count = 0;
            List<List<Integer>> starting = new ArrayList<>();
            for(int i =0; i<26;i++) {
            	starting.add(new ArrayList<>());
            }
            for(int i = 0; i<n;i++) {
            	starting.get(S[i]).add(i);
            }
            int [][] table = new int[26][n+1];
            for(int j=0; j<26;j++) {
            	for(int i =0; i< n;i++) {
            		table[j][i+1] = table[j][i];
            		if(S[i] == j) {
            			table[j][i+1]++;
            		}
            	}
            }
            for(List<Integer> ls : map) {
            	int [] freq = new int[26];
            	for(int wc : ls) {
            		freq[wc]++;
            	}
            	boolean ok = true;
            	for(int j =0; j< 26;j++) {
            		if(freq[j] > table[j][n]) {
            			ok = false;
            			break;
            		}
            	}
            	if(!ok) continue;
            	int wlen = ls.size();
            	int last = ls.get(wlen -1);
            	for(int first : starting.get(ls.get(0))) {
            		if( first + wlen - 1 >= n) break;
            		if(S[first + wlen - 1] != last) continue;
            		boolean good = true;
            		for(int i =0;i<26;i++) {
            			if(table[i][first+wlen] - table[i][first] != freq[i]) {
            				good = false;
            				break;
            			}
            		}
            		if(good) {
            			count++;
            			break;
            		}
            	}
            	
            }
			System.out.printf("Case #%d: %d\n",cs,count);
			
		}
		sc.close();
	}
	
}
