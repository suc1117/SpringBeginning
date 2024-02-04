package cs.spring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {  //html의 input에 있는 name 키 값에 대해 동작
        this.name = name;
    }
}
