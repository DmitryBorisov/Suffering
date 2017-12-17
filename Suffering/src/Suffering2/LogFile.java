package Suffering2;

// - - - Log - -

import java.io.*;

class LogFile {

    protected OutputStreamWriter oswriter;

    public LogFile(String logFileName) throws IOException {
        oswriter = new OutputStreamWriter(new FileOutputStream(logFileName));
    }

    void writeLog(String logRecord) throws IOException {
        oswriter.write(logRecord+ "\n");
    }

    void closeLog() throws IOException {
        oswriter.close();
    }
} // LogFile
