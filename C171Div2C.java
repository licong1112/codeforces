/**
 * Practiced on 3/6/2013
 * 
 * http://codeforces.com/contest/279/problem/C
 * 
 * ==========================
 * Be very careful to overlapping intervals.
 */

package com.congli.codeforces;

import java.io.*;
import java.util.*;

public class C171Div2C implements Runnable {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");

	public static void main(String[] args) {
		new Thread(null, new C171Div2C(), "", 256 * (1L << 20)).start();
	}

	public void solve() throws IOException 
	{
		int num_digits = readInt();
		int num_intervals = readInt();
		int[] digits = new int[num_digits];
		for(int i = 0; i < num_digits; ++i)
			digits[i] = readInt();
		
		ArrayList<Integer> starts = new ArrayList<Integer>();
		ArrayList<Integer> ends = new ArrayList<Integer>();
		ArrayList<Interval> intervals = new ArrayList<Interval>(num_intervals);
		build_base_intervals(starts, ends, digits);
		init_intervals(intervals, num_intervals);
		build_result(intervals, starts, ends);
	}
	
	private void build_result(ArrayList<Interval> intervals, ArrayList<Integer> starts, ArrayList<Integer> ends)
	{
		int base_index = 0;
		boolean[] result = new boolean[intervals.size()];
		for(int i = 0; i < intervals.size(); ++i)
		{
			int curr_start = intervals.get(i).start;
			int curr_end = intervals.get(i).end;
			while(base_index < starts.size()-1 && (curr_start >= starts.get(base_index+1)))
				++base_index;
			result[intervals.get(i).index] = intervals.get(i).end <= ends.get(base_index) ? true : false;
		}
		for(boolean b : result)
			out.println(b ? "Yes" : "No");
	}
	
	private void init_intervals(ArrayList<Interval> intervals, int num_intervals) throws IOException
	{
		for(int i = 0; i < num_intervals; ++i)
		{
			int start = readInt();
			int end = readInt();
			intervals.add(new Interval(start-1, end-1, i));
		}
		sort(intervals, 0, num_intervals-1);
	}
	
	private void sort(ArrayList<Interval> intervals, int start, int end)
    {
        if(start >= end) return;
        int mid = start + (end - start) / 2;
        sort(intervals, start, mid);
        sort(intervals, mid+1, end);
        merge(intervals, start, mid, end);
    }
    
    private void merge(ArrayList<Interval> intervals, int start, int mid, int end)
    {
        ArrayList<Interval> temp = new ArrayList<Interval>();
        int aInd = start;
        int bInd = mid+1;
        
        for(int i = 0; i <= end-start; ++i)
        {
            if(aInd <= mid && bInd <= end)
            {
                if(intervals.get(aInd).start < intervals.get(bInd).start)
                	temp.add(intervals.get(aInd++));
                else
                	temp.add(intervals.get(bInd++));
            }
            else if(aInd <= mid)
            	temp.add(intervals.get(aInd++));
            else
            	temp.add(intervals.get(bInd++));
        }
        
        for(int i = 0; i < temp.size(); ++i)
        	intervals.set(start++, temp.get(i));
    }
	
	private void build_base_intervals(ArrayList<Integer> starts, ArrayList<Integer> ends, int[] digits)
	{
		if(digits.length == 1)
		{
			starts.add(0);
			ends.add(0);
			return;
		}
		int start = 0;
		int end = 1;
		while(end < digits.length)
		{
			while(end < digits.length && digits[end] >= digits[end-1])
				++end;
			while(end < digits.length && digits[end] < digits[end-1]) // Be careful of overlapping intervals
				++end;												  // E.g. 3 4 2 2 3 4 3 2
			starts.add(start);										  // The intervals should be (0, 4)(2, 7)
			start = end-1;
			while(end < digits.length && digits[end] == digits[end-1])
				++end;
			ends.add(end-1);
		}
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

	class Interval
	{
		int start;
		int end;
		int index;
		public Interval(int start, int end, int index)
		{
			this.start = start;
			this.end = end;
			this.index = index;
		}
	}
	
}

