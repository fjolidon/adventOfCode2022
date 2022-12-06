package aoc2022.common;


import aoc2022.day01.Day01;
import aoc2022.day02.Day02;
import aoc2022.day03.Day03;
import aoc2022.day04.Day04;
import aoc2022.day05.Day05;
import aoc2022.day06.Day06;
import aoc2022.day07.Day07;
import aoc2022.day08.Day08;
import aoc2022.day09.Day09;
import aoc2022.day10.Day10;
import aoc2022.day11.Day11;
import aoc2022.day12.Day12;
import aoc2022.day13.Day13;
import aoc2022.day14.Day14;
import aoc2022.day15.Day15;
import aoc2022.day16.Day16;
import aoc2022.day17.Day17;
import aoc2022.day18.Day18;
import aoc2022.day19.Day19;
import aoc2022.day20.Day20;
import aoc2022.day21.Day21;
import aoc2022.day22.Day22;
import aoc2022.day23.Day23;
import aoc2022.day24.Day24;
import aoc2022.day25.Day25;


public class Main {


    public static void main(String[] args) throws Exception {

        SolutionFactory[] factories = {
                new Day01(),
                new Day02(),
                new Day03(),
                new Day04(),
                new Day05(),
                new Day06(),
                new Day07(),
                new Day08(),
                new Day09(),
                new Day10(),
                new Day11(),
                new Day12(),
                new Day13(),
                new Day14(),
                new Day15(),
                new Day16(),
                new Day17(),
                new Day18(),
                new Day19(),
                new Day20(),
                new Day21(),
                new Day22(),
                new Day23(),
                new Day24(),
                new Day25()
        };

        Solution[] solutions = new Solution[factories.length];

        for (int i=0; i<solutions.length; ++i) {
            solutions[i] = factories[i].get();
        }

        SolutionRunner runner = new SolutionRunner(solutions);

        runner.runAndPrintSolutionForDay(6);

//        runner.runAllSolutions();
//        runner.printAllSolutions();
    }

}
