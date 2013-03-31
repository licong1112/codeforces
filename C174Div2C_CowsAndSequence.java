/**
 * Practiced on 3/30/2013
 * 
 * http://www.codeforces.com/contest/284/problem/C
 * 
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C174Div2C_CowsAndSequence {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C174Div2C_CowsAndSequence test = new C174Div2C_CowsAndSequence();
		test.start();
	}

	public void solve() throws IOException 
	{
		int num_opers = readInt();
		long[] A = new long[num_opers+10];
		long[] S = new long[num_opers+10];
		int size = 0;
		
		long sum = 0;
		for(int i = 0; i < num_opers; ++i)
		{
			int type = readInt();
			if(type == 1)
			{
				int a = readInt();
				int x = readInt();
				S[a-1] += x;
				sum += a*x;
			}
			else if(type == 2)
			{
				int x_add = readInt();
				A[++size] = x_add;
				sum += x_add;
			}
			else
			{
				sum -= (A[size] + S[size]);
				S[size-1] += S[size];
				S[size--] = 0;
			}
			out.println(sum*1.0/(size+1));
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