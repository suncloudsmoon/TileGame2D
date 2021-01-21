/*
* Copyright (c) 2015 Dmitrii Shamrikov
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package com.github.suncloudsmoon.tilegame2d.io;

public final class Bytes {

	private Bytes() {}

	public static short reverse(short n) {
		return (short)(((n & 0xff) << 8) | ((n & 0xff00) >> 8));
	}

	public static int reverse(int n) {
		return ((n & 0xff) << 24) | ((n & 0xff00) << 8) | ((n & 0xff0000) >> 8) | ((n & 0xff000000) >>> 24);
	}

	public static long reverse(long n) {
		return ((n & 0xff) << 56) | ((n & 0xff00) << 40) | ((n & 0xff0000) << 24) | ((n & 0xff000000) << 8) |
				((n & 0xff00000000L) >> 8) | ((n & 0xff0000000000L) >> 24) | ((n & 0xff000000000000L) >> 40) | ((n & 0xff00000000000000L) >>> 56);
	}

	public static short makeShortB(byte b0, byte b1) {
		return (short)((Bytes.i(b0) << 8) | Bytes.i(b1));
	}

	public static int makeIntB(byte b0, byte b1, byte b2, byte b3) {
		return (Bytes.i(b0) << 24) | (Bytes.i(b1) << 16) | (Bytes.i(b2) << 8) | Bytes.i(b3);
	}

	public static long makeLongB(byte b0, byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7) {
		return (Bytes.l(b0) << 56) | (Bytes.l(b1) << 48) | (Bytes.l(b2) << 40) | (Bytes.l(b3) << 32) | (Bytes.l(b4) << 24) | (Bytes.l(b5) << 16) | (Bytes.l(b6) << 8) | Bytes.l(b7);
	}

	public static short makeShortL(byte b0, byte b1) {
		return (short)((Bytes.i(b1) << 8) | Bytes.i(b0));
	}

	public static int makeIntL(byte b0, byte b1, byte b2, byte b3) {
		return (Bytes.i(b3) << 24) | (Bytes.i(b2) << 16) | (Bytes.i(b1) << 8) | Bytes.i(b0);
	}

	public static long makeLongL(byte b0, byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7) {
		return (Bytes.l(b7) << 56) | (Bytes.l(b6) << 48) | (Bytes.l(b5) << 40) | (Bytes.l(b4) << 32) | (Bytes.l(b3) << 24) | (Bytes.l(b2) << 16) | (Bytes.l(b1) << 8) | Bytes.l(b0);
	}

	static long l(byte b) {
		return b & 0xffL;
	}

	static int i(byte b) {
		return b & 0xff;
	}

}
