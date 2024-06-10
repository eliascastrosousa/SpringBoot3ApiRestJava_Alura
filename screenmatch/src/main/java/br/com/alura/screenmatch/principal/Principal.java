package br.com.alura.screenmatch.principal;
import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.EpisodioRepository;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private SerieRepository serieRepository;
    private EpisodioRepository episodioRepository;
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=11c983a0";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private List<Serie> series = new ArrayList<>();

    private Optional<Serie> seriesEncontrada;

    private List<Episodio> episodios = new ArrayList<>();

    public Principal(SerieRepository serieRepository, EpisodioRepository episodioRepository) {
        this.serieRepository = serieRepository;
        this.episodioRepository = episodioRepository;
    }

    public void exibeMenu() throws JsonProcessingException {
        var opcao = -1;

        while (opcao != 0) {
        var menu = """
                \n1 - Buscar séries
                2 - Buscar episódios
                3 - Listar series buscadas
                4 - Buscar serie pelo nome
                5 - Buscar series por ator
                6 - Top 5 Séries
                7 - Buscar Series por Categoria
                8 - Buscar Series com Determinadas Temporadas
                9 - Buscar Series Por Determinadas Temporadas e Avaliacao
                10 - Buscar Episodio por titulo
                11 - Top Episodios por serie
                12 - Buscar Episodios por Data
                0 - Sair                                 
                """;

        System.out.println(menu);
        opcao = sc.nextInt();
        sc.nextLine();


            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    imprimirSerieBuscada();
                    break;
                case 5:
                    buscarSeriePorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarSeriesPorNTemporadas();
                    break;
                case 9:
                    buscarSeriesPorNTemporadasEAvaliacao();
                    break;
                case 10:
                    buscarEpisodioPorTrecho();
                    break;
                case 11:
                    TopEpisodiosPorSerie();
                    break;
                case 12:
                    buscarEpisodioPorData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }




    public DadosSerie getDadosSerie() throws JsonProcessingException {
        System.out.println("\nDigite o nome da série para busca: ");
        var nomeSerie = sc.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    public void buscarSerieWeb() throws JsonProcessingException {
        DadosSerie dados = getDadosSerie();
        System.out.println(dados);
        Serie serie = new Serie(dados);
        if (!serieRepository.existsByTitulo(serie.getTitulo()) ) {
            serieRepository.save(serie);
        }

    }

    public void buscarEpisodioPorSerie() throws JsonProcessingException {
        listarSeriesBuscadas();
        System.out.println("\nEscolha uma serie pelo nome: ");
        var nomeSerie = sc.nextLine();
        Optional<Serie> serie = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);
        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            if (episodioRepository.existsBySerieId(serieEncontrada.getId())) {
                System.out.println(serieEncontrada);
            }else {
                List<DadosTemporada> temporadas = new ArrayList<>();
                for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                    var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                    DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                    temporadas.add(dadosTemporada);
                }
                temporadas.forEach(System.out::println);

                episodios = temporadas.stream()
                        .flatMap(d -> d.episodios().stream()
                                .map(e -> new Episodio(d.temporada(), e)))
                        .collect(Collectors.toList());

                serieEncontrada.setEpisodios(episodios);
                serieRepository.save(serieEncontrada);
            }
        } else {
            System.out.println("Serie nao encontrada!");
        }
    }

    public void listarSeriesBuscadas() {
        series = serieRepository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(Serie::exibeSerieDetalhada);

    }
    private void buscarSeriesPorCategoria() {
        System.out.println("Qual categoria deseja buscar? ");
        var nomeCategoria = sc.nextLine();
        Categoria categoria = Categoria.fromStringPortugues(nomeCategoria);
        List<Serie> series = serieRepository.findByGenero(categoria);
        System.out.println("Series da Categoria: "+nomeCategoria);
        series.forEach(Serie::exibeSerie);
    }
    private void buscarSeriePorTitulo() {
        System.out.println("\nEscolha uma serie pelo nome: ");
        var nomeSerie = sc.nextLine();
        seriesEncontrada = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

    }
    private void imprimirSerieBuscada(){
        buscarSeriePorTitulo();
        if (seriesEncontrada.isPresent()) {
            System.out.println("Dados da Série: " + seriesEncontrada.get());
        } else {
            System.out.println("Série nao encontrada!");
        }
    }

    private void buscarSeriePorAtor() {
        System.out.println("\nDigite o nome do ator: ");
        var nomeAtor = sc.nextLine();
        System.out.println("Digite a nota de corte: ");
        var avaliacao = sc.nextDouble();
        List<Serie> series = serieRepository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        if (!series.isEmpty()) {
            System.out.println("Séries que " + nomeAtor + " trabalhou: " );
            series.forEach(serie -> System.out.println(serie.getTitulo() + ", Avaliação: " + serie.getAvaliacao()));
        } else {
            System.out.println("nenhuma serie encontrada!");
        }

    }

    private void buscarTop5Series() {
        List<Serie> series = serieRepository.findTop5ByOrderByAvaliacaoDesc();
        series.forEach(serie -> System.out.println(serie.getTitulo() + ", Avaliação: " + serie.getAvaliacao()));
    }

    private void buscarSeriesPorNTemporadas() {
        System.out.println("Digite a quantidade de temporadas que deseja limitar a consulta: ");
        int qtd = sc.nextInt();
        List<Serie> series = serieRepository.findBytotalTemporadasLessThanEqual(qtd);
        series.forEach(serie -> System.out.println(serie.getTitulo() + ", \nAvaliação: " + serie.getAvaliacao() + "\nQuantidade de Temporadas: " + serie.getTotalTemporadas()));
    }
    private void buscarSeriesPorNTemporadasEAvaliacao() {
        System.out.println("Digite a quantidade de temporadas que deseja limitar a consulta: ");
        Integer qtdTemporadas = sc.nextInt();
        System.out.println("Digite a nota minima de avaliação para limitar a consulta: ");
        Double avaliacao = sc.nextDouble();

        List<Serie> series = serieRepository.seriesPorTemporadaEAvaliacao(qtdTemporadas, avaliacao);
        series.forEach(serie -> System.out.println(serie.getTitulo() + ", \nAvaliação: " + serie.getAvaliacao() + "\nQuantidade de Temporadas: " + serie.getTotalTemporadas()));

    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite o nome do episodio para busca: ");
        var nomeEpisodio = sc.nextLine();
        List<Episodio> episodiosEncontrados = episodioRepository.findAllByTituloContains(nomeEpisodio);
        episodiosEncontrados.forEach(System.out::println);

    }

    private void TopEpisodiosPorSerie() {
        buscarSeriePorTitulo();
        if (seriesEncontrada.isPresent()) {
            Serie serie = seriesEncontrada.get();
            List<Episodio> topEpisodios = episodioRepository.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(System.out::println);
        }
    }

    private void buscarEpisodioPorData() {
        buscarSeriePorTitulo();
        if (seriesEncontrada.isPresent()) {
            System.out.println("Digite o ano limite de lançamento: ");
            var anoLancamento = sc.nextInt();
            sc.nextLine();

            Serie serie = seriesEncontrada.get();
            serie.exibeSerie();
            List<Episodio> episodios = episodioRepository.episodiosPorSerieEData(serie, anoLancamento);
            episodios.forEach(System.out::println);
        }
    }

}
