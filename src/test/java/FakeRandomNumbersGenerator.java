public class FakeRandomNumbersGenerator implements RandomNumbersGenerator{
    @Override
    public String generateRandomNumber(int count) {
        String rand = "";

        for (int i = 0; i < count; i++) {
            rand += "0";
        }

        return rand;
    }
}
