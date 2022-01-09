# DailyJava
LST Tree
- Append Only : 업데이트 되는 부분을 찾아서 수정하는 것이 아니라 Append한다. 따라서 쓰기 지연 시간을 줄일 수 있다. 쓰기가 많은 작업에 적합하다.
- Immutable : Write로 생성되는 파일은 불변한다. 키가 중복되는 Entry는 최신 Entry의 값을 사용한다.
- MemTable : 새로운 data들은 작은 사이즈의 파일에 저장되기 전에 빠른 write 성능을 위해 memory buffer(Memtable)에 추가된다. MemTable의 일정 사이즈가 넘어서면 디스크로 flush
- Compaction & Merge : 중복을 제거하기 위해 주기적으로 Compaction을 수행. 여러 파일을 병합하여 파일 수를 줄이고, 중복된 키나 삭제된 키 제거.
- WAL(Write Ahead Log) : 장애 및 트랜잭션 복구를 위해 디스크에 저장하는 append-only 보조 자료구조이다. 데이터 레코드를 로그에 추가하고 메모리에 커밋한 다음, 작업 결과를 클라이언트에게 반환한다.
- 정렬된 LSM Tree : 데이터를 정렬해서 저장, 비정렬 LSM Tree : 삽입 순서대로 저장
- Read 시에 최신 값들이 존재하는 MemTable을 먼저 탐색하고, MemTable에서 탐색에 실패하면 Disk의 파일을 최신 순으로 검사한다.
