package labs.lab03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public record Params1Data(
    double maxTargets,
    double samplingRate,
    double inputParams,
    double outputParams,
    double languageLevel
) {
    public static Params1Data load(String fileName) throws IOException {
        Map<String, Double> config = Files.lines(Path.of(fileName))
            .map(line -> line.split("=", 2))
            .collect(Collectors.toMap(
                    parts -> parts[0],
                    parts -> Double.parseDouble(parts[1])
            ));

        return new Params1Data(
            config.get("max_targets"),
            config.get("sampling_rate"),
            config.get("input_params"),
            config.get("output_params"),
            config.get("language_level")
        );
    }
}