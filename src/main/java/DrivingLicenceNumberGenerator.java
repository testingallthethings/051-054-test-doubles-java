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

        if (!applicant.isOver17()) {
            this.logger.Log("Applicant was too young to get a licence");
            throw new UnderAgeException();
        }

        String licence = applicant.getInitials() + applicant.getDateOfBirth();
        int numOfRands = 15 - licence.length();

        return  licence + this.randomNumbersGenerator.generateRandomNumber(numOfRands);
    }
}
