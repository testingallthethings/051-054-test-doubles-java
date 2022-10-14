public class MockRandomNumbersGenerator implements RandomNumbersGenerator{

    private String[] expectedNumbers = new String[10];

    public void setExpectedNumbers(int given, String expected) {
        expectedNumbers[given] = expected;
    }

    @Override
    public String generateRandomNumber(int count) {
        return expectedNumbers[count];
    }
}
