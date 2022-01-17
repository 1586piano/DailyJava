# DailyJava
### LST Tree
- Append Only : 업데이트 되는 부분을 찾아서 수정하는 것이 아니라 Append한다. 따라서 쓰기 지연 시간을 줄일 수 있다. 쓰기가 많은 작업에 적합하다.
- Immutable : Write로 생성되는 파일은 불변한다. 키가 중복되는 Entry는 최신 Entry의 값을 사용한다.
- MemTable : 새로운 data들은 작은 사이즈의 파일에 저장되기 전에 빠른 write 성능을 위해 memory buffer(Memtable)에 추가된다. MemTable의 일정 사이즈가 넘어서면 디스크로 flush
- Compaction & Merge : 중복을 제거하기 위해 주기적으로 Compaction을 수행. 여러 파일을 병합하여 파일 수를 줄이고, 중복된 키나 삭제된 키 제거.
- WAL(Write Ahead Log) : 장애 및 트랜잭션 복구를 위해 디스크에 저장하는 append-only 보조 자료구조이다. 데이터 레코드를 로그에 추가하고 메모리에 커밋한 다음, 작업 결과를 클라이언트에게 반환한다.
- 정렬된 LSM Tree : 데이터를 정렬해서 저장, 비정렬 LSM Tree : 삽입 순서대로 저장
- Read 시에 최신 값들이 존재하는 MemTable을 먼저 탐색하고, MemTable에서 탐색에 실패하면 Disk의 파일을 최신 순으로 검사한다.

### SOLID
결국, 클래스 내부적으로 응집도는 높이고, 타 클래스들 간의 결합도를 낮추는 관점에서 바라보면 된다.
- SRP : Single Responsibility Principle 단일 책임 원칙. 한 클래스는 하나의 책임만 가져야 한다.
- OCP : Open/Clsed Priciple 개방-폐쇄 원칙. 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- LSP : Liskov Substitution Principle 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다. ex) 아빠 아들과 같은 상속 관계 X. 포유류 고래와 같은 관계가 더 정확한 예시가 된다. 하위 타입의 인스턴스는 상위 인스턴스의 한 종류이면서 더 확장된 개념이다. 하위 인스턴스는 상위 인스턴스의 역할을 대체할 수 있다. 
- ISP : Interface Segregation Principle 특정 클래이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다. ex) 물고기가 날 필요가 없다. 필요한 것만 구현하여 사용하도록 하여 행동을 제한한다.
- DIP : Dependency Inversion Principle 프로그래머는 추상화에 의존해야지 구체화에 의존하면 안된다. 의존성 주입은 이 원칙을 따르는 방법 중 하나이다. ex) 자신보다 더 구체적인 것에 의존하면 안된다. 자신보다 변화기 쉬운 것에 의존해서는 안된다. 
