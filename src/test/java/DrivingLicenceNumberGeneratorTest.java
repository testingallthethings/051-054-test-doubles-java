import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrivingLicenceNumberGeneratorTest {

    @Test
    void underageApplicantCannotGetALicenceNumber() {
        Applicant applicant = new StubUnderAgeApplicant();

        SpyLogger logger = new SpyLogger();
        RandomNumbersGenerator generator = new DummyRandomNumbersGenerator();

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        assertThrows(UnderAgeException.class, () -> {
            licenceNumberGenerator.generate(applicant);
        });

        assertEquals(1, logger.countLogCalls());
        assertTrue(logger.GetInfoForCall(1).contains("too young"));
    }

    @Test
    void licenceHolderCannotGetSecondLicence() {
        Applicant applicant = new StubLicenceHolderApplicant();

        SpyLogger logger = new SpyLogger();
        RandomNumbersGenerator generator = new DummyRandomNumbersGenerator();

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        assertThrows(DuplicateApplicantException.class, () -> {
            licenceNumberGenerator.generate(applicant);
        });

        assertEquals(1, logger.countLogCalls());
        assertTrue(logger.GetInfoForCall(1).contains("duplicate"));
    }

    @Test
    void validApplicantGetsLicenceNumber() throws UnderAgeException, DuplicateApplicantException {
        MockValidApplicant applicant = new MockValidApplicant();
        applicant.setExpectedInitials("MDB");
        applicant.setExpectedDateOfBirth("23092015");

        Logger logger = new DummyLogger();

        MockRandomNumbersGenerator generator = new MockRandomNumbersGenerator();
        generator.setExpectedNumbers(4,"1234");

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        String number = licenceNumberGenerator.generate(applicant);

        assertEquals("MDB230920151234", number);
    }

    @Test
    void licenceNumberLengthAlwaysAtleast15() throws UnderAgeException, DuplicateApplicantException {
        MockValidApplicant applicant = new MockValidApplicant();
        applicant.setExpectedInitials("MB");
        applicant.setExpectedDateOfBirth("23092015");

        Logger logger = new DummyLogger();

        MockRandomNumbersGenerator generator = new MockRandomNumbersGenerator();
        generator.setExpectedNumbers(5, "12345");
        generator.setExpectedNumbers(6, "123456");

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        assertEquals("MB2309201512345", licenceNumberGenerator.generate(applicant));

        applicant.setExpectedInitials("M");
        assertEquals("M23092015123456", licenceNumberGenerator.generate(applicant));

    }
}