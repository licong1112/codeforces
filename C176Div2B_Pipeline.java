/**
 * Practiced on 3/28/2013
 * 
 * http://www.codeforces.com/contest/287/problem/B
 * 
 * =========================================
 * The maximum number of pipes that can be built out of i splitters are
 * k + k-1 + ... + k-i+1 - (i-1). So it can be calculated by the calculate(k, i)
 * function. 
 * 
 * Binary search. Find the smallest i such that n < calculate(k, i).
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

public class C176Div2B_Pipeline implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C176Div2B_Pipeline(), "", 256 * (1L << 20)).start();
	}
	
	public void solve() throws IOException 
	{
		long n = readLong();
		int k = readInt();

		int start = 0;
		int end = k;
		int mid = 0;
		while(start <= end)
		{
			mid = start + (end-start)/2;
			long val_mid = calculate(k, mid);
			
			if(n == val_mid)
			{
				System.out.println(mid);
				return;
			}
			if(n < val_mid)
				end = mid-1;
			else
				start = mid+1;
		}
		if(start > k)
			System.out.println(-1);
		else if(n < calculate(k, end))
			System.out.println(end);
		else
			System.out.println(start);
	}	
	
	public long calculate(int k, int level)
	{
		long start = k;
		long end = k-level+1;
		return (start+end) * level / 2 - level + 1;
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




