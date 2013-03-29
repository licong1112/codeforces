/**
 * Practiced on 1/12/2013
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class Test5A implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new Test5A(), "", 256 * (1L << 20)).start();
	}

	public void solve() throws IOException 
	{
		int num_tests = readInt();
		for(int i = 0; i < num_tests; ++i)
		{
			int N = readInt();
			int[] location = new int[N];
			int[] vineLength = new int[N];
			int destination = 0;
			for(int j = 0; j < N; ++j)
			{
				location[j] = readInt();
				vineLength[j] = readInt();
			}
			destination = readInt();
			calculate(location, vineLength, destination, i+1);
		}
	}
	
	private void calculate(int[] location, int[] vineLength, int destination, int case_num)
	{
		int currInd = 0;
		int currLength = Math.min(location[0], vineLength[0]);
		
		while(currInd < location.length)
		{
			if(currLength >= destination - location[currInd])
			{
				System.out.println("Case #" + case_num + ": YES");
				return;
			}
			
			if(currInd == location.length-1)
			{
				System.out.println("Case #" + case_num + ": NO");
				return;
			}
			
			int maxInd = currInd+1;
			while(maxInd < location.length && currLength >= location[maxInd]-location[currInd])
				++maxInd;
			
			if(maxInd == currInd+1)
			{
				System.out.println("Case #" + case_num + ": NO");
				return;
			}
			
			int maxNextLength = 0;
			int nextInd = currInd+1;
			for(int i = currInd+1; i < maxInd; ++i)
			{
				int iLength = Math.min(vineLength[i], location[i]-location[currInd]);
				int nextDistance = maxInd < location.length ? location[maxInd] : destination;
				if(iLength < nextDistance-location[i])
					continue;
				int iNextLength = Math.min(nextDistance-location[i], maxInd < location.length ? vineLength[maxInd] : Integer.MAX_VALUE);
				if(iNextLength >= maxNextLength)
				{
					maxNextLength = iNextLength;
					nextInd = i;
				}
			}

			if(maxNextLength == 0)
			{
				System.out.println("Case #" + case_num + ": NO");
				return;
			}
			currLength = Math.min(location[nextInd]-location[currInd], vineLength[nextInd]);
			currInd = nextInd;
		}
		
		System.out.println("Case #" + case_num + ": No");
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