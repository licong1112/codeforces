/**
 * Practiced on 4/2/2013
 * 
 * http://www.codeforces.com/contest/287/problem/D
 * 
 * =========================================
 * Don't try to solve it by brute force. The key is to observe the following:
 * 
 * 1 2 3 4 5 6
 *   2 1 4 3 6 5              shift=2
 *     1 4 2 6 5 3            shift=3
 *       4 2 6 1 3 5          shift=4
 *         2 6 1 3 4 5        shift=5
 *           6 1 3 4 5 2      shift=6
 * 
 * So only need to build an array with length 2*n-1, and keep filling the
 * blanks.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C176Div2D_Shifting {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C176Div2D_Shifting test = new C176Div2D_Shifting();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int[] array = new int[2*n-1];
		
		for(int i = 0; i < n; ++i)
			array[i] = i+1;
		
		for(int shift = 2; shift <= n; ++shift)
		{
			int start_ind = shift - 2;
			int next_ind = start_ind + shift;
			int start_num = array[start_ind];
			
			while(next_ind < n + shift - 2)
			{
				int temp = array[next_ind];
				array[next_ind] = start_num;
				start_num = temp;

				start_ind = next_ind;
				next_ind += shift;
			}
			array[n+shift-2] = start_num;
		}
		
		for(int i = n-1; i < array.length; ++i)
			out.print(array[i] + " ");
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