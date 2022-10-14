public class StubLicenceHolderApplicant implements Applicant{
    @Override
    public Boolean isOver17() {
        return true;
    }

    @Override
    public Boolean holdsDrivingLicence() {
        return true;
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
