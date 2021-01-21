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

public class ConsoleFlags extends BitSet32 {

	public ConsoleFlags(int n) {
		super(n);
	}
	
	public ConsoleFlags(ByteReader data) throws IOException {
		super(data);
	}
	
	public boolean isBoldFont() { return get(0); }
	public boolean isFullscreen() { return get(1); }
	public boolean isQuickEdit() { return get(2); }
	public boolean isInsertMode() { return get(3); }
	public boolean isAutoPosition() { return get(4); }
	public boolean isHistoryDup() { return get(5); }
	
	public ConsoleFlags setBoldFont() { set(0); return this; }
	public ConsoleFlags setFullscreen() { set(1); return this; }
	public ConsoleFlags setQuickEdit() { set(2); return this; }
	public ConsoleFlags setInsertMode() { set(3); return this; }
	public ConsoleFlags setAutoPosition() { set(4); return this; }
	public ConsoleFlags setHistoryDup() { set(5); return this; }
	
	public ConsoleFlags clearBoldFont() { clear(0); return this; }
	public ConsoleFlags clearFullscreen() { clear(1); return this; }
	public ConsoleFlags clearQuickEdit() { clear(2); return this; }
	public ConsoleFlags clearInsertMode() { clear(3); return this; }
	public ConsoleFlags clearAutoPosition() { clear(4); return this; }
	public ConsoleFlags clearHistoryDup() { clear(5); return this; }

}
