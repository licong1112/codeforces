/**
 * Practiced on 5/13/2013
 * 
 * http://www.codeforces.com/contest/298/problem/A
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C180Div2A_SnowFootprints {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C180Div2A_SnowFootprints test = new C180Div2A_SnowFootprints();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		String temp = readString();
		char[] array = temp.toCharArray();
		
		calculate(array);
	}
	
	
	public void calculate(char[] array)
	{
		int i = 0;
		while(array[i] == '.')
			++i;
		
		int start = i+1;

		while(array[i] == array[i+1])
			++i;
		
		int end = i+1;
		
		if(array[i] == 'L')
			out.println(end + " " + (start-1));
		else
		{
			if(array[i+1] == '.')
				out.println(start + " " + (end+1));
			else
				out.println(start + " " + end);
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
