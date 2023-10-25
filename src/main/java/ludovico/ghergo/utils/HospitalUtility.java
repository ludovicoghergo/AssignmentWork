package ludovico.ghergo.utils;

import ludovico.ghergo.common.patients.PhysicalPatient;
import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.enums.PatientState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HospitalUtility
{
    private static final SecureRandom random = new SecureRandom();
    private static final int RANDOM_SIZE = 1_000_000;
    private static final int CHOSEN_NUMBER = 17;
    private static final Logger LOGGER = LogManager.getLogger(HospitalUtility.class);
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
                .peek(patient -> LOGGER.info("Created new Patient with state:" + patient.getState()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean isFlyingSpaghettiMonsterAppeared()
    {
        return CHOSEN_NUMBER == random.nextInt(RANDOM_SIZE+1);
    }


}
