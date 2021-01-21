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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;

public class ByteWriter extends OutputStream {
	private boolean le = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);

	private OutputStream stream;
	private int pos = 0;
	
	
	public ByteWriter(OutputStream out) {
		stream = out;
	}
	
	public int getPosition() {
		return pos;
	}
	
	public ByteWriter changeEndiannes() {
		le = !le;
		return this;
	}

	@Override
	public void close() throws IOException
	{
		stream.close();
		super.close();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException
	{
		pos += len;
		stream.write(b, off, len);
	}
	
	@Override
	public void write(int b) throws IOException {
		pos++;
		stream.write(b);
	}

	public void write(long b) throws IOException {
		write((int)b);
	}
	
	public void write2bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		if (le) {
			write(b0); write(b1);
		} else {
			write(b1); write(b0);
		}
	}
	
	public void write3bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		long b2 = (n & 0xff0000) >> 16;
		if (le) {
			write(b0); write(b1); write(b2);
		} else {
			write(b2); write(b1); write(b0);
		}
	}
	
	public void write4bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		long b2 = (n & 0xff0000) >> 16;
		long b3 = (n & 0xff000000) >>> 24;
		if (le) {
			write(b0); write(b1); write(b2); write(b3);
		} else {
			write(b3); write(b2); write(b1); write(b0);
		}
	}
	
	public void write5bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		long b2 = (n & 0xff0000) >> 16;
		long b3 = (n & 0xff000000) >>> 24;
		long b4 = (n & 0xff00000000L) >> 32;
		if (le) {
			write(b0); write(b1); write(b2); write(b3); write(b4);
		} else {
			write(b4); write(b3); write(b2); write(b1); write(b0);
		}
	}
	
	public void write6bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		long b2 = (n & 0xff0000) >> 16;
		long b3 = (n & 0xff000000) >>> 24;
		long b4 = (n & 0xff00000000L) >> 32;
		long b5 = (n & 0xff0000000000L) >> 40;
		if (le) {
			write(b0); write(b1); write(b2); write(b3); write(b4); write(b5);
		} else {
			write(b5); write(b4); write(b3); write(b2); write(b1); write(b0);
		}
	}
	
	public void write7bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		long b2 = (n & 0xff0000) >> 16;
		long b3 = (n & 0xff000000) >>> 24;
		long b4 = (n & 0xff00000000L) >> 32;
		long b5 = (n & 0xff0000000000L) >> 40;
		long b6 = (n & 0xff000000000000L) >> 48;
		if (le) {
			write(b0); write(b1); write(b2); write(b3); write(b4); write(b5); write(b6);
		} else {
			write(b6); write(b5); write(b4); write(b3); write(b2); write(b1); write(b0);
		}
	}
	
	public void write8bytes(long n) throws IOException {
		long b0 = n & 0xff;
		long b1 = (n & 0xff00) >> 8;
		long b2 = (n & 0xff0000) >> 16;
		long b3 = (n & 0xff000000) >>> 24;
		long b4 = (n & 0xff00000000L) >> 32;
		long b5 = (n & 0xff0000000000L) >> 40;
		long b6 = (n & 0xff000000000000L) >> 48;
		long b7 = (n & 0xff00000000000000L) >>> 56;
		if (le) {
			write(b0); write(b1); write(b2); write(b3); write(b4); write(b5); write(b6); write(b7);
		} else {
			write(b7); write(b6); write(b5); write(b4); write(b3); write(b2); write(b1); write(b0);
		}
	}
	
	public void writeBytes(byte[] b) throws IOException {
		for (byte i : b) 
			write(i);
	}
	
	public void writeUnicodeString(String s) throws IOException {
		writeUnicodeString(s, false);
	}
	
	public void writeUnicodeString(String s, boolean nullterm) throws IOException {
		if (!nullterm) write2bytes(s.length());
		for (int i=0; i<s.length(); i++)
			write2bytes(s.charAt(i));
		if (nullterm) write2bytes(0);
	}
}
