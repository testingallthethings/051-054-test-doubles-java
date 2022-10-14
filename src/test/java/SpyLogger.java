public class SpyLogger implements Logger{

    private int logCallCount = 0;
    private String[] logInfos = new String[10];

    @Override
    public void Log(String info) {
        logInfos[logCallCount] = info;
        logCallCount++;
    }

    public int countLogCalls() {
        return logCallCount;
    }

    public String GetInfoForCall(int i) {
        return logInfos[i - 1];
    }
}
