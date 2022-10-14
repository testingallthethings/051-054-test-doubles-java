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

        RandomNumbersGenerator generator = new FakeRandomNumbersGenerator();

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        String number = licenceNumberGenerator.generate(applicant);

        assertEquals("MDB230920150000", number);
    }

    @Test
    void licenceNumberLengthAlwaysAtleast15() throws UnderAgeException, DuplicateApplicantException {
        MockValidApplicant applicant = new MockValidApplicant();
        applicant.setExpectedInitials("MB");
        applicant.setExpectedDateOfBirth("23092015");

        Logger logger = new DummyLogger();

        RandomNumbersGenerator generator = new FakeRandomNumbersGenerator();

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        assertEquals("MB2309201500000", licenceNumberGenerator.generate(applicant));

        applicant.setExpectedInitials("M");
        assertEquals("M23092015000000", licenceNumberGenerator.generate(applicant));
    }
}