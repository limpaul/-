package db;

import dto.ContactVO;

public class MemoryRepository extends MemoryDbAbImpl<ContactVO>{
    {
        save(new ContactVO("훌랄라","23","010-6278-5959","ibw1953@naver.com","남"));
        save(new ContactVO("정상엽","24","010-7382-2582","jsy2164@naver.com","남"));
        save(new ContactVO("이규민","25","010-8493-3694","igy3275@naver.com","남"));
        save(new ContactVO("이규민","25","010-8493-3694","igy3275@naver.com","남"));
    }
}
