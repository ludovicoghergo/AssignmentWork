package ludovico.ghergo.utils;

import ludovico.ghergo.common.patients.PhysicalPatient;
import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.enums.PatientState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HospitalUtility
{
    public static ArrayList<PhysicalPatient> extractPatientList(String _patientList)
    {
        return Arrays.stream(_patientList.split(","))
                .map(shortCode -> PatientState.getStateByShortCode(shortCode))
                .map(PhysicalPatient::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Medicine> extractMedicineList(String _patientList)
    {
        return Arrays.stream(_patientList.split(","))
                .map(shortCode -> Medicine.getMedicineByShortCode(shortCode))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<PhysicalPatient>  initPatientByStates(ArrayList<PatientState> states)
    {
        return states.stream()
                .map(PhysicalPatient::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
