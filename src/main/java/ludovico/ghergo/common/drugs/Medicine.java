package ludovico.ghergo.common.drugs;

import ludovico.ghergo.common.Drug;
import ludovico.ghergo.common.Patient;
import ludovico.ghergo.enums.PatientState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Medicine implements Drug
{
    ASPIRIN("As")
    {
        @Override
        public PatientState apply(Patient _patient)
        {
            if(_patient.hasTakenDrug(PARACETAMOL))
            {
                return PatientState.DEAD;
            }

            return _patient.getState() == PatientState.FEVER ? PatientState.HEALTHY : _patient.getState();
        }
    },
    ANTIBIOTIC("An")
    {
        @Override
        public PatientState apply(Patient _patient)
        {
            if(_patient.hasTakenDrug(INSULIN) && _patient.getState() == PatientState.HEALTHY)
            {
                return PatientState.FEVER;
            }
            return _patient.getState() == PatientState.TUBERCULOSIS ? PatientState.HEALTHY : _patient.getState();
        }
    },
    INSULIN("I")
            {
                @Override
                public PatientState apply(Patient _patient)
                {
                    if(_patient.hasTakenDrug(ANTIBIOTIC) && _patient.getState() == PatientState.HEALTHY)
                    {
                        return PatientState.FEVER;
                    }
                    return _patient.getState() == PatientState.DIABETES ? PatientState.DIABETES_CONTROLLED : _patient.getState();
                }
            },
    PARACETAMOL("P")
            {
                @Override
                public PatientState apply(Patient _patient)
                {
                    if(_patient.hasTakenDrug(ASPIRIN))
                    {
                        return PatientState.DEAD;
                    }

                    return _patient.getState() == PatientState.FEVER ? PatientState.HEALTHY : _patient.getState();
                }
            },
    UNKNOWN("#");

    private String shortCode;
    private static Map<String,Medicine> shortCodeToEnum = new HashMap<>();

    Medicine(String _shortCode)
    {
        this.shortCode = _shortCode;
    }

    static
    {
        Arrays.stream(Medicine.values())
                .forEach(medicine ->
                        shortCodeToEnum.put(medicine.getShortCode(),medicine)
                );
    }

    public static Medicine getMedicineByShortCode(String _shortCode)
    {
        return  shortCodeToEnum.getOrDefault(_shortCode,UNKNOWN);
    }
//    public PatientState applyMedicine(PatientState _patient,String _shortCode)
//    {
//        return  shortCodeToEnum.getOrDefault(_shortCode,UNKNOWN);
//    }

    public String getShortCode() {
        return shortCode;
    }
}
