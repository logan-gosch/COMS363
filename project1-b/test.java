package coms363;

import com.github.javafaker.Faker;

public class test {
    public static void main(String[] args) {
        Faker faker = new Faker();
        System.out.println(faker.date().birthday().toString());
    }
}
