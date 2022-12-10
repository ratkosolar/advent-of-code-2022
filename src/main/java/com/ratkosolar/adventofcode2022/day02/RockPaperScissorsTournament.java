package com.ratkosolar.adventofcode2022.day02;

import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;

@Component
public class RockPaperScissorsTournament {

    public Integer calculateTotalScoreV1(String input) {
        return stream(input.split("\\r\\n"))
            .mapToInt(round -> {
                    var inputs = round.split(" ");
                    var selectedShape = Shape.fromString(inputs[1]);
                    var opponentShape = Shape.fromString(inputs[0]);
                    var result = calculateResult(selectedShape, opponentShape);
                    return Shape.getPoints(selectedShape) + Result.getPoints(result);
                }
            )
            .sum();
    }

    public Integer calculateTotalScoreV2(String input) {
        return stream(input.split("\\r\\n"))
            .mapToInt(round -> {
                    var inputs = round.split(" ");
                    var opponentShape = Shape.fromString(inputs[0]);
                    var result = Result.fromString(inputs[1]);
                    var selectedShape = calculateSelectedShape(opponentShape, result);
                    return Shape.getPoints(selectedShape) + Result.getPoints(result);
                }
            )
            .sum();
    }

    private Result calculateResult(Shape selectedShape, Shape opponentShape) {
        if (selectedShape.equals(opponentShape)) {
            return Result.DRAW;
        } else if (Shape.getWinningShape(selectedShape).equals(opponentShape)) {
            return Result.LOSS;
        }
        return Result.WIN;
    }

    private Shape calculateSelectedShape(Shape opponentShape, Result result) {
        return switch (result) {
            case WIN -> Shape.getWinningShape(opponentShape);
            case DRAW -> opponentShape;
            case LOSS -> Shape.getLosingShape(opponentShape);
        };
    }

    private enum Result {
        WIN, DRAW, LOSS;

        public static Integer getPoints(Result result) {
            return switch (result) {
                case WIN -> 6;
                case DRAW -> 3;
                case LOSS -> 0;
            };
        }

        public static Result fromString(String input) {
            return switch (input) {
                case "X" -> Result.LOSS;
                case "Y" -> Result.DRAW;
                case "Z" -> Result.WIN;
                default -> throw new IllegalStateException("Not supported result");
            };
        }
    }

    private enum Shape {
        ROCK,
        PAPER,
        SCISSORS;

        public static Integer getPoints(Shape shape) {
            return switch (shape) {
                case ROCK -> 1;
                case PAPER -> 2;
                case SCISSORS -> 3;
            };
        }

        public static Shape getWinningShape(Shape shape) {
            return switch (shape) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
        }

        public static Shape getLosingShape(Shape shape) {
            return switch (shape) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }

        public static Shape fromString(String input) {
            return switch (input) {
                case "A", "X" -> Shape.ROCK;
                case "B", "Y" -> Shape.PAPER;
                case "C", "Z" -> Shape.SCISSORS;
                default -> throw new IllegalStateException("Not supported shape");
            };
        }
    }
}
