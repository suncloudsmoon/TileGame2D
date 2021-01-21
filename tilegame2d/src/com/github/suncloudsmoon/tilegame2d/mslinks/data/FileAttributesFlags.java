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

public class FileAttributesFlags extends BitSet32 {
	public FileAttributesFlags(int n) {
		super(n);
		reset();
	}
	
	public FileAttributesFlags(ByteReader data) throws IOException {
		super(data);
		reset();
	}
	
	private void reset() {
		clear(3);
		clear(6);
		for (int i=15; i<32; i++)
			clear(i);
	}
	
	public boolean isReadonly() 			{ return get(0); }
	public boolean isHidden() 				{ return get(1); }
	public boolean isSystem() 				{ return get(2); }
	public boolean isDirecory() 			{ return get(4); }
	public boolean isArchive() 				{ return get(5); }
	public boolean isNormal() 				{ return get(7); }
	public boolean isTemporary() 			{ return get(8); }
	public boolean isSparseFile() 			{ return get(9); }
	public boolean isReparsePoint() 		{ return get(10); }
	public boolean isCompressed() 			{ return get(11); }
	public boolean isOffline() 				{ return get(12); }
	public boolean isNotContentIndexed() 	{ return get(13); }
	public boolean isEncypted() 			{ return get(14); }
	
	public FileAttributesFlags setReadonly() 			{ set(0); return this; }
	public FileAttributesFlags setHidden() 				{ set(1); return this; }
	public FileAttributesFlags setSystem() 				{ set(2); return this; }
	public FileAttributesFlags setDirecory() 			{ set(4); return this; }
	public FileAttributesFlags setArchive() 			{ set(5); return this; }
	public FileAttributesFlags setNormal() 				{ set(7); return this; }
	public FileAttributesFlags setTemporary() 			{ set(8); return this; }
	public FileAttributesFlags setSparseFile() 			{ set(9); return this; }
	public FileAttributesFlags setReparsePoint() 		{ set(10); return this; }
	public FileAttributesFlags setCompressed() 			{ set(11); return this; }
	public FileAttributesFlags setOffline() 			{ set(12); return this; }
	public FileAttributesFlags setNotContentIndexed() 	{ set(13); return this; }
	public FileAttributesFlags setEncypted() 			{ set(14); return this; }
	
	public FileAttributesFlags clearReadonly() 			{ clear(0); return this; }
	public FileAttributesFlags clearHidden() 			{ clear(1); return this; }
	public FileAttributesFlags clearSystem() 			{ clear(2); return this; }
	public FileAttributesFlags clearDirecory() 			{ clear(4); return this; }
	public FileAttributesFlags clearArchive() 			{ clear(5); return this; }
	public FileAttributesFlags clearNormal() 			{ clear(7); return this; }
	public FileAttributesFlags clearTemporary() 		{ clear(8); return this; }
	public FileAttributesFlags clearSparseFile() 		{ clear(9); return this; }
	public FileAttributesFlags clearReparsePoint() 		{ clear(10); return this; }
	public FileAttributesFlags clearCompressed() 		{ clear(11); return this; }
	public FileAttributesFlags clearOffline() 			{ clear(12); return this; }
	public FileAttributesFlags clearNotContentIndexed() { clear(13); return this; }
	public FileAttributesFlags clearEncypted() 			{ clear(14); return this; }
	
}
