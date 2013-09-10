/**
 * Practiced on 9/10/2013
 * 
 * http://codeforces.com/contest/300/problem/A
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class C181Div2A_Array {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C181Div2A_Array test = new C181Div2A_Array();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int[] array = new int[n];
		int num_negative = 0;
		for(int i = 0; i < n; ++i) {
			array[i] = readInt();
			if (array[i] < 0) {
				++num_negative;
			}
		}
		
		int[] second, third;
		if (num_negative > 2) {
			second = new int[2];
			third = new int[n-3];
		} else {
			second = new int[1];
			third = new int[n-2];
		}
		
		int num_first = 0, num_second = 0, num_third = 0, first = 0;
		for(int i = 0; i < n; ++i) {
			if (array[i] < 0) {
				if (num_first < 1) {
					first = array[i];
					++num_first;
				} else if (num_negative > 2 && num_second < 2) {
					second[num_second++] = array[i];
				} else {
					third[num_third++] = array[i];
				}
			} else {
				if (array[i] > 0 &&num_negative <= 2 && num_second < 1) {
					second[num_second++] = array[i];
				} else {
					third[num_third++] = array[i];
				}
			}
		}
		
		out.println("1 " + first);
		out.print(num_second);
		for (int i = 0; i < num_second; ++i) {
			out.print(" " + second[i]);
		}
		out.println();
		out.print(num_third);
		for (int i = 0; i < num_third; ++i) {
			out.print(" " + third[i]);
		}
		out.println();
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

