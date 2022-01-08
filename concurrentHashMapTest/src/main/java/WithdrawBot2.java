import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class WithdrawBot2 extends Thread{

    private static ConcurrentHashMap account;

    public WithdrawBot2(ConcurrentHashMap account){
        this.account = account;
    }

    public void run(){
        int balance = (int) account.get(0);
        balance += 200;
        account.put(0, balance);
        System.out.println("200 입금");
    }
}
