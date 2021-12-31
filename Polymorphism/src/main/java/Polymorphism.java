//다형성이란 여러 형태를 가질 수 있는 능력을 의미한다.
//조상클래스타입의 참조변수로 자손클래스의 인스턴스를 참조할 수 있도록 한다. 반대는 안됨. 자손 멤버가 더 많기 때문.
//다만, 조상 클래스타입의 참조변수로 자손 클래스의 인스턴스를 생성하면, 조상 클래스타입의 멤버만 사용 가능하다.
import java.util.Vector;

//Airpod, Ipad, Macbook을 Product 클래스의 자손타입으로 생성하여 Product클래스의 참조변수면 어느 것이나 매개변수로 활용할 수 있도록 한다.
class Product{
    int price;
    int bonusPoint;

    Product(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }

    //위에 int형 매개변수를 받는 생성자를 생성했다.
    Product(){}
}

//Product클래스 상속
class Airpod extends Product{
    //매개변수 100으로 부모 생성자 Product(int price)실행
    Airpod(){ super(250); }
    public String toString() { return "Airpod"; }
}

class Ipad extends Product{
    //매개변수 100으로 부모 생성자 Product(int price)실행
    Ipad(){ super(800); }
    public String toString() { return "Ipad"; }
}

class Macbook extends Product{
    //매개변수 100으로 부모 생성자 Product(int price)실행
    Macbook(){ super(2000); }
    public String toString() { return "Macbook"; }
}

class Buyer{
    int money = 1500;
    int bonusPoint = 0;
    //Product[] item = new Product[10];//구입 물품 보관 배열
    //int i = 0; //배열 카운트

    //배열은 크기가 한정되어 있으므로 Vector로 관리(동적배열)
    Vector item = new Vector();

    //buy(Ipad ipad), buy(Airpod airpod), buy(Macbook macbook) 으로 따로 만들지 않고, 이들의 공통 조상인 Product로 생성.
    void buy( Product p ){
        if( (money+bonusPoint)<p.price ){
            System.out.println("Not enough money!");
            return;
        }

        money-=p.price;
        bonusPoint+=p.bonusPoint;
        item.add(p);
        //getClass 메서드로 인스턴스 클래스 이름을 얻을 수 있으며, 필요한 경우, instanceOf 연산자를 통해 인스턴스를 확인할 수 있다.
        System.out.println("인스턴스 클래스 이름 : + "+p.getClass());
        System.out.println(p+" is yours! ");
    }

    void summary(){
        int sum = 0;
        String itemList = "";

        if(item.isEmpty()){
            System.out.println("You've nothing");
            return;
        }

        for(int i=0; i<item.size(); i++){
            Product p = (Product)item.get(i);
            sum += p.price;
            itemList += (i==0) ? p : ", " + p;
        }

        System.out.println("You spent " + sum + "dollars");
        System.out.println("Your items : " + itemList);

    }

    void refund(Product p){
        if(item.remove(p)){
            money += p.price;
            bonusPoint -= p.bonusPoint;
            System.out.println("You returned " + p);
        }
        else{
            System.out.println("You didn't buy the item");
        }

    }
}

public class Polymorphism {
    public static void main(String[] args) {

        Buyer songhee = new Buyer();

        //songhee.buy(new Airpod()); f로 하면 벡터 참조변수를 알 수 없기 때문에 아래와 같이 객체 생성
        Airpod airpod = new Airpod();
        Ipad ipad = new Ipad();
        Macbook macbook = new Macbook();

        songhee.buy(airpod);
        songhee.buy(ipad);
        songhee.buy(macbook);
        songhee.summary();

        songhee.refund(ipad);
        songhee.summary();
    }
}