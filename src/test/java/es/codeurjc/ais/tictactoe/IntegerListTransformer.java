package es.codeurjc.ais.tictactoe;

import cucumber.api.Transformer;

public class IntegerListTransformer extends Transformer<int[]> {

    public int[] transform(String integer_list) {

        String[] numbers = integer_list.split(",");

        int[] result = new int[numbers.length];

        int index = 0;
        for (String number : numbers) {
            result[index] = Integer.parseInt(number);
            ++index;
        }
        return result;
    }
}
