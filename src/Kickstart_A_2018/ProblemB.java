package Kickstart_A_2018;
import java.util.Scanner;

public class ProblemB {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		for(int c = 1; c<= C; c++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			double [] V = new double[N];
			double [] E = new double[K + 1];
			double sum = 0;
			for(int i =0; i< N; i++) {
				V[i] = sc.nextDouble();
				sum += V[i];
			}
			E[0]  = sum/N;
			for(int i = 1; i<=K;i++) {
				for(int j =0; j<N;j++) {
					E[i] += Math.max(V[j], E[i-1])/N;
				}
			}
			System.out.printf("Case #%d: %.6f\n",c,E[K]);
		}
		sc.close();
	}
}
