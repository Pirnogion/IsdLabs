package labs.lab03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public record Params3Data(
    double initialRating,
    double languageLevel,
    double totalPrograms,
    double[] programsLengths,
    double[] programsErrors,
    double targetProgramLength
) {
    public static Params3Data load(String fileName) throws IOException {
        Map<String, String> config = Files.lines(Path.of(fileName))
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> parts[1].trim()
                ));

        double initialRating = Double.parseDouble(config.get("initial_rating"));
        double languageLevel = Double.parseDouble(config.get("language_level"));
        int totalPrograms = Integer.parseInt(config.get("total_programs"));
        double[] programsLengths = parseDoubleArray(config.get("programs_lengths"));
        double[] programsErrors = parseDoubleArray(config.get("programs_errors"));
        double targetProgramLength = Double.parseDouble(config.get("target_program_length"));

        return new Params3Data(
            initialRating,
            languageLevel,
            totalPrograms,
            programsLengths,
            programsErrors,
            targetProgramLength
        );
    }

    private static double[] parseDoubleArray(String value) {
        return Arrays.stream(value.split(","))
            .map(String::trim)
            .mapToDouble(Double::parseDouble)
            .toArray();
    }
}