import java.io.Serializable;

class TextFile implements Serializable {

    //serialVersionUID 생성
    //private static final long serialVersionUID = -5672458131765837069L;
    private String fileName;
    private String fileBody;
    //password와 같이 보안상 직렬화가 되면 안되는 값의 경우 transient를 붙인다.
    //transient가 붙은 인스턴스변수의 값은 그 타입의 기본값으로 직렬화된다.
    transient private String password;

    //serialVerUID를 생성하고, 고의로 TextFile 클래스를 변경한다. SerialEx2 에서 역직렬화 부분만 수행했을 때, 정상적으로 동작한다.
    //SerialVerUID를 설정하지 않은 경우, 클래스 변경 후 역직렬화 수행 시에 아래와 같은 오류가 발생한다.
    //Exception in thread "main" java.io.InvalidClassException: serial.TextFile; local class incompatible: stream classdesc serialVersionUID = -5672458131765837069, local class serialVersionUID = 444006529815424756
    private String fileEnd;

    TextFile(){
        this.fileName = "제목없음";
        this.fileBody = "내용없음";
        this.password = "1234";
        this.fileEnd = "끝";
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setFileBody(String fileBody){
        this.fileBody = fileBody;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFileName(){
        return this.fileName.toString();
    }

    public String getFileBody(){
        return this.fileBody.toString();
    }

    public String getPassword(){ return this.password.toString();
    }

    @Override
    public String toString() {
        return "fileName : " + this.fileName + ", fileBody : " + this.fileBody + ", pw : " + this.password;
    }
}