/**
 * Practiced on 4/4/2012
 * 
 * http://www.codeforces.com/contest/289/problem/B
 * 
 * ===========================================
 * If the mod of each element of the matrix and d are not the same,
 * then there is no solution.
 * 
 * Then, for the array which contains the the division of the elements
 * of the matrix and d, then find the median of the array. This is 
 * similar to the problem that you have several people stand on a line,
 * and find the shortest length the people need to travel such that
 * they meet at one point. The answer is median.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C177Div2B_PoloThePenguinAndMatrix {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C177Div2B_PoloThePenguinAndMatrix test = new C177Div2B_PoloThePenguinAndMatrix();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int m = readInt();
		int d = readInt();
		
		int element = readInt();
		int mod = element % d;
		int[] array = new int[n*m];
		array[0] = element / d;
		
		for(int i = 1; i < m*n; ++i)
		{
			element = readInt();
			if(element % d != mod)
			{
				out.println(-1);
				return;
			}
			array[i] = element / d;
		}
		
		Arrays.sort(array);
		calculate(array);
	}
	
	public void calculate(int[] array)
	{
		int mid = (array.length-1)/2;
		int sum = 0;

		for(int i = 0; i < array.length; ++i)
			sum += Math.abs(array[i] - array[mid]);
		out.println(sum);			
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
			//System.err.println("Time = " + (t2 - t1));
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
