package WordsCounting;

import java.io.*;


class Global_Count {
    static volatile private int global_count=0;
    synchronized static void add(){
        global_count++;
    }
    synchronized static int getGlobal_count(){
        return global_count;
    }
}

public class WordsCount extends Global_Count{
    private String fileName;
    private File file = null;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private InputStreamReader isReader;
    private BufferedReader bfReader=null;
    private String line="";
    private String lookFor;
    public int count=0;

    public WordsCount(String word, String s_fileName, LogFile log) {
        lookFor = word;
        fileName = s_fileName;

        try {
            file=new File(fileName);
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            isReader = new InputStreamReader(bis, "UTF-8");
            bfReader = new BufferedReader(isReader);

            int tmp;
            while ((line = bfReader.readLine()) != null) {
                for (String w : line.split("[ .,]")) {
                    if (w.contains(lookFor)) {
                        count++;
                            synchronized (Global_Count.class) {
                                add(); // global_count++
                            }
                        if ((tmp=getGlobal_count())%5==0) {
                            synchronized (LogFile.class){
                                log.writeLog(tmp, fileName);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bis.close();
                isReader.close();
                bfReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    } // constructor WordsCount

} // -- class WordsCount

// - - - Log - -
class LogFile {
    protected OutputStreamWriter oswriter;

    public LogFile(String logFileName)throws IOException{
            oswriter = new OutputStreamWriter(new FileOutputStream(logFileName));
    }
    synchronized void writeLog(int count, String fileName)throws IOException{
        oswriter.write(count + " " + fileName+"\n");
    }
     synchronized void  closeLog() throws IOException {
     oswriter.close();
     }
}
