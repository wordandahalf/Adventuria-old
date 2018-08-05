package io.github.wordandahalf.adventuria.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectSerializerUtils {
	public static void writeToFile(Object o, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		
		out.writeObject(o);
		
		fos.close();
		out.close();
	}
}
