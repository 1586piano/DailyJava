# DailyJava
LST Tree
- Append Only : 업데이트 되는 부분을 찾아서 수정하는 것이 아니라 Append한다. 따라서 쓰기 지연 시간을 줄일 수 있다. 쓰기가 많은 작업에 적합하다.
- Immutable : Write로 생성되는 파일은 불변한다. 키가 중복되는 Entry는 최신 Entry의 값을 사용한다.
- MemTable : 새로운 data들은 작은 사이즈의 파일에 저장되기 전에 빠른 write 성능을 위해 memory buffer(Memtable)에 추가된다. MemTable의 일정 사이즈가 넘어서면 디스크로 flush
- 중복을 제거하기 위해 주기적으로 Compaction을 수행. 여러 파일을 병합하여 파일 수를 줄이고, 중복된 키나 삭제된 키 제거.
- 파일 내부는 정렬되어 있다?
- 참고
https://sukill.tistory.com/87
https://velog.io/@jinh2352/LSM-Tree-%EB%B6%88%EB%B3%80-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0
