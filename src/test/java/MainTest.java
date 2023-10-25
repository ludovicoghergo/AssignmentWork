import ludovico.ghergo.Main;
import ludovico.ghergo.common.Patient;
import ludovico.ghergo.common.drugs.Medicine;
import ludovico.ghergo.common.patients.PhysicalPatient;
import ludovico.ghergo.enums.PatientState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest
{
    private PrintStream originalOut;
    private PrintStream originalErr;
    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out and System.err to custom streams
        originalOut = System.out;
        originalErr = System.err;
        outputStream = new ByteArrayOutputStream();
        errorStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @AfterEach
    public void restoreStreams() {
        // Restore original System.out and System.err
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testDeadDiabetics()
    {
        String[] params = new String[1];
        params[0] = "D,D";
        Main.main(params);

        String consoleOutput = outputStream.toString();

        assertEquals("F:0,H:0,D:0,T:0,X:2\n".trim(),consoleOutput.trim() );
    }

    @Test
    public void testCuredFever()
    {
        String[] params = new String[2];
        params[0] = "F";
        params[1] = "P";

        Main.main(params);

        String consoleOutput = outputStream.toString();

        assertEquals("F:0,H:1,D:0,T:0,X:0\n".trim(),consoleOutput.trim() );
    }

    @Test
    public void testNoParams()
    {
        Main.main(null);
        String consoleOutput = outputStream.toString();

        assertEquals("F:0,H:0,D:0,T:0,X:0\n".trim(),consoleOutput.trim() );
    }

    @Test
    public void testEmptyParams()
    {
        String[] param = new String[0];
        Main.main(param);
        String consoleOutput = outputStream.toString();

        assertEquals("F:0,H:0,D:0,T:0,X:0\n".trim(),consoleOutput.trim() );
    }

    @Test
    public void testWrongPatientParams()
    {
        String[] params = new String[1];
        params[0] = "F,K,M";
        Main.main(params);
        String errorOutput = errorStream.toString();

        assertEquals("Some errors has happened during parameters initialization.\n".trim(), errorOutput.trim() );
    }

    @Test
    public void testDoublePatientParams()
    {
        String[] params = new String[2];
        params[0] = "F,F";
        params[1] = "F,F";
        Main.main(params);
        String errorOutput = errorStream.toString();

        assertEquals("Some errors has happened during parameters initialization.\n".trim(), errorOutput.trim() );
    }


    @Test
    public void testWrongMedicineParams()
    {
        String[] params = new String[1];
        params[0] = "P,P,M";
        Main.main(params);
        String errorOutput = errorStream.toString();

        assertEquals("Some errors has happened during parameters initialization.\n".trim(), errorOutput.trim() );
    }
    @Test
    public void testDoubleMedicineParams()
    {
        String[] params = new String[2];
        params[0] = "P,P";
        params[1] = "P,P";
        Main.main(params);
        String errorOutput = errorStream.toString();

        assertEquals("Some errors has happened during parameters initialization.\n".trim(), errorOutput.trim() );
    }

}
