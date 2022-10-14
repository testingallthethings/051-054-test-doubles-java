import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrivingLicenceNumberGeneratorTest {

    @Test
    void underageApplicantCannotGetALicenceNumber() {
        Applicant applicant = new StubUnderAgeApplicant();

        Logger logger = new DummyLogger();
        RandomNumbersGenerator generator = new DummyRandomNumbersGenerator();

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        assertThrows(UnderAgeException.class, () -> {
            licenceNumberGenerator.generate(applicant);
        });
    }

    @Test
    void licenceHolderCannotGetSecondLicence() {
        Applicant applicant = new StubLicenceHolderApplicant();

        Logger logger = new DummyLogger();
        RandomNumbersGenerator generator = new DummyRandomNumbersGenerator();

        DrivingLicenceNumberGenerator licenceNumberGenerator = new DrivingLicenceNumberGenerator(generator, logger);

        assertThrows(DuplicateApplicantException.class, () -> {
            licenceNumberGenerator.generate(applicant);
        });
    }
}