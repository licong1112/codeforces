/**
 * Practiced on 3/28/2013
 * 
 * http://www.codeforces.com/contest/287/problem/A
 * 
 * =====================================
 * numSharp[i][j] stores the number of sharps that appeared in the 
 * 4*4 square from board[i-1][j-1] to board[i][j].
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

public class C176Div2A implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C176Div2A(), "", 256 * (1L << 20)).start();
	}
	
	public void solve() throws IOException 
	{
		char[][] board = new char[4][4];
		int[][] numSharp = new int[4][4];
		for(int i = 0; i < 4; ++i)
		{
			String temp = readString();
			for(int j = 0; j < 4; ++j)
				board[i][j] = temp.charAt(j);
		}
		
		for(int i = 1; i < 4; ++i)
		{
			for(int j = 1; j < 4; ++j)
			{
				if(board[i-1][j-1] == '#') ++numSharp[i][j]; //left-up
				if(board[i][j-1] == '#') ++numSharp[i][j]; //left
				if(board[i-1][j] == '#') ++numSharp[i][j]; //up
				if(board[i][j] == '#') ++numSharp[i][j];
			}
		}
		
		if(calculate(board, numSharp))
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	
	public boolean calculate(char[][] board, int[][] numSharp)
	{
		for(int i = 0; i < 4; ++i)
		{
			for(int j = 0; j < 4; ++j)
			{
				if(board[i][j] == '.')
				{
					if(i < 3 && j < 3 && (numSharp[i+1][j+1] == 3 || numSharp[i+1][j+1] == 0)) return true;
					if(i < 3 && j > 0 && (numSharp[i+1][j] == 3 || numSharp[i+1][j] == 0)) return true;
					if(j < 3 && i > 0 && (numSharp[i][j+1] == 3 || numSharp[i][j+1] == 0)) return true;
					if(i > 0 && j > 0 && (numSharp[i][j] == 3 || numSharp[i][j] == 0)) return true;
				}
				else
				{
					if(i < 3 && j < 3 && (numSharp[i+1][j+1] == 1 || numSharp[i+1][j+1] == 4)) return true;
					if(i < 3 && j > 0 && (numSharp[i+1][j] == 1 || numSharp[i+1][j] == 4)) return true;
					if(j < 3 && i > 0 && (numSharp[i][j+1] == 1 || numSharp[i][j+1] == 4)) return true;
					if(i > 0 && j > 0 && (numSharp[i][j] == 1 || numSharp[i][j] == 4)) return true;
				}
			}
		}
		return false;
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




