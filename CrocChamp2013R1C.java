/**
 * Practiced on 5/6/2013
 * http://www.codeforces.com/contest/292/problem/C
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class CrocChamp2013R1C {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		CrocChamp2013R1C test = new CrocChamp2013R1C();
		test.start();
	}

	public void solve() throws IOException 
	{
		int n = readInt();
		int[] array = new int[n];
		for(int i = 0; i < n; ++i)
			array[i] = readInt();
		
		int[] count = new int[10];
		ArrayList<String> result = new ArrayList<String>();
		calculate(array, 0, new StringBuilder(), count, 0, result);
		
		out.println(result.size());
		for(String str : result)
			out.println(str);
	}
	
	public void calculate(int[] array, int level, StringBuilder sb, int[] count, int total, ArrayList<String> result)
	{
		if(sb.length() > 5) return;
		
		for(int i = 0; i < array.length; ++i)
		{
			sb.append(array[i]);
			if(count[array[i]] == 0)
				++total;
			++count[array[i]];
			if(total == array.length)
			{
				build_ip(sb.toString() + sb.reverse().toString(), 0, 0, new StringBuilder(), result);
				sb.reverse();
				if(sb.length() > 1)
				{
					build_ip(sb.toString() + sb.reverse().substring(1), 0, 0, new StringBuilder(), result);
					sb.reverse();
				}
			}
			calculate(array, level+1, sb, count, total, result);
			if(count[array[i]] == 1)
				--total;
			--count[array[i]];
			sb.delete(sb.length()-1, sb.length());
		}
	}
	
	public void build_ip(String str, int start, int level, StringBuilder ip, ArrayList<String> result)
	{
		String temp = "";
		if(level == 3)
		{
			temp = str.substring(start);
			if(is_valid(temp))
			{
				result.add(ip.append(temp).toString());
				if(temp.length() > 0)
					ip.delete(ip.length()-temp.length(), ip.length());
			}
			return;
		}
		
		for(int i = 1; i <= 3; ++i)
		{
			if(str.length() >= start+i)
			{
				temp = str.substring(start, start+i);
				if(is_valid(temp))
				{
					build_ip(str, start+i, level+1, ip.append(temp).append("."), result);
					ip.delete(ip.length()-temp.length()-1, ip.length());
				}
			}
		}
	}
	
	public boolean is_valid(String str)
	{
		if(str.length() > 3 || str.length() == 0) return false;
		if(str.length() >= 2 && str.charAt(0) == '0') return false;
		if(str.length() == 3 && str.compareTo("255") > 0) return false;
		return true;
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