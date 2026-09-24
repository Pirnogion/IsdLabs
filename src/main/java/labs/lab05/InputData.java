package labs.lab05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputData {

    public int Q;
    public int N;
    public double TvMin, TvMax, TvDop;
    public int Ntv;
    public double TpMin, TpMax, TpDop;
    public int Ntp;
    public double baseCriterion;

    public static InputData load(String fileName) throws IOException {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) map.put(parts[0].trim(), parts[1].trim());
            }
        }

        InputData d = new InputData();
        d.Q             = Integer.parseInt(map.get("Q"));
        d.N             = Integer.parseInt(map.get("N"));
        d.TvMin         = Double.parseDouble(map.get("Tv_min"));
        d.TvMax         = Double.parseDouble(map.get("Tv_max"));
        d.TvDop         = Double.parseDouble(map.get("Tv_dop"));
        d.Ntv           = Integer.parseInt(map.get("N_tv"));
        d.TpMin         = Double.parseDouble(map.get("Tp_min"));
        d.TpMax         = Double.parseDouble(map.get("Tp_max"));
        d.TpDop         = Double.parseDouble(map.get("Tp_dop"));
        d.Ntp           = Integer.parseInt(map.get("N_tp"));
        d.baseCriterion = Double.parseDouble(map.get("base_criterion"));
        return d;
    }
}