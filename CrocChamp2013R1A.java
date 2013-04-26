/**
 * Practiced on 4/26/2013
 * http://www.codeforces.com/contest/292/problem/A
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class CrocChamp2013R1A {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		CrocChamp2013R1A test = new CrocChamp2013R1A();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int curr_time = readInt(), queue_size = readInt(), curr_size = queue_size;
		int pre_time = 0;
		
		int max_queue_size = queue_size;
		for(int i = 1; i < n; ++i)
		{
			pre_time = curr_time;
			
			curr_time = readInt();
			curr_size = readInt();
			
			queue_size = Math.max(0, queue_size - (curr_time - pre_time)) + curr_size;
			max_queue_size = Math.max(max_queue_size, queue_size);
		}
		out.println((curr_time+queue_size) + " " + max_queue_size);
	}

	

	public void start()
	{
		try {
			long t1 = System.currentTimeMillis();
			if (System.getProperty("ONLINE_JUDGE") != null) {
				in = new BufferedReader(new InputStreamReader(System.in));
				out = new PrintWriter(System.out);
			} else {
				in = new BufferedReader(new FileReader("..\\codeforces\\src\\com\\congli\\codeforces\\input.txt"));
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