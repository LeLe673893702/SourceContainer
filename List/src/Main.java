import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i = 0 ; i < 10; i++) {
            integers.add(i);
        }

        ArrayList<Integer> integers1 = new ArrayList<>();
        integers1.add(5);
        integers1.add(1);
        integers.removeAll(integers1);
        for (Integer integer : integers){
            System.out.println(integer);
        }

    }


}
