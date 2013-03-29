/**
 * Practiced on 3/7/2013
 * 
 * http://codeforces.com/contest/279/problem/E
 * 
 * ==========================
 * For example, given string "1101011".
 * 1. Build int[] marker = {0 1 1 0 1 0 1 1}
 * 2. After first while loop: marker = {1 0 -1 1 0 -1 0 -1}, where 1 represents plus and -1 represents minus.
 *    For example, this marker represents 2^7 - 2^5 + 2^4 - 2^2 - 2^0.
 * 3. This representation can be further shorten. This is the second while loop come into play:
 *    after the first run of the second while loop: marker = {1 0 0 -1 0 -1 0 -1}. The idea is that
 *    since -2^5 + 2^4 = -2^4, so we can shorten the representation. 
 * 4. After the first run of the second while loop, if the representation can be further shorten, the
 *    while loop will keep running.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C171Div2E implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C171Div2E(), "", 256 * (1L << 20)).start();
	}

	public void solve() throws IOException 
	{
		String str = readString();
		int[] marker = new int[str.length()+1];
		for(int i = 0; i < str.length(); ++i)
			marker[i+1] = str.charAt(i)-'0';

		int end = marker.length-1;
		while(end >= 0)
		{
			int start = end;
			while(start >= 0 && marker[start] == 1)
				--start;
			if(end-start >= 2)
			{
				marker[end] = -1; //minus
				marker[start] = 1; //plus
				for(int i = start+1; i < end; ++i)
					marker[i] = 0;
			}
			else if(end-start == 1)
				marker[start+1] = 1; //plus
			while(start >= 0 && marker[start] == 0)
				--start;
			end = start;
		}
		
		boolean is_changed = true;
		while(is_changed)
		{
			is_changed = false;
			for(int i = 0; i < marker.length-1; ++i)
			{
				if(marker[i] * marker[i+1] == -1)
				{	
					is_changed = true;
					marker[i] = 0;
					marker[i+1] = -marker[i+1];
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < marker.length; ++i)
			if(marker[i] != 0)
				++count;
		
		out.println(count);
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

	class Interval
	{
		int start;
		int end;
		int index;
		public Interval(int start, int end, int index)
		{
			this.start = start;
			this.end = end;
			this.index = index;
		}
	}
	
}


