package dominio;

public record Jogo(Integer rodada,
                   DataDoJogo data,
                   Time mandante,
                   Time visitante,
                   Time vencedor,
                   String arena,
                   Integer mandantePlacar,
                   Integer visitantePlacar,
                   String estadoMandante,
                   String estadoVisitante,
                   String estadoVencedor){

    public static Jogo obterJogo(String[] atributosJogo){
        return new Jogo(Integer.parseInt(atributosJogo[0]),
                DataDoJogo.obterDataDoJogo(atributosJogo[1],atributosJogo[2],atributosJogo[3]),
                new Time(atributosJogo[4]),
                new Time(atributosJogo[5]),
                new Time(atributosJogo[6]),
                atributosJogo[7],
                Integer.parseInt(atributosJogo[8]),
                Integer.parseInt(atributosJogo[9]),
                atributosJogo[10],
                atributosJogo[11],
                atributosJogo[12]);
    }
}
