import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MainTeste 
{
//TestandoOnline
	private static ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
	private static ArrayList<String> listaCodigos = new ArrayList<String>();
	private static ArrayList<Periodo> listaPeriodos = new ArrayList<Periodo>();
	private static String arquivoCSV = "arquivos/matricula_disciplinas_2019.1_turmas_ofertadas.csv";
	private static String arquivo = "arquivos/Roger v1.txt";

	public static void main(String[] args) throws IOException
	{
		System.out.println("Iniciando simulação!!");
		
		queroFazer(arquivo);

		gerarListaMaterias();

		limparMaterias();

		System.out.println(listaMaterias.size());

		for(Materia m1 : listaMaterias)
		{
			Periodo p = new Periodo();

			p.adcionarMateria(m1);

			for(Materia m2 : listaMaterias)
			{
				if(!p.materiaConflito(m2))
				{
					p.adcionarMateria(m2);
				}
			}
			p.ordenaCodigoTurma();

			listaPeriodos.add(p);

		}

		removeDuplicados(listaPeriodos);

		System.out.println(listaPeriodos.size());
		
		maioresCreditos();
		
		System.out.println(listaPeriodos.size());

		for(int i=0; i<listaPeriodos.size(); i++)
		{
			for(String s : listaPeriodos.get(i).codigosTurma)
				System.out.println(s);

			System.out.println();
		}

	}

	public static void queroFazer(String path) throws IOException
	{
		BufferedReader br = null;
		String linha = "";
		
		br = new BufferedReader(new FileReader(new File(path)));
		
		while ((linha = br.readLine()) != null) 
		{
			listaCodigos.add(linha.trim());
		}
		
		br.close();
		
	}

	@SuppressWarnings("unused")
	private static void testar() {
		Periodo p = new Periodo();

		Scanner teclado = new Scanner(System.in);

		int n = teclado.nextInt();

		while(n>=0)
		{
			if(p.materiaConflito(listaMaterias.get(n)))
			{
				System.out.println("Matéria conflitante");
				System.out.println();
			}else
			{
				p.adcionarMateria(listaMaterias.get(n));

				p.sem1.exibir();
				System.out.println();
				p.sem2.exibir();
				System.out.println();
				System.out.println("Próximo?");
			}

			n = teclado.nextInt();
		}

		p.sem1.exibir();
		System.out.println();
		p.sem2.exibir();
		System.out.println();
		
		teclado.close();
	}

	public static void maioresCreditos()
	{
		int c=0;
		for(Periodo p : listaPeriodos)
		{
			if(p.creditos > c)
				c = p.creditos;
		}
		
		System.out.println("Creditos: " + c);
		
		for(int i=0; i<listaPeriodos.size(); i++)
		{
			if(listaPeriodos.get(i).creditos < c)
			{
				listaPeriodos.remove(i);
				i--;
			}
		}
	}
	
	@SuppressWarnings("resource")
	private static void gerarListaMaterias() throws FileNotFoundException, IOException {
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";

		br = new BufferedReader(new FileReader(arquivoCSV));

		br.readLine(); //título

		while ((linha = br.readLine()) != null) 
		{
			Materia m = new Materia(linha.split(csvDivisor));
			listaMaterias.add(m);

		}
	}

	private static void limparMaterias()
	{
		for(int i=0; i< listaMaterias.size(); i++)
		{
			if(!listaMaterias.get(i).periodo.equalsIgnoreCase("N"))
			{
				listaMaterias.remove(i);
				i--;
			}
		}
		for(int i=0; i< listaMaterias.size(); i++)
		{
			boolean existe = false;
			for(String s : listaCodigos)
				if(listaMaterias.get(i).codigo.equalsIgnoreCase(s))
				{
					existe = true;
				}

			if(!existe)
			{
				listaMaterias.remove(i);
				i--;
			}

		}


	}

	public static ArrayList<Periodo> removeDuplicados(ArrayList<Periodo> lista)
	{
		for(int i=0; i < lista.size(); i++)
		{
			for(int j=i+1; j<lista.size(); j++)
			{
				if(lista.get(i).codigosTurma.equals(lista.get(j).codigosTurma))
				{
					lista.remove(j);
					j--;
				}
			}
		}

		return lista;
	}


}
