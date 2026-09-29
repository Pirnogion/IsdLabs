package labs.lab03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public record Params2Data(
    double maxTargets,
    double samplingRate,
    double inputParams,
    double outputParams,
    double languageLevel,
    double writtenPrograms,
    double incorrectPrograms,
    double workingHours
) {
    public static Params2Data load(String fileName) throws IOException {
        Map<String, Double> config = Files.lines(Path.of(fileName))
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> Double.parseDouble(parts[1])
                ));

        return new Params2Data(
                config.get("max_targets"),
                config.get("sampling_rate"),
                config.get("input_params"),
                config.get("output_params"),
                config.get("language_level"),
                config.get("written_programs"),
                config.get("incorrect_programs"),
                config.get("working_hours")
        );
    }
}