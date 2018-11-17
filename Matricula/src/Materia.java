
public class Materia 
{
	public String codigo, turma, codigoTurma;
	public String hTeoria, hPratica;
	public String campus, periodo;
	public String professorTeoria, professorPratica;
	public Semana sem1, sem2;

	final String semanal = "semanal", quin1 = "quinzenal I", quin2 = "quinzenal II";
	final int h19=0, h20=1, h21=2, h22=3;
	final int seg=0, ter=1, qua=2, qui=3, sex=4;

	public Materia(String[] linha)
	{
		String temp = linha[1];
		codigoTurma = temp;
		//System.out.println(codigoTurma);
		codigo = temp.substring(temp.indexOf("-")-7, temp.indexOf("-"));
		turma = linha[2];
		hTeoria = linha[3];
		hPratica = linha[4];
		campus = temp.substring(temp.length() - 2, temp.length());
		periodo = temp.substring(0, 1);
		try {
			professorTeoria = linha[8];
		} catch (IndexOutOfBoundsException e) {
			professorTeoria = null;
		}
		try
		{
			professorPratica = linha[9];
		}catch (IndexOutOfBoundsException e) {
			professorPratica = null;
		}

		sem1 = new Semana();
		sem2 = new Semana();

		interpretaData(hTeoria);
		interpretaData(hPratica);


	}

	public void interpretaData(String h)
	{
			if (!h.equals("") && h != null) 
			{
				int qtdDias = h.split(",").length / 2;
				String[] vetor = h.split(",");
				for (int i = 0; i <= qtdDias; i += 2) {
					String dia = vetor[i].trim().substring(0, 3);
					String horaI = vetor[i].trim().substring(vetor[i].trim().indexOf("das") + 4,
							vetor[i].trim().indexOf("das") + 4 + 2);
					String horaF = vetor[i].trim().substring(vetor[i].trim().indexOf(horaI) + 9,
							vetor[i].trim().indexOf(horaI) + 9 + 2);
					String semana = vetor[i + 1].trim();

					int hI, hF, dia2;
					hI = hora(Integer.parseInt(horaI));
					hF = hora(Integer.parseInt(horaF) - 1);

					
					dia2 = dia(dia);
			
					
					if (dia2 >= 0) {
						if (semana.equals(this.semanal)) {
							for (int ho = hI; ho <= hF - hI + hI; ho++) {
								sem1.semana[ho][dia2] = 1;
								sem2.semana[ho][dia2] = 1;
							}

						} else if (semana.equals(this.quin1)) {
							for (int ho = hI; ho <= hF - hI+hI; ho++) {
								sem1.semana[ho][dia2] = 1;
							}

						} else if (semana.equals(this.quin2)) {
							for (int ho = hI; ho <= hF - hI+hI; ho++) {
								sem2.semana[ho][dia2] = 1;
							}

						} else {
						} 
					}

				} 
			}

	}

	private int hora(int h)
	{
		switch(h)
		{
		case 19:
			return h19;
		case 20:
			return h20;
		case 21:
			return h21;
		case 22:
			return h22;
		default:
			return 0;
		}
	}

	private int dia(String d)
	{
		if(d.equals("seg"))
			return seg;
		if(d.equals("ter"))
			return ter;
		if(d.equals("qua"))
			return qua;
		if(d.equals("qui"))
			return qui;
		if(d.equals("sex"))
			return sex;

		return -1;
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
