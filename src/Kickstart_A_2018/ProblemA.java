package Kickstart_A_2018;
import java.util.Scanner;

public class ProblemA {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		for (int c = 1; c <= C; c++) {
			String s = sc.next();
			long N = Long.parseLong(s);
			int[] num = new int[s.length() + 1];
			int[] num2 = new int[s.length() + 1];
			for (int i = 1; i <= s.length(); i++) {
				num[i] = s.charAt(i - 1) - '0';
				num2[i] = num[i];
			}
			long B1 = findB1(num);
			long B2 = findB2(num2);
			long ans = Math.min(Math.abs(N - B1), Math.abs(N - B2));
			System.out.printf("Case #%d: %d\n", c, ans);
		}
		sc.close();
	}

	public static long findB1(int[] num) {
		long M = 0;
		boolean firstOdd = false;

		for (int i = 1; i < num.length; i++) {
			if (num[i] % 2 == 1) {
				if (!firstOdd) {
					num[i] = num[i] - 1;
					firstOdd = true;
				} else {
					num[i] = 8;
				}
			} else {
				if (firstOdd == true) {
					num[i] = 8;
				}

			}
		}

		for (int i = 0; i < num.length; i++) {
			M = M * 10 + num[i];
		}
		return M;
	}

	public static long findB2(int[] num) {
		long P = 0;
		boolean firstOdd = false;
		for (int i = 1; i < num.length; i++) {
			if (num[i] % 2 == 1) {
				if (!firstOdd) {
					if (num[i] == 9) {
						int j = i - 1;
						while (num[j] == 8) {
							j--;
							num[j] = 0;
						}
						num[j] += 2;
						num[i] = 0;
					} else {
						num[i] += 1;
					}
					firstOdd = true;
				} else {
					num[i] = 0;
				}
			} else {
				if (firstOdd) {
					num[i] = 0;
				}
			}
		}
		for (int i = 0; i < num.length; i++) {
			P = P * 10 + num[i];
		}
		return P;
	}
}
