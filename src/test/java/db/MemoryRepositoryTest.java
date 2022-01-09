package db;

import dto.ContactVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


/*
   author: baole
   date: 2022-01-09
*/
class MemoryRepositoryTest {

    private MemoryRepository memoryRepository;
    @BeforeEach
    public void db(){
        memoryRepository = new MemoryRepository();
        memoryRepository.save(new ContactVO("유닛3","11","010-1111-2222","junit1@naver.com","남"));
        memoryRepository.save(new ContactVO("유닛3","22","010-3333-4444","junit2@naver.com","남"));
    }
    @Test
    public void 전체데이터(){
        int size = memoryRepository.findAll().size();
        Assertions.assertEquals(size, 6, "초기 db갯수");
    }
    @Test
    public void 데이터저장(){
        Assertions.assertEquals(memoryRepository.findAll().size(), 6);
    }
    @Test
    public void 데이터조회(){
        List<ContactVO> users = memoryRepository.findByName("이규민");
        Assertions.assertEquals(2, users.size(), " 데이터 중복시 데이터를 여러개 가져오는가?");
        users.forEach(it -> Assertions.assertEquals("이규민", it.getName(), "중복된 두 개의 데이터 이름이 모두 이규민인가?"));
    }
    @Test
    public void 데이터삭제(){
        List<ContactVO> users =  memoryRepository.findByName("이규민");
        users.forEach(it -> memoryRepository.deleteById(it.getId()));
        Assertions.assertEquals(memoryRepository.findAll().size(), 4, "데이터 2개 삭제시 4개가 되는가?");
    }
}