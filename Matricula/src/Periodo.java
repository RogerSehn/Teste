import java.util.ArrayList;
import java.util.Collections;

public class Periodo 
{
	public Semana sem1, sem2;
	public int creditos;
	public ArrayList<String> codigosTurma = new ArrayList<>();
	public ArrayList<String> codigos = new ArrayList<>();

	public Periodo()
	{
		creditos = 0;
		sem1 = new Semana();
		sem2 = new Semana();

	}

	public void adcionarMateria(Materia m)
	{
		codigosTurma.add(m.codigoTurma);
		codigos.add(m.codigo);

		int[][] matriz = m.sem1.semana;

		for(int i=0; i < matriz.length; i++)
		{
			for(int j=0; j < matriz[i].length; j++)
			{
				sem1.semana[i][j] += matriz[i][j];
			}
		}

		matriz = m.sem2.semana;

		for(int i=0; i < matriz.length; i++)
			for(int j=0; j < matriz[i].length; j++)
			{
				sem2.semana[i][j] += matriz[i][j];
			}
		
		int t=0;
		for(int i=0; i < matriz.length; i++)
			for(int j=0; j < matriz[i].length; j++)
			{
				t += matriz[i][j];
			}
		
		creditos = t;


	}

	public boolean materiaConflito(Materia m)
	{
		if(codigosTurma.contains(m.codigoTurma))
			return true;
		
		if(codigos.contains(m.codigo))
			return true;

		int[][] matriz = m.sem1.semana;

		for(int i=0; i < matriz.length; i++)
			for(int j=0; j < matriz[i].length; j++)
			{
				if(sem1.semana[i][j] == 1 && sem1.semana[i][j] == matriz[i][j])
					return true;
			}

		matriz = m.sem2.semana;

		for(int i=0; i < matriz.length; i++)
			for(int j=0; j < matriz[i].length; j++)
			{
				if(sem2.semana[i][j] == 1 && sem2.semana[i][j] == matriz[i][j])
					return true;
			}
		
		int t=0;
		for(int i=0; i < matriz.length; i++)
			for(int j=0; j < matriz[i].length; j++)
			{
				t += matriz[i][j];
			}
		if(t==0) return true;

		return false;
	}

	public void ordenaCodigoTurma()
	{
		Collections.sort(codigosTurma);
	}

	class Semana
	{
		int[][] semana = new int[4][5];

		public void exibir()
		{
			for (int i = 0; i < semana.length; i++) {
				for (int j = 0; j < semana[i].length; j++) {
					System.out.printf("%d ", this.semana[i][j]);
				}
				System.out.println();
			}		
		}
	}

}
