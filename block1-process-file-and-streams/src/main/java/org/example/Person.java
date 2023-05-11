package org.example;

public class Person {
    private String name;
    private String town;
    private byte age;

    public Person(String name, String town, byte age) {
        setName(name);
        setTown(town);
        setAge(age);
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();

        t.append("Su nombre es : "+name+"\n");
        t.append("Su ciudad es : "+town+"\n");
        t.append("Su edad es : "+age+"\n");
        return t.toString();
    }
}
