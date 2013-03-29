/**
 * Practiced on 1/20/2013
 * 
 * http://codeforces.com/contest/264/problem/A
 * 
 * Same as Div2C.
 * 
 * =========================
 * O(n) algorithm is needed.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C162Div1A implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C162Div1A(), "", 256 * (1L << 20)).start();
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
		tok = new StringTokenizer(in.readLine());
		String directionString = tok.nextToken();

		int[] result = new int[directionString.length()];
		int start = 0;
		int end = result.length-1;
		int count = 1;
		
		char direction = 'a';
		for(int i = 0; i < directionString.length(); ++i)
		{
			direction = directionString.charAt(i);
			if(direction == 'l')
			{
				result[end] = count;
				--end;
			}
			else
			{
				result[start] = count;
				++start;
			}
			++count;
		}
		for(int i = 0; i < result.length; ++i)
			out.println(result[i]);
	}
}
