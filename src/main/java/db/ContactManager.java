package db;

import dto.ContactVO;

import java.util.ArrayList;


public class ContactManager {



    private static ArrayList<ContactVO> list = new ArrayList<>();

    static
    {
        if(list == null)
        {
            list = new ArrayList<>();

        }
    }
    public  static String searchAll() {
        list.add(new ContactVO("훌랄라","23","010-6278-5959","ibw1953@naver.com","남"));
        list.add(new ContactVO("정상엽","24","010-7382-2582","jsy2164@naver.com","남"));
        list.add(new ContactVO("이규민","25","010-8493-3694","igy3275@naver.com","남"));
        return list.toString();

    }


    public static String insert(ContactVO vo) {
        String a;
        if(list.add(vo)) {
            a="등록성공";
        }else {
            a="등록실패";
        }
        return a;
    }
    public static String searchByName(String name){
        String s=null;
        for(int i=0;i<list.size();i++) {
            ContactVO vo = list.get(i);
            if(list.get(i).getName().equals(name)) {
                s=list.get(i).toString();
                break;
            }else {
                s="존재 하지 않다";
            }
        }
        return s;

    }


    public static String deleteByName(String name) {
        String s=null;
        for(int i=0;i<list.size();i++) {
            ContactVO vo = list.get(i);
            if(list.get(i).getName().equals(name)) {
                list.remove(i);
                s="삭제됨";
                break;
            }else {
                s="존재하지 않습니다";
            }
        }
        return s;
    }
}


