/**
 * Practiced on 11/23/2014
 * 
 * http://codeforces.com/contest/374/problem/C
 * 
 * ===============================
 * Java8 TLE, Java6 146ms pass... WTF!!!!!!
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class C220Div2C_InnaAndDima {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	int[][] nextInd = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) {
		C220Div2C_InnaAndDima test = new C220Div2C_InnaAndDima();
		test.start();
	}

	public void solve() throws IOException {
		int n = readInt(), m = readInt();
		int[][] matrix = new int[n][m];
		int[][] visited = new int[n][m];
		int[][] dp = new int[n][m];
		
		for (int i = 0; i < n; ++i) {
			String str = in.readLine();
			for (int j = 0; j < m; ++j) {
				switch(str.charAt(j)) {
				case 'D':
					matrix[i][j] = 0;
					break;
				case 'I':
					matrix[i][j] = 1;
					break;
				case 'M':
					matrix[i][j] = 2;
					break;
				case 'A':
					matrix[i][j] = 3;
					break;
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (matrix[i][j] == 0 && visited[i][j] == 0) {
					int currentTime = dfs(matrix, visited, dp, i, j, n, m);
					if (currentTime == Integer.MAX_VALUE) {
						out.println("Poor Inna!");
						return;
					}
					result = Math.max(result, currentTime);
				}
			}
		}
		if (result / 4 == 0) {
			out.println("Poor Dima!");
		} else {
			out.println(result / 4);
		}
		
	}
	
	public int dfs(int[][] matrix, int[][] visited, int[][] dp, int r, int c, int n, int m) {
		if (visited[r][c] == 1) {
			return Integer.MAX_VALUE;
		}
		if (visited[r][c] == 2) {
			return dp[r][c];
		}
		
		visited[r][c] = 1;
		int maxTime = 0;
		for (int i = 0; i < 4; ++i) {
			int nextR = r + nextInd[i][0];
			int nextC = c + nextInd[i][1];
			if (insideMatrix(nextR, nextC, n, m) && 
				(matrix[r][c] + 1) % 4 == matrix[nextR][nextC]) {
				int currentTime = dfs(matrix, visited, dp, nextR, nextC, n, m);
				if (currentTime == Integer.MAX_VALUE) {
					return currentTime;
				}
				maxTime = Math.max(maxTime, currentTime);
			}
		}
		dp[r][c] = maxTime + 1;
		visited[r][c] = 2;
		return dp[r][c];
	}

	public boolean insideMatrix(int r, int c, int n, int m) {
		if (r >= 0 && r < n && c >= 0 && c < m) {
			return true;
		}
		return false;
	}
	
	public void start()
	{
		try {
			long t1 = System.currentTimeMillis();
			if (System.getProperty("ONLINE_JUDGE") != null) {
				in = new BufferedReader(new InputStreamReader(System.in));
				out = new PrintWriter(System.out);
			} else {
				in = new BufferedReader(new FileReader("/Users/congli/Documents/javaworkspace/codeforces/src/com/congli/codeforces/input.txt"));
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


