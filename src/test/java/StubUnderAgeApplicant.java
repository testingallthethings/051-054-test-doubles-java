public class StubUnderAgeApplicant implements Applicant {
    @Override
    public Boolean isOver17() {
        return false;
    }

    @Override
    public Boolean holdsDrivingLicence() {
        return false;
    }

    @Override
    public String getInitials() {
        return null;
    }

    @Override
    public String getDateOfBirth() {
        return null;
    }
}
