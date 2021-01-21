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

import com.github.suncloudsmoon.tilegame2d.io.ByteReader;
import com.github.suncloudsmoon.tilegame2d.io.ByteWriter;
import com.github.suncloudsmoon.tilegame2d.mslinks.Serializable;
import com.github.suncloudsmoon.tilegame2d.mslinks.ShellLinkException;
import com.github.suncloudsmoon.tilegame2d.mslinks.data.GUID;

public class Tracker implements Serializable {
	
	public static final int signature = 0xA0000003;
	public static final int size = 0x60;
	
	private String netbios;
	private GUID d1;
	private GUID d2;
	private GUID db1;
	private GUID db2;
	
	public Tracker() {
		netbios = "localhost";
		d1 = db1 = new GUID();
		d2 = db2 = new GUID("539D9DC6-8293-11E3-8FB0-005056C00008");
	}
	
	public Tracker(ByteReader br, int sz) throws ShellLinkException, IOException {
		if (sz != size)
			throw new ShellLinkException();
		int len = (int)br.read4bytes();
		if (len < 0x58)
			throw new ShellLinkException();
		br.read4bytes();
		int pos = br.getPosition();
		netbios = br.readString(16);
		br.seek(pos + 16 - br.getPosition());
		d1 = new GUID(br);
		d2 = new GUID(br);
		db1 = new GUID(br);
		db2 = new GUID(br);
	}

	@Override
	public void serialize(ByteWriter bw) throws IOException {
		bw.write4bytes(size);
		bw.write4bytes(signature);
		bw.write4bytes(0x58);
		bw.write4bytes(0);
		byte[] b = netbios.getBytes();
		bw.writeBytes(b);
		for (int i=0; i<16-b.length; i++)
			bw.write(0);
		d1.serialize(bw);
		d2.serialize(bw);
		db1.serialize(bw);
		db2.serialize(bw);
	}
	
	public String getNetbiosName() { return netbios; }
	public Tracker setNetbiosName(String s) throws ShellLinkException {
		if (s.length() > 16)
			throw new ShellLinkException("netbios name length must be <= 16");
		netbios = s;
		return this;
	}
}
