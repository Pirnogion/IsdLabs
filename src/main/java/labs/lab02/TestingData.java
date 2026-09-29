package labs.lab02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public record TestingData(int[] intervals) {
    public static TestingData load(String fileName) throws IOException {
        Map<String, String> config = Files.lines(Path.of(fileName))
                .filter(line -> !line.isBlank())
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> parts[1].trim()
                ));

        int[] intervals = Arrays.stream(config.get("intervals").split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        return new TestingData(intervals);
    }
}