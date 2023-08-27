import java.util.Scanner;

public class TimberMaxDilemma {

    public static void main(String[] args) {
        Scanner scannn = new Scanner(System.in);

        String[] pricesStr = scannn.nextLine().split(" ");
        int[] pricesArr = new int[pricesStr.length];
        for (int i = 0; i < pricesStr.length; i++) {
            pricesArr[i] = Integer.parseInt(pricesStr[i]);
        }

        int k = Integer.parseInt(scannn.nextLine());

        int[] mpr = new int[k + 1];
        mpr[0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= i && j <= pricesArr.length; j++) {
                mpr[i] = Math.max(mpr[i], pricesArr[j - 1] + mpr[i - j]);
            }
        }

        int out = mpr[k];

        System.out.println(out);
    }
}
