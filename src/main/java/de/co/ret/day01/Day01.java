package de.co.ret.day01;

import de.co.ret.common.FileHelper;
import de.co.ret.common.Sum;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day01 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> lines = FileHelper.readAoCFile("01");

        Calibration calibration = Calibration.fromLines(lines);

        System.out.println(Sum.ofInts(calibration.getValues()));
    }
}
