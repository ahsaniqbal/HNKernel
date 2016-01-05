package com.dmlab.utils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
	public static List<String> readAllLines(String path) throws IOException {
		return Files.readAllLines(Paths.get(path), Charset.defaultCharset());
	}
}
