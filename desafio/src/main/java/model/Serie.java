package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Serie {


    private Long id;
    private String titulo;
    private String frase;
    private String personagem;
    private String poster;


}