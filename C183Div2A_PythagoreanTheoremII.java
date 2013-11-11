/**
 * Practiced on 11/10/2013
 * 
 * http://codeforces.com/contest/304/problem/A
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class C183Div2A_PythagoreanTheoremII {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C183Div2A_PythagoreanTheoremII test = new C183Div2A_PythagoreanTheoremII();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt(), a = 0, b = 0;
		int a_square = 0, b_square = 0, c_square = 0, total = 0;
		
		for (int c = n; c >= 5; --c) {
			c_square = c*c;
			a = 1; 
			b = c-1;
			a_square = a*a; 
			b_square = b*b;
			while (a < b) {
				if (a_square + b_square == c_square) {
					++ total;
					++a;
					--b;
					a_square = a*a;
					b_square = b*b;
				} else if (a_square + b_square > c_square) {
					--b;
					b_square = b*b;
				} else {
					++a;
					a_square = a*a;
				}
			}
		}
		out.println(total);
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

