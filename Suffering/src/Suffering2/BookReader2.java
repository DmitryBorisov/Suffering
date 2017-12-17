package Suffering2;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BookReader2  {

    static String file_path = "txt\\";

    static ArrayList<String> bookList = new ArrayList<>();
    static AtomicInteger global_count = new AtomicInteger();
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    static AtomicInteger th_counter = new AtomicInteger();

    public static void main(String[] args) throws IOException, InterruptedException {
        //log = new  LogFile("log.txt");
        BookListReader bl_reader = new BookListReader();
        bl_reader.readList(file_path+"00_Список.txt");
        System.out.println("Список файлов для анализа :");
        ArrayList<String> checkedList = new ArrayList<>();
        for (String s : bookList) {
            File file = new File(s);
            if (file.exists()){
                System.out.println(s+ " - ok!");
                checkedList.add(s);
            } else {
                System.out.println(s + " - не найден !");
            }
        }
        bookList = checkedList;

        MultiCount2 books = new MultiCount2("страдани",bookList);
        books.makeThreads();
        //Thread.sleep(2500);
        while (th_counter.get()>0); // ожидание окончания все процессов
        OutputStreamWriter oswriter = new OutputStreamWriter(new FileOutputStream("log.txt"));
        while (!queue.isEmpty()){
            oswriter.write(queue.take()+"\n");
        }
            oswriter.close();
        System.out.println("Всего в книгах найдено "+ global_count.get() );
    }


  static class BookListReader {
      void readList(String fname) {
          String listFileName = fname;
          BufferedInputStream bis = null;
          InputStreamReader isReader = null;
          BufferedReader bfReader = null;
          String line;
          try {
              bis = new BufferedInputStream(new FileInputStream(new File(listFileName)));
              isReader = new InputStreamReader(bis, "UTF-8");
              bfReader = new BufferedReader(isReader);

              while ((line = bfReader.readLine()) != null) {
                  bookList.add(file_path + line);
              }
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              try {
                  bis.close();
                  isReader.close();
                  bfReader.close();
              } catch (FileNotFoundException ex) {
                  System.out.println(ex.getMessage());
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          }
      } // readList()
  }  // class BookListReader
} // class BookReader2

