import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DepositBot extends Thread{

    private static HashMap account;

    public DepositBot(HashMap account){
        this.account = account;
    }

    public void run(){
        int balance = (int) account.get(0);
        balance -= 100;
        account.put(0, balance);
        System.out.println("출금");
    }
}
