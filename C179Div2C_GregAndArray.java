/**
 * Practiced on 4/19/2013
 * http://www.codeforces.com/contest/296/problem/C
 * 
 * ==============================================================
 * The following problem can trigger the solution to this problem:
 * 
 * For each people, he/she arrives at a bus station at a certain
 * time, and leaves at another certain time. Given a set of time
 * intervals that each person stays at the bus station, count how
 * many people are there at the bus station for each time spot.
 * 
 * The solution to this problem is this:
 * 
 * Maintain an array, where array[i] represents the NET number of
 * people that arrives at the station at time spot i. For example,
 * if at time spot i, there are 2 person comes, and 1 person leaves,
 * then the NET number of people that arrives at this time point
 * is 1.
 * 
 * So we can traverse the two ends of each interval, and get array[].
 * Then, for each time spot i, the total number of people that are
 * staying at the station is simply sum{array[0]...array[i]}.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C179Div2C_GregAndArray {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C179Div2C_GregAndArray test = new C179Div2C_GregAndArray();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int m = readInt();
		int k = readInt();
		
		long[] array = new long[n+1];
		for(int i = 1; i <= n; ++i)
			array[i] = readInt();
		
		long[] additor = new long[m+1];
		PartInterval[] part_interval = new PartInterval[2*m];
		int index = 0;
		for(int i = 1; i <= m; ++i)
		{
			part_interval[index++] = new PartInterval(readInt(), i, 1); // Eqv. to one person come at time readInt()
			part_interval[index++] = new PartInterval(readInt()+1, i, -1); // Eqv. to one person leave at time readInt()+1
			additor[i] = readInt();
		}
		
		int[] num_ope_each_interval = new int[m+2];
		for(int i = 0; i < k; ++i)
		{
			num_ope_each_interval[readInt()]++;
			num_ope_each_interval[readInt()+1]--;
		}
		for(int i = 1; i <= m; ++i)
		{
			num_ope_each_interval[i] += num_ope_each_interval[i-1];
			additor[i] *= num_ope_each_interval[i];
		}
		
		PartInterval curr = null;
		long[] add_to_array = new long[n+2]; 
		for(int i = 0; i < part_interval.length; ++i)
		{
			curr = part_interval[i];
			add_to_array[curr.index] += curr.sign * additor[curr.oper_id];
		}
		
		long sum = 0l;
		for(int i = 1; i <= n; ++i)
		{
			sum += add_to_array[i];
			array[i] += sum;
		}
		
		for(int i = 1; i <= n; ++i)
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

class PartInterval
{
	int index;
	int oper_id;
	int sign;
	public PartInterval(int index, int oper_id, int sign)
	{
		this.index = index;
		this.oper_id = oper_id;
		this.sign = sign;
	}
}