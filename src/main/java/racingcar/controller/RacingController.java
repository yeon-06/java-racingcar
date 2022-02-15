package racingcar.controller;

import racingcar.model.Cars;
import racingcar.model.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {
    private Cars cars;
    private TryCount tryCount;

    public void start() {
        try {
            cars = new Cars(InputView.inputCarNames());
            inputTryCount();
            race();
            terminate();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            start();
        }
    }

    private void inputTryCount() {
        try {
            tryCount = new TryCount(InputView.inputTryCount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            inputTryCount();
        }
    }

    private void race() {
        int nowTryCnt = 0;
        OutputView.printStartMessage();
        while (tryCount.isNotSame(nowTryCnt++)) {
            cars.moveAll();
            OutputView.printCarsStatus(cars.getCars());
        }
    }

    private void terminate() {
        OutputView.printCarsStatus(cars.getCars());
        OutputView.printString(cars.getWinners().getWinnersSentence());
    }
}

