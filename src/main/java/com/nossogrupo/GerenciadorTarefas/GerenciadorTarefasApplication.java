package com.nossogrupo.GerenciadorTarefas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nossogrupo.GerenciadorTarefas.model.Tarefa;
import com.nossogrupo.GerenciadorTarefas.model.TaskUser;
import com.nossogrupo.GerenciadorTarefas.model.Colaborador;
import com.nossogrupo.GerenciadorTarefas.repository.*;
@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner{

	@Autowired TaskUserRepository acaoUser;
	@Autowired TarefaRepository acaoTarefa;
	@Autowired ColaboradorRepository acaoColaborador;
	List<Colaborador> listaColaboradores = new ArrayList<>();
	List<TaskUser> listaUsers = new ArrayList<>(); 
	List<Tarefa> listaTarefas = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Colaborador c1 = new Colaborador((long) 1, "Letícia", "Backend", "https://github.com/leticosta4", "https://www.linkedin.com/in/letícia-almeida-9704162a0/", "#800080", "#f109af");
		Colaborador c2 = new Colaborador((long) 2, "Alysson", "Frontend", "https://github.com/VerttB", "https://www.linkedin.com/in/alysson-dos-anjos-00b431305/", "#ff00aa", "#ff5bc8");
		Colaborador c3 = new Colaborador((long) 3, "Kaik", "Frontend", "https://github.com/Syrex72", "https://www.linkedin.com/in/kaik-costa-pereira-655544273/",  "#FF0000", "#ffc177");
		Colaborador c4 = new Colaborador((long) 4, "Cainan", "Backend", "https://github.com/Cainan-bas", null, "#0000FF",  "#0070ff");
		listaColaboradores.addAll(Arrays.asList(c1, c2, c3, c4));

		for(Colaborador c : listaColaboradores){
			if(!acaoColaborador.existsByColaboradorId(c.getColaboradorId())){ acaoColaborador.save(c); }
		}
		listaColaboradores.clear();

		TaskUser u1 = new TaskUser((long) 1, "Let", "leticiacostaoa@gmail.com", "123456");
        TaskUser u2 = new TaskUser((long) 2, "Alysson", "alyssonoliveira456@gmail.com", "654321");
        TaskUser u3 = new TaskUser((long) 3, "Kaik", "kaikcpereira@gmail.com", "1142878");
        TaskUser u4 = new TaskUser((long) 4, "Cainan", "cainan.bas@gmail.com", "1109876");
		listaUsers.addAll(Arrays.asList(u1, u2, u3, u4));

		for(TaskUser u : listaUsers){
			if(!acaoUser.existsByUserId(u.getUserId())){ acaoUser.save(u); }
		}
		listaUsers.clear();

		Tarefa t1 = new Tarefa((long) 1, "estudar nodejs", "fazer mini projeto tbm", "Pendente", null, "2024-12-31", null, null, u1);
        Tarefa t2 = new Tarefa((long) 2, "ir à praia", null, "Atrasada", null, "2024-07-31", null, "Guarajuba", u1);
        Tarefa t3 = new Tarefa((long) 3, "projeto ed2", "flask", "Concluída", "2024-03-10", "2024-07-02", null, null, u1);
        Tarefa t4 = new Tarefa((long) 4, "comprar mantimentos", "lista de compras", "Pendente", "2024-08-05", "2024-08-15", null, "Supermercado", u2);
        Tarefa t5 = new Tarefa((long) 5, "limpar a casa", "limpeza geral", "Atrasada", null, "2024-07-10", null, "Casa", u2);
        Tarefa t6 = new Tarefa((long) 6, "projeto final", "entregar projeto de conclusão", "Concluída", "2024-06-09", "2024-09-01", null, "Universidade", u2);
        Tarefa t7 = new Tarefa((long) 7, "leitura de livro", "ler livro de ficção", "Pendente", null, "2024-09-30", null, "Biblioteca", u3);
        Tarefa t8 = new Tarefa((long) 8, "exercícios físicos", "ir à academia", "Atrasada", null, "2024-08-01", null, "Academia", u3);
        Tarefa t9 = new Tarefa((long) 9, "fazer relatório", "escrever relatório mensal", "Concluída", null, "2024-07-05", null, "Escritório", u3);
        Tarefa t10 = new Tarefa((long) 10, "estudar Java", "praticar programação", "Pendente", null, "2024-10-15", null, "Casa", u4);
        Tarefa t11 = new Tarefa((long) 11, "cozinhar jantar", "preparar refeição", "Atrasada", null, "2024-07-08", null, "Casa", u4);
        Tarefa t12 = new Tarefa((long) 12, "revisar código", "revisão de projeto", "Concluída", null, "2024-07-02", null, "Escritório", u4);
        
		Tarefa t13 = new Tarefa((long) 13, "LP3 AV4", null, "Em andamento", "2024-06-01", "2024-07-10", null, "UNEB", u1);
		Tarefa t14 = new Tarefa((long) 14, "LP3 AV4", null, "Em andamento", "2024-06-01", "2024-07-10", null, "UNEB", u2);
		Tarefa t15 = new Tarefa((long) 15, "LP3 AV4", null, "Em andamento", "2024-06-01", "2024-07-10", null, "UNEB", u3);
		Tarefa t16 = new Tarefa((long) 16, "LP3 AV4", null, "Em andamento", "2024-06-01", "2024-07-10", null, "UNEB", u4);

		Tarefa t17 = new Tarefa((long) 17, "dormir", "necessário demais", "Concluída", null, "2024-07-09", null, null, u1);
		Tarefa t18 = new Tarefa((long) 18, "dormir", "necessário demais", "Atrasada", null, "2024-07-09", null, null, u2);
        Tarefa t19 = new Tarefa((long) 19, "dormir", "necessário demais", "Em andamento", null, "2024-07-09", null, null, u3);
        Tarefa t20 = new Tarefa((long) 20, "dormir", "necessário demais", "Pendente", null, "2024-07-09", null, null, u4);
		
		listaTarefas.addAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20));
		for(Tarefa t : listaTarefas){
			if(!acaoTarefa.existsByTarefaId(t.getTarefaId())){ acaoTarefa.save(t); }
		}
		listaTarefas.clear();
	}

}

	

