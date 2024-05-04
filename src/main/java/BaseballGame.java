import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BaseballGame {

    public void startGame() {
        boolean isPlaying = true;
        while (isPlaying) {
            playGame();
            isPlaying = askToPlayAgain();
        }
    }

    public void playGame() {
        int[] targetNumbers = generateRandomNumbers();

        while (true) {
            int[] userInput = getUserInput();
            System.out.println(Arrays.toString(targetNumbers));
            System.out.println(Arrays.toString(userInput));
            int[] comparedResult = compareNumbers(targetNumbers, userInput);
            System.out.println(getResultMessage(comparedResult));
            if (isGameEnded(comparedResult)) {
                break;
            }
        }
    }

    public static int[] generateRandomNumbers() {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);

        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = digits.get(i);
        }
        return result;
    }

    public int[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("세 자리 숫자를 입력하세요: ");
        String input = scanner.nextLine();

        String[] inputArray = input.split("");
        int[] intArray = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            intArray[i] = Integer.parseInt(inputArray[i]);
        }
        return intArray;
    }


    public String getResultMessage(int[] comparedResult) {
        String message = "";
        int strikeCount = comparedResult[0];
        int ballCount = comparedResult[1];

        switch (strikeCount) {
            case 3:
                message = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
                break;
            case 0:
                if (ballCount == 0) {
                    message = "낫싱";
                } else {
                    message = String.format("%d볼", ballCount);
                }
                break;
            default:
                if (ballCount == 0) {
                    message = String.format("%d스트라이크", strikeCount);
                } else {
                    message = String.format("%d볼 %d스트라이크", ballCount, strikeCount);
                }
                break;
        }
        return message;
    }


    public int[] compareNumbers(int[] target, int[] userInput) {
        // 배열의 첫번쨰 자리에는 strike 개수, 두번째 자리에는 ball 개수
        int strikeCount = 0;
        int ballCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (target[i] == userInput[j] && i == j) {
                    strikeCount++;
                    continue;
                }
                if (target[i] == userInput[j] && i != j) {
                    ballCount++;
                }
            }
        }
        return new int[]{strikeCount, ballCount};
    }

    public boolean isGameEnded(int[] comparedResult) {
        if (comparedResult[0] == 3) {
            return true;
        }
        return false;
    }

    public static boolean askToPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요: ");
            int input = scanner.nextInt();
            System.out.println(input);
            if (input == 1) {
                System.out.println("1입력");
                return true;
            } else {
                System.out.println("2입력");
                return false;
            }
        }
    }
}
