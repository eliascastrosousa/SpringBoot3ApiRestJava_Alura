package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    public void exibeMenu() throws JsonProcessingException {


        System.out.println("Digite o nome da serie para busca: ");
        var nomeSerie = sc.nextLine();
        var enderecoSerie = ENDERECO + nomeSerie.replace(" ", "+") ;
        System.out.println(enderecoSerie);

        var json = consumoApi.obterDados(enderecoSerie + API_KEY);
        System.out.println(json);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(enderecoSerie + "&Season="+ i +API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //temporadas.forEach(System.out::println);


        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        dadosEpisodios.add(new DadosEpisodio("teste", 8, "10", "2024-02-10"));

        //dadosEpisodios.forEach(System.out::println);

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(
                        t-> t.episodios().stream()
                            .map(d -> new Episodio(t.temporada(), d))
                        )
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = sc.nextInt();
//        sc.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1,1);
//        episodios.stream()
//                .filter(e-> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(System.out::println);
//
//        System.out.println("Digite um trecho do titulo do episodio");
//        var trecho = sc.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trecho.toUpperCase()))
//                .findFirst();
//
//        if (episodioBuscado.isPresent()) {
//            System.out.println("Episodio encontrado: Nome completo: " + episodioBuscado.get().getTitulo());
//            System.out.println("Temporada: " + episodioBuscado.get().getNumTemporada());
//        }else {
//            System.out.println("Episodio nao encontrado!");
//        }

        System.out.println("\n\n\n\n");

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e->e.getAvaliacao()>0.0)
                .collect(Collectors.groupingBy(Episodio::getNumTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e->e.getAvaliacao()>0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println(est);

        System.out.println("Quantidade de episodios: " + est.getCount());
        System.out.println("Melhor nota de episodio: " + est.getMax());
        System.out.println("Pior nota de episodio: " + est.getMin());
        System.out.println("Media nota de episodio: " + est.getAverage());



//        List<List<String>> list = List.of(
//                List.of("a", "b"),
//                List.of("c", "d")
//        );
//        System.out.println(list);
//
//        Stream<String> stream = list.stream()
//                .flatMap(Collection::stream);
//
//        stream.forEach(System.out::println);
//
//        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//        Optional<Integer> result = numbers.stream().reduce(Integer::sum);
//        result.ifPresent(System.out::println); //prints 15

    }
}
