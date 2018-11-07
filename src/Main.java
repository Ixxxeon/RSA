import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        long number = random.nextInt(1000) + 1000;
        long message;
        boolean loop = true;
        Scanner in = new Scanner(System.in);
        List<Long> first = simpl(number);
        long a1 = getA(first);
        long p1 = getP(first);
        long module1 = getModule(a1, p1);
        long f1 = getF( a1, p1);
        long d1 = getD(f1);
        long e1 = getE(f1, d1);
        System.out.println("Открытый ключ = { " + e1 + ", " + module1 + "}");
        while (loop) {
            System.out.println("Алиса хочет отправить число - ");
            message = in.nextLong();
            long result1 = 1;
            for(int i = 1; i<=e1; i++){
                result1 = (message * result1)%module1;
            }
            System.out.println("Алиса отправляет: " + result1);
            long result2 = 1;
            for(int i = 1; i<=d1; i++){
                result2 = (result1 * result2)%module1;
            }
            System.out.println("Боб расшифровывает сообщение секретным ключом и получает: " + result2);
            System.out.println("Отправить еще сообщение? y/n");
            in.nextLine();
            String s = in.nextLine();
            if (s.equals("n")) {
                loop = false;
            }
        }
    }

    private static List<Long> simpl(long number) {
        List<Long> numbers = new ArrayList<>();
        long st = 0;
        long n = 3;
        numbers.add((long)2);
        while (st < number) {
            for (int i = 0; i < numbers.size(); i++) {
                if (n % numbers.get(i) == 0) {
                    break;
                }
                if (numbers.get(i) >= Math.sqrt(n)) {
                    st++;
                    numbers.add(n);
                    break;
                }
            }
            n += (long) 2;
        }
        return numbers;
    }

    private static long getA(List<Long> numbers) {
        long a;
        Random random1 = new Random();
        a = numbers.get(random1.nextInt(numbers.size() - 2));
        return a;
    }

    private static long getP(List<Long> numbers){
        long p = numbers.get(numbers.size()-1);
        return p;
    }
    private static long getModule(long a, long p) {
        long module;
        module = a * p;
        return module;
    }

    private static long getF(long a, long p) {
        long f;
        f = (p-1) * (a - 1);
        return f;
    }

    private static long getE(long f, long d) {
        long e = 11;
        while (true) {
            if ((e * d) % f == 1)
                break;
            else
                e++;
        }
        return e;
    }

    private static long getD(long f){
        long d = f - (long) Math.random()*100;
        for (long i = 2; i <= f; i++)
            if ((f % i == 0) && (d % i == 0)) {
                d--;
                i = 1;
            }
        return d;
    }


}