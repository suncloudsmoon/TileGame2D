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

public class EnvironmentVariable implements Serializable {

	public static final int signature = 0xA0000001;
	public static final int size = 0x314;
	
	private String variable;
	
	public EnvironmentVariable() {
		variable = "";
	}
	
	public EnvironmentVariable(ByteReader br, int sz) throws ShellLinkException, IOException {
		if (sz != size)
			throw new ShellLinkException();
		
		int pos = br.getPosition();
		variable = br.readString(260);
		br.seek(pos + 260 - br.getPosition());
		
		pos = br.getPosition();
		variable = br.readUnicodeString(260);
		br.seek(pos + 520 - br.getPosition());
	}
	
	@Override
	public void serialize(ByteWriter bw) throws IOException {
		bw.write4bytes(size);
		bw.write4bytes(signature);
		byte[] b = variable.getBytes();
		bw.writeBytes(b);
		for (int i=0; i<260-b.length; i++)
			bw.write(0);
		for (int i=0; i<variable.length(); i++)
			bw.write2bytes(variable.charAt(i));
		for (int i=0; i<260-variable.length(); i++)
			bw.write2bytes(0);
	}
	
	public String getVariable() { return variable; }
	public EnvironmentVariable setVariable(String s) { variable = s; return this; }

}
