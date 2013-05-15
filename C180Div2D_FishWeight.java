/**
 * Practiced on 5/15/2013
 * http://www.codeforces.com/contest/298/problem/D
 * 
 * ======================================
 * The idea is to sort the number of fishes by their weight. If there is a type i, such that
 * the total number of fish with type i+1, i+2, ... got by the first person is more than the
 * the second person, then it must be YES. Otherwise, if such a type cannot be found, then
 * should be NO.
 * 
 * As a simple example. First person has x fishes of type i, and second person has x-1 fishes
 * of type i+1. Then, we can choose w(i) = a, and w(i+1) = a+1. Thus the total of first person
 * is ax = a*(x-1)+a, and the second person is (a+1)*(x-1). So the difference is a-(x-1). So we
 * can choose a to be large enough, such that the difference is positive.
 * 
 * For more general case, the discussion is similar.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C180Div2D_FishWeight {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C180Div2D_FishWeight test = new C180Div2D_FishWeight();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int m = readInt();
		int k = readInt();
		
		Hash[] hash = new Hash[m+n+10];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		int hash_ind = 0;
		int weight = 0;
		for(int i = 0; i < n; ++i)
		{
			weight = readInt();
			if(map.containsKey(weight))
				hash[map.get(weight)].count++;
			else
			{
				map.put(weight, hash_ind);
				hash[hash_ind++] = new Hash(weight, 1);
			}
		}
		
		for(int i = 0; i < m; ++i)
		{
			weight = readInt();
			if(map.containsKey(weight))
				hash[map.get(weight)].count--;
			else
			{
				map.put(weight, hash_ind);
				hash[hash_ind++] = new Hash(weight, -1);
			}
		}
		
		while(hash_ind < hash.length)
			hash[hash_ind++] = new Hash(0, 0);
		
		Arrays.sort(hash);
		calculate(hash);
	}
	
	public void calculate(Hash[] hash)
	{
		int i = 0;
		if(hash[0].count > 0)
		{
			out.println("YES");
			return;
		}
		
		int count_plus = 0, count_minus = 0;
		while(i < hash.length && hash[i].key > 0)
		{
			while(i < hash.length && hash[i].count <= 0)
				count_minus -= hash[i++].count;
			
			//count_minus = -count_minus;
			
			while(i < hash.length && hash[i].count >= 0)
				count_plus += hash[i++].count;
			
			if(count_plus > count_minus)
			{
				out.println("YES");
				return;
			}
			
			/*else if(count_plus < count_minus)
			{
				out.println("NO");
				return;
			}*/
		}
		
		out.println("NO");
	}
	
	class Hash implements Comparable<Hash>
	{
		int key;
		int count;
		public Hash(int key, int count)
		{
			this.key = key;
			this.count = count;
		}
		@Override
		public int compareTo(Hash o) {
			// TODO Auto-generated method stub
			return o.key - this.key;
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
