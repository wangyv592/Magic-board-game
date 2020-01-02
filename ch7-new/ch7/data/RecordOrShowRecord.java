package ch7.data;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecordOrShowRecord{

    public boolean saveRecord(String name, int time) throws URISyntaxException {
        URL url = this.getClass().getResource("/resource/record.txt");
        String path = url.getFile().replace("ch7-new.jar!/", "").replace("file:/", "");
        System.out.println(path);
        File file = new File(path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(name + " " + time);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String[][] getRecord() {
        URL url = this.getClass().getResource("/resource/record.txt");
        String path = url.getFile().replace("ch7-new.jar!/", "").replace("file:/", "");
        System.out.println(path);
        File file = new File(path);
        InputStreamReader reader = null; // 建立一个输入流对象reader
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            List<String> tmpList = new ArrayList<>();
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
                tmpList.add(line);
            }
            String[][] recordList = new String[tmpList.size() + 1][tmpList.size() + 1];
            for (int i = 0; i < tmpList.size(); i++) {
                if (tmpList.get(i) != null) {
                    String[] tmp = tmpList.get(i).split(" ");
                    if (tmp.length == 2) {
                        recordList[i][0] = tmp[0];
                        recordList[i][1] = tmp[1];
                    }
                }
            }
            return recordList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0][0];
    }

    public static void main(String[] args) throws URISyntaxException {
        RecordOrShowRecord recordOrShowRecord = new RecordOrShowRecord();
        recordOrShowRecord.saveRecord("dasd", 123);
        recordOrShowRecord.saveRecord("dasd", 123);
        recordOrShowRecord.saveRecord("dasd", 123);
        recordOrShowRecord.saveRecord("dasd", 123);
        recordOrShowRecord.getRecord();
    }
}