/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamapiteste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author luciano
 */
public class StreamAPI {
    public void run(){
        List<Pessoa> listPessoas = Arrays.asList(new Pessoa("Joao", 32),
                                                new Pessoa("Ana", 30),
                                                new Pessoa("Paulo", 25),
                                                new Pessoa("Sula", 23),
                                                new Pessoa("Aline", 54),
                                                new Pessoa("James", 27),
                                                new Pessoa("John", 29));
        Stream<Pessoa> streamPessoa = listPessoas.stream();
        
        //soma das pessoas começadas com 'A'
        Integer idadePessoas = listPessoas.stream()
                                .filter(p -> p.getNome().startsWith("A"))
                                .mapToInt(p -> p.getIdade())
                                .sum();
        System.out.println("soma das idades = "+idadePessoas);
        
        //Filtar pessoas por nome que contenha "Jo"
        List<Pessoa> listF = listPessoas.stream()
                                .filter(p -> p.getNome().contains("Jo"))
                                .collect(Collectors.toList());
        
        listF.stream().forEach(p -> System.out.println(p.getNome()));
        
        //mostrar quantas pessoas tem mais de 30 anos
        Long maiorIgual30 = listPessoas.stream()
                               .filter(p -> p.getIdade() >= 30)
                               .count();
        System.out.println("A quantidade de pessoas com idade maior ou igual a 30 é: "+maiorIgual30);
        
               
        //Extrair uma lista de uma Stream<T>
        List<Pessoa> teste = streamPessoa.collect(Collectors.toList());
        teste.stream().forEach(p -> System.out.print(p.getNome()+" | "));
        System.out.println("");
        

        //mostrar quantidade de pessoas que possuem mais de uma letra 'a' ou 'A' no nome
        Long maisDeUmA = listPessoas.stream()
                            .filter(p -> StreamAPI.maisDeUmChar(p.getNome(), 'a'))
                            .count();
        System.out.println("Quantidade de pessoas que possuem mais de uma letra 'a' ou 'A' no nome: "+maisDeUmA);
       
        
        //Classe IntSumaryStatistics
        IntSummaryStatistics intSumaryStatistics = listPessoas.stream()
                                                    .filter(p -> p.getNome().toLowerCase().startsWith("j"))
                                                    .mapToInt(p -> p.getIdade())
                                                    .summaryStatistics();
        System.out.println("Pessoas com nomes começados com J");
        System.out.println("Count "+intSumaryStatistics.getCount());
        System.out.println("Soma das idades "+intSumaryStatistics.getSum());
        System.out.println("idade maior "+intSumaryStatistics.getMax());
        System.out.println("idade menor "+intSumaryStatistics.getMin());
        System.out.println("Média das idades "+intSumaryStatistics.getAverage());
        
        //Classe com Map()
        System.out.print("Pessoas com idade 30 anos: ");
        Map<Integer,List<Pessoa>> map = listPessoas.stream()
                                            .collect(Collectors.groupingBy(Pessoa::getIdade));
        map.get(30).forEach(p -> System.out.println(p.getNome()+" "));
        
        
        //Classe Optional
        Optional<Pessoa> optPessoa = listPessoas.stream()
                                        .filter(p -> p.getIdade() < 10)
                                        .findFirst();
        System.out.println("Primeira Pessoa com idade maior de 50: ");
        if(optPessoa.isPresent()){
            Pessoa p = optPessoa.get();
            System.out.println(p.getNome()+" - "+p.getIdade());
        }
        
        optPessoa.ifPresent(p -> System.out.println(p.getNome()+" - "+p.getIdade()));
        
        //optPessoa.orElseThrow();
        optPessoa.orElse(new Pessoa("Camila", 7));
        optPessoa.ifPresent(p -> System.out.println(p.getNome()+" - "+p.getIdade()));
    }
    
    
    /**
     * Retorna true caso haja incidencia de mais de um char
     * em determinada String.
     * 
     * @param word
     * @param ch
     * @return 
     */
    static boolean maisDeUmChar(String word,char ch){
        
        //sp
        word = word.toLowerCase();
        List<Integer> charList = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            charList.add((int)word.charAt(i));
        }
        Long count = charList.stream()
                        .filter(c -> c == (int)ch)
                        .count();
        
        return count > 1 ? true : false;
    }
}
