/**
 * Practiced on 4/15/2013
 * http://www.codeforces.com/contest/294/problem/B
 * 
 * ========================================
 * dp[i][j] represents the shortest width on the second layer that can be
 * achieved, when considering only the first i books and when the thick
 * of the first layer is j.
 * 
 * dp[i][j] = -1 represents that it's not possible to organize the first i
 * books such that the first layer has thick j.
 * 
 * However, note that it is valid status for dp[i][0]. This means all books
 * are put on the second layer, even though this cannot be physically achieved.
 *
 * Then we need to return the first j, which has dp[n-1][j] <= j.
 * 
 * It can be optimized to use O(n) space.
 */

package com.congli.codeforces;
 
import java.io.*;
import java.util.*;

public class C178Div2B_ShaassAndBookshelf {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C178Div2B_ShaassAndBookshelf test = new C178Div2B_ShaassAndBookshelf();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int[] thick = new int[n];
		int[] width = new int[n];
		for(int i = 0; i < n; ++i)
		{
			thick[i] = readInt();
			width[i] = readInt();
		}
		
		int[][] dp = new int[n][2*n + 1];
		for(int i = 0; i < n; ++i)
			Arrays.fill(dp[i], -1);
		
		dp[0][0] = width[0];
		dp[0][thick[0]] = 0;
		for(int i = 1; i < n; ++i)
		{
			for(int total_t = 0; total_t <= 2*n; ++total_t)
			{
				if(total_t < thick[i])
				{
					if(dp[i-1][total_t] < 0) continue; 
					dp[i][total_t] = dp[i-1][total_t] + width[i];
				}	
				else
				{
					if(dp[i-1][total_t - thick[i]] < 0 && dp[i-1][total_t] < 0) continue;
					if(dp[i-1][total_t - thick[i]] < 0)
						dp[i][total_t] = dp[i-1][total_t] + width[i];
					else if(dp[i-1][total_t] < 0)
						dp[i][total_t] = dp[i-1][total_t - thick[i]];
					else
						dp[i][total_t] = Math.min(dp[i-1][total_t - thick[i]], dp[i-1][total_t] + width[i]);
				}
			}
		}
		
		int result = 0;
		while(dp[n-1][result] == -1 || result < dp[n-1][result])
			++result;
		System.out.println(result);
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