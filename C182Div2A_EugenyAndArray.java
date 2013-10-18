package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class C182Div2A_EugenyAndArray {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C182Div2A_EugenyAndArray test = new C182Div2A_EugenyAndArray();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt(), m = readInt();
		int[] array = new int[2];
		for (int i = 0; i < n; ++i) {
			if (readInt() == 1) {
				++array[0];
			} else {
				++array[1];
			}
		}
		
		int num_pair = Math.min(array[0], array[1]);
		int left = 0, right = 0, num_digits = 0;
		for (int i = 0; i < m; ++i) {
			left = readInt();
			right = readInt();
			num_digits = right-left+1;
			if (num_digits % 2 == 0 && num_digits/2 <= num_pair) {
				out.println(1);
			} else {
				out.println(0);
			}
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


