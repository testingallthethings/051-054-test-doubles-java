public class MockValidApplicant implements Applicant {

    private String expectedDateOfBirth = "";
    private String expectedInitials = "";

    @Override
    public Boolean isOver17() {
        return true;
    }

    @Override
    public Boolean holdsDrivingLicence() {
        return false;
    }

    @Override
    public String getInitials() {
        return expectedInitials;
    }

    @Override
    public String getDateOfBirth() {
        return expectedDateOfBirth;
    }

    public void setExpectedDateOfBirth(String expectedDateOfBirth) {
        this.expectedDateOfBirth = expectedDateOfBirth;
    }

    public void setExpectedInitials(String expectedInitials) {
        this.expectedInitials = expectedInitials;
    }
}
