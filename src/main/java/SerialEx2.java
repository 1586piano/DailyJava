import java.io.*;
import java.util.ArrayList;

public class SerialEx2 {
    public static void main(String[] args) throws Exception {

        String fileName = "test.ser";
        // serialization
        TextFile file1 = new TextFile();
        //확장자는 .ser로 하는 것이 보통이지만, 제약은 없다.
        file1.setFileName("test1");
        file1.setFileBody("testFile1");
        //직렬화되지 않기  때문에 역직렬화하면, null이 된다.
        file1.setPassword("5678");

        TextFile file2 = new TextFile();
        file2.setFileName("test2");
        file2.setFileBody("testFile2");
        file2.setPassword("5678");

        ArrayList<TextFile> arrFile = new ArrayList<>();
        arrFile.add(file1);
        arrFile.add(file2);

        try (FileOutputStream fos = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(file1);
            oos.writeObject(file2);
            oos.writeObject(arrFile);
            oos.flush();
        }

        // deserialization
        ArrayList result = null;
        TextFile resFile1 = null;
        TextFile resFile2 = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            //readObjec의 반환 타입이 Object이기 때문에 형변환이 필요하다.
            //역직렬화하여 객체를 읽을 때는, 직렬화한 순서와 일치해야한다.
            //역직렬화할 때는 직려화 했을 때와 같은 클래스를 사용해야 한다.
            //클래스 이름이 같더라도 클래스의 내용이 변경되면 역직렬화가 실패하므로, 수동으로 클래스 UID를 지정할 수도 있다. (944p 참고)
            resFile1 = (TextFile) ois.readObject();
            resFile2 = (TextFile) ois.readObject();
            result = (ArrayList) ois.readObject();
            System.out.println(resFile1.toString());
            System.out.println(resFile2.toString());
            System.out.println(result.toString());
            ois.close();
        }
    }
}
