/**
 * Practiced on 10/18/2013
 * 
 * http://codeforces.com/contest/302/problem/B
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class C182Div2B_EugenyAndPlayList {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		C182Div2B_EugenyAndPlayList test = new C182Div2B_EugenyAndPlayList();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt(), m = readInt();
		int[] playlist = new int[n+1];
		for (int i = 0; i < n; ++i) {
			playlist[i+1] = playlist[i] + readInt()*readInt();
		}
		for (int i = 0; i < m; ++i) {
			out.println(findSong(readInt(), playlist));
		}
	}	
	
	public int findSong(int time, int[] playlist) {
		int start = 1, end = playlist.length-1, mid = 0;
		while (start <= end) {
			mid = start + (end-start)/2;
			if (playlist[mid] >= time && playlist[mid-1] < time) {
				return mid;
			} else if (playlist[mid] < time) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		return 0;
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

