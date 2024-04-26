package br.com.alura.screenmatch.util;

import java.util.Arrays;
import java.util.List;

public class Funcoes_Lambdas {

    public void funcoesLambdas() {
        List<String> nomes = Arrays.asList("elias", "karina", "bruno", "nico", "bruno", "bruno");
        nomes.stream()
                //operacoes intermediarias
                .sorted()
                .distinct()
                .limit(3)
                .filter(n -> n.startsWith("b"))
                .map(n -> n.toUpperCase())
                // operacoes finais
                .forEach(System.out::println);
    }
}