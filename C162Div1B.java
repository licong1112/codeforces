/**
 * Practiced on 1/20/2013
 * 
 * http://codeforces.com/contest/264/problem/B
 * 
 * Same as Div2D.
 * =========================
 * This problem is not difficult in essence, but very hard to pass the online judge.
 * Intuitively, we can let dp[i] represent the number of good numbers up array[i], then
 * 
 * dp[i] = max{dp[k], k \in [1, i-1] and gcd(array[k], array[i]) > 1}.
 * 
 * Then the algorithm looks like O(n^2). However, each time we need to check gcd(array[k], array[i]),
 * which makes the algorithm very slow and cannot pass the online judge.
 * 
 * The strategy is to find all prime divisors of each array[i]. If a specific divisor is also 
 * a divisor of some array[j] (j < i), then it means gcd(array[i], array[j]) > 1. So,
 * for each array[i], instead of running gcd(array[k], array[i]) for all k's, we only 
 * calculate all divisors of array[i], and see if it is a divisor of any other array[j].
 * To calculate all divisors of array[i], we do the following:
 * 
 * for(int j = 2; j*j <= array[i]; ++j)
	{
		if(num % j == 0)
			divisors.add(j);
		while(num % j == 0) num /= j;
	}
			
 *	This is a much quicker method than iterate through all j's from 2 to array[i]-1 and see
 *  if num % j == 0.
 *  
 *  Note that it is sufficient to consider only prime divisors. This is because, if for a non-prime
 *  divisor is the divisor of array[i] and array[j], then there must be a prime divisor which is
 *  the divisor of array[i] and array[j]. For example, 12 and 24 has divisor 6, but 2 and 3 (both
 *  are prime divisors) are divisors of 12 and 24. So only found 2 and 3 are divisors of 12 and 24
 *  is sufficient to know gcd(array[i], array[j]) > 1.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C162Div1B implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C162Div1B(), "", 256 * (1L << 20)).start();
	}

	public void run() {
		try {
			long t1 = System.currentTimeMillis();
			if (System.getProperty("ONLINE_JUDGE") != null) {
				in = new BufferedReader(new InputStreamReader(System.in));
				out = new PrintWriter(System.out);
			} else {
				in = new BufferedReader(new FileReader("..\\Codeforces\\src\\com\\congli\\codeforces\\input.txt"));
				out = new PrintWriter(System.out);
			}
			Locale.setDefault(Locale.US);
			solve();
			in.close();
			out.close();
			long t2 = System.currentTimeMillis();
			System.err.println("Time = " + (t2 - t1));
		} catch (Throwable t) {
			t.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	public String readString() throws IOException {
		while (!tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}

	public int readInt() throws IOException {
		return Integer.parseInt(readString());
	}

	public long readLong() throws IOException {
		return Long.parseLong(readString());
	}

	public double readDouble() throws IOException {
		return Double.parseDouble(readString());
	}

	// solution
	public void solve() throws IOException 
	{
		int numInput = readInt();		
		int[] array = new int[numInput];
		int maxNumInArray = Integer.MIN_VALUE;
		for(int i = 0; i < numInput; ++i)
		{
			array[i] = readInt();
			maxNumInArray = Math.max(maxNumInArray, array[i]);
		}
		
		int[] dp = new int[numInput];	
		int[] maxInd = new int[maxNumInArray+1];
		Arrays.fill(maxInd, -1);
		
		int result = 1;
		for(int i = 0; i < numInput; ++i)
		{
			ArrayList<Integer> divisors = new ArrayList<Integer>();
			int num = array[i];
			// Find all divisors of num
			for(int j = 2; j*j <= array[i]; ++j)
			{
				if(num % j == 0)
					divisors.add(j);
				while(num % j == 0) num /= j;
			}
			if(num > 1) divisors.add(num); // If num is prime, add num itself.
			
			for(int j = 0; j < divisors.size(); ++j)
			{
				int divisor = divisors.get(j);
				dp[i] = Math.max(dp[i], maxInd[divisor] == -1 ? 1 : dp[maxInd[divisor]]+1);
				maxInd[divisor] = i;
			}
			result = Math.max(result, dp[i]);
		}
		out.println(result);
	}
}
