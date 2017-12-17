package Suffering2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiCount2 {
    private Thread thread;
    private WordsCount book;
    private String word;
    private ArrayList<String> fileNameList;


    MultiCount2(String v_word, ArrayList<String> v_list){
        this.word = v_word;
        this.fileNameList = v_list;
    }
    void makeThreads (){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //BookReader2.log.runLogging(); // запускаем логгер
        // создаем список потоков по списку файлов
        for (String fname : fileNameList) {
        executorService.submit(new Runnable() {

                public void run() {
                    run_reading(fname);
                    executorService.shutdown();
                }
            });
                BookReader2.th_counter.addAndGet(1);
        }
    }

    private void run_reading(String fileName) {
        int count=0;

        try {
            book = new WordsCount(word, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(fileName);
        System.out.println(" Найдено слов : "+ book.local_count.get());
        BookReader2.th_counter.decrementAndGet();
    }
}
