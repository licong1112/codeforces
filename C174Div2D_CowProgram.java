/**
 * Practiced on 3/30/2013
 * 
 * http://www.codeforces.com/contest/287/problem/B
 * 
 * =========================================
 * DFS is the method. For each position, store the sum that can be achieved if
 * starts from this position. For any other path that passes here, we already 
 * have the sum that starts from this position, thus no need to recalculate.
 * 
 * It's not hard to write recursion-based DFS. 
 * Iteration-based version was written also, but didn't make it write... and can't
 * figure out where the problem was...
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C174Div2D_CowProgram {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C174Div2D_CowProgram test = new C174Div2D_CowProgram();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int[] array = new int[n+1];

		for(int i = 2; i <= n; ++i)
			array[i] = readInt();
		
		boolean[] visited = new boolean[n+1];

		long[] sum = new long[n+1];
		for(int ind = 2; ind <= n; ++ind)
		{
			array[1] = ind-1;
			HashSet<Integer> set = new HashSet<Integer>();
			if(!visited[ind])
			{
				cal_sum_from_ind(ind, array, visited, sum, set);
			}
			out.println(sum[ind] == -1 ? -1 : array[1] + sum[ind]);
		}
	}
	
	public long cal_sum_from_ind(int ind, int[] array, boolean[] visited, long[] sum, HashSet<Integer> set)
	{
		int n = array.length-1;
		if(ind > n || ind <= 0) return 0;
		
		if(visited[ind]) return sum[ind];
		long sum_curr = array[ind];
		visited[ind] = true;
		set.add(ind);
		
		if(array[ind] > n || array[ind] <= 0) 
		{
			sum[ind] = sum_curr;
			return sum_curr;
		}
		int next_ind = ind - array[ind];
		
		if(next_ind > n || next_ind <= 0) 
		{
			sum[ind] = sum_curr;
			return sum_curr;
		}
		sum_curr += array[next_ind];
		
		if(array[next_ind] > n || array[next_ind] <= 0) 
		{
			sum[ind] = sum_curr;
			return sum_curr;
		}
		
		int next_next_ind = next_ind + array[next_ind];
		if(next_next_ind == ind || set.contains(next_next_ind))
		{
			sum[ind] = -1;
			return -1;
		}
		
		long next_next_sum = cal_sum_from_ind(next_next_ind, array, visited, sum, set);
		sum_curr = next_next_sum == -1 ? -1 : sum_curr + next_next_sum;
		sum[ind] = sum_curr;
		return sum_curr;
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