/**
 * Practiced on 5/21/2013
 * http://www.codeforces.com/contest/299/problem/C
 * 
 * =====================================================
 * Solution: http://codeforces.com/blog/entry/7516
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class CrocChamp2013R2Div2C {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		CrocChamp2013R2Div2C test = new CrocChamp2013R2Div2C();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int[] a = new int[2*n];
		int[] b = new int[2*n];
		String str = readString();
		for(int i = 0; i < 2*n; ++i)
			a[i] = str.charAt(i)-'0';
		str = readString();
		for(int i = 0; i < 2*n; ++i)
			b[i] = str.charAt(i)-'0';
		calculate(a, b, n);
	}
	
	public void calculate(int[] a, int[] b, int n)
	{
		int[] hash = new int[4];
		for(int i = 0; i < 2*n; ++i)
		{
			if(a[i]==1 && b[i]==1)
				++hash[0];
			else if(a[i]==1 && b[i]==0)
				++hash[1];
			else if(a[i]==0 && b[i]==1)
				++hash[2];
			else
				++hash[3];
		}
		
		int score_a = 0, score_b = 0;
		
		for(int i = 0; i < n; ++i)
		{
			if(hash[0] > 0 || hash[1] > 0) ++score_a;
			if(hash[0] > 0)
				--hash[0];
			else if(hash[1] > 0)
				--hash[1];
			else if(hash[2] > 0)
				--hash[2];
			
			if(hash[0] > 0 || hash[2] > 0) ++score_b;
			if(hash[0] > 0)
				--hash[0];
			else if(hash[2] > 0)
				--hash[2];
			else if(hash[1] > 0)
				--hash[1];

		}
		
		if(score_a > score_b)
			out.println("First");
		else if(score_a < score_b)
			out.println("Second");
		else
			out.println("Draw");
	}
	
	class Pair implements Comparable<Pair>
	{
		int a;
		int b;
		
		Pair(int a, int b)
		{
			this.a = a;
			this.b = b;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(this.a < o.a)
				return 1;
			else
				return o.b - this.b;
		}
		
	}
	
	public void start()
	{
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
