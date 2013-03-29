/**
 * Practiced on 2/11/2013
 * 
 * http://codeforces.com/contest/271/problem/B
 * 
 * ==========================
 * One trick
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

public class C166Div2B implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	final int max_num = (int) 2e5;
	boolean[] is_prime = new boolean[max_num];
	int[] next_prime = new int[max_num];
	
	public static void main(String[] args) {
		new Thread(null, new C166Div2B(), "", 256 * (1L << 20)).start();
	}

	// solution
	public void solve() throws IOException 
	{
		Initialize();
		int n = readInt();
		int m = readInt();
		
		int[][] matrix = new int[n][m];
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < m; ++j)
				matrix[i][j] = readInt();
		
		int[] row_sum = new int[n];
		int[] col_sum = new int[m];
		
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < m; ++j)
			{
				if(!is_prime[matrix[i][j]])
				{
					row_sum[i] += (next_prime[matrix[i][j]] - matrix[i][j]);
					col_sum[j] += (next_prime[matrix[i][j]] - matrix[i][j]);
				}
			}
		}
		
		int min_steps = Integer.MAX_VALUE;
		for(int i = 0; i < n; ++i)
			min_steps = Math.min(min_steps, row_sum[i]);
		for(int j = 0; j < m; ++j)
			min_steps = Math.min(min_steps, col_sum[j]);
		
		out.println(min_steps);
	}
	
	private void Initialize()
	{
		Arrays.fill(is_prime, true);
		is_prime[0] = false;
		is_prime[1] = false;
		for(int i = 2; i*i < max_num; ++i)
			if(is_prime[i])
				for(int j = 2*i; j < max_num; j += i)
					is_prime[j] = false;
		
		int next = Integer.MAX_VALUE;
		for(int i = max_num-1; i >= 0; --i)
		{
			if(is_prime[i])
				next = i;
			next_prime[i] = next;
		}
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

