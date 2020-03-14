public class Main {

    public static void main(String[] args) {
        B b = new B();

        b.f();
    }
}

class A {
    void f() {
        System.out.println("Do some stuff");
    }
}

class B {
    A a = new A();
    void f() {
        a.f();
    }
}