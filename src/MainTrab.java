import java.io.RandomAccessFile;
import java.util.Scanner;
public class MainTrab {
	public static void main(String[] args) throws Exception 
	{
		
		RandomAccessFile f = new RandomAccessFile("cep_ordenado.dat", "r");
		Endereco e = new Endereco();
		Scanner input = new Scanner(System.in);
		long posicao, primeiro, ultimo, meio;
		f.seek(f.length());
		posicao = f.getFilePointer();
		f.seek(0L*300);
		System.out.println("Tamanho do arquivo: "+posicao);
		System.out.println("Tamanho do Registro: "+300);
		System.out.println("Tamanho do arquivo em Registros: "+posicao/300);
		primeiro=0;
		ultimo=(posicao/300)-1;
		System.out.println("O cep e composto de 8 numeros. Digite o cep a ser pesquisado: ");
		String cepInput = input.nextLine();
		boolean achou = false;
		int contador=0;
		while(primeiro<=ultimo && achou==false) {
			contador++;
			meio=(primeiro+ultimo)/2;
			f.seek(meio*300);
			System.out.println("Posicao atual: "+f.getFilePointer()/300);
			e.leEndereco(f);
			long cep1 = Long.parseLong(cepInput);
			long cep2 = Long.parseLong(e.getCep());
			if(cep1==cep2)
				achou=true;
			else if(cep1>cep2)
				primeiro=meio+1;
			else if(cep1<cep2)
				ultimo=meio-1;
			
		}
		System.out.println("Loops percorridos: "+contador);
		if(!achou)
			System.out.println("Cep nao encontrado");
		else {
			System.out.println("Cep encontrado!");
			while(f.getFilePointer()-1 < f.length()) // para Detectar EOF
			{
				f.seek(f.getFilePointer()-1*300L);
				System.out.println("Posicao do endereco: "+f.getFilePointer()/300);
				e.leEndereco(f);
				System.out.println(e.getLogradouro());
				System.out.println(e.getBairro());
				System.out.println(e.getCidade());
				System.out.println(e.getEstado());
				System.out.println(e.getSigla());
				System.out.println(e.getCep());
				break;
			}
		}
		input.close();
		f.close();
	}
}
