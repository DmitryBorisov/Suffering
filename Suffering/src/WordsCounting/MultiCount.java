package WordsCounting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiCount extends Thread{
    private Thread thread;
    private WordsCount book;
    private String word;
    private String fileName;
    private String threadName;
    private int count=0;
    LogFile log;


    MultiCount(String th_name, String v_word, String v_fileName,LogFile v_log){
        this.threadName = th_name;
        this.word = v_word;
        this.fileName = v_fileName;
        this.log = v_log;
    }

    public void run() {
        try {
            book = new WordsCount(word, fileName, log);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        count = book.count;
        System.out.print("Thread " +  threadName + " exiting.");
        System.out.print(fileName);
        System.out.println(" Найдено слов : "+ count);

    }

    public void start () {
        System.out.println("Starting " +  threadName + "..." );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start ();
        }
    }




}
