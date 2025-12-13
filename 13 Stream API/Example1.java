import java.util.stream.IntStream;

public class Example1{
    public void main(String[] args){
        System.out.println("Пример с потоком: ");
        IntStream.of(50,60,70,80,90,100,110,120)
                .filter(x -> x < 90)
                .map(x -> x + 10)
                .limit(3)
                .forEach(System.out::print);

        System.out.println("\nПример без потока: ");
        int[] arr = {50,60,70,80,90,100,110,120};
        int count = 0;
        for (int x : arr){
            if (x >= 90) continue;
            x += 10;
            count++;
            if (count > 3) break;
            System.out.print(x);
        }
    }

}