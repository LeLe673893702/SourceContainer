import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SourceArrayList<Integer> integerSourceArrayList = new SourceArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerSourceArrayList.add(i);
        }
        integerSourceArrayList.set(5,100);

        for (int i =0 ; i < 10; i++) {
            System.out.println(integerSourceArrayList.get(i));
        }
    }


}
