import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {
    public static void main(String[] args){

        ObjectStreamClass c = ObjectStreamClass.lookup(TextFile.class);
        //serialVerUID 생성
        long serialID = c.getSerialVersionUID();
        System.out.println("serialId : " + serialID);

        String fileName = "TestFile.ser";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(bos);

            TextFile t1 = new TextFile();
            TextFile t2 = new TextFile();

            t1.setFileName("t1");
            t1.setFileBody("test2");
            t2.setFileBody("test2");
            t2.setFileName("t1");

            ArrayList<TextFile> arrText = new ArrayList<>();
            arrText.add(t1);
            arrText.add(t2);

            out.writeObject(t1);
            out.writeObject(t2);
            out.writeObject(arrText);
            out.close();
            System.out.println("직렬화끝~");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
