package WordsCounting;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;


public class BookReader extends Global_Count {

    public static void main(String[] args) throws IOException, InterruptedException {
        LogFile log = new LogFile("log.txt");

        System.out.println("Имя потока "+Thread.currentThread().getName());
        MultiCount book1 = new MultiCount("Thread-1","страдани",
                "txt\\Анна_Каренина.txt",log);

        MultiCount book2 = new MultiCount("Thread-2","страдани",
                "txt\\Братья_Карамазовы.txt",log);

        MultiCount book3 = new MultiCount("Thread-3","страдани",
                "txt\\Война_и_мир(том_1_и_2).txt",log);

        MultiCount book4 = new MultiCount("Thread-4","страдани",
                "txt\\Война_и_мир(том_3_и_4).txt",log);

        MultiCount book5 = new MultiCount("Thread-5","страдани",
                "txt\\Герой_нашего_времени.txt",log);

        MultiCount book6 = new MultiCount("Thread-6","страдани",
                "txt\\Горе_от_ума.txt",log);

        MultiCount book7 = new MultiCount("Thread-7","страдани",
                "txt\\Идиот_.txt",log);

        MultiCount book8 = new MultiCount("Thread-8","страдани",
                "txt\\Капитанская_дочка.txt",log);

        MultiCount book9 = new MultiCount("Thread-9","страдани",
                "txt\\Мёртвые_души.txt",log);

        MultiCount book10 = new MultiCount("Thread-10","страдани",
                "txt\\Тарас_Бульба.txt",log);

        book1.start();
        book2.start();
        book3.start();
        book4.start();
        book5.start();
        book6.start();
        book7.start();
        book8.start();
        book9.start();
        book10.start();

        // wait for threads to end
        try {
            book1.join();
            book2.join();
            book3.join();
            book4.join();
            book5.join();
            book6.join();
            book7.join();
            book8.join();
            book9.join();
            book10.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }

        Thread.sleep(5000);  // как узнать о завершении потоков??
            log.writeLog(getGlobal_count()," слова найдено всего");
            log.closeLog();

    }
}

