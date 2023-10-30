package racingcar;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Race {
    public List<Car> cars = new ArrayList<>();
    private int moveNum;

    public void play() {
        createCarObjects(inputCarNames());
        inputMovingNumber();

        System.out.println("실행 결과");
        for (int num = 0; num < moveNum; num++) {
            updateCarsPosition();
            displayCarMoving();
        }

        displayWinner(decideWinner());
    }

    private String[] inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = readLine();
        return input.split(",");
    }

    public void createCarObjects(String[] carNames){
        for(int idx = 0; idx < carNames.length; idx++) {
            cars.add(new Car(carNames[idx]));
        }
    }

    private void inputMovingNumber() {
        System.out.println("시도할 회수는 몇회인가요?");
        moveNum = Integer.parseInt(readLine());
    }

    public void updateCarsPosition() {

        for (Car car : cars) {
            int number = NumberGenerator.createRandomNumber();

            if (number >= 4) {
                car.setPosition(car.getPosition() + 1);
            }
        }
    }

    private void displayCarMoving() {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
        System.out.println(" ");
    }

    public List<Car> decideWinner() {
        List<Car> winners = new ArrayList<>();
        int maxPosition = -1;

        for (Car car : cars) {
            int position = car.getPosition();
            if (position > maxPosition) {
                winners.clear();
                winners.add(car);
                maxPosition = position;
            } else if (position == maxPosition) {
                winners.add(car);
            }
        }

        return winners;
    }

    public void displayWinner(List<Car> winners) {
        System.out.print("최종 우승자 : ");
        for (int idx = 0; idx < winners.size(); idx++){
            System.out.print(winners.get(idx).getName());
            if(idx < winners.size() - 1){
                System.out.print(", ");
            }
        }
    }
}