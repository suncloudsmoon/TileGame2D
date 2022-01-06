/*
* Copyright (c) 2021, suncloudsmoon
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package test;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.github.suncloudsmoon.tilegame2d.mslinks.ShellLink;

public class Test {
	
	private static SimpleGameTest simpleTest;

	public static void main(String[] args) {	
		runSimpleTest();
	}
	
	public static void runSimpleTest() {
		try {
			simpleTest = new SimpleGameTest(100, 100, 100, 100, "Test me, Hello World!", 500, 500, new NimbusLookAndFeel());
			System.out.println("Started!");
			simpleTest.start(true);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			runShortcutTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void runShortcutTest() throws IOException {
		ShellLink s = ShellLink.createLink("C:\\Users\\super\\Desktop\\Lol.txt");
		// s.setWorkingDir("..");
		s.setIconLocation("C:\\Users\\super\\Desktop\\favicon.ico");
		s.saveTo("C:\\Users\\super\\Desktop\\HelloWorld1.lnk");
	}
	
	public static void runComplexTest() {
		
	}

}
