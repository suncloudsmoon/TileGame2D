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

package com.github.suncloudsmoon.tilegame2d.mslinks.extra;

import java.io.IOException;
import java.util.LinkedList;

import com.github.suncloudsmoon.tilegame2d.io.ByteReader;
import com.github.suncloudsmoon.tilegame2d.io.ByteWriter;
import com.github.suncloudsmoon.tilegame2d.mslinks.Serializable;
import com.github.suncloudsmoon.tilegame2d.mslinks.ShellLinkException;

public class VistaIDList implements Serializable {

	public static final int signature = 0xA000000C;
	
	private LinkedList<byte[]> list = new LinkedList<>();
	
	public VistaIDList(ByteReader br, int size) throws ShellLinkException, IOException {
		if (size < 0xa)
			throw new ShellLinkException();
		
		int s = (int)br.read2bytes();
		while (s != 0) {
			s -= 2;
			byte[] b = new byte[s];
			for (int i=0; i<s; i++)
				b[i] = (byte)br.read();
			list.add(b);
			s = (int)br.read2bytes();
		}		
	}
	
	@Override
	public void serialize(ByteWriter bw) throws IOException {
		int size = 10;
		for (byte[] i : list)
			size += i.length + 2;
		bw.write2bytes(size);
		for (byte[] i : list) {
			bw.write2bytes(i.length + 2);
			for (byte j : i)
				bw.write(j);
		}
		bw.write2bytes(0);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (byte[] b : list)
			sb.append(new String(b) + "\n");
		return sb.toString();
	}
}
