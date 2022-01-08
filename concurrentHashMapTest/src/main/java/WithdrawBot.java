import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class WithdrawBot extends Thread{

    private static ConcurrentHashMap account;

    public WithdrawBot(ConcurrentHashMap account){
        this.account = account;
    }

    public void run(){
        int balance = (int) account.get(0);
        balance += 100;
        account.put(0, balance);
        System.out.println("100 입금");
    }
}
