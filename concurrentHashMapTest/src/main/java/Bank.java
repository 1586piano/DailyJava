import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Bank{

    //HashTable : 메서드 전체에 synchronized. 메서드가 실행중이라면, 메서드 자체에 lock이 걸리기 때문에 병목 가능성이 높아짐.
    //private static HashMap<Integer, Integer> account = new HashMap<>();
    //thread-safe 하지 않음. 빠름.
    //입금 순서와 관계없이 모든 쓰레드들이 계속 입금을 시도한다. 잔액은 맞았지만, 순서가 달라짐
    private static ConcurrentHashMap<Integer, Integer> account = new ConcurrentHashMap<Integer, Integer>();
    //thread-safe함. 빠름. 버킷별로 lock. lock이 잡힌 버킷에 포함되지 않은 데이터 영역에 대한 수정은 영향을 받지 않음.
    //입금을 시도한 쓰레드 순서대로 작업을 수행한다.

    public static void main(String[] args) throws InterruptedException {

        account.put(0,0);

        //입/출금은 하나의 트랙잭션이어야 한다. 메소드에 synchronized가 되어야 할 듯
        //쭉 입금해본다잉~
        for(int i=0; i<20; i++) {
            //DepositBot depositBot = new DepositBot(account);
            WithdrawBot withdrawBot = new WithdrawBot(account);
            WithdrawBot2 withdrawBot2 = new WithdrawBot2(account);
            //depositBot.start();
            withdrawBot.start();
            withdrawBot2.start();
            //Thread.sleep(100);
        }

        Thread.sleep(500);
        System.out.println("잔액 : "+account.get(0));
    }
}
