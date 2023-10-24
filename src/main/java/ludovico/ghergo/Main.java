package ludovico.ghergo;

import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.enums.PatientState;
import ludovico.ghergo.exceptions.InvalidParamException;
import ludovico.ghergo.modules.Hospital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static int NUM_PARAMS = 2;
    static ArrayList<PatientState> patientStates = new ArrayList<>();
    static ArrayList<Medicine> medicines = new ArrayList<>();

    public static void main(String[] args)
    {
        if (args.length ==  0)
        {
            System.out.println("F:0,H:0,D:0,T:0,X:0");
            return;
        }



        try
        {
            initParams(args[0]);
            if(args.length > 1)
            {
                initParams(args[1]);
            }
        }
        catch (InvalidParamException e)
        {
            System.out.println("Some errors has happened during parameters initialization.");
            return;
        }


        Hospital myHospital = new Hospital(patientStates,medicines);
        myHospital.run();
    }


    private static void initParams(String paramTxt)
            throws InvalidParamException
    {
        String[] values = paramTxt.split(",");
        if(values.length == 0)
        {
            throw new InvalidParamException("Param is empty");
        }

        if(Medicine.getMedicineByShortCode(values[0]) != Medicine.UNKNOWN)
        {
            initMedicines(values);
        }
        else if (PatientState.getStateByShortCode(values[0]) != PatientState.UNKNOWN)
        {
            initPatient(values);
        }
        else
        {
            throw new InvalidParamException("Invalid Param: must be a patient's state token or medicine's.");
        }

    }

    private static void initMedicines(String[] medicineString)
            throws InvalidParamException {
        if(medicines.size() > 0)
        {
            throw new InvalidParamException("You can't declare patients more than once.");
        }
         medicines = Arrays.stream(medicineString)
                .map(shortCode -> Medicine.getMedicineByShortCode(shortCode))
                .collect(Collectors.toCollection(ArrayList::new));

        if(medicines.contains(Medicine.UNKNOWN))
        {
            throw new InvalidParamException("Invalid Param: must be a patient's state token or medicine's.");
        }
    }




    private static void initPatient(String[] patientString) throws InvalidParamException {
        if(patientStates.size() > 0)
        {
            throw new InvalidParamException("You can't declare patients more than once.");
        }

        patientStates = Arrays.stream(patientString)
                .map(shortCode -> PatientState.getStateByShortCode(shortCode))
                .collect(Collectors.toCollection(ArrayList::new));

        if(patientStates.contains(Medicine.UNKNOWN))
        {
            throw new InvalidParamException("Invalid Param: must be a patient's state token or medicine's.");
        }
    }

}