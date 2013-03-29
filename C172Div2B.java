/**
 * Practiced on 3/14/2013
 * 
 * http://codeforces.com/contest/281/problem/B
 * 
 * ==================================
 * The key is to go through "end" from n to 1. For each "end", the target start
 * is easy to locate: it must be one of the three elements {end*target-1, end*target, end*target+1}.
 * So for each "end", we only need to search three "start".
 * 
 * Another issue need to be careful is that, when calculating the distance, instead of using
 * start/end - numerator/denominator, we should use the dist() function that I write below.
 * The reason is that, the previous calculation can be inaccurate, due to the subtraction between
 * two double numbers. For example, 5/(double)3-1/(double)3 gives result "1.3333333333333335", while
 * the actual value should be 1.3333333333333333.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C172Div2B implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C172Div2B(), "", 256 * (1L << 20)).start();
	}

	private double dist(int start, int end, double numerator, double denominator)
	{
		return (Math.abs(start*denominator-end*numerator)/(end*denominator));
	}
	
	public void solve() throws IOException 
	{
		double numerator = readDouble();
		double denominator = readDouble();
		int n = readInt();
		double target = numerator / denominator;
		
		int end = Math.min(n, (int)denominator);
		int opt_start = 0;
		int opt_end = 0;
		double closest_dist = Double.MAX_VALUE;
		
		while(end > 0)
		{
			int start_init = Math.min((int)numerator, (int)(end*target)+1);
			for(int start = start_init; start >= 0 && start >= start_init-3 && start <= numerator; --start)
			{
				double distance = dist(start, end, numerator, denominator);
				if(closest_dist >= distance)
				{
					opt_start = start;
					opt_end = end;
					closest_dist = distance;
				}
			}
			--end;
		}
		out.println(opt_start + "/" + opt_end);
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
}



