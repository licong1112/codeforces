/**
 * Practiced on 4/4/2012
 * 
 * http://www.codeforces.com/contest/289/problem/A
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C177Div2A_PoloThePenguinAndSegments {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C177Div2A_PoloThePenguinAndSegments test = new C177Div2A_PoloThePenguinAndSegments();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int target = readInt();
		int sum = 0;
		
		int a = 0;
		int b = 0;
		for(int i = 0; i < n; ++i)
		{
			a = readInt();
			b = readInt();
			sum += (b-a+1);
		}
		out.println((target - sum%target) % target);
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
