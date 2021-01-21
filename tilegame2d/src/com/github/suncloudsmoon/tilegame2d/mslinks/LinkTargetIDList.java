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

package com.github.suncloudsmoon.tilegame2d.mslinks;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.github.suncloudsmoon.tilegame2d.io.ByteReader;
import com.github.suncloudsmoon.tilegame2d.io.ByteWriter;
import com.github.suncloudsmoon.tilegame2d.mslinks.data.ItemID;

public class LinkTargetIDList extends LinkedList<ItemID> implements Serializable {
	
	public LinkTargetIDList() {}
	
	public LinkTargetIDList(ByteReader data) throws IOException, ShellLinkException {
		int size = (int)data.read2bytes();
		
		int pos = data.getPosition(); 
		
		boolean binary = false;
		int s = (int)data.read2bytes();
		while (s != 0) {
			s -= 2;
			if (binary) {
				byte[] b = new byte[s];
				for (int i=0; i<s; i++)
					b[i] = (byte)data.read();
				add(new ItemID(b));
			} else try {
				add(new ItemID(data, s));
			} catch (UnsupportedCLSIDException e) {
				System.err.println("unsupported CLSID");
				binary = true;
			}
			s = (int)data.read2bytes();
		}
		
		pos = data.getPosition() - pos;
		if (pos != size) 
			throw new ShellLinkException();
	}

	public void serialize(ByteWriter bw) throws IOException {
		int size = 2;
		byte[][] b = new byte[size()][];
		int i = 0;
		for (ItemID j : this) {
			ByteArrayOutputStream ba = new ByteArrayOutputStream();
			ByteWriter w = new ByteWriter(ba);
			
			j.serialize(w);
			b[i++] = ba.toByteArray();
		}
		for (byte[] j : b)
			size += j.length + 2;
		
		bw.write2bytes(size);
		for (byte[] j : b) {
			bw.write2bytes(j.length + 2);
			bw.writeBytes(j);
		}
		bw.write2bytes(0);
	}
	
	public boolean isCorrect() {
		for (ItemID i : this)
			if (i.getType() == ItemID.TYPE_UNKNOWN)
				return false;
		return true;
	}

	public String buildPath()
	{
		StringBuilder path = new StringBuilder();
		for (ItemID i : this) {
			if (i.getType() == ItemID.TYPE_DRIVE || i.getType() == ItemID.TYPE_DRIVE_OLD)
				path.append(i.getName());
			else if (i.getType() == ItemID.TYPE_DIRECTORY || i.getType() == ItemID.TYPE_DIRECTORY_OLD)
				path.append(i.getName() + File.separator);
			else if (i.getType() == ItemID.TYPE_FILE || i.getType() == ItemID.TYPE_FILE_OLD)
				path.append(i.getName());
		}
		return path.toString();
	}
}
