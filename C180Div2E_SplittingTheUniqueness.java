/**
 * Practiced on 5/17/2013
 * 
 * http://www.codeforces.com/contest/298/problem/E
 * 
 * ==================================
 * Solution: http://www.codeforces.com/blog/entry/7437
 * 
 * See how the sorting works. It's interesting.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C180Div2E_SplittingTheUniqueness {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C180Div2E_SplittingTheUniqueness test = new C180Div2E_SplittingTheUniqueness();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		long[] array = new long[n];
		for(int i = 0; i < n; ++i)
		{
			array[i] = readInt() * (100000l) + i;
		}
		Arrays.sort(array);
		
		int[] data = new int[n];
		int[] index = new int[n];
		for(int i = 0; i < n; ++i)
		{
			data[i] = (int)(array[i] / 100000);
			index[(int)(array[i] % 100000)] = i;
		}
		
		calculate(data, index);
	}
	
	public void calculate(int[] data, int[] index)
	{
		int i = 0;
		int n = data.length;
		int[] a = new int[n];
		int[] b = new int[n];
		
		while(i*3 < n)
		{
			a[i] = i;
			b[i] = data[i]-a[i];
			++i;
		}
		
		while(i*3 < 2*n)
		{
			b[i] = i;
			a[i] = data[i]-b[i];
			++i;
		}
		
		while(i*3 < 3*n)
		{
			b[i] = n-1-i;
			a[i] = data[i]-b[i++];
		}
		
		out.println("YES");
		for(int j = 0; j < n-1; ++j)
			out.print(a[index[j]] + " ");
		out.println(a[index[n-1]]);
		
		for(int j = 0; j < n-1; ++j)
			out.print(b[index[j]] + " ");
		out.println(b[index[n-1]]);
		
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
