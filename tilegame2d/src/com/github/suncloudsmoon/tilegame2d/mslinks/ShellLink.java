/*
* Copyright (c) 2015 Dmitrii Shamrikov
* Copyright (c) 2021 Ganesha Ajjampura
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.github.suncloudsmoon.tilegame2d.io.ByteReader;
import com.github.suncloudsmoon.tilegame2d.io.ByteWriter;
import com.github.suncloudsmoon.tilegame2d.mslinks.data.CNRLink;
import com.github.suncloudsmoon.tilegame2d.mslinks.data.ItemID;
import com.github.suncloudsmoon.tilegame2d.mslinks.data.LinkFlags;
import com.github.suncloudsmoon.tilegame2d.mslinks.data.VolumeID;
import com.github.suncloudsmoon.tilegame2d.mslinks.extra.ConsoleData;
import com.github.suncloudsmoon.tilegame2d.mslinks.extra.ConsoleFEData;
import com.github.suncloudsmoon.tilegame2d.mslinks.extra.EnvironmentVariable;
import com.github.suncloudsmoon.tilegame2d.mslinks.extra.Stub;
import com.github.suncloudsmoon.tilegame2d.mslinks.extra.Tracker;
import com.github.suncloudsmoon.tilegame2d.mslinks.extra.VistaIDList;

public class ShellLink {

	public static final String VERSION = "1.0.6";

	private static Map<String, String> env = System.getenv();

	private static HashMap<Integer, Class<? extends Serializable>> extraTypes = new HashMap<>(
			Map.of(ConsoleData.signature, ConsoleData.class, ConsoleFEData.signature, ConsoleFEData.class,
					Tracker.signature, Tracker.class, VistaIDList.signature, VistaIDList.class,
					EnvironmentVariable.signature, EnvironmentVariable.class));

	private ShellLinkHeader header;
	private LinkTargetIDList idlist;
	private LinkInfo info;
	private String name;
	private String relativePath;
	private String workingDir;
	private String cmdArgs;
	private String iconLocation;
	private HashMap<Integer, Serializable> extra = new HashMap<>();

	private Path linkFileSource;

	public ShellLink() {
		header = new ShellLinkHeader();
		header.getLinkFlags().setIsUnicode();
	}

	public ShellLink(String file) throws IOException, ShellLinkException {
		this(Paths.get(file));
	}

	public ShellLink(File file) throws IOException, ShellLinkException {
		this(file.toPath());
	}

	public ShellLink(Path file) throws IOException, ShellLinkException {
		this(Files.newInputStream(file));
		linkFileSource = file.toAbsolutePath();
	}

	public ShellLink(InputStream in) throws IOException, ShellLinkException {
		try (ByteReader reader = new ByteReader(in)) {
			parse(reader);
		}
	}

	private void parse(ByteReader data) throws ShellLinkException, IOException {
		header = new ShellLinkHeader(data);
		LinkFlags lf = header.getLinkFlags();
		if (lf.hasLinkTargetIDList())
			idlist = new LinkTargetIDList(data);
		if (lf.hasLinkInfo())
			info = new LinkInfo(data);
		if (lf.hasName())
			name = data.readUnicodeString();
		if (lf.hasRelativePath())
			relativePath = data.readUnicodeString();
		if (lf.hasWorkingDir())
			workingDir = data.readUnicodeString();
		if (lf.hasArguments())
			cmdArgs = data.readUnicodeString();
		if (lf.hasIconLocation())
			iconLocation = data.readUnicodeString();

		while (true) {
			int size = (int) data.read4bytes();
			if (size < 4)
				break;
			int sign = (int) data.read4bytes();
			try {
				Class<?> cl = extraTypes.get(sign);
				if (cl != null)
					extra.put(sign,
							(Serializable) cl.getConstructor(ByteReader.class, int.class).newInstance(data, size));
				else
					extra.put(sign, new Stub(data, size, sign));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	private void serialize(OutputStream out) throws IOException {
		LinkFlags lf = header.getLinkFlags();
		ByteWriter bw = new ByteWriter(out);
		header.serialize(bw);
		if (lf.hasLinkTargetIDList())
			idlist.serialize(bw);

		if (lf.hasLinkInfo())
			info.serialize(bw);
		if (lf.hasName())
			bw.writeUnicodeString(name);
		if (lf.hasRelativePath())
			bw.writeUnicodeString(relativePath);
		if (lf.hasWorkingDir())
			bw.writeUnicodeString(workingDir);
		if (lf.hasArguments())
			bw.writeUnicodeString(cmdArgs);
		if (lf.hasIconLocation())
			bw.writeUnicodeString(iconLocation);

		for (Serializable i : extra.values())
			i.serialize(bw);

		bw.write4bytes(0);
		out.close();
	}

	public ShellLinkHeader getHeader() {
		return header;
	}

	public LinkInfo getLinkInfo() {
		return info;
	}

	public LinkInfo createLinkInfo() {
		info = new LinkInfo();
		header.getLinkFlags().setHasLinkInfo();
		return info;
	}

	public String getName() {
		return name;
	}

	public ShellLink setName(String s) {
		if (s == null)
			header.getLinkFlags().clearHasName();
		else
			header.getLinkFlags().setHasName();
		name = s;
		return this;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public ShellLink setRelativePath(String s) {
		if (s == null)
			header.getLinkFlags().clearHasRelativePath();
		else {
			header.getLinkFlags().setHasRelativePath();
			if (!s.startsWith("."))
				s = ".\\" + s;
		}
		relativePath = s;
		return this;
	}

	public String getWorkingDir() {
		return workingDir;
	}

	public ShellLink setWorkingDir(String s) {
		if (s == null)
			header.getLinkFlags().clearHasWorkingDir();
		else {
			header.getLinkFlags().setHasWorkingDir();
			s = Paths.get(s).toAbsolutePath().normalize().toString();
		}
		workingDir = s;
		return this;
	}

	public String getCMDArgs() {
		return cmdArgs;
	}

	public ShellLink setCMDArgs(String s) {
		if (s == null)
			header.getLinkFlags().clearHasArguments();
		else
			header.getLinkFlags().setHasArguments();
		cmdArgs = s;
		return this;
	}

	public String getIconLocation() {
		return iconLocation;
	}

	/**
	 * The shortcut can display an icon because there is an image set for that icon.
	 * 
	 * @param s location of the icon
	 * @return a ShellLink object
	 */
	public ShellLink setIconLocation(String s) {
		if (s == null)
			header.getLinkFlags().clearHasIconLocation();
		else {
			header.getLinkFlags().setHasIconLocation();
			String t = resolveEnvVariables(s);
			if (!Paths.get(t).isAbsolute())
				s = Paths.get(s).toAbsolutePath().toString();
		}
		iconLocation = s;
		return this;
	}

	public ConsoleData getConsoleData() {
		ConsoleData cd = (ConsoleData) extra.get(ConsoleData.signature);
		if (cd == null) {
			cd = new ConsoleData();
			extra.put(ConsoleData.signature, cd);
		}
		return cd;
	}

	public String getLanguage() {
		ConsoleFEData cd = (ConsoleFEData) extra.get(ConsoleFEData.signature);
		if (cd == null) {
			cd = new ConsoleFEData();
			extra.put(ConsoleFEData.signature, cd);
		}
		return cd.getLanguage();
	}

	public ShellLink setLanguage(String s) {
		ConsoleFEData cd = (ConsoleFEData) extra.get(ConsoleFEData.signature);
		if (cd == null) {
			cd = new ConsoleFEData();
			extra.put(ConsoleFEData.signature, cd);
		}
		cd.setLanguage(s);
		return this;
	}

	/**
	 * Saves the shortcut settings in the ShellLink object to a path in user
	 * directory.
	 * 
	 * @param path A directory in the user's storage
	 * @return a ShellLink object
	 * @throws IOException
	 */
	public ShellLink saveTo(String path) throws IOException {
		linkFileSource = Paths.get(path).toAbsolutePath().normalize();
		if (Files.isDirectory(linkFileSource))
			throw new IOException("path is directory!");

		if (!header.getLinkFlags().hasRelativePath()) {
			Path target = Paths.get(resolveTarget());
			Path origin = linkFileSource.getParent();
			if (target.getRoot().equals(origin.getRoot()))
				setRelativePath(origin.relativize(target).toString());
		}

		if (!header.getLinkFlags().hasWorkingDir()) {
			Path target = Paths.get(resolveTarget());
			if (!Files.isDirectory(target))
				setWorkingDir(target.getParent().toString());
		}

		serialize(Files.newOutputStream(linkFileSource));
		return this;
	}

	public String resolveTarget() {
		if (header.getLinkFlags().hasLinkTargetIDList() && idlist != null && idlist.isCorrect()) {
			return idlist.buildPath();
		}

		if (header.getLinkFlags().hasLinkInfo() && info != null) {
			CNRLink l = info.getCommonNetworkRelativeLink();
			String cps = info.getCommonPathSuffix();
			String lbp = info.getLocalBasePath();

			if (lbp != null) {
				String path = lbp;
				if (cps != null && !cps.equals("")) {
					if (path.charAt(path.length() - 1) != File.separatorChar)
						path += File.separatorChar;
					path += cps;
				}
				return path;
			}

			if (l != null && cps != null)
				return l.getNetName() + File.separator + cps;
		}

		if (linkFileSource != null && header.getLinkFlags().hasRelativePath() && relativePath != null)
			return linkFileSource.resolveSibling(relativePath).normalize().toString();

		return "<unknown>";
	}

	/**
	 * Set path of target file of directory. Function accepts local paths and
	 * network paths. Environment variables are accepted but resolved here and
	 * aren't kept in link.
	 */
	public ShellLink setTarget(String target) {
		target = resolveEnvVariables(target);

		Path tar = Paths.get(target).toAbsolutePath();
		target = tar.toString();

		if (target.startsWith("\\\\")) {
			int p1 = target.indexOf('\\', 2);
			int p2 = target.indexOf('\\', p1 + 1);

			LinkInfo inf = createLinkInfo();
			inf.createCommonNetworkRelativeLink().setNetName(target.substring(0, p2));
			inf.setCommonPathSuffix(target.substring(p2 + 1));

			if (Files.isDirectory(Paths.get(target)))
				header.getFileAttributesFlags().setDirecory();

			header.getLinkFlags().setHasExpString();
			extra.put(EnvironmentVariable.signature, new EnvironmentVariable().setVariable(target));

		} else
			try {
				header.getLinkFlags().setHasLinkTargetIDList();
				idlist = new LinkTargetIDList();
				String[] path = target.split("\\\\");
				idlist.add(new ItemID().setType(ItemID.TYPE_CLSID));
				idlist.add(new ItemID().setType(ItemID.TYPE_DRIVE).setName(path[0]));
				for (int i = 1; i < path.length; i++)
					idlist.add(new ItemID().setType(ItemID.TYPE_DIRECTORY).setName(path[i]));

				LinkInfo inf = createLinkInfo();
				inf.createVolumeID().setDriveType(VolumeID.DRIVE_FIXED);
				inf.setLocalBasePath(target);

				if (Files.isDirectory(tar))
					header.getFileAttributesFlags().setDirecory();
				else
					idlist.getLast().setType(ItemID.TYPE_FILE);

			} catch (ShellLinkException e) {
			}

		return this;
	}

	/**
	 * Creates a link to the target, but it does not save it.
	 * 
	 * @param target where the shortcut link should point towards!
	 * @return A ShellLink object
	 */
	public static ShellLink createLink(String target) {
		ShellLink sl = new ShellLink();
		sl.setTarget(target);
		return sl;
	}

	/**
	 * Creates a shortcut link (lnk file).This is equivalent to
	 * createLink(target).saveTo(linkpath).
	 * 
	 * @param target   Where the shortcut should point towards
	 * @param linkpath Where the shortcut should reside in (ex. Desktop);
	 * @return A ShellLink object
	 * @throws IOException
	 */
	public static ShellLink createLink(String target, String linkpath) throws IOException {
		return createLink(target).saveTo(linkpath);
	}

	private static String resolveEnvVariables(String path) {
		for (Map.Entry<String, String> i : env.entrySet()) {
			String p = i.getKey().replace("(", "\\(").replace(")", "\\)");
			String r = i.getValue().replace("\\", "\\\\");
			path = Pattern.compile("%" + p + "%", Pattern.CASE_INSENSITIVE).matcher(path).replaceAll(r);
		}
		return path;
	}
}
