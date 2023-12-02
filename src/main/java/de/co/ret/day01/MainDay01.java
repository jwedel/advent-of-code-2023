package de.co.ret.day01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainDay01 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/java/de/co/ret/day01/day01.txt"));
        Calibration calibration = Calibration.fromLines(lines);
        System.out.println(calibration.getSum());
    }
}

