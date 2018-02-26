package kickstart_G_2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CardsGame {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new File("B-small-practice.in"));
		PrintStream ps = new PrintStream(new File("output.txt"));
		System.setOut(ps);

		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int C = sc.nextInt();
			long [] R = new long[C];
			long [] B = new long[C];
			for(int i =0; i< C;i++) {
				R[i] = sc.nextLong();				
			}
			for(int i = 0 ; i < C; i++) {
				B[i] = sc.nextLong();
			}
			
			long [][] graph = new long[C][C];
			for(int i =0 ;i<C;i++) {
				for(int j =i +1;j<C;j++) {
					graph[j][i] = graph[i][j] = Math.min(R[i]^B[j], R[j]^B[i]);
				}
			}
			
			long ans = prim(graph, C);
			System.out.println("Case #"+t+": "+ans);
			
		}
		sc.close();
	}
	
	public static int minindex(long [] key,boolean [] mst) {
		int V = mst.length;
		long min = Long.MAX_VALUE;
		int min_index = -1;
		for(int i =0; i< V; i++) {
			if(mst[i] == false && key[i] < min) {
				min = key[i];
				min_index = i;
			}
		}
		return min_index;
	}
	public static long prim(long [][] graph,int C) {
		int [] parent = new int[C];
		boolean [] mst = new boolean[C];
		long [] key = new long[C];
		for(int i =0; i <C; i++) {
			key[i] = Integer.MAX_VALUE;
		}
		key[0] = 0;
		parent[0] = -1;
		for(int i = 0; i< C - 1; i++) {
			int u = minindex(key, mst);
			mst[u] = true;
			for(int j = 0; j < C;j++) {
				if(mst[j] == false && graph[u][j] != 0 && graph[u][j] < key[j]) {
						key[j] = graph[u][j];
						parent[j] = u;
				}
			}
			
		}
		long ans = 0;
		for(int i = 1; i < C; i++) {
			System.out.print(parent[i]+"->" + i + "= "+graph[parent[i]][i]+" ");
			ans += graph[parent[i]][i];
		}
		return ans;
	}
}
