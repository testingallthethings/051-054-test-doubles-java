public class DrivingLicenceNumberGenerator {

    private RandomNumbersGenerator randomNumbersGenerator;
    private Logger logger;

    public DrivingLicenceNumberGenerator(RandomNumbersGenerator randomNumbersGenerator, Logger logger) {
        this.randomNumbersGenerator = randomNumbersGenerator;
        this.logger = logger;
    }

    public String generate(Applicant applicant) throws UnderAgeException, DuplicateApplicantException {
        if (applicant.holdsDrivingLicence()) {
            this.logger.Log("Applicant requested duplicate licence");
            throw new DuplicateApplicantException();
        }

        this.logger.Log("Applicant was too young to get a licence");
        throw new UnderAgeException();
    }
}
