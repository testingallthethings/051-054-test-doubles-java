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
}