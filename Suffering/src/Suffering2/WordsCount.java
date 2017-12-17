package Suffering2;

import WordsCounting.BookReader;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static Suffering2.BookReader2.global_count;

public class WordsCount {
    private String fileName;
    private File file = null;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private InputStreamReader isReader;
    private BufferedReader bfReader=null;
    private String line="";
    private String lookFor;
    public AtomicInteger local_count = new AtomicInteger();


    public WordsCount(String word, String s_fileName) {
        lookFor = word;
        fileName = s_fileName;

//        count.addAndGet(1);
        //System.out.println("WordsCount.constructor for " + fileName);
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
                        local_count.addAndGet(1);
                        tmp= global_count.addAndGet(1);
                        if (tmp%5==0) {
                            BookReader2.queue.add(global_count.get()+"  "+ fileName);
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


