/**
 * Practiced on 11/10/2013
 * 
 * http://codeforces.com/contest/304/problem/A
 * 
 * ==========================================
 * Interesting problem. Should read tutorial.
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class C183Div2C_LuckyPermutationTriple {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C183Div2C_LuckyPermutationTriple test = new C183Div2C_LuckyPermutationTriple();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		if (n%2 == 0) {
			out.println(-1);
			return;
		}
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < n; ++j) {
				out.print(j + " ");
			}
			out.println();
		}
		for (int i = 0; i < n; ++i) {
			out.print(2*i % n + " ");
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
				in = new BufferedReader(new FileReader("/Users/congli/Documents/javaworkspace/codeforces/src/com/congli/codeforces/input.txt"));
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

