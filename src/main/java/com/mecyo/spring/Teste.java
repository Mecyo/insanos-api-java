package com.mecyo.spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.TorneioDTO;
import com.mecyo.spring.domain.service.TorneioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Teste implements CommandLineRunner {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TorneioService torneioService;

    @Autowired
    public Teste(TorneioService torneioService) {
        this.torneioService = torneioService;
    }

    @Override
    public void run(String... args) throws Exception {

        //TorneioDTO dto = this.torneioService.getFinal();
        //System.out.print(dto.toString());
        /*List<Time> tabela = new ArrayList<>();

        tabela.add(new Time("Alemanha"));
        tabela.add(new Time("Brasil"));
        tabela.add(new Time("Argentina"));

        Random ran = new Random();

        System.out.println("╔════════════════╗");
        System.out.println("║ PRIMEIRA RODADA║");
        System.out.println("╚════════════════╝");
        for (int i = 0; i < tabela.size(); i++) {
            int x = ran.nextInt(5);
            for (int j = 0; j < 3; j++) {
                int y = ran.nextInt(5);
                if (i != j) {
                    System.out.printf("");
                    System.out.print(tabela.get(i).getNome() + " ");
                    System.out.print(x + " x " + y);
                    System.out.println(" " + tabela.get(j).getNome());
                    
                    tabela.get(i).addGols(x - y);
                    tabela.get(j).addGols(y - x);

                    if(x == y) {//empate
                        tabela.get(i).addPontos(1);
                        tabela.get(j).addPontos(1);
                    } else if(x > y) {
                        tabela.get(i).addPontos(3);
                    } else {
                        tabela.get(j).addPontos(3);
                    }
                }
            }
        }
        System.out.println("════════════════════");

        //Ordeno os dados
        Collections.sort(tabela, (Object o1, Object o2) -> {
            Time time1 = (Time) o1;
            Time time2 = (Time) o2;

            int retorno;

            //Verifica se os pontos sao iguais
            if (Objects.equals(time1.getPontos(), time2.getPontos())) {
                //Se forem verifica quem tem mais gols
                if (time1.getGols() > time2.getGols()) {
                    retorno = -1;
                } else {
                    if (time1.getGols() < time2.getGols()) {
                        retorno = 1;
                    } else {
                        retorno = 0;
                    }
                }
                //Senao verifica quem tem mais pontos
            } else if (time1.getPontos() > time2.getPontos()) {
                retorno = -1;
            } else {
                if (time1.getPontos() < time2.getPontos()) {
                    retorno = 1;
                } else {
                    retorno = 0;
                }
            }
            return retorno;
        });

        //Mostra as posicoes na tabela
        int posicao = 1;
        for (Time time : tabela) {
            System.out.println("Posicao: " + posicao + " | Nome: " + time.getNome() + " | Pontuacao: " + time.getPontos() + " | Saldo de gol: " + time.getGols());
            posicao++;
        }*/
    }

    class Time {

        String nome;
        int gols = 0;
        int pontos = 0;
        
        public int getPontos() {
            return pontos;
        }
        
        public void setPontos(int pontos) {
            this.pontos = pontos;
        }
        
        public Time(String time) {
            this.nome = time;
        }

        public Time(String time, int pontos, int gols) {
            this.nome = time;
            this.gols = gols;
            this.pontos = pontos;
        }
        
        public String getNome() {
            return nome;
        }
        
        public void setNome(String time) {
            this.nome = time;
        }
        
        public int getGols() {
            return gols;
        }
        
        public void setGols(int gols) {
            this.gols = gols;
        }

        public void addGols(int gols) {
            this.gols += gols;
        }

        public void addPontos(int pontos) {
            this.pontos += pontos;
        }
    }
        
}
