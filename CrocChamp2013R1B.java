/**
 * Practiced on 4/26/2013
 * http://www.codeforces.com/contest/292/problem/B
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class CrocChamp2013R1B {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		CrocChamp2013R1B test = new CrocChamp2013R1B();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt(), m = readInt();
		if(m > n)
		{
			out.println("unknown topology");
			return;
		}
		
		int[] count = new int[n+1];
		int node = 0;
		boolean has_larger_three = false;
		for(int i = 0; i < m; ++i)
		{
			node = readInt();
			++count[node];
			if(count[node] >= 3) has_larger_three = true;

			node = readInt();
			++count[node];
			if(count[node] >= 3) has_larger_three = true;
		}
		
		if(has_larger_three)
		{
			for(int i = 1; i <= n; ++i)
			{
				if(count[i] != 1 && count[i] != n-1)
				{
					out.println("unknown topology");
					return;
				}
			}
			out.println("star topology");
		}
		else
		{
			int count_one = 0;
			for(int i = 1; i <= n; ++i)
			{
				if(count[i] < 1)
				{
					out.println("unknown topology");
					return;
				}
				if(count[i] == 1) ++count_one;
			}
			if(count_one == 2)
				out.println("bus topology");
			else if(count_one == 0)
				out.println("ring topology");
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
				in = new BufferedReader(new FileReader("..\\codeforces\\src\\com\\congli\\codeforces\\input.txt"));
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