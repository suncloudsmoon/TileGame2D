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
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.github.suncloudsmoon.tilegame2d.io.ByteReader;
import com.github.suncloudsmoon.tilegame2d.io.ByteWriter;
import com.github.suncloudsmoon.tilegame2d.mslinks.Serializable;

public class Filetime extends GregorianCalendar implements Serializable {
	private long residue;

	public Filetime() {
		super();
	}

	public Filetime(ByteReader data) throws IOException {
		this(data.read8bytes());
	}

	public Filetime(long time) {
		long t = time / 10000;
		residue = time - t;
		setTimeInMillis(t);
		add(Calendar.YEAR, -369);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		if (!super.equals(o))
			return false;

		Filetime obj = (Filetime) o;
		return residue == obj.residue;
	}

	@Override
	public int hashCode() {
		return (int) (super.hashCode() ^ ((residue & 0xffffffff00000000l) >> 32) ^ (residue & 0xffffffffl));
	}

	public long toLong() {
		GregorianCalendar tmp = (GregorianCalendar) clone();
		tmp.add(Calendar.YEAR, 369);
		return tmp.getTimeInMillis() + residue;
	}

	public void serialize(ByteWriter bw) throws IOException {
		bw.write8bytes(toLong());
	}

	public String toString() {
		return String.format("%d:%d:%d %d.%d.%d", get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE), get(Calendar.SECOND),
				get(Calendar.DAY_OF_MONTH), get(Calendar.MONTH) + 1, get(Calendar.YEAR));
	}
}
