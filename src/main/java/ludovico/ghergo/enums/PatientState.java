package ludovico.ghergo.enums;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum PatientState
{
    FEVER("F"),
    HEALTHY("H"),
    DIABETES("D"),
    DIABETES_CONTROLLED("DC"),
    TUBERCULOSIS("T"),
    DEAD("X"),
    UNKNOWN("#");

    private String shortCode;
    private static Map<String,PatientState> shortCodeToEnum = new HashMap<>();

    PatientState(String _short)
    {
        this.shortCode = _short;
    }

    static
    {
        Arrays  .stream(PatientState.values())
                .forEach(state ->
                        shortCodeToEnum.put(state.getShortCode(),state)
                );
    }

    public static PatientState getStateByShortCode(String _shortCode)
    {
        return  shortCodeToEnum.getOrDefault(_shortCode,UNKNOWN);
    }

    public String getShortCode()
    {
        return shortCode;
    }
}
