package dto;

public class ContactVO extends User {
    private String name;
    private String age;
    private String call;
    private String email;
    private String gender;


    public ContactVO(String name, String age, String call, String email,String gender) {
        super(name);
        this.name = name;
        this.age = age;
        this.call = call;
        this.email = email;
        this.gender=gender;
    }
    public ContactVO( ) {

    }

    @Override
    public String toString() {
        String str="번호: "+ getId() + "이름:"+name+"/"+"나이:"+age+"/"+"전화번호:"+call+"/"+"이메일:"+email+"/"+"성:"+gender;
        return str+"\n";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getCall() {
        return call;
    }
    public void setCall(String call) {
        this.call = call;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}
