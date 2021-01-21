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

package com.github.suncloudsmoon.tilegame2d.mslinks.data;

import java.io.IOException;

import com.github.suncloudsmoon.tilegame2d.io.ByteReader;

public class LinkInfoFlags extends BitSet32 {
	
	public LinkInfoFlags(int n) {
		super(n);
		reset();
	}
	
	public LinkInfoFlags(ByteReader data) throws IOException {
		super(data);
		reset();
	}
	
	private void reset() {
		for (int i=2; i<32; i++)
			clear(i);
	}
	
	public boolean hasVolumeIDAndLocalBasePath() 						{ return get(0); }
	public boolean hasCommonNetworkRelativeLinkAndPathSuffix()			{ return get(1); }
	
	public LinkInfoFlags setVolumeIDAndLocalBasePath() 					{ set(0); return this; }
	public LinkInfoFlags setCommonNetworkRelativeLinkAndPathSuffix()	{ set(1); return this; }
	
	public LinkInfoFlags clearVolumeIDAndLocalBasePath() 				{ clear(0); return this; }
	public LinkInfoFlags clearCommonNetworkRelativeLinkAndPathSuffix()	{ clear(1); return this; }
}
