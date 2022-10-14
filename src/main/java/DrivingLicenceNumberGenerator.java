public class DrivingLicenceNumberGenerator {

    private RandomNumbersGenerator randomNumbersGenerator;
    private Logger logger;

    public DrivingLicenceNumberGenerator(RandomNumbersGenerator randomNumbersGenerator, Logger logger) {
        this.randomNumbersGenerator = randomNumbersGenerator;
        this.logger = logger;
    }

    public String generate(Applicant applicant) throws UnderAgeException, DuplicateApplicantException {
        if (applicant.holdsDrivingLicence()) {
            throw new DuplicateApplicantException();
        }

        throw new UnderAgeException();
    }
}
