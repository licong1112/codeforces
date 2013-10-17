/**
 * Practiced on 10/18/2013
 * 
 * http://codeforces.com/contest/300/problem/C
 * ========================================
 * The key is to calculate combination values. It's interesting to see how to calculate it:
 * 
 * In this problem it's impossible to calculate binomial coefficients using Pascal's triangle, 
 * because of large n. However it can be done this way C[n][i] = fact[n]inv(fact[n-i]fact[i]). 
 * inv(a) is multiplicative inverse element(modulo MOD). MOD is a prime number, so inv(a) = a^{MOD-2}. 
 * Calculating this values for each i from 0 to n will give correct answer in O(nlog(MOD)).
 * 
 * WHY???????
 */

package com.congli.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class C181Div2C_BeautifulNumbers {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static final int mod = 1000000007;
	
	public static void main(String[] args) {
		C181Div2C_BeautifulNumbers test = new C181Div2C_BeautifulNumbers();
		test.start();
	}

	public void solve() throws IOException 
	{
		int a = readInt();
		int b = readInt();
		int n = readInt();
		long result = 0;
		long[] factorial = new long[n+1];
		factorial[0] = 1;
		for (int i = 1; i <= n; ++i) {
			factorial[i] = (factorial[i-1] * i) % mod;
		}
		
		for (int i = 0; i <= n; ++i) {
			int num = i*a + (n-i)*b;
			if (isGood(num, a, b)) {
				long r = factorial[n] % mod;  
				r = (r * pow(factorial[n-i] , mod-2)) % mod;
				r = (r * pow(factorial[i], mod-2)) % mod;
				result = (result + r) % mod;
			}
		}
		out.println(result % mod);
	}
	
	public long pow(long x, int n) 
    {        
		long temp = x;
		long result = 1;
        while(n > 0)
        {
            if((n&1) == 1) {
            	result = (result*temp) % mod;
            }  
            temp = (temp*temp) % mod;
            n >>= 1;
        }
        return result;
    }
	
	public boolean isGood(int num, int a, int b) {
		int digit = 0;
		while (num > 0) {
			digit = num % 10;
			if (digit != a && digit != b) return false;
			num /= 10;
		}
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
