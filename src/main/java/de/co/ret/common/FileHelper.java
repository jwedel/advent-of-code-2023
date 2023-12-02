package de.co.ret.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {
    public static List<String> readAoCFile(String day) throws IOException {
        return Files.readAllLines(Paths.get("src/main/java/de/co/ret/day%s/day%s.txt".formatted(day, day)));
    }
}
